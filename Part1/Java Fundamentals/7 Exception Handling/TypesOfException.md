In Java, exceptions are categorized into three main types: **checked exceptions**, **unchecked exceptions**, and **errors**. Understanding these categories helps in effectively handling errors and ensuring robust program behavior. Here’s a breakdown of each type:

### 1. **Checked Exceptions**

**Checked exceptions** are exceptions that are checked at compile time. The Java compiler requires that these exceptions be either handled using a `try-catch` block or declared in the method signature with the `throws` keyword. They typically represent conditions that a program can anticipate and recover from.

#### Examples:
- **IOException**: Thrown when an I/O operation fails or is interrupted.
- **SQLException**: Thrown when there is an issue with database access.

#### Example Code:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
            System.out.println(line);
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
```

### 2. **Unchecked Exceptions**

**Unchecked exceptions** are exceptions that are not checked at compile time. They are subclasses of `RuntimeException` and typically indicate programming errors, such as logic mistakes or improper use of APIs. These exceptions can occur anywhere in the program and do not need to be explicitly handled.

#### Examples:
- **NullPointerException**: Thrown when an application attempts to use `null` where an object is required.
- **ArrayIndexOutOfBoundsException**: Thrown when trying to access an array with an illegal index.
- **ArithmeticException**: Thrown when an exceptional arithmetic condition occurs, such as division by zero.

#### Example Code:
```java
public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        
        // This will throw ArrayIndexOutOfBoundsException
        try {
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // This will throw NullPointerException
        String str = null;
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### 3. **Errors**

**Errors** are not exceptions but rather severe conditions that a typical application should not try to catch. They are subclasses of `Error` and indicate serious problems that are usually external to the application, such as hardware failures or JVM issues. Errors are typically not recoverable.

#### Examples:
- **OutOfMemoryError**: Thrown when the Java Virtual Machine cannot allocate an object because it is out of memory.
- **StackOverflowError**: Thrown when a stack overflow occurs, often due to deep recursion.
- **InternalError**: Indicates an internal error in the JVM.

#### Example Code:
```java
public class Main {
    public static void main(String[] args) {
        // Simulate StackOverflowError
        try {
            recursiveMethod(); // This will lead to a StackOverflowError
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow occurred: " + e.getMessage());
        }
    }

    public static void recursiveMethod() {
        recursiveMethod(); // Recursive call without a base case
    }
}
```

### Summary of Exception Types

- **Checked Exceptions**: Must be handled or declared; checked at compile time. (e.g., `IOException`)
- **Unchecked Exceptions**: Runtime exceptions that do not need to be declared or caught. (e.g., `NullPointerException`)
- **Errors**: Serious issues that should not be caught; typically indicate problems outside the program's control. (e.g., `OutOfMemoryError`)

Understanding these types of exceptions helps you write more robust and error-tolerant Java applications by properly handling expected conditions and avoiding unexpected crashes.