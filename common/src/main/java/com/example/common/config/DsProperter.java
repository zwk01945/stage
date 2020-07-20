package com.example.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ClassName: DsProperter
 * Description: 数据源配置文件
 * date: 2020/7/20 16:53
 *
 * @author zwk
 */
public class DsProperter {

    public static final String PROPERTY_FILE = "application.properties";
    public static final String PROPERTY_FILE_YAML = "application.yml";

    private static DsProperter instance = null;

    private static Properties paraProps = new Properties();

    public DsProperter() {

        InputStream properties = getClass().getResourceAsStream("/" + PROPERTY_FILE);
        InputStream yaml = getClass().getResourceAsStream("/" + PROPERTY_FILE_YAML);
        try {
            if (properties != null) {
                paraProps.load(properties);
            } else {
                paraProps.load(yaml);
            }
        } catch (Exception e) {

            System.err.println("Can not read system.properties file!");

        } finally {
            try {
                properties.close();
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
    public static synchronized DsProperter getInstance() {

        if (instance == null) {
            instance = new DsProperter();
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
            property = paraProps.getProperty(paraName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }


    public Object getObject(String paraName) {

        Object property = null;
        try {
            property = paraProps.get(paraName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

}
