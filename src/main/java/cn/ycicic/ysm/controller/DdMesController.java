package cn.ycicic.ysm.controller;

import cn.ycicic.ysm.config.DdCpConfiguration;
import cn.ycicic.ysm.config.DdCpProperties;
import cn.ycicic.ysm.config.YsmConfiguration;
import cn.ycicic.ysm.dto.DdMes;
import cn.ycicic.ysm.dto.YsmMes;
import cn.ycicic.ysm.exception.YsmException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dingtalkrobot_1_0.Client;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTOHeaders;
import com.aliyun.dingtalkrobot_1_0.models.BatchSendOTORequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycc
 */
@Slf4j
@RestController
@RequestMapping("/dd/cp/message/{agentId}")
public class DdMesController {

    private static final Long H = 1000 * 60 * 60L;

    @SneakyThrows
    @PostMapping(value = "/send", produces = "application/json; charset=UTF-8")
    public void send(@PathVariable Integer agentId, @RequestBody YsmMes ysmMes) {
        log.info("接收到消息：{}", ysmMes);
        DdCpProperties.AppConfig config = DdCpConfiguration.getConfig(agentId);
        Client client = createClient();
        BatchSendOTOHeaders headers = new BatchSendOTOHeaders();
        headers.xAcsDingtalkAccessToken = DdCpConfiguration.getToken(agentId);
        Map<String, String> param = new HashMap<>(2);
        if (null == ysmMes.getTitle()) {
            param.put("title", "OpenWrt消息通知");
        } else {
            param.put("title", ysmMes.getTitle());
        }
        param.put("text", ysmMes.getText());

        List<String> toUsers = YsmConfiguration.getToUsers();
        BatchSendOTORequest request = new BatchSendOTORequest()
                .setRobotCode(config.getAppKey())
                .setUserIds(toUsers)
                .setMsgKey("sampleMarkdown")
                .setMsgParam(JSON.toJSONString(param));
        try {
            client.batchSendOTOWithOptions(request, headers, new RuntimeOptions());
            log.info("发送通知成功");
        } catch (Exception err) {
            throw new YsmException("发送通知异常",err);
        }
    }

    @SneakyThrows
    @PostMapping(value = "/receive", produces = "application/json; charset=UTF-8")
    public JSONObject receive(@PathVariable Integer agentId,
                              @RequestHeader("timestamp") Long timestamp,
                              @RequestHeader("sign") String sign,
                              @RequestBody DdMes mes) {
        if (!sign(agentId, timestamp, sign)) {
            throw new YsmException("认证失败");
        }
        return JSONObject.parseObject("{\n" +
                "    \"msgtype\": \"markdown\",\n" +
                "    \"markdown\": {\n" +
                "        \"title\": \"杭州天气\",\n" +
                "        \"text\": \"#### 杭州天气 @14783103233 \\n> 9度，西北风1级，空气良89，相对温度73%\\n> ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\\n> ###### 10点20分发布 [天气](https://www.dingalk.com) \\n\"\n" +
                "    },\n" +
                "    \"at\": {\n" +
                "        \"atMobiles\": [\n" +
                "            \"14783103233\"\n" +
                "        ],\n" +
                "        \"isAtAll\": false\n" +
                "    }\n" +
                "}");
    }

    private boolean sign(Integer agentId, Long timestamp, String sign) {
        try {
            if (System.currentTimeMillis() - timestamp > H) {
                throw new RuntimeException("非法请求：" + timestamp);
            }

            DdCpProperties.AppConfig config = DdCpConfiguration.getConfig(agentId);
            String secret = config.getSecret();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String mySign = new String(Base64.encodeBase64(signData));

            if (!mySign.equals(sign)) {
                throw new RuntimeException("非法请求：" + sign);
            }
            return true;
        } catch (Exception e) {
            log.error("认证失败: {}", e.getMessage(), e);
            return false;
        }
    }

    private Client createClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new Client(config);
    }

}
