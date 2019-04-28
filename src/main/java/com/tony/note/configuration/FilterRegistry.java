package com.tony.note.configuration;

import com.tony.note.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jli2
 * @date 4/11/2019 1:55 PM
 **/
@Configuration
public class FilterRegistry {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        return new FilterRegistrationBean(new AuthFilter());
    }
}
