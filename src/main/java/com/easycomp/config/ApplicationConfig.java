package com.easycomp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.easycomp.interceptor.CustomInterceptor;

/**
 * @description: 拦截器配置
 * @author rensq
 * @create 2019-07-26 15:28
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    //swagger域名
    @Value("${springfox.documentation.swagger.v2.host}")
    private String swaggerHost;

    //对外域名和端口
    @Value("${domainurl}")
    private String domainurl;

    /**
     * 注册自定义的拦截器
     **/
/*    @Bean
    public HandlerInterceptor getCustomInterceptor() {
        return new CustomInterceptor();
    }*/

    /**
     *  配置静态资源
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/","classpath:/static/");
        /*放行swagger*/
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        logger.info("初始化系统配置");
        logger.info("swagger域名："+swaggerHost);
        logger.info("项目地址："+domainurl);
        super.addResourceHandlers(registry);
    }


    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        //解决跨域问题
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
    }
}