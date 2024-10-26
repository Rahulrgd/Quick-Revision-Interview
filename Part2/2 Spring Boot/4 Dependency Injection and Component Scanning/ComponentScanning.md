### Component Scanning in Spring

**Component scanning** is a powerful feature in Spring that enables the framework to automatically detect and register beans in the Spring application context based on specific annotations. This reduces the need for explicit bean declarations in XML or Java configuration files and simplifies the configuration process.

#### How Component Scanning Works

1. **Annotation-Based Configuration**: Spring relies on specific annotations, such as `@Component`, `@Service`, `@Repository`, and `@Controller`, to identify classes that should be managed as beans.

2. **Base Package Specification**: When setting up component scanning, you specify the base package(s) to scan for annotated classes. Spring will then search through these packages and their sub-packages to find any classes with the specified annotations.

3. **Automatic Registration**: Once the classes are detected, Spring automatically creates bean instances for them and registers them in the application context, making them available for dependency injection.

#### Key Annotations for Component Scanning

1. **`@Component`**: A generic stereotype annotation indicating that a class is a Spring-managed component.

   ```java
   import org.springframework.stereotype.Component;

   @Component
   public class MyComponent {
       // Business logic
   }
   ```

2. **`@Service`**: A specialization of `@Component`, typically used to indicate that a class provides business services.

   ```java
   import org.springframework.stereotype.Service;

   @Service
   public class MyService {
       // Business logic
   }
   ```

3. **`@Repository`**: A specialization of `@Component`, used to indicate that a class is responsible for data access logic. It also provides additional features like exception translation.

   ```java
   import org.springframework.stereotype.Repository;

   @Repository
   public class MyRepository {
       // Data access logic
   }
   ```

4. **`@Controller`**: A specialization of `@Component`, typically used in the context of web applications to define a controller for handling requests.

   ```java
   import org.springframework.stereotype.Controller;

   @Controller
   public class MyController {
       // Request handling logic
   }
   ```

#### Configuring Component Scanning

You can configure component scanning in several ways:

1. **Using XML Configuration**:

   ```xml
   <context:component-scan base-package="com.example.package" />
   ```

   Make sure to include the context namespace in your XML configuration:

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                              http://www.springframework.org/schema/beans/spring-beans.xsd
                              http://www.springframework.org/schema/context
                              http://www.springframework.org/schema/context/spring-context.xsd">
   ```

2. **Using Java Configuration**:

   You can enable component scanning using the `@ComponentScan` annotation in a configuration class.

   ```java
   import org.springframework.context.annotation.ComponentScan;
   import org.springframework.context.annotation.Configuration;

   @Configuration
   @ComponentScan(basePackages = "com.example.package")
   public class AppConfig {
       // Other bean definitions
   }
   ```

3. **Using Spring Boot**:

   In a Spring Boot application, component scanning is enabled by default for the package of the main application class and its sub-packages. You can still use `@ComponentScan` to specify additional packages if needed.

   ```java
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyApplication.class, args);
       }
   }
   ```

#### How Spring Detects Annotated Classes

When the Spring application context is initialized, the following steps occur during component scanning:

1. **Scanning the Classpath**: Spring scans the specified base packages for classes that are annotated with the relevant annotations.

2. **Reflection**: Using Java Reflection, Spring examines the classes and identifies those with the specified annotations.

3. **Bean Creation**: For each detected class, Spring creates an instance (bean) and registers it in the application context.

4. **Dependency Injection**: After the beans are registered, Spring resolves their dependencies based on the configuration and injects them wherever needed.

### Example of Component Scanning in Action

Here’s a simple example of how component scanning works in a Spring application:

1. **Directory Structure**:

   ```
   src
   └── main
       └── java
           └── com
               └── example
                   ├── MyApplication.java
                   ├── service
                   │   └── MyService.java
                   └── controller
                       └── MyController.java
   ```

2. **Service Class**:

   ```java
   package com.example.service;

   import org.springframework.stereotype.Service;

   @Service
   public class MyService {
       public String getGreeting() {
           return "Hello, World!";
       }
   }
   ```

3. **Controller Class**:

   ```java
   package com.example.controller;

   import com.example.service.MyService;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Controller;

   @Controller
   public class MyController {
       private final MyService myService;

       @Autowired
       public MyController(MyService myService) {
           this.myService = myService;
       }

       public void handleRequest() {
           System.out.println(myService.getGreeting());
       }
   }
   ```

4. **Main Application Class**:

   ```java
   package com.example;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyApplication.class, args);
       }
   }
   ```

### Summary

Component scanning is a fundamental feature in Spring that automates the detection and registration of beans based on annotations. By using annotations like `@Component`, `@Service`, `@Repository`, and `@Controller`, developers can create Spring-managed beans without the need for extensive XML or Java configuration. Configuring component scanning is straightforward, whether using XML or Java configuration, and it significantly streamlines the setup process for Spring applications, enhancing modularity and maintainability.