### Working with Embedded Servers in Spring Boot

Spring Boot comes with built-in support for embedded servers like **Tomcat**, **Jetty**, and **Undertow**. This means you can run your Spring Boot application as a standalone Java application without the need for a separate server installation. Here’s how to work with embedded servers in Spring Boot:

#### 1. **Default Embedded Server: Tomcat**

By default, Spring Boot uses **Tomcat** as the embedded server. When you include the `spring-boot-starter-web` dependency in your project, Tomcat is included automatically.

##### Example: Using Tomcat

1. **Add Dependency**:
   Include the Spring Web Starter in your `pom.xml` (for Maven):

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

2. **Create a Spring Boot Application**:

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

3. **Run the Application**:
   You can run the application from your IDE or by using the command line:

   ```bash
   ./mvnw spring-boot:run
   ```

   By default, Tomcat listens on port **8080**. You can access your application at `http://localhost:8080`.

#### 2. **Changing the Embedded Server**

If you want to use a different embedded server like **Jetty**, you need to exclude the Tomcat starter and include the Jetty starter in your dependencies.

##### Example: Using Jetty

1. **Exclude Tomcat and Include Jetty**:

   In your `pom.xml`, exclude the Tomcat dependency and include the Jetty dependency:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
       <exclusions>
           <exclusion>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-tomcat</artifactId>
           </exclusion>
       </exclusions>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-jetty</artifactId>
   </dependency>
   ```

2. **Run the Application**:
   Just like with Tomcat, you can run your Spring Boot application, and it will now use Jetty as the embedded server. It will also default to port **8080** unless configured otherwise.

#### 3. **Customizing Server Configuration**

You can customize the server configuration (like changing the port, context path, etc.) in the `application.properties` or `application.yml` file.

##### Example Configuration:

```properties
# Change server port
server.port=8081

# Change context path
server.servlet.context-path=/myapp
```

#### 4. **Using Undertow as an Embedded Server**

If you prefer **Undertow**, the steps are similar to using Jetty:

1. **Add Undertow Dependency**:

   In your `pom.xml`, exclude Tomcat and include Undertow:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
       <exclusions>
           <exclusion>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-tomcat</artifactId>
           </exclusion>
       </exclusions>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-undertow</artifactId>
   </dependency>
   ```

2. **Run Your Application**:
   Undertow will also run the application similarly on the default port 8080 or any other port you configure.

### Summary

Using embedded servers in Spring Boot allows you to develop and run applications easily without the overhead of a separate server setup. Whether you use Tomcat, Jetty, or Undertow, Spring Boot makes it simple to switch between them and customize the server settings to fit your application's needs. This flexibility is one of the many reasons developers prefer Spring Boot for building web applications.