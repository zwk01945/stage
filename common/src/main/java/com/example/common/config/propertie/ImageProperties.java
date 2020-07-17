package com.example.common.config.propertie;


import com.example.common.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: ImageProperties <br/>
 * Description: <br/>
 * date: 2020/7/15 15:27<br/>
 *
 * @author zwk<br />
 */
@Configuration
@ConfigurationProperties(prefix = "image")
@PropertySource(value = "classpath:sys.properties")
public class ImageProperties {

//    public static  String serverIp = Config.getInstance().getProperty("image.serverIp");
//    public static  String serverPort = Config.getInstance().getProperty("image.serverPort");
//    public static  String serverPath = Config.getInstance().getProperty("image.serverPath");

    public   String serverIp;
    public   String serverPort;
    public   String serverPath;

    public ImageProperties() {
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
}