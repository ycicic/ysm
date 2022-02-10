package cn.ycicic.ysm.config;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ycc
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(YsmProperties.class)
@AutoConfigureAfter(DdCpConfiguration.class)
public class YsmConfiguration {

    private final YsmProperties ysmProperties;

    private static List<String> TO_USERS;

    @Autowired
    public YsmConfiguration(YsmProperties ysmProperties) {
        this.ysmProperties = ysmProperties;
    }

    @PostConstruct
    public void init() {
        TO_USERS = ysmProperties.getToUsers().stream().map(user -> {
            String token = DdCpConfiguration.getToken(user.getAgentId());
            String post = HttpUtil.post("https://oapi.dingtalk.com/topapi/v2/user/getbymobile?access_token=" + token, "{\"mobile\":\"" + user.getPhone() + "\"}");
            JSONObject object = JSONObject.parseObject(post);
            int errCode = object.getIntValue("errcode");
            if (0 == errCode) {
                return object.getJSONObject("result").getString("userid");
            } else {
                log.error("获取用户信息失败：{}", object.toJSONString());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static List<String> getToUsers() {
        return TO_USERS;
    }

}
