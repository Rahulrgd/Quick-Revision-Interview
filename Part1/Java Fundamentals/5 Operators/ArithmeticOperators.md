In Java, **arithmetic operators** are used to perform basic mathematical operations. Here’s a quick overview with examples for each operator:

### 1. Addition (`+`)
The `+` operator adds two values together. It can also be used to concatenate strings.

```java
int a = 5;
int b = 3;
int sum = a + b; // Output: 8

String greeting = "Hello, " + "World!"; // Output: Hello, World!
```

### 2. Subtraction (`-`)
The `-` operator subtracts the second operand from the first.

```java
int x = 10;
int y = 4;
int difference = x - y; // Output: 6
```

### 3. Multiplication (`*`)
The `*` operator multiplies two values.

```java
int m = 7;
int n = 5;
int product = m * n; // Output: 35
```

### 4. Division (`/`)
The `/` operator divides the first operand by the second. For integers, it returns the quotient (any remainder is discarded).

```java
int p = 20;
int q = 4;
int quotient = p / q; // Output: 5

// Note: Division of integers gives an integer result.
int r = 7;
int s = 2;
int result = r / s; // Output: 3, as the remainder is discarded
```

If you need a decimal result, ensure one of the operands is a `double` or `float`.

```java
double dResult = (double) r / s; // Output: 3.5
```

### 5. Modulus (`%`)
The `%` operator returns the remainder of a division operation. It’s useful for determining even/odd numbers, cycling through values, etc.

```java
int num = 10;
int remainder = num % 3; // Output: 1 (10 divided by 3 leaves a remainder of 1)

// Check if a number is even or odd
if (num % 2 == 0) {
    System.out.println("Even");
} else {
    System.out.println("Odd");
}
```

### Example Program with All Operators

```java
public class Main {
    public static void main(String[] args) {
        int a = 15;
        int b = 4;

        System.out.println("Addition: " + (a + b));      // Output: 19
        System.out.println("Subtraction: " + (a - b));   // Output: 11
        System.out.println("Multiplication: " + (a * b));// Output: 60
        System.out.println("Division: " + (a / b));      // Output: 3 (quotient)
        System.out.println("Modulus: " + (a % b));       // Output: 3 (remainder)
    }
}
```

---

These basic arithmetic operators help perform essential calculations in Java, making them fundamental for building logic in programs.