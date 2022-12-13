package com.ycicic.ysm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * ysm webhook通用消息体
 *
 * @author ycicic
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 5700535921042939964L;

    @JsonIgnore
    private Integer channelId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

}
