In Java, the `break`, `continue`, and `return` keywords are control flow statements that alter the normal execution of loops and methods. Here’s a breakdown of each keyword, along with examples.

### 1. **Break Keyword**

The `break` keyword is used to exit a loop or a switch statement prematurely. When `break` is encountered, the control exits the loop immediately.

#### Example in a Loop:

```java
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break; // Exits the loop when i is 5
            }
            System.out.println("i: " + i); // Outputs 0 to 4
        }
        System.out.println("Loop terminated.");
    }
}
```

### 2. **Continue Keyword**

The `continue` keyword is used to skip the current iteration of a loop and move to the next iteration. When `continue` is encountered, the rest of the loop's code for that iteration is skipped.

#### Example in a Loop:

```java
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue; // Skips even numbers
            }
            System.out.println("Odd number: " + i); // Outputs odd numbers: 1, 3, 5, 7, 9
        }
    }
}
```

### 3. **Return Keyword**

The `return` keyword is used to exit a method and optionally return a value to the method caller. If the method has a return type other than `void`, a value must be returned.

#### Example in a Method:

```java
public class Main {
    public static void main(String[] args) {
        int sum = add(5, 3);
        System.out.println("Sum: " + sum); // Outputs: Sum: 8
    }

    // Method that returns the sum of two integers
    public static int add(int a, int b) {
        return a + b; // Returns the sum of a and b
    }
}
```

### Summary of Usage

- **`break`**: Exits a loop or switch statement entirely.
- **`continue`**: Skips the current iteration of a loop and proceeds to the next iteration.
- **`return`**: Exits a method and can return a value to the caller if the method is not `void`.

### Example Program Using All Three Keywords

Here’s a complete Java program that demonstrates the use of `break`, `continue`, and `return`:

```java
public class Main {
    public static void main(String[] args) {
        // Demonstrating break
        System.out.println("Demonstrating break:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break; // Exit loop when i is 5
            }
            System.out.println("i: " + i);
        }

        // Demonstrating continue
        System.out.println("\nDemonstrating continue:");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            System.out.println("Odd number: " + i);
        }

        // Demonstrating return
        System.out.println("\nDemonstrating return:");
        int sum = add(10, 20);
        System.out.println("Sum: " + sum);
    }

    public static int add(int a, int b) {
        return a + b; // Return the sum
    }
}
```

---

Understanding the `break`, `continue`, and `return` keywords is crucial for controlling the flow of execution in loops and methods, enabling you to create more complex and efficient Java programs.