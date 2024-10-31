`WebClient` is the newer, non-blocking, and reactive alternative to `RestTemplate` in Spring. It’s built for asynchronous requests, which can improve performance in high-throughput applications.

### Setting Up and Using `WebClient`

### 1. Add Dependencies

If you’re using Spring Boot with **Spring WebFlux**, `WebClient` is included by default. You can add **Spring Boot Starter WebFlux** to your dependencies if it's not already present:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

### 2. Create a `WebClient` Bean

Define a `WebClient` bean to make it injectable. You can customize this bean with a base URL or other configurations as needed.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8081").build();
    }
}
```

### 3. Using `WebClient` for HTTP Requests

With `WebClient`, you can perform asynchronous calls and handle responses reactively using methods like `retrieve()` or `exchangeToMono()`.

#### Example: GET Request

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Product> getProductById(Long productId) {
        return webClient.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
```

- `.get()` – Specifies the HTTP method.
- `.uri()` – Specifies the endpoint.
- `.retrieve()` – Executes the request and retrieves the response.
- `.bodyToMono(Product.class)` – Converts the response to a `Mono<Product>`, representing an async single value.

#### Example: POST Request

```java
public Mono<Product> createProduct(Product product) {
    return webClient.post()
            .uri("/products")
            .bodyValue(product)
            .retrieve()
            .bodyToMono(Product.class);
}
```

#### Example: PUT Request

```java
public Mono<Void> updateProduct(Long productId, Product product) {
    return webClient.put()
            .uri("/products/{id}", productId)
            .bodyValue(product)
            .retrieve()
            .bodyToMono(Void.class);
}
```

#### Example: DELETE Request

```java
public Mono<Void> deleteProduct(Long productId) {
    return webClient.delete()
            .uri("/products/{id}", productId)
            .retrieve()
            .bodyToMono(Void.class);
}
```

### Handling Errors (Optional)

You can handle errors with `onStatus` to check for specific status codes and return error messages or fallbacks.

```java
public Mono<Product> getProductById(Long productId) {
    return webClient.get()
            .uri("/products/{id}", productId)
            .retrieve()
            .onStatus(
                HttpStatus::is4xxClientError, 
                clientResponse -> Mono.error(new RuntimeException("Client error"))
            )
            .onStatus(
                HttpStatus::is5xxServerError, 
                clientResponse -> Mono.error(new RuntimeException("Server error"))
            )
            .bodyToMono(Product.class);
}
```

### Summary

`WebClient` allows you to perform non-blocking HTTP calls, making it ideal for reactive and asynchronous applications. It’s recommended for new development, especially when performance and scalability are priorities.