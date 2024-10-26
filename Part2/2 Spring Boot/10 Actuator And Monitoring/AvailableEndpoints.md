### Understanding Available Endpoints for Health Checks and Metrics in Spring Boot Actuator

Spring Boot Actuator provides several built-in endpoints that allow you to monitor the health and performance of your application. These endpoints can give you insights into various aspects of your application, including health status, application metrics, and system performance.

#### Common Actuator Endpoints

1. **Health Endpoint**: `/actuator/health`
   - **Description**: This endpoint provides the health status of the application. It checks the application's components, such as database connectivity, message brokers, etc., and reports their statuses.
   - **Response Example**:
     ```json
     {
       "status": "UP",
       "components": {
         "diskSpace": {
           "status": "UP",
           "details": {
             "total": 1000000000,
             "free": 800000000,
             "threshold": 10000000
           }
         },
         "db": {
           "status": "UP"
         }
       }
     }
     ```
   - **Customization**: You can enable or disable specific health indicators via configuration properties. For example, to enable the disk space health indicator:
     ```properties
     management.health.diskspace.enabled=true
     ```

2. **Info Endpoint**: `/actuator/info`
   - **Description**: This endpoint provides arbitrary application information, such as build version, description, and other custom details you may configure in your application.
   - **Response Example**:
     ```json
     {
       "app": {
         "name": "Demo Application",
         "version": "1.0.0"
       }
     }
     ```
   - **Customization**: You can populate this endpoint by adding properties to `application.properties` or `application.yml`:
     ```properties
     info.app.name=Demo Application
     info.app.version=1.0.0
     ```

3. **Metrics Endpoint**: `/actuator/metrics`
   - **Description**: This endpoint exposes application metrics, such as memory usage, garbage collection statistics, and request count.
   - **Response Example**:
     ```json
     {
       "names": [
         "jvm.memory.used",
         "jvm.threads.live",
         "http.server.requests"
       ]
     }
     ```
   - **Detailed Metrics**: You can access detailed metrics by appending the metric name to the endpoint. For example:
     - Memory used: `/actuator/metrics/jvm.memory.used`
     - HTTP requests: `/actuator/metrics/http.server.requests`

4. **Loggers Endpoint**: `/actuator/loggers`
   - **Description**: This endpoint allows you to view and modify the logging levels of your application at runtime.
   - **Response Example**:
     ```json
     {
       "levels": {
         "ROOT": "INFO",
         "com.example": "DEBUG"
       }
     }
     ```
   - **Adjust Logging Level**: You can change the logging level by sending a `POST` request:
     ```bash
     curl -X POST -H "Content-Type: application/json" \
     -d '{"configuredLevel": "DEBUG"}' \
     http://localhost:8080/actuator/loggers/com.example
     ```

5. **Thread Dump Endpoint**: `/actuator/threaddump`
   - **Description**: This endpoint provides a thread dump of the application, useful for diagnosing performance issues.
   - **Response Example**:
     ```json
     {
       "threads": [
         {
           "name": "http-nio-8080-exec-1",
           "state": "RUNNABLE",
           ...
         }
       ]
     }
     ```

6. **Heap Dump Endpoint**: `/actuator/heapsdump`
   - **Description**: This endpoint allows you to trigger a heap dump of the JVM. The dump is returned as a binary file, which you can analyze later.
   - **How to Use**: Access the endpoint via a `GET` request to download the heap dump.

#### Customizing Endpoint Exposure

You can control which endpoints are exposed using properties in `application.properties` or `application.yml`. For example, to expose only specific endpoints:

```properties
management.endpoints.web.exposure.include=health,info,metrics
```

To exclude endpoints:

```properties
management.endpoints.web.exposure.exclude=shutdown
```

#### Securing Endpoints

If you have Spring Security configured, you can secure these endpoints using basic authentication or any other authentication method. Ensure to restrict access to sensitive endpoints, especially in production environments.

#### Conclusion

Spring Boot Actuator provides a comprehensive set of endpoints for monitoring the health and metrics of your application. By utilizing these endpoints, you can gain valuable insights into the performance and state of your application, enabling you to diagnose issues quickly and effectively.