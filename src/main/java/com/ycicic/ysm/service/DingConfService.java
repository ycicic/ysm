package com.ycicic.ysm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycicic.ysm.domain.DingConf;

/**
 * @author ycicic
 */
public interface DingConfService extends IService<DingConf> {
    DingConf getByChannelId(Integer channelId);
}
