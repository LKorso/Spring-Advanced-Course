package web.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import beans.configuration.AppConfiguration;
import beans.configuration.AuditoriumConfiguration;
import beans.configuration.EventConfiguration;
import beans.configuration.StrategiesConfiguration;

@Order(2)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                AppConfiguration.class,
                AuditoriumConfiguration.class,
                EventConfiguration.class,
                StrategiesConfiguration.class,
                EventConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/mvc/*"};
    }

}
