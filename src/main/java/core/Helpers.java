package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Helpers {

    public static Properties getProperties() {
        Properties profileProperties = new Properties();

        try {
            profileProperties.load(Helpers.class.getClassLoader().getResourceAsStream("config.properties"));

            Properties sysTemProperties = System.getProperties();
            for (Map.Entry entry : profileProperties.entrySet()) {
                if (sysTemProperties.containsKey(entry.getKey())) {
                    profileProperties.setProperty(String.valueOf(entry.getKey()), sysTemProperties.getProperty(String.valueOf(entry.getKey())));
                }
            }

        } catch (IOException e) {
            System.out.println("Error : config.properties is not exist");
            e.printStackTrace();
        }
        return profileProperties;
    }
}
