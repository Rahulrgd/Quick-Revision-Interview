The `@Value` annotation in Spring injects property values directly into fields, methods, or constructor parameters. It’s used for injecting literals, system properties, or values from configuration files like `application.properties`.

### Basic Usage

1. **Injecting Simple Values**:
   ```java
   @Value("Hello, World!")
   private String greeting;
   ```

2. **Injecting from `application.properties`**:
   Assume we have a property defined in `application.properties`:
   ```properties
   app.name=My Application
   ```
   Inject it as:
   ```java
   @Value("${app.name}")
   private String appName;
   ```

3. **Injecting with Default Values**:
   If a property might be missing, a default value can be provided:
   ```java
   @Value("${app.version:1.0}")
   private String appVersion;  // Defaults to "1.0" if not specified
   ```

4. **Using Expressions**:
   The `@Value` annotation also supports SpEL (Spring Expression Language) for more complex expressions:
   ```java
   @Value("#{systemProperties['user.name']}")
   private String systemUserName;
   ```

### Summary
The `@Value` annotation is versatile, allowing injection from property files, system properties, or inline values, and supports default values for safer configuration management.