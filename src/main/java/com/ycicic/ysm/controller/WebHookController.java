package com.ycicic.ysm.controller;

import com.ycicic.ysm.pojo.Message;
import com.ycicic.ysm.webhook.SendFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ycicic
 */
@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebHookController {

    private SendFactory sendFactory;

    @Autowired
    public void setSendFactory(SendFactory sendFactory) {
        this.sendFactory = sendFactory;
    }

    @PostMapping("/{type}")
    public void send(@PathVariable String type, @RequestAttribute("channelId") Integer channelId, @RequestBody Message message) {
        message.setChannelId(channelId);
        sendFactory.getSendIns(type).exec(message);
    }

}
