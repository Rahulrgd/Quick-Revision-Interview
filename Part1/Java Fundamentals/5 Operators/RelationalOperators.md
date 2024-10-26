In Java, **relational operators** are used to compare two values. The result of a relational operation is a boolean value (`true` or `false`). Here’s an overview of the relational operators along with code examples:

### 1. **Equal to (`==`)**
The `==` operator checks if two values are equal.

```java
int a = 5;
int b = 5;
boolean isEqual = (a == b); // Output: true
```

### 2. **Not equal to (`!=`)**
The `!=` operator checks if two values are not equal.

```java
int x = 10;
int y = 5;
boolean isNotEqual = (x != y); // Output: true
```

### 3. **Greater than (`>`)**
The `>` operator checks if the left operand is greater than the right operand.

```java
int m = 8;
int n = 3;
boolean isGreater = (m > n); // Output: true
```

### 4. **Less than (`<`)**
The `<` operator checks if the left operand is less than the right operand.

```java
int p = 2;
int q = 5;
boolean isLess = (p < q); // Output: true
```

### 5. **Less than or equal to (`<=`)**
The `<=` operator checks if the left operand is less than or equal to the right operand.

```java
int r = 7;
int s = 7;
boolean isLessOrEqual = (r <= s); // Output: true
```

### 6. **Greater than or equal to (`>=`)**
The `>=` operator checks if the left operand is greater than or equal to the right operand.

```java
int t = 6;
int u = 4;
boolean isGreaterOrEqual = (t >= u); // Output: true
```

### Example Program with All Relational Operators

Here’s a simple Java program demonstrating the use of all relational operators:

```java
public class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println("a == b: " + (a == b));        // Output: false
        System.out.println("a != b: " + (a != b));        // Output: true
        System.out.println("a > b: " + (a > b));          // Output: false
        System.out.println("a < b: " + (a < b));          // Output: true
        System.out.println("a <= b: " + (a <= b));        // Output: true
        System.out.println("a >= b: " + (a >= b));        // Output: false
    }
}
```

---

Relational operators are crucial for making decisions in programming, allowing you to implement conditional logic through statements like `if`, `while`, and loops. They enable you to compare values effectively and determine the flow of your program based on those comparisons.