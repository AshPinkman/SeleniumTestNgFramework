package utils;

import enums.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;

    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.QA));
        switch(EnvType.valueOf(env)){
            case QA:
                properties = PropertyUtils.propertyLoader("src/test/resources/qa_config.properties");
                break;
            case DEV:
                properties = PropertyUtils.propertyLoader("src/test/resources/dev_config.properties");
                break;
            case STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            default:
                throw new IllegalArgumentException("Invalid env type");
        }

    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the config.properties file");
    }

    public String getUsername() {
        String prop = properties.getProperty("username");
        if (prop != null) return prop;
        else throw new RuntimeException("property username is not specified in the config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the config.properties file");
    }

    public Long getTimeout() {
        String prop = properties.getProperty("timeout");
        if (prop != null) return Long.valueOf(prop);
        else throw new RuntimeException("property timeout is not specified in the config.properties file");
    }

}
