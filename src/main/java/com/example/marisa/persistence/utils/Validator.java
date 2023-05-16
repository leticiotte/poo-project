package com.example.marisa.persistence.utils;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class Validator {

    public static <T> boolean validateFields(Object entity, ArrayList<String> params) {
        Class<?> clazz = entity.getClass();

        for (String param : params) {
            try {
                Field field = clazz.getDeclaredField(param);
                field.setAccessible(true);
                Object value = field.get(param);

                if (value == null) {
                    return false;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
}
