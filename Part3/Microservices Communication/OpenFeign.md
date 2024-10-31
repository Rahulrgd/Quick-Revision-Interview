Implementing OpenFeign in a Spring Boot application is simple. Here’s a step-by-step guide to get started:

### 1. Set Up the Project

1. Go to [Spring Initializr](https://start.spring.io/).
2. Add **OpenFeign**, **Spring Web**, and optionally **Spring Boot DevTools** as dependencies.
3. Generate and download the project.

### 2. Add OpenFeign Dependency

If it's not already present, add the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 3. Enable Feign in Your Application

In your main Spring Boot application class, add the `@EnableFeignClients` annotation to scan for Feign clients.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 4. Define a Feign Client Interface

Create an interface for the service you want to communicate with. Annotate it with `@FeignClient` and specify the service name or URL.

```java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8081")  // URL is optional if using service discovery
public interface InventoryClient {

    @GetMapping("/inventory/{id}")
    Inventory getInventoryById(@PathVariable("id") Long id);
}
```

Here:
- `name` is the name of the service (useful when using service discovery like Eureka).
- `url` is the endpoint base URL of the service (optional if using a service registry).
- `@GetMapping` defines the API endpoint in the target service.

### 5. Use the Feign Client in Your Service

Now you can inject and use the `InventoryClient` in your service classes.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final InventoryClient inventoryClient;

    @Autowired
    public ProductService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public Inventory getInventory(Long inventoryId) {
        return inventoryClient.getInventoryById(inventoryId);
    }
}
```

### 6. Configure Properties (Optional)

To enable logging for Feign requests, add this to `application.properties`:

```properties
feign.client.config.default.loggerLevel=full
```

This will enable detailed logs of the Feign client requests and responses, which is helpful for debugging.

### 7. Run the Application

Start the application and make a request to the service. The `ProductService` will use the `InventoryClient` to communicate with the `inventory-service`.

This setup provides an easy, declarative way to call other services with OpenFeign, simplifying RESTful inter-service communication.