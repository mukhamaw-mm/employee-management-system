package com.mukham.employee.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isValidString(String value){
        if(value==null || value.trim().equals("")){
            return false;
        }else return true;
    }

    public static boolean isValidEmail(String value) {

        Pattern pattern = Pattern.compile(ConstantUtil.EMAIL_REGEX);

        Matcher matcher = pattern.matcher(value);
        boolean b = matcher.matches();
        return b;
    }
}
