package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Helpers {

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Helpers.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Error : config.properties is not exist");
            e.printStackTrace();
        }
        return properties;
    }
}
