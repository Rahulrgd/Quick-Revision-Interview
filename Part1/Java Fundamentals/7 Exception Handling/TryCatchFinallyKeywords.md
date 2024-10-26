In Java, **`try-catch` blocks** and the **`finally` keyword** are used for handling exceptions and ensuring that certain code executes regardless of whether an exception occurs. Here’s an explanation of how they work, along with examples.

### 1. **Try-Catch Blocks**

A `try-catch` block allows you to handle exceptions gracefully. The code that might throw an exception is placed in the `try` block, and the handling code is placed in the `catch` block. If an exception occurs in the `try` block, control transfers to the `catch` block, where you can specify how to handle the exception.

#### Syntax:
```java
try {
    // Code that may throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
}
```

#### Example:
```java
public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};

        try {
            // Attempt to access an invalid index
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Program continues after exception handling.");
    }
}
```

### 2. **Finally Keyword**

The `finally` block is used to execute code after the `try` and `catch` blocks, regardless of whether an exception occurred or not. It is typically used for cleanup activities, such as closing files or releasing resources.

#### Syntax:
```java
try {
    // Code that may throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
} finally {
    // Code that will always execute
}
```

#### Example:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            // Handle the exception
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Always executed
            try {
                if (reader != null) {
                    reader.close(); // Close the resource
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }
}
```

### Key Points

- **`try` Block**: Contains code that may throw an exception.
- **`catch` Block**: Contains code to handle the exception if it occurs.
- **`finally` Block**: Executes after the `try` and `catch` blocks, regardless of whether an exception occurred. It is useful for cleanup code.
- If an exception is not caught in a `catch` block, it propagates up the call stack, potentially terminating the program.

### Combining Multiple Catch Blocks

You can have multiple `catch` blocks to handle different types of exceptions separately:

```java
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This will throw ArithmeticException
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        } finally {
            System.out.println("This will always execute.");
        }
    }
}
```

---

Using `try-catch` blocks along with the `finally` keyword is essential for robust error handling and resource management in Java applications, ensuring that your code can handle unexpected situations gracefully.