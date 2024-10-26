### Understanding Bean Scopes in Spring

In the Spring framework, the scope of a bean defines the lifecycle and visibility of that bean in the application. Spring provides several built-in scopes that determine how and when beans are created and managed. The most common scopes are **Singleton**, **Prototype**, **Request**, **Session**, and **Global Session**.

---

### 1. Singleton

**Definition:**  
The Singleton scope is the default scope in Spring. It ensures that a single instance of a bean is created for the entire Spring IoC container.

**Key Characteristics:**
- Only one instance of the bean is created per Spring container.
- The same instance is shared across all requests and throughout the application.

**Example:**
```java
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean instance created.");
    }
}
```

**Usage:**
```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
SingletonBean bean1 = context.getBean(SingletonBean.class);
SingletonBean bean2 = context.getBean(SingletonBean.class);
// Outputs the same instance
System.out.println(bean1 == bean2); // true
```

---

### 2. Prototype

**Definition:**  
The Prototype scope creates a new instance of a bean each time it is requested from the Spring container.

**Key Characteristics:**
- A new instance is created every time a request for the bean is made.
- The container does not manage the lifecycle of the prototype bean after it is created.

**Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean instance created.");
    }
}
```

**Usage:**
```java
PrototypeBean bean1 = context.getBean(PrototypeBean.class);
PrototypeBean bean2 = context.getBean(PrototypeBean.class);
// Outputs different instances
System.out.println(bean1 == bean2); // false
```

---

### 3. Request

**Definition:**  
The Request scope is specific to web applications. A new instance of the bean is created for each HTTP request.

**Key Characteristics:**
- Each HTTP request gets a new instance of the bean.
- The bean is available only during the lifecycle of the request.

**Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class RequestScopedBean {
    public RequestScopedBean() {
        System.out.println("RequestScopedBean instance created.");
    }
}
```

**Usage:**
In a web application context, a new instance is created for each request, and it is not accessible outside of that request.

---

### 4. Session

**Definition:**  
The Session scope is also specific to web applications. A new instance of the bean is created for each HTTP session.

**Key Characteristics:**
- A new instance is created for each user session.
- The bean remains available throughout the session.

**Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionScopedBean {
    public SessionScopedBean() {
        System.out.println("SessionScopedBean instance created.");
    }
}
```

**Usage:**
This bean is available to a user for the duration of their session and is not shared between sessions.

---

### 5. Global Session

**Definition:**  
The Global Session scope is similar to the Session scope but is used in portlet-based web applications. A new instance of the bean is created for each global session.

**Key Characteristics:**
- Each global session gets its own instance of the bean.
- The bean is accessible across multiple portlets during the global session.

**Example:**
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("globalSession")
public class GlobalSessionScopedBean {
    public GlobalSessionScopedBean() {
        System.out.println("GlobalSessionScopedBean instance created.");
    }
}
```

**Usage:**
This scope is rarely used in standard web applications and is specifically for use with portlet-based applications.

---

### Summary of Bean Scopes

| Scope          | Description                                               | Lifecycle Management                |
|----------------|-----------------------------------------------------------|-------------------------------------|
| **Singleton**  | One instance per Spring container.                        | Managed by Spring container.        |
| **Prototype**  | New instance every time the bean is requested.           | Not managed by Spring after creation.|
| **Request**    | New instance for each HTTP request.                       | Managed during the request lifecycle.|
| **Session**    | New instance for each HTTP session.                       | Managed during the session lifecycle.|
| **Global Session** | New instance for each global session (portlet context). | Managed during the global session lifecycle.|

By understanding these bean scopes, you can effectively manage the lifecycle and visibility of your Spring beans based on the requirements of your application.