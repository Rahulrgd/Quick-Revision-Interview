**Constructor-based DI** injects dependencies through a class constructor, ensuring all required dependencies are set at object creation. This approach is preferred for mandatory dependencies, promotes immutability, and helps with testability by enforcing dependencies at the object’s instantiation.

```java
public class Service {
    private final Repository repository;

    public Service(Repository repository) {  // Constructor-based DI
        this.repository = repository;
    }
}
```

**Setter-based DI** injects dependencies using setter methods, allowing optional dependencies to be set or changed after object creation. This approach is flexible but can lead to partially initialized objects if not used carefully.

```java
public class Service {
    private Repository repository;

    public void setRepository(Repository repository) {  // Setter-based DI
        this.repository = repository;
    }
}
```

**Key Difference**: Constructor-based DI is better for mandatory dependencies, while setter-based DI is suitable for optional ones.