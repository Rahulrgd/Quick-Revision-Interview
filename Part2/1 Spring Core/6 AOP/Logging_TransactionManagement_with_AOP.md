Spring AOP (Aspect-Oriented Programming) allows you to manage cross-cutting concerns like logging and transaction management by separating them from the core business logic. Here’s how it can be used to address these concerns:

### Example 1: Logging with Spring AOP

**Goal**: Log details before and after a method executes.

1. **Define the Logging Aspect**: This aspect will intercept method executions for logging.
   ```java
   import org.aspectj.lang.annotation.Aspect;
   import org.aspectj.lang.annotation.Before;
   import org.aspectj.lang.annotation.After;
   import org.springframework.stereotype.Component;

   @Aspect
   @Component
   public class LoggingAspect {

       // Pointcut to target all methods in MyService class
       @Before("execution(* com.example.MyService.*(..))")
       public void logBeforeMethod() {
           System.out.println("Executing before method...");
       }

       @After("execution(* com.example.MyService.*(..))")
       public void logAfterMethod() {
           System.out.println("Executing after method...");
       }
   }
   ```

2. **Enable AspectJ in Spring Configuration**:
   In your configuration class, add `@EnableAspectJAutoProxy` to enable AOP proxy support.
   ```java
   @Configuration
   @EnableAspectJAutoProxy
   public class AppConfig {
       // Bean definitions if needed
   }
   ```

### Example 2: Transaction Management with Spring AOP

**Goal**: Apply transactional behavior to methods.

1. **Define the Transaction Aspect**:
   Use `@Transactional` on methods or classes to automatically start and commit transactions, rolling back if an exception occurs.
   ```java
   import org.springframework.transaction.annotation.Transactional;
   import org.springframework.stereotype.Service;

   @Service
   public class MyService {

       @Transactional  // Starts a transaction for this method
       public void performTransaction() {
           // Business logic that needs transactional behavior
       }
   }
   ```

2. **Configure Transaction Management**:
   In your configuration class, enable transaction management with `@EnableTransactionManagement`.
   ```java
   @Configuration
   @EnableTransactionManagement
   public class AppConfig {
       // DataSource and transaction manager beans configuration
   }
   ```

### Summary:
- **Logging**: Use Spring AOP `@Before` and `@After` advice to log method execution details.
- **Transaction Management**: Use `@Transactional` to handle transactions declaratively.

With Spring AOP, these cross-cutting concerns are modularized, making the code cleaner and easier to maintain.