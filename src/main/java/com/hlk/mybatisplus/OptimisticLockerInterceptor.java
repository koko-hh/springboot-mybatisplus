package com.hlk.mybatisplus;

import org.springframework.context.annotation.Bean;

//乐观锁插件的实现
public class OptimisticLockerInterceptor {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
