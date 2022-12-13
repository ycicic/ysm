
package com.ycicic.ysm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ycicic
 */
@Data
@TableName("bark_conf")
public class BarkConf {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer channelId;

    private String serverUrl;

    private String appKey;

    @TableField("`group`")
    private String group;

    private String icon;

    @TableField("`level`")
    private String level;

    private String url;

    private Integer isArchive;

    private String remark;

}
