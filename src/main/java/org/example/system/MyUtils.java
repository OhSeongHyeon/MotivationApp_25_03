package org.example.system;

public class MyUtils {

    public static boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

}
