package utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomUtils {

    public static String generateRandomEmail(){
        return new Faker().internet().safeEmailAddress();
    }

    public static Properties loadPropertiesFile(String path){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            Log.LOGGER.info(String.format("An error ocurred when the properties file was opened: %s",e.getCause()));
            Assertions.fail();
        }
        return prop;
    }
}
