Handling exceptions effectively in Spring applications is crucial for maintaining robustness and providing meaningful feedback to users. Spring offers various approaches for exception handling, allowing you to customize how exceptions are managed across your application.

### 1. Using `@ControllerAdvice`

`@ControllerAdvice` is a powerful way to handle exceptions globally across all controllers. You can define a class annotated with `@ControllerAdvice` and use methods annotated with `@ExceptionHandler` to manage specific exceptions.

**Example**:
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
```

### 2. Using `@ResponseStatus`

For specific exceptions, you can use the `@ResponseStatus` annotation to specify the HTTP status code returned when the exception is thrown.

**Example**:
```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### 3. Using `@ExceptionHandler` within Controllers

If you prefer to handle exceptions at the controller level rather than globally, you can use `@ExceptionHandler` directly within your controller classes.

**Example**:
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/resource/{id}")
    public Resource getResource(@PathVariable String id) {
        // Some logic that may throw ResourceNotFoundException
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

### 4. Using `ResponseEntityExceptionHandler`

You can extend `ResponseEntityExceptionHandler` to customize the default behavior of handling common Spring MVC exceptions.

**Example**:
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Custom response for validation errors
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + ex.getMessage());
    }
}
```

### 5. Logging Exceptions

Regardless of the approach, logging exceptions is essential for debugging and monitoring purposes. You can use a logging framework like SLF4J with Logback or Log4j.

**Example**:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("An error occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}
```

### Summary

1. **`@ControllerAdvice`**: Use for global exception handling across all controllers.
2. **`@ResponseStatus`**: Specify HTTP status codes for exceptions.
3. **`@ExceptionHandler`**: Handle exceptions at the controller level.
4. **Extend `ResponseEntityExceptionHandler`**: Customize handling for common exceptions.
5. **Logging**: Always log exceptions for better debugging and monitoring.

By using these strategies, you can create a robust error-handling mechanism in your Spring applications, ensuring that users receive meaningful feedback and developers have the necessary context to debug issues effectively.