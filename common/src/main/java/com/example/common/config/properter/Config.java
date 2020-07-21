package com.example.common.config.properter;


import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * ClassName: Config
 * Description: 读取系统配置文件配置类
 * date: 2020/7/15 14:52
 *
 * @author zwk
 */
public class Config {


    public static final String SYS_NAME_WIN = "win";
    public static final String SYS_NAME_LINUX = "linux";

    public static final String PROPERTY_FILE = "sys.properties";

    private static Config instance = null;

    private static Properties paraProps = new Properties();

    public Config() {

        InputStream is = getClass().getResourceAsStream("/" + PROPERTY_FILE);

        try {

            paraProps.load(is);

        } catch (Exception e) {

            System.err.println("Can not read system.properties file!");

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static synchronized Config getInstance() {

        if (instance == null) {
            instance = new Config();
        }
        return instance;

    }

    /**
     * 获取属性
     *
     * @param paraName
     * @return
     */
    public String getProperty(String paraName) {

        String property = "";
        try {
            Set<String> strings = paraProps.stringPropertyNames();
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
            property = paraProps.getProperty(paraName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

}
