package com.ycicic.ysm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ycicic
 */
@Data
@TableName("wx_conf")
public class WxConf {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer channelId;

    private String corpId;

    private Long agentId;

    private String appKey;

    private String appSecret;

    private String token;

    private String remark;

}
