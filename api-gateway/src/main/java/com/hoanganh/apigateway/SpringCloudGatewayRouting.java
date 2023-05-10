package com.hoanganh.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/car/v1/**")
                        .uri("lb://car")
                )
//                .route(r -> r.path("/api/auth/v1/**")
//                        .uri("lb://user")
//                )
//                .route(r -> r.path("/api/batdongsan/v1/**")
//                        .uri("lb://batdongsan")
//                )
//                .route(r -> r.path("/api/news/**")
//                        .uri("lb://news-service")
//                )
//                .route(r -> r.path("/api/partner/**")
//                        .uri("lb://partner-service")
//                )
//                .route(r -> r.path("/api/feedback/**")
//                        .uri("lb://partner-service")
//                )
                .build();
    }
}
