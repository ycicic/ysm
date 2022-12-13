package com.ycicic.ysm.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycicic.ysm.domain.DingConf;
import com.ycicic.ysm.mapper.DingConfMapper;
import com.ycicic.ysm.service.DingConfService;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Service
public class DingConfServiceImpl extends ServiceImpl<DingConfMapper, DingConf> implements DingConfService {
    @Override
    public DingConf getByChannelId(Integer channelId) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .eq(DingConf::getChannelId, channelId)
                .one();
    }
}
