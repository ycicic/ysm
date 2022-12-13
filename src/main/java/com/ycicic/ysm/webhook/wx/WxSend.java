package com.ycicic.ysm.webhook.wx;

import com.ycicic.ysm.pojo.Message;
import com.ycicic.ysm.webhook.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Slf4j
@Service("wx")
public class WxSend implements SendService {

    @Override
    public void exec(Message message) {
        log.debug("微信执行{}", message);
    }

}
