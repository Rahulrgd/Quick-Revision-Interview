Here’s an example demonstrating the different types of comments in Java: single-line, multi-line, and documentation comments:

```java
// This is a single-line comment.
// It briefly describes the code that follows.

public class Calculator {

    /*
     * This is a multi-line comment.
     * Multi-line comments are useful for explaining more complex logic,
     * providing notes, or temporarily disabling code during debugging.
     */
    private int result;

    /**
     * Adds two numbers and returns the result.
     *
     * @param a the first number to add
     * @param b the second number to add
     * @return the sum of a and b
     */
    public int add(int a, int b) {
        result = a + b;
        return result;
    }

    /**
     * Subtracts the second number from the first and returns the result.
     *
     * @param a the number to be subtracted from
     * @param b the number to subtract
     * @return the result of a - b
     */
    public int subtract(int a, int b) {
        result = a - b;
        return result;
    }

    /**
     * Main method to test the Calculator class.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Single-line comment explaining this line of code
        int sum = calculator.add(10, 5);
        System.out.println("Sum: " + sum);

        int difference = calculator.subtract(10, 5);
        System.out.println("Difference: " + difference);
    }
}
```

### Explanation of Comment Types:
1. **Single-Line Comment (`//`)**:
   - Used for brief comments that explain a single line or a small part of the code.
   - Example: `// Single-line comment explaining this line of code`.

2. **Multi-Line Comment (`/* ... */`)**:
   - Used for longer explanations or for commenting out multiple lines of code during testing or debugging.
   - Example:
     ```java
     /*
      * This is a multi-line comment.
      * It spans multiple lines.
      */
     ```

3. **Documentation Comment (`/** ... */`)**:
   - Used for generating documentation using tools like Javadoc.
   - Includes tags like `@param` (parameter description), `@return` (return value description), and `@throws` (exceptions thrown).
   - Example:
     ```java
     /**
      * Adds two numbers and returns the result.
      *
      * @param a the first number to add
      * @param b the second number to add
      * @return the sum of a and b
      */
     ```

These comments help make the code clearer and more maintainable, especially in team environments or when returning to code after a while. Let me know if you need further details on Javadoc or other comment best practices!