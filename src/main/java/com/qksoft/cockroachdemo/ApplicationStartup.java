package com.qksoft.cockroachdemo;

import org.flywaydb.core.Flyway;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        FlywayProperties flywayProp = contextRefreshedEvent
                .getApplicationContext()
                .getBean(FlywayProperties.class);
        Flyway flyway = Flyway
                .configure()
                .baselineOnMigrate(true)
                .dataSource(flywayProp.getUrl(), flywayProp.getUsername(), flywayProp.getPassword())
                .load();
        flyway.migrate();
    }
}

class ApplicationStartup2 implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 初始化环境变量
        } else if (event instanceof ApplicationPreparedEvent) {
            // 初始化完成
        } else if (event instanceof ContextRefreshedEvent) {
            // 应用刷新
        } else if (event instanceof ApplicationReadyEvent) {
            // 应用已启动完成
        } else if (event instanceof ContextStartedEvent) {
            // 应用启动，需要在代码动态添加监听器才可捕获
        } else if (event instanceof ContextStoppedEvent) {
            // 应用停止
        } else if (event instanceof ContextClosedEvent) {
            // 应用关闭
        } else {
        }
    }
}