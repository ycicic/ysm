package com.ycicic.ysm.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ycicic
 */
@Service
public class SendFactory {

    private Map<String,SendService> sendIns;

    @Autowired
    public void setSendIns(Map<String, SendService> sendIns) {
        this.sendIns = sendIns;
    }

    public SendService getSendIns(String code) {
        SendService sendInstance = sendIns.get(code);
        if (sendInstance == null) {
            throw new RuntimeException("sendInstance未定义");
        }
        return sendInstance;
    }
}
