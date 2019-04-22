package ru.churkin.config;

import com.sun.faces.config.FacesInitializer;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {DatabaseConfig.class
                            , SecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    // ---------- ТАК ТОЖЕ РАБОТАЕТ
    //public class SpringWebAppInitializer extends FacesInitializer implements WebApplicationInitializer {
/// add extends FacesInitializer when comment FacesServlet in web.xml
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
//        appContext.register(WebMvcConfig.class);
//        appContext.register(DatabaseConfig.class);
//        appContext.register(SecurityConfig.class);
//        appContext.setServletContext(servletContext);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
//        servletContext.addListener(new ContextLoaderListener(appContext));
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//    }

}
