Spring Boot is built on top of the Spring Framework and enhances its capabilities with features like starters, auto-configuration, and various utilities that simplify the development of Spring applications. Here's a detailed breakdown of these concepts and how they relate to Spring Core.

### 1. Starters

**Starters** are a set of convenient dependency descriptors that you can include in your application. They provide a way to aggregate common dependencies, making it easier to manage your project’s dependencies.

#### Key Features of Starters:

- **Convenience**: Instead of specifying individual dependencies for a specific feature (like Spring MVC or Spring Data JPA), you can include a single starter dependency. This reduces the complexity of managing multiple dependencies.
  
- **Example Starters**:
  - `spring-boot-starter-web`: For building web applications, includes Spring MVC and an embedded web server (Tomcat by default).
  - `spring-boot-starter-data-jpa`: For JPA (Java Persistence API) and Hibernate, includes the necessary dependencies to work with databases.
  - `spring-boot-starter-security`: For adding security features to your application.
  - `spring-boot-starter-test`: For testing support, includes JUnit, Mockito, and Spring Test.

#### Example Usage:

To include a starter in your `pom.xml` (Maven):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### 2. Auto-Configuration

**Auto-configuration** is one of the core features of Spring Boot that automatically configures your application based on the libraries present on the classpath and the beans defined in your application. This allows developers to start building applications quickly without needing extensive configuration.

#### Key Features of Auto-Configuration:

- **Conditional Configuration**: Auto-configuration classes use `@Conditional` annotations to determine whether a specific configuration should be applied based on the presence of certain classes or beans. For example, if you include `spring-boot-starter-data-jpa`, Spring Boot automatically configures a `DataSource`, an `EntityManagerFactory`, and other necessary components for JPA.

- **Configuration Classes**: Auto-configuration is implemented through a series of `@Configuration` classes (found in the `spring-boot-autoconfigure` module) that define beans based on the conditions met.

- **Customization**: You can override auto-configured beans or customize their properties through the `application.properties` or `application.yml` files.

#### Example of Auto-Configuration:

Suppose you have a Spring Boot application with `spring-boot-starter-data-jpa` included. You don’t need to manually configure a `DataSource` or `EntityManager`. Spring Boot automatically sets these up based on the properties defined in your `application.properties` file.

**Sample `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### 3. Spring Boot Features Related to Spring Core

Spring Boot builds upon core Spring features and enhances them with additional capabilities. Here are some features closely related to Spring Core:

#### a. **Spring Context Support**

- **Component Scanning**: Spring Boot automatically scans for components, configurations, and services in the package where your main application class resides and its sub-packages.

- **Dependency Injection**: It leverages Spring's powerful dependency injection capabilities, allowing you to inject beans into other components seamlessly.

#### b. **Configuration Properties**

- **Externalized Configuration**: Spring Boot allows you to externalize configuration using properties files, YAML files, environment variables, and command-line arguments. This flexibility helps you adapt the application to different environments without changing the code.

- **@ConfigurationProperties**: This annotation allows you to bind properties to Java objects, making it easier to manage complex configurations.

#### c. **Profiles**

- **Profile Support**: Spring Boot supports different profiles (e.g., development, testing, production) to load specific configurations based on the active profile. You can define different configurations in `application-dev.properties`, `application-test.properties`, etc.

```properties
# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
```

#### d. **Spring Boot Actuator**

- **Monitoring and Management**: Actuator provides endpoints for monitoring your application’s health, metrics, and other operational information. It allows you to expose management features like health checks, environment details, and custom metrics.

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean isHealthy = checkServiceHealth();
        if (isHealthy) {
            return Health.up().build();
        }
        return Health.down().withDetail("Error", "Service is down").build();
    }

    private boolean checkServiceHealth() {
        // Logic to check health
        return true; // or false based on health check
    }
}
```

### Conclusion

Spring Boot significantly simplifies the development process by:

- **Starters**: Providing convenient dependency management with starters.
- **Auto-Configuration**: Automatically configuring beans based on classpath dependencies and conditions.
- **Spring Core Features**: Enhancing the existing Spring Core features with ease of use, externalized configurations, profile support, and production-ready features like Actuator.

With these capabilities, Spring Boot enables developers to build robust, scalable applications quickly and efficiently while leveraging the power of the Spring ecosystem.