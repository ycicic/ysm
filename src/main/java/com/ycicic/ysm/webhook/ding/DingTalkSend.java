package com.ycicic.ysm.webhook.ding;

import com.alibaba.fastjson.JSON;
import com.ycicic.ysm.pojo.Message;
import com.ycicic.ysm.webhook.SendService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Slf4j
@Service("dingtalk")
public class DingTalkSend implements SendService {

    @SneakyThrows
    @Override
    public void exec(Message message) {
        log.info("钉钉执行: {}", JSON.toJSONString(message));
    }

}
