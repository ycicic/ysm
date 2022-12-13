package com.ycicic.ysm.webhook.bark.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ycicic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarkParam {

    @JSONField(name = "title")
    private String title;

    @JSONField(name = "body")
    private String body;

    @JSONField(name = "device_key")
    private String deviceKey;

    @JSONField(name = "level")
    private String level;

    @JSONField(name = "icon")
    private String icon;

    @JSONField(name = "group")
    private String group;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "isArchive")
    private Integer isArchive;

}
