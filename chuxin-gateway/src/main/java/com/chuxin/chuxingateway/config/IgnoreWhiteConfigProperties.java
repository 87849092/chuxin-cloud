package com.chuxin.chuxingateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 初心
 * @create 2021-08-27 12:44
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
@Setter
@Getter
public class IgnoreWhiteConfigProperties {
    /**
     * 白名单配置，网关不校验此处的白名单
     */
    private Set<String> whites = new HashSet<>();
}
