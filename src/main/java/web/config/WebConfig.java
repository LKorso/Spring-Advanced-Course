package web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import web.converters.StringToLocalDateTimeConverter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter(DATE_PATTERN));
    }
}
