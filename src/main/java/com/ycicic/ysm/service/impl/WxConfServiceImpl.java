package com.ycicic.ysm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycicic.ysm.domain.WxConf;
import com.ycicic.ysm.mapper.WxConfMapper;
import com.ycicic.ysm.service.WxConfService;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Service
public class WxConfServiceImpl extends ServiceImpl<WxConfMapper, WxConf> implements WxConfService {
}
