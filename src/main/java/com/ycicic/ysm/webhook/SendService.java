package com.ycicic.ysm.webhook;

import com.ycicic.ysm.pojo.Message;

/**
 * @author ycicic
 */
public interface SendService {

    void exec(Message message);

}
