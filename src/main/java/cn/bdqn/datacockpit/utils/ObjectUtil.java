/**
 * Project Name:DataCockpit
 * File Name:ObjectUtil.java
 * Package Name:cn.bdqn.datacockpit.utils
 * Date:2017年8月27日下午12:43:22
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.bdqn.datacockpit.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: <br/>
 * Date: 2017年8月27日 下午12:43:22 <br/>
 * 
 * @author yuanX
 * @version
 * @see
 */
public class ObjectUtil {
    /**
     * 返回一个对象的属性和属性值
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getProperty(Object entityName) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Class c = entityName.getClass();
            // 获得对象属性
            Field field[] = c.getDeclaredFields();
            for (Field f : field) {
                Object v = invokeMethod(entityName, f.getName(), null);
                map.put(f.getName(), v.toString());
            }
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    /**
     * 获得对象属性的值
     */
    @SuppressWarnings("unchecked")
    private static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        Method method = null;
        try {
            method = ownerClass.getMethod("get" + methodName);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
            return " can't find 'get" + methodName + "' method";
        }
        return method.invoke(owner);
    }
}
