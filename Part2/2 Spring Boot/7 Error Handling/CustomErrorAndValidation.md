### Custom Error Responses and Handling Validation Errors in Spring Boot

In a Spring Boot application, it's common to encounter validation errors when user input doesn't meet the required criteria. To provide a better user experience, you can create custom error responses and handle validation errors effectively. Below is a guide on how to achieve this using `@ControllerAdvice`, `@ExceptionHandler`, and the `BindingResult` interface for validation errors.

#### 1. Creating a Custom Error Response Class

First, define a custom error response class to standardize the format of your error messages. This class can include fields for status, message, and any additional details you wish to provide.

**Example:**

```java
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors;

    public ErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
```

#### 2. Create a Global Exception Handler

Next, create a global exception handler class annotated with `@ControllerAdvice`. This class will contain methods to handle both validation errors and other exceptions.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();
        
        // Collect all validation error messages
        bindingResult.getFieldErrors().forEach(error -> 
            errors.add(error.getField() + ": " + error.getDefaultMessage())
        );

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Other exception handling methods...
}
```

#### 3. Using Validation Annotations in Your Model

In your model class, use validation annotations to define the rules for user input. Common annotations include `@NotNull`, `@Size`, `@Email`, etc.

**Example:**

```java
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    // Getters and setters
}
```

#### 4. Controller to Handle Requests

In your controller, use `@Valid` to trigger validation and `@RequestBody` to bind the request body to your model class.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        // Business logic to save user
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
}
```

#### 5. Testing Validation Errors

When a request is made to create a user with invalid data, the `MethodArgumentNotValidException` will be thrown, and your global exception handler will return a structured error response.

**Example Request:**

```json
POST /api/users
{
    "username": "",
    "email": "invalid-email"
}
```

**Example Response:**

```json
{
    "status": 400,
    "message": "Validation failed",
    "errors": [
        "username: Username is required",
        "email: Email should be valid"
    ]
}
```

#### Summary

- **Custom Error Response Class**: Standardizes the error response format.
- **Global Exception Handler**: Uses `@ControllerAdvice` to handle validation and other exceptions globally.
- **Validation Annotations**: Use annotations like `@NotBlank` and `@Email` in model classes to enforce input rules.
- **MethodArgumentNotValidException**: Caught in the global exception handler to return a structured error response for validation failures.

This approach not only improves the user experience by providing clear error messages but also centralizes error handling in your Spring Boot application, making it more maintainable.