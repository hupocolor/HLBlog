package com.hl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : hupo, 创建于:2023/3/12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns()
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedOriginPatterns("*")
//                .allowedHeaders("*")
                .maxAge(3600);
    }


}
