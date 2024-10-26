In Java, **assignment operators** are used to assign values to variables. The basic assignment operator is `=`, but there are also **compound assignment operators** that combine an arithmetic operation with assignment. Here's an overview of these operators along with examples:

### Basic Assignment Operator

1. **Assignment (`=`)**
   The `=` operator assigns the value of the right operand to the left operand.

   ```java
   int a = 5;  // Assigns 5 to variable a
   ```

### Compound Assignment Operators

Compound assignment operators simplify the code by combining the assignment operation with an arithmetic operation. Here's how they work:

1. **Addition Assignment (`+=`)**
   Adds the right operand to the left operand and assigns the result to the left operand.

   ```java
   int b = 10;
   b += 5; // Equivalent to b = b + 5; 
   System.out.println(b); // Output: 15
   ```

2. **Subtraction Assignment (`-=`)**
   Subtracts the right operand from the left operand and assigns the result to the left operand.

   ```java
   int c = 20;
   c -= 4; // Equivalent to c = c - 4;
   System.out.println(c); // Output: 16
   ```

3. **Multiplication Assignment (`*=`)**
   Multiplies the left operand by the right operand and assigns the result to the left operand.

   ```java
   int d = 3;
   d *= 4; // Equivalent to d = d * 4;
   System.out.println(d); // Output: 12
   ```

4. **Division Assignment (`/=`)**
   Divides the left operand by the right operand and assigns the result to the left operand.

   ```java
   int e = 24;
   e /= 6; // Equivalent to e = e / 6;
   System.out.println(e); // Output: 4
   ```

5. **Modulus Assignment (`%=`)**
   Takes the modulus using two operands and assigns the result to the left operand.

   ```java
   int f = 10;
   f %= 3; // Equivalent to f = f % 3;
   System.out.println(f); // Output: 1
   ```

### Example Program with All Assignment Operators

Here’s a Java program demonstrating the use of assignment and compound assignment operators:

```java
public class Main {
    public static void main(String[] args) {
        int value = 100; // Basic assignment

        // Compound assignments
        value += 20; // value = value + 20;
        System.out.println("After += : " + value); // Output: 120

        value -= 10; // value = value - 10;
        System.out.println("After -= : " + value); // Output: 110

        value *= 2; // value = value * 2;
        System.out.println("After *= : " + value); // Output: 220

        value /= 4; // value = value / 4;
        System.out.println("After /= : " + value); // Output: 55

        value %= 6; // value = value % 6;
        System.out.println("After %= : " + value); // Output: 1
    }
}
```

---

The assignment and compound assignment operators in Java provide a convenient way to update the value of variables while performing arithmetic operations, leading to cleaner and more readable code.