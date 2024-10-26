Using `@ControllerAdvice` and `@ExceptionHandler` in Spring allows you to handle exceptions globally or at the controller level, providing a centralized way to manage error responses in your application. Here’s a detailed guide on how to use these annotations effectively.

### 1. `@ControllerAdvice`

`@ControllerAdvice` is a specialization of the `@Component` annotation that allows you to define global exception handling for all controllers or specific ones. It can also be used to apply model attributes or configure response body advice.

### 2. `@ExceptionHandler`

`@ExceptionHandler` is used to define methods that handle specific exceptions thrown by request-handling methods in your controllers. 

### Example: Using `@ControllerAdvice` and `@ExceptionHandler`

#### Step 1: Define Custom Exceptions

First, create custom exception classes to represent the specific errors your application might encounter.

```java
// ResourceNotFoundException.java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

// InvalidInputException.java
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
```

#### Step 2: Create a Global Exception Handler

Next, define a class annotated with `@ControllerAdvice` that will handle exceptions globally.

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

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
```

#### Step 3: Use the Exceptions in a Controller

Now, in your controller, you can throw these exceptions when necessary.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/resource/{id}")
    public String getResource(@PathVariable String id) {
        if (id.equals("0")) {
            throw new ResourceNotFoundException("Resource not found for id: " + id);
        }
        if (id.equals("-1")) {
            throw new InvalidInputException("Invalid input: " + id);
        }
        return "Resource " + id;
    }
}
```

### Summary

1. **Define Custom Exceptions**: Create exceptions that represent specific error conditions.
2. **Global Exception Handler**:
   - Use `@ControllerAdvice` to define a class for global exception handling.
   - Use `@ExceptionHandler` to create methods that respond to specific exceptions.
3. **Use Exceptions in Controllers**: Throw the defined exceptions in your controller methods to trigger the corresponding handler methods.

### Benefits of Using `@ControllerAdvice` and `@ExceptionHandler`

- **Centralized Error Handling**: Provides a single place to manage all exceptions, promoting cleaner code in your controllers.
- **Custom Error Responses**: Allows you to customize HTTP response codes and messages based on the exception type.
- **Improved Maintainability**: Makes your error-handling logic reusable and easier to maintain.

By implementing this pattern, you ensure that your Spring application can handle errors gracefully and return appropriate responses to clients.