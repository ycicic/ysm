package cn.ycicic.ysm.config;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author ycc
 */
@Configuration
@EnableConfigurationProperties(DdCpProperties.class)
public class DdCpConfiguration {

    private final DdCpProperties ddCpProperties;

    private static Map<Integer, DdCpProperties.AppConfig> CONFIGS;

    private static final Map<Integer, String> TOKENS = Maps.newHashMap();

    private static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1, (ThreadFactory) Thread::new);

    @Autowired
    public DdCpConfiguration(DdCpProperties ddCpProperties) {
        this.ddCpProperties = ddCpProperties;
    }

    @PostConstruct
    public void init() {
//        CONFIGS = this.ddCpProperties.getAppConfigs().stream().collect(Collectors.toMap(DdCpProperties.AppConfig::getAgentId, appConfig -> appConfig));
    }

    public static DdCpProperties.AppConfig getConfig(Integer agentId) {
        return CONFIGS.get(agentId);
    }

    @SneakyThrows
    public static String getToken(Integer agentId) {
        if (TOKENS.containsKey(agentId)) {
            return TOKENS.get(agentId);
        }
        Map<String, Object> map = new HashMap<>();
        DdCpProperties.AppConfig appConfig = CONFIGS.get(agentId);
        String appKey = appConfig.getAppKey();
        String secret = appConfig.getSecret();
        map.put("appkey", appKey);
        map.put("appsecret", secret);
        String s = HttpUtil.get("https://oapi.dingtalk.com/gettoken", map);
        JSONObject object = JSONObject.parseObject(s);
        int errCode = object.getIntValue("errcode");
        if (0 == errCode) {
            String accessToken = object.getString("access_token");
            TOKENS.put(agentId, accessToken);
            long expiresIn = object.getLong("expires_in");
            SCHEDULED_THREAD_POOL_EXECUTOR.schedule(() -> TOKENS.remove(agentId), expiresIn, TimeUnit.MILLISECONDS);
            return accessToken;
        } else {
            throw new Exception("获取TOKEN异常：" + object.toJSONString());
        }
    }

}
