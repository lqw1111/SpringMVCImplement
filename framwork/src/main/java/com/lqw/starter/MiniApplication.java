package com.lqw.starter;

import com.lqw.beans.BeanFactory;
import com.lqw.core.ClassScanner;
import com.lqw.web.handler.HandlerManager;
import com.lqw.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.util.List;

public class MiniApplication {
//    传参数第一个为应用的入口类,由入口类进入要进入的类
//    第二个传参数是应用入口类中所需要的参数信息
    public static void run(Class<?> cls, String[] args){
        System.out.println("Hello Mini Spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.start();
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());

            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
