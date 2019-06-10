package com.mq.demo.config;


import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class FlowableConfig {
    @Autowired
    private DataSource dataSource;


    /**
     * 配置springboot 事务
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置uuid bean
     *
     * @return
     */
//    @Bean
//    public StrongUuidGenerator strongUuidGenerator() {
//        return new StrongUuidGenerator();
//    }
    @Bean
    public ThisUuidGenerator thisUuidGenerator() {
        return new ThisUuidGenerator();
    }

    /**
     * 配置bean
     *
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
        springProcessEngineConfiguration.setTransactionManager(dataSourceTransactionManager(dataSource));
        springProcessEngineConfiguration.setAsyncExecutorActivate(false);
        springProcessEngineConfiguration.setDatabaseType("mysql");
        springProcessEngineConfiguration.setIdGenerator(thisUuidGenerator());
        return springProcessEngineConfiguration;


    }

}

