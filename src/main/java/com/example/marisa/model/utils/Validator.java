package com.example.marisa.model.utils;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class Validator {

    public static <T> boolean validateFields(Object entity, ArrayList<String> params) {
        Class<?> classType = entity.getClass();

        for (String param : params) {
            try {
                Field field = classType.getDeclaredField(param);
                field.setAccessible(true);
                Object value = field.get(entity);

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
