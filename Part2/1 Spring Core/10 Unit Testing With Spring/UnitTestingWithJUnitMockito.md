Writing unit tests for Spring components using JUnit and Mockito is essential for ensuring the reliability and correctness of your application. This guide will walk you through the process of setting up and writing unit tests effectively.

### Setting Up the Environment

1. **Dependencies**: Make sure you have the following dependencies in your `pom.xml` (for Maven) or `build.gradle` (for Gradle):

   ```xml
   <!-- Maven -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
   </dependency>
   ```

   ```groovy
   // Gradle
   testImplementation 'org.springframework.boot:spring-boot-starter-test'
   ```

### Writing Unit Tests

#### Example Component

Let's say you have a simple service component that performs basic operations.

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
```

#### Step 1: Writing a Unit Test for the Service

You can write unit tests using JUnit and Mockito to verify the behavior of the `UserService` component.

```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_UserExists() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "John Doe");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(user, result);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(userId);
        });
        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findById(userId);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User(null, "John Doe");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertEquals(user, result);
        verify(userRepository).save(user);
    }
}
```

### Breakdown of the Test Code

1. **Annotations**:
   - `@Mock`: Creates a mock instance of `UserRepository`.
   - `@InjectMocks`: Injects the mock repository into the `UserService` instance.
   - `@BeforeEach`: Sets up the mocks before each test method is executed.

2. **Writing Tests**:
   - **Arrange**: Set up the necessary preconditions and inputs.
   - **Act**: Invoke the method being tested.
   - **Assert**: Verify the output and any interactions with the mock.

3. **Using Mockito**:
   - `when(...)`: Specifies the behavior of the mock.
   - `verify(...)`: Confirms that a method was called on the mock.

### Running the Tests

You can run the tests using your IDE's built-in test runner or through the command line using Maven or Gradle:

- **Maven**:
   ```bash
   mvn test
   ```

- **Gradle**:
   ```bash
   ./gradlew test
   ```

### Summary

1. **Dependencies**: Ensure you have the necessary testing libraries in your project.
2. **Mockito**: Use Mockito to create mock objects and verify interactions.
3. **JUnit**: Use JUnit to structure your tests with assertions to validate behavior.
4. **Test Lifecycle**: Use `@BeforeEach` to set up your mocks before each test.

By following these practices, you can write effective unit tests for your Spring components, ensuring that they work as intended and providing a safety net for future changes.