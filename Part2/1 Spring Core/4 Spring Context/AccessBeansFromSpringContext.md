To access beans from the Spring context, you typically use the `ApplicationContext` or `BeanFactory`. Here's a quick guide on accessing beans:

### 1. Using `ApplicationContext` (Recommended)

The `ApplicationContext` is the main way to access beans, offering additional features like event handling and internationalization.

**Steps**:
1. Create an `ApplicationContext`.
2. Use the `getBean()` method to retrieve beans.

**Example**:
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load Spring configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        // Accessing a bean by class
        MyService myService = context.getBean(MyService.class);
        myService.performAction();

        // Accessing a bean by ID
        MyService myServiceById = (MyService) context.getBean("myServiceBean");
        myServiceById.performAction();
    }
}
```

### 2. Using `BeanFactory` (Basic)

If you prefer a more lightweight container, use `BeanFactory`. However, it lacks some features found in `ApplicationContext`.

**Example**:
```java
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {
        // Load Spring configuration
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        
        // Accessing a bean by ID
        MyService myService = (MyService) factory.getBean("myServiceBean");
        myService.performAction();
    }
}
```

### 3. Using `@Autowired` with `@Component`-scanned Classes

If you’re using annotations and component scanning, you can inject beans directly into other components without manually fetching from `ApplicationContext`.

**Example**:
```java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MainComponent {
    
    private final MyService myService;

    @Autowired
    public MainComponent(MyService myService) {
        this.myService = myService;
    }

    public void run() {
        myService.performAction();
    }
}
```

### Summary
- **`ApplicationContext`**: Recommended for accessing beans with more features.
- **`BeanFactory`**: Lightweight but limited.
- **`@Autowired`**: Automatically injects beans when using annotations and component scanning.