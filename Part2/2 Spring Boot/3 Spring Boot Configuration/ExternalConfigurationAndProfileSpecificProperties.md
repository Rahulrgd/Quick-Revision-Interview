### Externalized Configuration in Spring Boot

**Externalized configuration** in Spring Boot allows you to define application settings outside of your codebase, making it easier to manage different environments (e.g., development, testing, production) without modifying your application code. This approach promotes better separation of concerns and helps maintain the same codebase across different configurations.

#### Key Concepts of Externalized Configuration

1. **Property Sources**: Spring Boot can read configuration properties from various sources, including:
   - `application.properties` or `application.yml` files
   - Environment variables
   - Command-line arguments
   - Java system properties
   - Configuration files in external locations

2. **Priority Order**: Spring Boot follows a specific order to resolve properties:
   - Command-line arguments
   - Environment variables
   - `application-{profile}.properties` or `application-{profile}.yml`
   - `application.properties` or `application.yml`
   - Default properties (defined in code)

3. **Flexible Configuration**: You can define properties that vary between environments (e.g., database URLs, server ports) without changing your application code.

#### Example of Externalized Configuration

Consider a simple example where you want to externalize the database connection details. In your `application.properties`, you might have:

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
```

You can override these properties for different environments using profile-specific configuration files, like `application-dev.properties` and `application-prod.properties`.

### Profile-Specific Properties

**Profile-specific properties** allow you to define different configurations for various environments, enabling your application to adapt its settings based on the active profile.

#### Setting Up Profiles

1. **Create Profile-Specific Files**:
   - `application-dev.properties`
   - `application-prod.properties`

**Example**: In `application-dev.properties`:

```properties
# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/devdb
spring.datasource.username=devuser
spring.datasource.password=devsecret
```

In `application-prod.properties`:

```properties
# application-prod.properties
spring.datasource.url=jdbc:mysql://localhost:3306/proddb
spring.datasource.username=produser
spring.datasource.password=prodsecret
```

2. **Activating a Profile**: You can activate a profile in several ways:
   - **Command Line**: Pass the `--spring.profiles.active` parameter when running your application.

   ```bash
   ./mvnw spring-boot:run --spring.profiles.active=dev
   ```

   - **Environment Variable**: Set the environment variable `SPRING_PROFILES_ACTIVE` to the desired profile.

   ```bash
   export SPRING_PROFILES_ACTIVE=prod
   ```

   - **In the `application.properties` file**:

   ```properties
   spring.profiles.active=dev
   ```

#### Accessing Profile-Specific Properties

Once a profile is active, Spring Boot will automatically load the corresponding properties file and override the default configurations. You can access these properties in your application as you would normally.

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void printConfig() {
        System.out.println("Database URL: " + url);
        System.out.println("Username: " + username);
        // Note: Avoid printing passwords in real applications
    }
}
```

### Summary

**Externalized configuration** in Spring Boot allows you to manage application settings outside your codebase, making it easier to maintain different configurations for various environments. By using **profile-specific properties**, you can customize settings for development, testing, and production without modifying your application's source code. This flexibility is a key benefit of using Spring Boot for building robust and adaptable applications.