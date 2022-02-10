package cn.ycicic.ysm.config;

import cn.ycicic.ysm.util.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ycc
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "ysm")
public class YsmProperties {

    private List<User> toUsers;

    @Getter
    @Setter
    public static class User {
        private Integer agentId;
        private String phone;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
