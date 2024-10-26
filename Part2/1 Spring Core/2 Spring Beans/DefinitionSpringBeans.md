### Definition of Spring Beans and Their Role in the Spring Framework

**Spring Beans:**  
In the Spring framework, a **Spring Bean** is an object that is instantiated, assembled, and managed by the Spring IoC (Inversion of Control) container. A Spring Bean is typically a service or a component that performs a specific function in an application. These beans are defined in the Spring configuration file or annotated with specific Spring annotations.

**Key Characteristics of Spring Beans:**
- **Lifecycle Management:** The Spring container manages the entire lifecycle of a bean, from instantiation to destruction. It handles the initialization and destruction processes through configuration settings and callback methods.
- **Dependency Injection:** Beans can have their dependencies injected by the Spring container, promoting loose coupling and easier testing.
- **Scope:** Beans can have different scopes, determining their lifecycle and visibility. Common scopes include:
  - **Singleton:** A single instance of the bean is created and shared across the entire Spring context.
  - **Prototype:** A new instance is created each time the bean is requested.
  - **Request:** A new instance is created for each HTTP request (used in web applications).
  - **Session:** A new instance is created for each HTTP session (used in web applications).
  - **Global Session:** Similar to session scope but shared across multiple sessions.

### Role of Spring Beans in the Spring Framework

1. **Central to Application Configuration:** Spring Beans are the backbone of a Spring application, as they encapsulate the business logic and data access. They are defined in the configuration files (XML or Java-based) and can be easily managed.

2. **Dependency Management:** By allowing the Spring container to manage dependencies, beans can be loosely coupled, making the application more modular and easier to maintain. For example, if a bean relies on another bean, Spring can automatically inject it without the need for explicit instantiation.

3. **Lifecycle Control:** Spring manages the lifecycle of beans, allowing developers to define custom initialization and destruction logic. This control ensures that beans are ready to use when needed and are cleaned up properly when no longer in use.

4. **Integration with AOP:** Spring Beans can easily be integrated with Aspect-Oriented Programming (AOP) features, enabling cross-cutting concerns such as logging, security, and transaction management to be applied to the beans.

### Example Code

#### Defining a Spring Bean using Annotations

```java
import org.springframework.stereotype.Component;

@Component
public class UserService {
    public void createUser() {
        System.out.println("User created.");
    }
}
```

In this example, `UserService` is a Spring Bean defined using the `@Component` annotation. This tells the Spring container to manage this class as a bean.

#### Accessing a Spring Bean

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
    }
}
```

Here, the `Main` class retrieves the `UserService` bean from the Spring application context and invokes the `createUser` method.

### Summary

In summary, Spring Beans are essential components in the Spring framework that encapsulate the application's functionality and provide a structured way to manage object creation and dependencies. They enhance modularity, testability, and lifecycle management, making Spring a powerful framework for building enterprise applications.