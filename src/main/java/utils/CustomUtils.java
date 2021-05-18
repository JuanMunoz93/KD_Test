package utils;

import com.github.javafaker.Faker;

public class CustomUtils {

    public static String generateRandomEmail(){
        return new Faker().internet().safeEmailAddress();
    }

}
