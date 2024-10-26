In Java, **logical operators** are used to combine boolean expressions or to invert the value of a boolean expression. Here’s a breakdown of the logical operators along with code examples:

### 1. **Logical AND (`&&`)**
The `&&` operator returns `true` if both operands are true. If either operand is false, it returns `false`.

```java
boolean a = true;
boolean b = false;
boolean resultAnd = a && b; // Output: false
```

### 2. **Logical OR (`||`)**
The `||` operator returns `true` if at least one of the operands is true. It only returns `false` if both operands are false.

```java
boolean x = true;
boolean y = false;
boolean resultOr = x || y; // Output: true
```

### 3. **Logical NOT (`!`)**
The `!` operator inverts the value of a boolean expression. If the expression is `true`, it returns `false`, and vice versa.

```java
boolean p = true;
boolean resultNot = !p; // Output: false
```

### Example Program with All Logical Operators

Here’s a Java program demonstrating the use of logical operators:

```java
public class Main {
    public static void main(String[] args) {
        boolean condition1 = true;
        boolean condition2 = false;

        // Logical AND
        System.out.println("condition1 && condition2: " + (condition1 && condition2)); // Output: false

        // Logical OR
        System.out.println("condition1 || condition2: " + (condition1 || condition2)); // Output: true

        // Logical NOT
        System.out.println("!condition1: " + (!condition1)); // Output: false
        System.out.println("!condition2: " + (!condition2)); // Output: true
    }
}
```

### Combined Usage in Conditions

Logical operators are often used in conditional statements, allowing for more complex boolean expressions:

```java
public class Main {
    public static void main(String[] args) {
        int age = 18;
        boolean hasID = true;

        // Check if the person can enter the club
        if (age >= 18 && hasID) {
            System.out.println("You can enter the club.");
        } else {
            System.out.println("You cannot enter the club.");
        }
    }
}
```

---

Logical operators are essential for controlling program flow through conditional logic, enabling developers to create complex conditions that dictate the execution of code based on multiple boolean expressions.