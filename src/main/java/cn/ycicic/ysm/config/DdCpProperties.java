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
@ConfigurationProperties(prefix = "dingding.cp")
public class DdCpProperties {

    private List<AppConfig> appConfigs;

    @Getter
    @Setter
    public static class AppConfig {
        private Integer agentId;
        private String appKey;
        private String secret;

    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
