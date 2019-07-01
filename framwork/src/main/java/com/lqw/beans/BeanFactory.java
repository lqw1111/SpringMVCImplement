package com.lqw.beans;

import com.lqw.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    private static Map<Class<?>, Object> classToBean = new ConcurrentHashMap<>();

    public static Object getBean(Class<?> cls) {
        return classToBean.get(cls);
    }

    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreated = new ArrayList<>(classList);
        while(toCreated.size() != 0) {
            int remainSize = toCreated.size();
            for (int i = 0 ; i < toCreated.size(); i++) {
                if(finishCreate(toCreated.get(i))){
                    toCreated.remove(i);
                }
            }

            if (toCreated.size() == remainSize) {
                throw new Exception("cycle dependency !");
            }
        }
    }

    private static boolean finishCreate(Class<?> cls) throws IllegalAccessException, InstantiationException {
        if (!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)) {
            return true;
        }

        Object bean = cls.newInstance();

        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoWired.class)) {
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType);
                if (reliantBean == null) {
                    return false;
                }
                field.setAccessible(true);
                field.set(bean, reliantBean);

            }
        }

        classToBean.put(cls, bean);
        return true;
    }
}
