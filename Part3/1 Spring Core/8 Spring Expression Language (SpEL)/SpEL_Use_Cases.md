Spring Expression Language (SpEL) provides a powerful way to access properties, invoke methods, and perform operations within Spring applications. It can be used in various contexts, such as annotations, XML configuration, and programmatically.

### Basic Syntax

1. **Accessing Properties**:
   You can access properties of beans or system properties using SpEL.

   - **Accessing Bean Properties**:
     ```java
     @Value("#{myBean.propertyName}")
     private String myProperty;
     ```

   - **Accessing System Properties**:
     ```java
     @Value("#{systemProperties['user.home']}")
     private String userHome;
     ```

2. **Invoking Methods**:
   SpEL allows you to invoke methods on beans or static methods of classes.

   - **Invoking Bean Methods**:
     ```java
     @Value("#{myBean.methodName()}")
     private String result;
     ```

   - **Invoking Static Methods**:
     You can call static methods from classes directly:
     ```java
     @Value("#{T(java.lang.Math).random() * 100}")
     private double randomValue;  // Generates a random value between 0 and 100
     ```

3. **Using Operators**:
   SpEL supports various operators for arithmetic, logical, relational, and conditional expressions.

   - **Arithmetic Operations**:
     ```java
     @Value("#{2 + 3}")
     private int sum;  // 5
     ```

   - **Conditional Expressions**:
     ```java
     @Value("#{myBean.value > 10 ? 'High' : 'Low'}")
     private String valueDescription;  // 'High' or 'Low' based on the condition
     ```

4. **Working with Collections**:
   SpEL can also be used to manipulate and access elements in collections.

   - **Accessing List Elements**:
     ```java
     @Value("#{myList[0]}")  // Accesses the first element of myList
     private String firstElement;
     ```

   - **Filtering Collections**:
     ```java
     @Value("#{myList.?[name == 'John']}")  // Filters elements where name is 'John'
     private List<MyObject> filteredList;
     ```

### Example Usage in Spring

Here’s how you might typically use SpEL in a Spring application:

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Value("#{systemProperties['user.name']}")
    private String username;

    @Value("#{myService.getGreeting()}")
    private String greeting;

    @Value("#{2 * 5}")
    private int product;

    @Value("#{myList.?[age > 18]}")
    private List<Person> adults;

    // Getters and other methods
}
```

### Summary

- **Access Properties**: Use `#{bean.property}` to access properties.
- **Invoke Methods**: Use `#{bean.method()}` or `#{T(className).staticMethod()}` for method invocation.
- **Operators**: Perform arithmetic and logical operations directly in SpEL expressions.
- **Collections**: Access and manipulate lists and maps with ease.

SpEL enhances the flexibility and power of Spring configurations, allowing dynamic access to properties and methods as needed.