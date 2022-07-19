package com.goods.partner.sqltracker;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class JpaTestConfiguration {
    @Bean
    public String testDataSource(DataSource dataSource) {
//        ChainListener listener = new ChainListener();
//        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
//        loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());
//        listener.addListener(loggingListener);
//        listener.addListener(new DataSourceQueryCountListener());
//        return ProxyDataSourceBuilder
//                .create(dataSource)
//                .name("test")
//                .listener(loggingListener)
//                .build();

        return "";
    }
}
