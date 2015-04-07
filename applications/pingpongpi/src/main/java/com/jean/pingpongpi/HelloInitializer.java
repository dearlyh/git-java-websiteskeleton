package com.jean.pingpongpi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class HelloInitializer implements WebApplicationInitializer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("starting.");
        AnnotationConfigWebApplicationContext springRootContext = new AnnotationConfigWebApplicationContext();
        springRootContext.setConfigLocations("com.jean");
        servletContext.addListener(new ContextLoaderListener(springRootContext));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(springRootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}