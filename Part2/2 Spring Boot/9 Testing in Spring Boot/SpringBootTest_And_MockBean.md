### Using `@SpringBootTest` and `@MockBean` in Spring Boot

In Spring Boot, `@SpringBootTest` and `@MockBean` are powerful annotations that simplify integration testing by allowing you to load the entire application context while mocking specific beans. This enables you to isolate tests and control the behavior of certain dependencies, making it easier to test individual components without relying on their actual implementations.

#### 1. What are `@SpringBootTest` and `@MockBean`?

- **`@SpringBootTest`**: This annotation is used to create an application context for integration tests. It loads the full Spring application context and is commonly used to test controllers, services, or other beans that require the entire application context.

- **`@MockBean`**: This annotation is used to add mocks to the Spring application context. When you annotate a field with `@MockBean`, Spring will replace the existing bean of the same type with the mock, allowing you to define its behavior for the test.

### Example Usage

Let’s create a simple example to demonstrate how to use `@SpringBootTest` and `@MockBean` in a Spring Boot application.

#### Example Service and Controller

**User Service**:

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
```

**User Controller**:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

### Integration Test with `@SpringBootTest` and `@MockBean`

**Test Class**:

```java
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Mock the UserService bean

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1L, "john_doe", "password123");
        String userJson = "{\"username\":\"john_doe\", \"password\":\"password123\"}";

        // Define the behavior of the mocked UserService
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void testGetUserById_UserExists() throws Exception {
        User user = new User(1L, "john_doe", "password123");

        // Define the behavior of the mocked UserService
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void testGetUserById_UserDoesNotExist() throws Exception {
        // Define the behavior of the mocked UserService
        when(userService.getUserById(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }
}
```

### Explanation of the Test Class

1. **Annotations**:
   - `@SpringBootTest`: Loads the entire application context for the integration test.
   - `@AutoConfigureMockMvc`: Configures and enables `MockMvc` for testing web layers.
   - `@MockBean`: Creates a mock instance of the `UserService` bean that will replace the actual `UserService` in the application context.

2. **Test Methods**:
   - **`testCreateUser`**: Tests the creation of a user. The behavior of the mocked `userService` is defined to return a user object when the `createUser` method is called.
   - **`testGetUserById_UserExists`**: Tests the retrieval of a user when the user exists in the mock service.
   - **`testGetUserById_UserDoesNotExist`**: Tests the retrieval of a user when the user does not exist, expecting a 404 Not Found response.

3. **Performing Assertions**:
   - `andExpect(status().isCreated())`: Verifies that the response status is 201 Created.
   - `andExpect(jsonPath("$.username").value("john_doe"))`: Checks that the username returned in the response matches the expected value.
   - `andExpect(status().isNotFound())`: Checks that the response status is 404 Not Found.

#### 3. Running the Tests

You can run your integration tests using your IDE's built-in test runner or via the command line with Maven:

```bash
mvn test
```

### Conclusion

Using `@SpringBootTest` and `@MockBean`, you can easily create integration tests that load the entire application context while mocking specific beans. This approach allows you to isolate the component being tested and control the behavior of its dependencies, leading to more reliable and maintainable tests. Integrating these annotations into your testing strategy will help ensure your Spring Boot applications are robust and function correctly in various scenarios.