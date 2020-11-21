package com.magic.liuzm.utils;

import java.lang.reflect.Field;

/**
 * @author zemin.liu
 * @date 2020/6/11 09:29
 * @description 反射工具类
 */
public class ReflectUtil {

    /**
     * @author zemin.liu
     * @description 用反射通过属性名获得属性值
     * @date 2020/6/11 09:26
     * @param obj
     * @param propertyName
     * @return java.lang.Object
     */
    public static Object getPropertyValue(Object obj, String propertyName){
        try {
            // clsss对象
            Class<?> Clazz = obj.getClass();
            // 用反射通过属性名获得属性对象
            Field field = getField(Clazz, propertyName);
            if (field== null){
                return null;
            }
            // 获得属性值
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * @author zemin.liu
     * @description 用反射通过属性名获得属性对象
     * @date 2020/6/11 09:20
     * @param clazz
     * @param propertyName
     * @return java.lang.reflect.Field
     */
    private static Field getField(Class<?> clazz, String propertyName) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            // 重试父类
            return getField(clazz.getSuperclass(), propertyName);
        }
    }
}
