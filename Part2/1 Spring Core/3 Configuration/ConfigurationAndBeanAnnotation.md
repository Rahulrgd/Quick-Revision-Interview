### Using `@Configuration` and `@Bean` for Java-based Configuration in Spring

In Spring, Java-based configuration is an alternative to XML configuration, allowing you to define beans using Java classes. The `@Configuration` annotation indicates that a class can be used by the Spring IoC container as a source of bean definitions, while the `@Bean` annotation is used to declare individual beans.

### 1. `@Configuration`

**Definition:**  
The `@Configuration` annotation is used to indicate that a class contains one or more `@Bean` methods. This class will be processed by the Spring container to generate bean definitions and service requests for those beans.

**Key Characteristics:**
- A class annotated with `@Configuration` can contain multiple methods annotated with `@Bean`.
- The methods defined in a `@Configuration` class can return instances of the beans that will be managed by the Spring container.

### 2. `@Bean`

**Definition:**  
The `@Bean` annotation is used to declare a single bean. It can be placed on methods within a `@Configuration` class, indicating that the returned object from the method should be registered as a bean in the Spring application context.

**Key Characteristics:**
- The method annotated with `@Bean` can have parameters, which will be automatically resolved by Spring's dependency injection.

### Example of Using `@Configuration` and `@Bean`

Here's an example that demonstrates how to use `@Configuration` and `@Bean` for Java-based configuration.

#### Step 1: Define Your Beans

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository()); // Injecting UserRepository
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(); // Creating a UserRepository instance
    }
}
```

#### Step 2: Define Your Service and Repository Classes

```java
public class UserService {
    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser() {
        System.out.println("User created.");
    }
}

public class UserRepository {
    public void saveUser() {
        System.out.println("User saved to the database.");
    }
}
```

#### Step 3: Using the Application Context

Now, you can retrieve and use your beans from the Spring application context.

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Create the application context using AppConfig
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // Retrieve UserService bean
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
        
        // Retrieve UserRepository bean
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.saveUser();
    }
}
```

### Summary

Using `@Configuration` and `@Bean` annotations in Spring allows you to define and manage your beans in a more type-safe and maintainable way compared to XML configuration. This approach provides several benefits, including:
- **Clear Structure**: Configuration is clearly structured in Java classes.
- **Type Safety**: Compile-time checks ensure that bean dependencies are correctly defined.
- **Flexibility**: You can use standard Java constructs, such as conditionals and loops, to define your beans.

Overall, Java-based configuration is a preferred method for many developers when configuring Spring applications due to its readability and maintainability.