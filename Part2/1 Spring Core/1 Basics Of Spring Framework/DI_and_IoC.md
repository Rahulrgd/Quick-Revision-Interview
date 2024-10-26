### Understanding the Principles of Dependency Injection (DI) and Inversion of Control (IoC)

**Inversion of Control (IoC):**  
IoC is a design principle that reverses the control flow of a program. Instead of the application code controlling the flow of execution, the framework manages the flow, allowing for more modular and testable code. In IoC, the framework calls the application code rather than the other way around.

**Dependency Injection (DI):**  
DI is a specific implementation of the IoC principle. It involves providing an object's dependencies (the objects it needs to function) from the outside rather than creating them internally. This decouples the components of the application, making it easier to manage and test.

#### Key Points:
- **Loose Coupling:** By injecting dependencies, components are less dependent on each other, making changes and testing easier.
- **Testability:** DI facilitates unit testing, as mock or stub implementations can be easily injected instead of real dependencies.
- **Configuration Flexibility:** Dependencies can be configured externally, allowing for different implementations or settings based on the environment (e.g., development vs. production).

### Example Code

#### Without Dependency Injection (Tightly Coupled)

```java
class Car {
    private Engine engine;

    public Car() {
        this.engine = new Engine(); // Directly creating the dependency
    }

    public void start() {
        engine.start();
    }
}

class Engine {
    public void start() {
        System.out.println("Engine started.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }
}
```

In the above code, `Car` directly creates an instance of `Engine`, leading to tight coupling. If you want to change the `Engine` implementation, you'll need to modify the `Car` class.

#### With Dependency Injection (Loosely Coupled)

**Using Constructor Injection:**

```java
class Car {
    private Engine engine;

    public Car(Engine engine) { // Dependency is injected through the constructor
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }
}

class Engine {
    public void start() {
        System.out.println("Engine started.");
    }
}

class ElectricEngine extends Engine {
    @Override
    public void start() {
        System.out.println("Electric engine started.");
    }
}

public class Main {
    public static void main(String[] args) {
        Engine engine = new ElectricEngine(); // Injecting a different implementation
        Car car = new Car(engine);
        car.start();
    }
}
```

In this example, `Car` accepts an `Engine` instance through its constructor, allowing for flexible dependency management. This makes it easy to swap out different `Engine` implementations without changing the `Car` class.

### Summary

In summary, IoC and DI are foundational concepts in the Spring framework that promote cleaner, more maintainable, and testable code by managing object lifecycles and dependencies efficiently. By following these principles, you can create applications that are easier to develop, test, and maintain.