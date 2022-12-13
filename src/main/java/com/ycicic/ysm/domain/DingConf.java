package com.ycicic.ysm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ycicic
 */
@Data
@TableName("ding_conf")
public class DingConf {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer channelId;

    private Long agentId;

    private String appKey;

    private String appSecret;

    private String remark;

}
