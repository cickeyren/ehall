package com.easycomp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlus配置
 * @author rensq
 * @create 2019-07-17
 **/
@EnableTransactionManagement
//事务注解
@Configuration
public class MyBatisPlusConfig {
    /**
     * 配置mybatis-plus 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}