package com.ycicic.ysm.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycicic.ysm.domain.Channel;
import com.ycicic.ysm.mapper.ChannelMapper;
import com.ycicic.ysm.service.ChannelService;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {
    @Override
    public Channel getByChannel(String channel) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(Channel::getChannel,channel)
                .one();
    }
}
