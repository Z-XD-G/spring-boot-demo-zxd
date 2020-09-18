package com.example.demo.annotation;

import java.lang.reflect.Field;

public class AnnotationValid {

    public String validate(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    NotNull notNull = field.getAnnotation(NotNull.class);
                    field.setAccessible(true);
                    if (field.get(object) == null) {
                        return notNull.message();
                    }
                }
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
                    field.setAccessible(true);
                    if ("".equals(field.get(object))) {
                        return notEmpty.messsage();
                    }
                }
            }
        }catch (IllegalAccessException e){
            e.getStackTrace();
        }
        return "0";
    }

}
