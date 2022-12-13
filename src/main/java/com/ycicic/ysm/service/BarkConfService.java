package com.ycicic.ysm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycicic.ysm.domain.BarkConf;

/**
 * @author ycicic
 */
public interface BarkConfService extends IService<BarkConf> {
    BarkConf getByChannelId(Integer channelId);
}
