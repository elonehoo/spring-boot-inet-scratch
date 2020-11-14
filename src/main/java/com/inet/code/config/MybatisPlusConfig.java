package com.inet.code.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlus的配置类
 * @author HCY
 * @since 2020-11-14
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件的配置
     * @author HCY
     * @since 2020-9-21
     * @return PaginationInterceptor 分页插件的设置
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setCountSqlParser((ISqlParser)new JsqlParserCountOptimize(true));
        page.setDialectType("mysql");
        return page;
    }
}
