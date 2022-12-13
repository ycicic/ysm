package com.ycicic.ysm.handler;

import com.ycicic.ysm.domain.Channel;
import com.ycicic.ysm.service.ChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Agent验证拦截器
 *
 * @author ycicic
 */
@Component
public class ChannelCheckHandler implements HandlerInterceptor {

    private ChannelService channelService;

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String channelStr = request.getParameter("channel");
        if (StringUtils.isEmpty(channelStr)) {
            throw new IllegalAccessException("can not find channel parameter.");
        }
        Channel channel = channelService.getByChannel(channelStr);
        if (Objects.isNull(channel)) {
            throw new IllegalAccessException("can not find channel parameter.");
        }

        request.setAttribute("channelId", channel.getId());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
