package com.ycicic.ysm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycicic.ysm.domain.Channel;

/**
 * @author ycicic
 */
public interface ChannelService extends IService<Channel> {

    Channel getByChannel(String channel);

}
