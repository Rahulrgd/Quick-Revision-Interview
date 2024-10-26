In Java, both `throw` and `throws` are used in the context of exception handling, but they serve different purposes. Here’s a detailed explanation of the differences between `throw` and `throws`, along with examples to illustrate their usage.

### 1. **Throw**

The `throw` keyword is used to explicitly throw an exception from a method or a block of code. You can throw either a checked or unchecked exception using `throw`. When you use `throw`, you create an instance of an exception and throw it.

#### Syntax:
```java
throw new ExceptionType("Error message");
```

#### Example:
```java
public class Main {
    public static void main(String[] args) {
        try {
            checkAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }

    // Method that throws an exception
    static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older");
        }
        System.out.println("Access granted.");
    }
}
```

### 2. **Throws**

The `throws` keyword is used in a method signature to declare that the method may throw one or more exceptions. It indicates to the caller of the method that they should be prepared to handle those exceptions. This is particularly relevant for checked exceptions, which must be either caught or declared in the method signature.

#### Syntax:
```java
void methodName() throws ExceptionType1, ExceptionType2 {
    // Method implementation
}
```

#### Example:
```java
public class Main {
    public static void main(String[] args) {
        try {
            divide(10, 0); // This will cause an exception
        } catch (ArithmeticException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }

    // Method that declares an exception using 'throws'
    static void divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        System.out.println("Result: " + (a / b));
    }
}
```

### Key Differences

| Feature        | `throw`                                    | `throws`                                   |
|----------------|--------------------------------------------|-------------------------------------------|
| Purpose        | Used to explicitly throw an exception.     | Used to declare that a method may throw an exception. |
| Location       | Used inside the method body or a block.   | Used in the method signature.             |
| Exception Type | Can throw any type of exception (checked or unchecked). | Typically used for checked exceptions.    |
| Control Flow   | Transfers control to the nearest catch block. | Not responsible for handling exceptions; it indicates that exceptions may occur. |

### Summary

- **`throw`**: Use this keyword when you want to explicitly throw an exception within your code. It creates and throws an instance of an exception.
  
- **`throws`**: Use this keyword in a method signature to declare that the method can throw exceptions, which must be handled by the caller of the method.

Understanding the difference between `throw` and `throws` is crucial for effective exception handling in Java, allowing you to design robust and maintainable applications.