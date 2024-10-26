### Global Exception Handling with `@ControllerAdvice` in Spring Boot

In a Spring Boot application, it's essential to handle exceptions gracefully to provide a better user experience and avoid exposing sensitive information. `@ControllerAdvice` is a powerful annotation that allows you to define a global exception handling mechanism. This enables you to centralize exception handling logic for all controllers, making your code cleaner and easier to maintain.

Here’s how to implement global exception handling using `@ControllerAdvice`:

#### 1. Create a Custom Exception Class

First, create a custom exception class that extends `RuntimeException`. This allows you to throw this exception from your application wherever needed.

**Example:**

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

#### 2. Create a Global Exception Handler Class

Next, create a class annotated with `@ControllerAdvice`. This class will contain methods to handle specific exceptions and provide a unified response format.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // You can handle more exceptions here...
}
```

#### 3. Create an Error Response Class

Define a class to structure the error response that you want to return to the client. This class can include details such as the status code and error message.

**Example:**

```java
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
```

#### 4. Throw the Custom Exception

Now, you can throw the custom exception in your controller or service when a specific condition occurs (e.g., when a resource is not found).

**Example Controller:**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id).orElseThrow(() -> 
            new ResourceNotFoundException("User not found with id: " + id)
        );
    }
}
```

#### 5. Handle Multiple Exceptions

You can add multiple methods in the `GlobalExceptionHandler` class to handle different exceptions. Each method can specify a different exception class to handle.

**Example:**

```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}
```

#### 6. Return Custom HTTP Status Codes

By specifying the appropriate `HttpStatus` in your response entity, you can control the HTTP status code returned to the client. This helps clients understand the nature of the error.

#### Summary

- **`@ControllerAdvice`**: Used to define a global exception handler for all controllers.
- **`@ExceptionHandler`**: Specifies the type of exception to handle.
- **Custom Exception Classes**: You can create specific exceptions to represent different error states.
- **Error Response Class**: A structured way to return error details to the client.
- **Flexible Handling**: You can add multiple methods to handle various exceptions.

This approach keeps your codebase clean and allows you to handle exceptions in a consistent manner across your entire application, improving maintainability and user experience.