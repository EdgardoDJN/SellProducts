package com.example.SellProducts;

//@TestConfiguration(proxyBeanMethods = false)

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainersConfig {
    //@Bean
    //@ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine");
    }
}
