package pers.zcy.myblogboot.utils;

import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BeanFieldJudgeUtils {
    public static String[] getNullPropertyNames(Object wait2Judge) {
        Field[] declaredFields = wait2Judge.getClass().getDeclaredFields();
        List<String> nullFieldNames = Arrays.asList(declaredFields).stream()
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        if (null == field.get(wait2Judge)) {
                            return field.getName();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(s -> null == s?false:true)
                .collect(Collectors.toList());
        return nullFieldNames.toArray(new String[0]);
    }
}
