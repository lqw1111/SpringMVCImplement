package com.lqw.web.handler;

import com.lqw.web.mvc.Controller;
import com.lqw.web.mvc.RequestMapping;
import com.lqw.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static void resolveMappingHandler (List<Class<?>> classList){
        for (Class<?> cls : classList) {
            if (cls.isAnnotationPresent(Controller.class)) {
                parseHandlerFromController(cls);
            }
        }
    }

    private static void parseHandlerFromController (Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();

        for (Method m : methods) {

            if (!m.isAnnotationPresent(RequestMapping.class)) {
                continue;
            }
            System.out.println(m.getName());
            String uri = m.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();

            for (Parameter parameter : m.getParameters()) {
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }

            String[] params = paramNameList.toArray(new String[paramNameList.size()]);

            MappingHandler mappingHandler = new MappingHandler(uri, m, cls, params);

            mappingHandlerList.add(mappingHandler);
            System.out.println(mappingHandlerList.size());
        }
    }
}
