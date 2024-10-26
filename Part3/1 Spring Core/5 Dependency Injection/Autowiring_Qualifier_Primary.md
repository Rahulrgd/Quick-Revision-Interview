**`@Autowired`**: This annotation injects dependencies automatically by type. It can be used on constructors, setters, and fields, allowing Spring to resolve and inject the required bean.

```java
@Component
public class MyService {
    @Autowired
    private MyRepository myRepository;  // Autowires by type
}
```

**`@Qualifier`**: When multiple beans of the same type exist, `@Qualifier` specifies which bean to inject by name, helping to avoid ambiguity.

```java
@Component
public class MyService {
    @Autowired
    @Qualifier("specificRepository")
    private MyRepository myRepository;  // Specifies the bean by name
}
```

**`@Primary`**: This annotation marks a bean as the default when multiple beans of the same type are present. It resolves dependency ambiguity without specifying a `@Qualifier`.

```java
@Component
@Primary
public class DefaultRepository implements MyRepository {  // Default bean when no qualifier is specified
}
``` 

**Summary**:
- `@Autowired` injects by type.
- `@Qualifier` resolves ambiguity by name.
- `@Primary` designates a default bean among multiple candidates.