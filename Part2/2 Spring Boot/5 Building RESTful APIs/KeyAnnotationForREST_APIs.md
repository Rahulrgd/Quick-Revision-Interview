### Using `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` for REST Endpoints

In Spring, the `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` annotations are specialized forms of the `@RequestMapping` annotation, designed to handle HTTP requests in a RESTful manner. These annotations make it easy to create RESTful web services by mapping HTTP requests to specific Java methods in a controller.

#### 1. `@GetMapping`

The `@GetMapping` annotation is used to handle HTTP GET requests. It is typically used for retrieving data from the server.

**Example:**

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Logic to fetch and return all users
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        // Logic to fetch and return user by ID
        return userService.getUserById(id);
    }
}
```

#### 2. `@PostMapping`

The `@PostMapping` annotation is used to handle HTTP POST requests. It is commonly used for creating new resources on the server.

**Example:**

```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Logic to create and return the new user
        return userService.createUser(user);
    }
}
```

#### 3. `@PutMapping`

The `@PutMapping` annotation is used to handle HTTP PUT requests. It is typically used for updating existing resources on the server.

**Example:**

```java
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Logic to update and return the updated user
        return userService.updateUser(id, user);
    }
}
```

#### 4. `@DeleteMapping`

The `@DeleteMapping` annotation is used to handle HTTP DELETE requests. It is used for deleting resources on the server.

**Example:**

```java
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Logic to delete the user by ID
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
```

### Summary

- **`@GetMapping`**: Used for retrieving data (GET requests).
- **`@PostMapping`**: Used for creating new resources (POST requests).
- **`@PutMapping`**: Used for updating existing resources (PUT requests).
- **`@DeleteMapping`**: Used for deleting resources (DELETE requests).

These annotations simplify the process of creating RESTful APIs in Spring by clearly defining the type of operation associated with each endpoint, enhancing the readability and maintainability of the code. Each method in the controller can handle different types of HTTP requests while adhering to REST principles.