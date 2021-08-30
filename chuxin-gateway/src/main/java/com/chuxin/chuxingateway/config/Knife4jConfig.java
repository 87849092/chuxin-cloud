package com.chuxin.chuxingateway.config;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 初心
 * @create 2021-08-27 11:13
 */
@Component
public class Knife4jConfig implements SwaggerResourcesProvider {

    private static final String KNIFE4J_URL = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;


    public Knife4jConfig(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    /**
     * 聚合其他服务的接口
     * @return java.util.List
     */
    @Override
    public List<SwaggerResource> get() {
        // 接口资源列表
        List<SwaggerResource> resources = new ArrayList<>();
        // 服务名称列表
        List<String> routes = new ArrayList<>();
        // 获取所有可用的应用名称
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        // 过滤出配置文件中自定义的路由 -> 过滤出Path Route Predicate 根据路径拼接api-docs路径 生成资源
        gatewayProperties.getRoutes().stream()
                .filter(Objects::nonNull)
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(route -> route.getPredicates().stream()
                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources
                                .add(swaggerResource(route.getId(), predicateDefinition.getArgs()
                                        .get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", KNIFE4J_URL)))));

        return resources;
    }
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }

}
