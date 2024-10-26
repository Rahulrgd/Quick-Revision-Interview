### Response Handling and Status Codes in Spring

In a Spring application, handling responses and appropriately managing HTTP status codes is crucial for building a RESTful API. This ensures that clients receive the correct information regarding the success or failure of their requests. Here’s how to handle responses and status codes in Spring.

#### 1. Sending Responses

You can send responses back to the client in various formats, including JSON, XML, or plain text. The response can be customized using `ResponseEntity`, which allows you to set the response body, headers, and status code.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 Created
    }
}
```

#### 2. Common HTTP Status Codes

Here are some common HTTP status codes that you might use in your Spring applications:

- **200 OK**: The request was successful.
- **201 Created**: A new resource was successfully created (often used in POST requests).
- **204 No Content**: The request was successful, but there is no content to send in the response (often used in DELETE requests).
- **400 Bad Request**: The server cannot process the request due to client error (e.g., malformed request).
- **401 Unauthorized**: Authentication is required and has failed or has not yet been provided.
- **403 Forbidden**: The server understood the request but refuses to authorize it.
- **404 Not Found**: The requested resource was not found on the server.
- **500 Internal Server Error**: The server encountered an unexpected condition that prevented it from fulfilling the request.

#### 3. Using `@ResponseStatus`

You can also use the `@ResponseStatus` annotation to specify the HTTP status code that should be returned with a response.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
```

#### 4. Handling Errors

For better error handling, you can create a global exception handler using `@ControllerAdvice`. This allows you to manage exceptions and customize the responses sent to the client.

**Example:**

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
    }
}
```

### Summary

- **ResponseEntity**: Use it to customize responses with a body, headers, and status code.
- **Common Status Codes**: Familiarize yourself with HTTP status codes and their meanings for effective response handling.
- **@ResponseStatus**: Use this annotation to specify the status code for specific methods.
- **Global Exception Handling**: Implement a global exception handler to manage errors gracefully and return appropriate responses.

Proper response handling and status code management are essential for creating robust, user-friendly APIs in Spring applications.