Implementing an API Gateway in Spring Boot with **Spring Cloud Gateway** is straightforward. Here’s a basic guide to get you started:

### 1. Set Up Your Spring Boot Project

1. Go to [Spring Initializr](https://start.spring.io/).
2. Add **Spring Cloud Gateway** and **Spring Boot DevTools** dependencies.
3. Generate and download the project.

### 2. Add Dependencies

In your `pom.xml`, make sure you have the dependencies for Spring Cloud Gateway and optionally a service registry like Eureka if you plan to use service discovery.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId> <!-- Optional for Service Discovery -->
</dependency>
```

### 3. Configure Your Application Properties

Configure routes in your `application.properties` or `application.yml`. Here’s an example:

```yaml
server:
  port: 8080  # Port for API Gateway to run on

spring:
  cloud:
    gateway:
      routes:
        - id: inventory-service
          uri: lb://inventory-service  # 'lb://' enables load-balancing with a service registry
          predicates:
            - Path=/inventory/**
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/orders/**
```

This configuration routes:
- All `/inventory/**` requests to `inventory-service`.
- All `/orders/**` requests to `order-service`.

If `inventory-service` and `order-service` are registered in Eureka, Spring Cloud Gateway will balance requests across their instances.

### 4. (Optional) Enable Service Discovery (Eureka Client)

In `application.properties`, enable Eureka client if you're using it for service discovery:

```properties
spring.application.name=api-gateway
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### 5. Create Custom Filters (Optional)

You can add filters for custom logic (like modifying headers, logging, or rate limiting) as follows:

```java
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("inventory_route", r -> r.path("/inventory/**")
                .filters(f -> f.addRequestHeader("X-Request-Source", "API-Gateway"))
                .uri("lb://inventory-service"))
            .build();
    }
}
```

### 6. Run the Gateway

Start the API Gateway and other services. The Gateway will now handle routing and load balancing based on the routes you set up. Requests to `http://localhost:8080/inventory` will be forwarded to the `inventory-service`. 

With this setup, your API Gateway acts as a central entry point for routing and load balancing across services.