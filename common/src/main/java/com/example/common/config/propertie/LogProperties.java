package com.example.common.config.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: LogProperties
 * Description: 自定义日志配置
 * date: 2020/7/16 10:43
 * @author zwk
 */
@Configuration
@ConfigurationProperties(prefix = "log")
@PropertySource(value = "classpath:sys.properties")
public class LogProperties {

//    public static final String winPath = Config.getInstance().getProperty("log.winPath");
//    public static final String linuxPath = Config.getInstance().getProperty("log.linuxPath");

    public   String winPath;
    public   String linuxPath;

    public LogProperties() {
    }

    public String getWinPath() {
        return winPath;
    }

    public void setWinPath(String winPath) {
        this.winPath = winPath;
    }

    public String getLinuxPath() {
        return linuxPath;
    }

    public void setLinuxPath(String linuxPath) {
        this.linuxPath = linuxPath;
    }
}
