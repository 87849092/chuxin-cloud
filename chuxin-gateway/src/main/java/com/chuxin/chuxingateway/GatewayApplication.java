package com.chuxin.chuxingateway;

import com.chuxin.common.knife4j.annotation.EnableCustomKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 初心
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCustomKnife4j
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
