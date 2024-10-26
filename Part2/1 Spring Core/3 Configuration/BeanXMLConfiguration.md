### XML-Based Configuration in Spring

In Spring, XML-based configuration allows you to define beans and their dependencies in an XML file. This method is particularly useful for legacy applications or when you want to have a clear separation between configuration and code. 

#### The `<bean>` Tag

The `<bean>` tag is the core element in XML configuration for defining a Spring bean. Each `<bean>` tag represents a single bean in the Spring container.

**Basic Structure:**
```xml
<bean id="beanId" class="fully.qualified.ClassName">
    <!-- Property and Constructor injection -->
</bean>
```

### Attributes of the `<bean>` Tag

The `<bean>` tag comes with several attributes that control the behavior and properties of the bean. Here are some of the most commonly used attributes:

1. **`id`**: 
   - **Description**: The unique identifier for the bean. This is how you reference the bean in the application context.
   - **Example**: `<bean id="userService" class="com.example.UserService"/>`

2. **`class`**: 
   - **Description**: The fully qualified name of the class that the bean should instantiate. This attribute is mandatory.
   - **Example**: `<bean id="userRepository" class="com.example.UserRepository"/>`

3. **`scope`**: 
   - **Description**: Defines the scope of the bean. Common values include:
     - `singleton`: (default) One instance per Spring container.
     - `prototype`: A new instance each time the bean is requested.
     - `request`: One instance per HTTP request (web applications).
     - `session`: One instance per HTTP session (web applications).
   - **Example**: `<bean id="userService" class="com.example.UserService" scope="singleton"/>`

4. **`init-method`**: 
   - **Description**: Specifies a method to call on the bean after it has been initialized but before it is available for use. This is useful for custom initialization logic.
   - **Example**: `<bean id="userService" class="com.example.UserService" init-method="initMethod"/>`

5. **`destroy-method`**: 
   - **Description**: Specifies a method to call on the bean when it is being destroyed. This is useful for cleanup operations.
   - **Example**: `<bean id="userService" class="com.example.UserService" destroy-method="destroyMethod"/>`

6. **`constructor-arg`**: 
   - **Description**: Used to specify constructor arguments for the bean. You can define multiple constructor arguments using this tag.
   - **Example**:
   ```xml
   <bean id="userService" class="com.example.UserService">
       <constructor-arg value="Some value" />
       <constructor-arg ref="userRepository" />
   </bean>
   ```

7. **`property`**: 
   - **Description**: Used to inject values or references into the bean's properties (setter injection). 
   - **Example**:
   ```xml
   <bean id="userService" class="com.example.UserService">
       <property name="userRepository" ref="userRepository" />
       <property name="someProperty" value="Some value" />
   </bean>
   ```

### Example XML Configuration

Here’s a complete example of an XML configuration file defining a `UserService` bean with a dependency on a `UserRepository` bean:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="userRepository" class="com.example.UserRepository" />

    <bean id="userService" class="com.example.UserService" init-method="initMethod" destroy-method="destroyMethod">
        <property name="userRepository" ref="userRepository" />
    </bean>
</beans>
```

### Summary

XML-based configuration provides a clear and structured way to define beans in Spring applications. The `<bean>` tag and its attributes allow you to specify important characteristics such as the bean's scope, initialization and destruction methods, and dependency injection through constructor or property injection. While modern Spring applications often use Java-based configuration, XML configuration remains a powerful option for certain use cases, especially in legacy systems.