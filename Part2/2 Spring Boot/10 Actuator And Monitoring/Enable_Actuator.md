### Enabling Spring Boot Actuator for Monitoring

Spring Boot Actuator provides a set of production-ready features to help you monitor and manage your application. It exposes various endpoints that can provide insights into the application’s health, metrics, environment properties, and more. Here’s how to enable and configure Spring Boot Actuator in your application.

#### 1. Add Dependencies

To use Spring Boot Actuator, you need to include the `spring-boot-starter-actuator` dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 2. Configure Application Properties

You can customize the actuator's endpoints and their exposure using the `application.properties` or `application.yml` file.

**Example `application.properties`**:

```properties
# Enable specific actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics

# Configure the health indicator to show detailed information
management.health.diskspace.enabled=true
management.health.diskspace.threshold=10MB

# Configure the server port for actuator endpoints
management.server.port=8081
```

**Example `application.yml`**:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health, info, metrics
  health:
    diskspace:
      enabled: true
      threshold: 10MB
  server:
    port: 8081
```

### Key Properties

- **`management.endpoints.web.exposure.include`**: Specifies which actuator endpoints should be exposed. You can use `*` to expose all endpoints, or list specific ones (e.g., `health`, `info`, `metrics`).

- **`management.health.diskspace.enabled`**: Enables the disk space health indicator.

- **`management.health.diskspace.threshold`**: Sets the threshold for the disk space health indicator (e.g., alert if less than 10MB of free disk space).

- **`management.server.port`**: Configures a separate port for the actuator endpoints, allowing you to secure or isolate them from the main application port.

#### 3. Accessing Actuator Endpoints

Once you have added the dependency and configured the properties, you can access the actuator endpoints:

1. **Health Endpoint**: Access `/actuator/health` to check the health status of your application.
2. **Info Endpoint**: Access `/actuator/info` to get application information.
3. **Metrics Endpoint**: Access `/actuator/metrics` to view various metrics related to your application.

**Example URLs**:
- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`
- Metrics: `http://localhost:8080/actuator/metrics`

#### 4. Securing Actuator Endpoints

If you want to secure your actuator endpoints, you can use Spring Security. Here’s a basic example of how to secure the actuator endpoints.

**Add Spring Security Dependency**:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**Configure Security in `application.properties`**:

```properties
spring.security.user.name=admin
spring.security.user.password=admin123
```

With this configuration, when you access any actuator endpoint, you will be prompted for the username and password. You can change the credentials as per your requirement.

#### 5. Monitoring with Actuator

Once enabled, Spring Boot Actuator provides various monitoring capabilities, including:

- **Health Checks**: Monitor the health status of the application.
- **Metrics**: Gather metrics related to the application’s performance (e.g., memory usage, number of requests).
- **Environment Information**: Access properties from the application’s environment.
- **Application Versioning**: Retrieve the version of the application from the info endpoint.

### Conclusion

Spring Boot Actuator is a powerful tool for monitoring and managing your Spring Boot application. By enabling it and configuring the necessary properties, you gain access to valuable insights about your application’s health and performance. Remember to secure your actuator endpoints, especially in production environments, to protect sensitive information. With these features, you can ensure your application runs smoothly and efficiently while having the tools needed for monitoring and troubleshooting.