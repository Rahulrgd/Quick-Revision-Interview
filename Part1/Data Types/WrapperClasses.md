Here's an explanation of wrapper classes in Java, along with code examples to clarify their importance:

---

In Java, **wrapper classes** provide a way to use primitive data types (`int`, `double`, etc.) as objects. Each primitive type has a corresponding wrapper class, such as `Integer` for `int`, `Double` for `double`, `Character` for `char`, and so on.

### Key Wrapper Classes and Their Usage
1. **Integer** - wraps an `int`
2. **Double** - wraps a `double`
3. **Character** - wraps a `char`
4. **Boolean** - wraps a `boolean`

### Importance of Wrapper Classes

1. **Collections Support**  
   Java’s collection framework (e.g., `ArrayList`, `HashMap`) only supports objects, not primitives. Wrappers allow us to store primitive values in these collections.

   ```java
   ArrayList<Integer> numbers = new ArrayList<>();
   numbers.add(10);  // Autoboxing: int is converted to Integer
   System.out.println(numbers.get(0)); // Output: 10
   ```

2. **Autoboxing and Unboxing**  
   Wrapper classes allow **autoboxing** (automatic conversion of primitives to wrapper objects) and **unboxing** (conversion of wrapper objects back to primitives), making code more readable and reducing manual conversions.

   ```java
   Integer num = 5;    // Autoboxing: int 5 is automatically converted to Integer
   int primitiveNum = num; // Unboxing: Integer is converted back to int
   ```

3. **Utility Methods**  
   Wrapper classes provide useful methods for conversion and manipulation, such as parsing strings to numbers or getting the maximum/minimum values.

   ```java
   String str = "42";
   int parsedInt = Integer.parseInt(str);  // Convert String to int
   System.out.println(parsedInt); // Output: 42

   System.out.println(Integer.MAX_VALUE);  // Output: 2147483647 (maximum value for int)
   ```

4. **Immutability**  
   Wrapper classes are immutable, meaning their values cannot be changed once created, ensuring thread-safety and reducing unexpected behavior.

### Example: Using Wrapper Classes in Java

```java
public class Main {
    public static void main(String[] args) {
        Integer age = 30;              // Autoboxing
        Double salary = 75000.50;      // Autoboxing

        System.out.println(age);       // Output: 30
        System.out.println(salary);    // Output: 75000.5

        // Using utility methods
        String numberStr = "100";
        int number = Integer.parseInt(numberStr);
        System.out.println(number);    // Output: 100

        // Using wrapper in Collections
        ArrayList<Double> prices = new ArrayList<>();
        prices.add(19.99);
        prices.add(5.49);
        System.out.println(prices);    // Output: [19.99, 5.49]
    }
}
```

---

Wrapper classes are essential for enabling object-oriented features in Java, making them vital for handling primitives in collections, using utility methods, and ensuring seamless conversions between primitives and objects.