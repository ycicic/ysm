package com.ycicic.ysm.webhook.bark;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.ycicic.ysm.domain.BarkConf;
import com.ycicic.ysm.pojo.Message;
import com.ycicic.ysm.service.BarkConfService;
import com.ycicic.ysm.webhook.SendService;
import com.ycicic.ysm.webhook.bark.dto.BarkParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ycicic
 */
@Slf4j
@Service("bark")
public class BarkSend implements SendService {

    private BarkConfService barkConfService;

    @Autowired
    public void setBarkConfService(BarkConfService barkConfService) {
        this.barkConfService = barkConfService;
    }

    @Override
    public void exec(Message message) {
        log.info("Bark开始执行: {}", JSON.toJSONString(message));

        Integer channelId = message.getChannelId();

        BarkConf barkConf = barkConfService.getByChannelId(channelId);

        BarkParam barkParam = BarkParam.builder()
                .deviceKey(barkConf.getAppKey())
                .title(message.getTitle())
                .body(message.getContent())
                .level(barkConf.getLevel())
                .group(barkConf.getGroup())
                .url(barkConf.getUrl())
                .icon(barkConf.getIcon())
                .isArchive(barkConf.getIsArchive())
                .build();

        String result = HttpUtil.post(barkConf.getServerUrl(), JSON.toJSONString(barkParam));
        log.info("Bark执行完成: {}", result);
    }


}
