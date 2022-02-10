package cn.ycicic.ysm.dto;

import cn.ycicic.ysm.enums.ParseMode;
import cn.ycicic.ysm.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ycc
 */
@Data
public class YsmMes implements Serializable {

    private static final long serialVersionUID = -5400507457069574317L;

    @JsonProperty("parse_mode")
    private ParseMode parseMode;

    private String text;
    private String title;

    @JsonProperty("chat_id")
    private String chatId;


    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
