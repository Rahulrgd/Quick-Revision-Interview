### Configuring Beans Using Annotations in Spring

In Spring, annotations provide a powerful and convenient way to configure beans. The most common annotations used to define Spring beans are `@Component`, `@Service`, `@Repository`, and `@Controller`. Each of these annotations serves a specific purpose and is part of the Spring framework's stereotype annotations.

#### 1. `@Component`

**Definition:**  
`@Component` is a generic stereotype annotation used to indicate that a class is a Spring-managed component. It can be used for any Spring-managed bean.

**Example:**
```java
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

#### 2. `@Service`

**Definition:**  
`@Service` is a specialization of the `@Component` annotation. It is used to annotate service layer classes, which contain business logic. Using this annotation provides better clarity and semantics for developers.

**Example:**
```java
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void createUser() {
        System.out.println("User created.");
    }
}
```

#### 3. `@Repository`

**Definition:**  
`@Repository` is another specialization of the `@Component` annotation. It is used to annotate DAO (Data Access Object) classes. This annotation enables exception translation, converting database-related exceptions into Spring's DataAccessException hierarchy.

**Example:**
```java
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void saveUser() {
        System.out.println("User saved to the database.");
    }
}
```

#### 4. `@Controller`

**Definition:**  
`@Controller` is a specialization of the `@Component` annotation that is used to define Spring MVC controllers. It is responsible for handling user requests and returning appropriate responses.

**Example:**
```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/users")
    @ResponseBody
    public String getUsers() {
        return "List of users";
    }
}
```

### Enabling Component Scanning

To enable component scanning, which allows Spring to automatically detect and register these annotated classes as beans, you need to configure your application context. This can be done using XML configuration or Java-based configuration.

#### Java-based Configuration Example

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example") // Specify the package to scan for components
public class AppConfig {
}
```

### Using Beans in Your Application

Once you have defined your beans using annotations and enabled component scanning, you can retrieve and use them in your application.

#### Example of Using Beans:

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
        
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.saveUser();
        
        UserController userController = context.getBean(UserController.class);
        String users = userController.getUsers();
        System.out.println(users);
    }
}
```

### Summary

Using annotations like `@Component`, `@Service`, `@Repository`, and `@Controller` makes it easy to configure and manage beans in Spring applications. Each annotation serves a specific purpose, enhancing the clarity and maintainability of the code. By enabling component scanning, you can automate the process of detecting and registering these beans in the Spring context, streamlining the overall development process.