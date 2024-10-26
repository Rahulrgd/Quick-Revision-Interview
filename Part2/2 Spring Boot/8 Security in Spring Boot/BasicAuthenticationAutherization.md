### Basic Authentication and Authorization Mechanisms in Spring Security

Spring Security provides robust mechanisms for implementing authentication and authorization in Java applications. Below is an overview of basic authentication and authorization mechanisms, along with examples of how to implement them.

#### 1. Authentication

**Authentication** is the process of verifying the identity of a user or system. In Spring Security, there are several methods to authenticate users, but the most common ones are:

- **Basic Authentication**: This method involves sending the username and password encoded in Base64 in the HTTP headers. It is simple but should be used over HTTPS to ensure security.

- **Form-Based Authentication**: This involves creating a custom login form where users can enter their credentials.

##### Basic Authentication Example

To implement basic authentication in a Spring Boot application, follow these steps:

1. **Add Dependencies**:

Make sure you have the Spring Security starter dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2. **Configure Basic Authentication**:

Create a security configuration class that extends `WebSecurityConfigurerAdapter`:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll() // Allow public access to /public
                .anyRequest().authenticated() // All other requests require authentication
                .and()
            .httpBasic(); // Enable Basic Authentication
    }
}
```

3. **User Details Service**:

You also need to configure a `UserDetailsService` to provide user authentication. You can define users in-memory for simplicity:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER").build());
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic(); // Enable Basic Authentication
    }
}
```

4. **Testing Basic Authentication**:

When you send an HTTP request to a secured endpoint (e.g., `/api/secure`), you will need to include the `Authorization` header with Base64 encoded credentials.

**Example Request** (using `curl`):

```bash
curl -u user:password http://localhost:8080/api/secure
```

#### 2. Authorization

**Authorization** is the process of determining whether a user has permission to access a particular resource or perform a specific action. Spring Security allows you to implement various authorization mechanisms, including:

- **Role-Based Authorization**: Users are assigned roles, and access is granted based on these roles.
- **Method-Level Security**: Use annotations to secure specific methods in your services.

##### Role-Based Authorization Example

Continuing from the basic authentication example, you can implement role-based authorization by defining access rules in your security configuration:

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access /admin
            .antMatchers("/user/**").hasRole("USER") // Only USER can access /user
            .anyRequest().authenticated() // All other requests require authentication
            .and()
        .httpBasic();
}
```

##### Method-Level Security

You can also enable method-level security by adding `@EnableGlobalMethodSecurity(prePostEnabled = true)` to your security configuration class:

```java
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Configuration as shown above
}
```

Now you can secure your service methods using annotations like `@PreAuthorize`:

```java
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PreAuthorize("hasRole('ADMIN')")
    public void adminOnlyMethod() {
        // Only accessible by users with ADMIN role
    }

    @PreAuthorize("hasRole('USER')")
    public void userOnlyMethod() {
        // Only accessible by users with USER role
    }
}
```

#### Conclusion

- **Authentication** verifies user identity, with options like basic authentication and form-based authentication.
- **Authorization** determines user access rights, often using roles and method-level security.
- Spring Security provides a comprehensive framework for securing applications with built-in support for various authentication and authorization methods, making it a robust solution for managing application security.