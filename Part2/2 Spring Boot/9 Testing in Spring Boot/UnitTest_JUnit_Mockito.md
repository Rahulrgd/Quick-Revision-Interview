### Testing in Spring Boot: Writing Unit Tests with JUnit and Mockito

Testing is a crucial aspect of software development, and Spring Boot provides robust support for unit testing applications. JUnit is a popular framework for writing tests in Java, while Mockito is used for mocking dependencies. Here's how to write unit tests for your Spring Boot applications using these tools.

#### 1. Add Dependencies

First, ensure that you have the necessary dependencies for JUnit and Mockito in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

This starter includes JUnit, Mockito, and other testing utilities, making it easy to get started with testing.

#### 2. Writing Unit Tests with JUnit and Mockito

Let's create a simple example to demonstrate how to write unit tests for a service class in a Spring Boot application.

**Example Service Class**:

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

**Example User Repository**:

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```

#### 3. Writing Unit Tests

Now, let’s write unit tests for the `UserService` class.

**Test Class**:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_UserExists() {
        User user = new User(1L, "john_doe", "password123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals("john_doe", foundUser.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_UserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User foundUser = userService.getUserById(1L);

        assertNull(foundUser);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "john_doe", "password123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals("john_doe", createdUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
```

### Explanation of the Test Class

1. **Annotations**:
   - `@InjectMocks`: Creates an instance of the class and injects the mocked dependencies into it.
   - `@Mock`: Creates a mock instance of the specified class.
   - `@BeforeEach`: A JUnit annotation that runs before each test case. Here, it initializes the mocks.

2. **Test Methods**:
   - **`testGetUserById_UserExists`**: Tests that when a user exists, the service correctly retrieves it. It uses `when` to define the behavior of the mocked `userRepository`.
   - **`testGetUserById_UserDoesNotExist`**: Tests that if the user does not exist, the service returns `null`.
   - **`testCreateUser`**: Tests that the `createUser` method correctly saves a user and returns the saved user.

3. **Assertions**:
   - `assertEquals`: Checks that the expected value matches the actual value.
   - `assertNull`: Checks that the returned value is `null`.

4. **Verification**:
   - `verify`: Checks that a particular method was called a specific number of times on the mock.

#### 4. Running the Tests

You can run the tests using your IDE's built-in test runner or through the command line using Maven:

```bash
mvn test
```

### Conclusion

By leveraging JUnit and Mockito, you can effectively write unit tests for your Spring Boot applications. This ensures your service logic is functioning correctly and helps maintain the quality of your code as your application grows. Unit tests provide a safety net for future changes, making refactoring easier and more reliable.