package com.lqw.web.servlet;

import com.lqw.web.handler.HandlerManager;
import com.lqw.web.handler.MappingHandler;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DispatcherServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        System.out.println(HandlerManager.mappingHandlerList.size());
        for (MappingHandler mappingHandler: HandlerManager.mappingHandlerList) {
            try {
                if (mappingHandler.handle(req, res)) {
                    System.out.println("handler");
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
