package com.ycicic.ysm.config;

import com.ycicic.ysm.handler.ChannelCheckHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ycicic
 */
@Configuration
public class CheckHandlerConfigurer implements WebMvcConfigurer {

    private ChannelCheckHandler channelCheckHandler;

    @Autowired
    public void setChannelCheckHandler(ChannelCheckHandler channelCheckHandler) {
        this.channelCheckHandler = channelCheckHandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(channelCheckHandler).addPathPatterns("/webhook/**");
    }
}
