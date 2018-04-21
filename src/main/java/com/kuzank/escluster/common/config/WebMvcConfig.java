package com.kuzank.escluster.common.config;

import com.kuzank.escluster.common.interceptor.AuthInterceptor;
import com.kuzank.escluster.common.interceptor.GlobalInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author kuzan
 * @since 2018/01/28
 */
@Configuration
@ControllerAdvice
//@EnableWebMvc   // 加上这行会关闭spring boot默认的mvc配置 see boot chapter 26.1.1
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private GlobalInterceptor globalInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns(new String[]{"/static/**", "/error/**", "/login"});
        registry.addInterceptor(globalInterceptor).addPathPatterns("/**")
                .excludePathPatterns(new String[]{"/static/**", "/error/**"});
        super.addInterceptors(registry);
    }


}
