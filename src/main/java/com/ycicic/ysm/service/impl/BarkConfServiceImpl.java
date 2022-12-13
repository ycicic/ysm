package com.ycicic.ysm.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycicic.ysm.domain.BarkConf;
import com.ycicic.ysm.mapper.BarkConfMapper;
import com.ycicic.ysm.service.BarkConfService;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Service
public class BarkConfServiceImpl extends ServiceImpl<BarkConfMapper, BarkConf> implements BarkConfService {
    @Override
    public BarkConf getByChannelId(Integer channelId) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(BarkConf::getChannelId,channelId)
                .one();
    }
}
