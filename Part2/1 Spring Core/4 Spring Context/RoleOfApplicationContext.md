### Role of ApplicationContext in Spring

The `ApplicationContext` is a central interface in the Spring Framework that represents the Spring IoC (Inversion of Control) container. It is responsible for managing the complete lifecycle of beans and providing various services, including:

- **Dependency Injection**: Automatically wiring beans together based on configuration.
- **Lifecycle Management**: Managing the creation, initialization, and destruction of beans.
- **Event Propagation**: Publishing events to registered listeners.
- **Internationalization**: Supporting message resource bundles for localization.
- **AOP (Aspect-Oriented Programming)**: Providing support for aspects and proxies.

### Key Implementations of ApplicationContext

There are several implementations of the `ApplicationContext` interface, each suited for different use cases. Here are some of the most commonly used implementations:

#### 1. `ClassPathXmlApplicationContext`

**Description**:  
This implementation loads the application context definition from an XML file located in the classpath. It is typically used when you want to configure your beans in XML.

**Usage Example**:
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load the context from an XML file in the classpath
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
    }
}
```

#### 2. `FileSystemXmlApplicationContext`

**Description**:  
This implementation loads the application context definition from an XML file located in the file system. It is used when the configuration file is not on the classpath but in the filesystem.

**Usage Example**:
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load the context from an XML file in the file system
        ApplicationContext context = new FileSystemXmlApplicationContext("file:/path/to/applicationContext.xml");
        
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
    }
}
```

#### 3. `AnnotationConfigApplicationContext`

**Description**:  
This implementation is used to load an application context from Java-based configuration using the `@Configuration` annotated classes. It is the preferred choice for modern Spring applications that leverage annotation-based configuration.

**Usage Example**:
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load the context from Java configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        UserService userService = context.getBean(UserService.class);
        userService.createUser();
    }
}
```

#### 4. `WebApplicationContext`

**Description**:  
This is a specialized extension of the `ApplicationContext` interface for web applications. It provides features such as access to the servlet context and support for loading beans from a web application context configuration file. 

- It is commonly used in Spring MVC applications and is typically configured in `web.xml` or through Java configuration.
- **Common Implementations**:
  - `XmlWebApplicationContext`
  - `AnnotationConfigWebApplicationContext`

**Usage Example** (in a web application):
```java
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

// In web.xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

### Summary

The `ApplicationContext` is a core part of the Spring Framework that provides the necessary features for managing the lifecycle of beans and facilitating dependency injection. The different implementations of `ApplicationContext`—such as `ClassPathXmlApplicationContext`, `FileSystemXmlApplicationContext`, `AnnotationConfigApplicationContext`, and `WebApplicationContext`—allow developers to choose the best approach based on the application's requirements, whether they prefer XML or Java-based configuration, or are building a web application.