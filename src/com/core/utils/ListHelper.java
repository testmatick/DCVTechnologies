package com.core.utils;

import java.lang.reflect.Field;
import java.util.List;

public class ListHelper {

    /**
     * Method search for element in list by one field
     * Warning! Reflection used! Don't change field's names :)
     *
     * @param list      - Where to search
     * @param fieldName - field to check
     * @param value     - fields value which checks
     * @param <T>
     * @return First element with this parameters
     */
    public static <T> T findElement(List<T> list, String fieldName, Object value) {
        try {
            for (T temp : list) {
                if (fieldName == null)
                    return null;
                Field field = temp.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object o = field.get(temp);
                if (o == null || value == null)
                    return null;
                if (!o.getClass().getName().equals(value.getClass().getName()))
                    return null;
                Class klass = value.getClass();
                if (klass.cast(o).equals(klass.cast(value)))
                    return temp;
            }
        } catch (NoSuchFieldException n) {
            System.out.println(String.format("Field \"%s\" not found!", fieldName));
        } catch (IllegalAccessException iac) {
            System.out.println("Caught illegal access exception!");
        }
        return null;
    }
}
