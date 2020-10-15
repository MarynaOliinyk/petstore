package io.swagger.petstore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class PropertiesCache {

    private static final PropertiesCache INSTANCE = new PropertiesCache();
    private final Properties configProp = new Properties();

    private PropertiesCache() {
        String load_property = System.getProperty("LOAD_PROPERTY", "test.properties");
        InputStream in = this.getClass()
                .getClassLoader()
                .getResourceAsStream(load_property);
        InputStreamReader inputStream = new InputStreamReader(in, StandardCharsets.UTF_8);
        try {
            configProp.load(inputStream);
        } catch (IOException e) {
        }
    }

    public static String getProperty(String key) {
        return INSTANCE.configProp.getProperty(key);
    }

    public static String getEnviroment(String loadProperties) {
        return System.getenv(loadProperties);
    }
}
