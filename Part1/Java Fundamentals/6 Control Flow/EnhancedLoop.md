In Java, the **enhanced `for` loop**, also known as the **for-each loop**, provides a simpler and more readable way to iterate over arrays and collections (like lists, sets, and maps). It abstracts away the complexity of using an index or iterator, making it easier to work with collections.

### Syntax of Enhanced `for` Loop

The syntax of the enhanced `for` loop is as follows:

```java
for (Type element : collection) {
    // Code to execute using the element
}
```

### Example with Arrays

Here's an example of using the enhanced `for` loop with an array:

```java
public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        // Enhanced for loop to iterate through the array
        System.out.println("Using enhanced for loop:");
        for (int number : numbers) {
            System.out.println("Number: " + number); // Outputs each number in the array
        }
    }
}
```

### Example with Collections

You can also use the enhanced `for` loop with Java Collections like `ArrayList`, `HashSet`, etc. Here's an example with an `ArrayList`:

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Enhanced for loop to iterate through the ArrayList
        System.out.println("Using enhanced for loop with ArrayList:");
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit); // Outputs each fruit in the list
        }
    }
}
```

### Example with HashSet

You can also use the enhanced `for` loop with a `HashSet`:

```java
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");

        // Enhanced for loop to iterate through the HashSet
        System.out.println("Using enhanced for loop with HashSet:");
        for (String color : colors) {
            System.out.println("Color: " + color); // Outputs each color in the set
        }
    }
}
```

### Key Benefits of Enhanced `for` Loop

- **Simplicity**: The enhanced `for` loop eliminates the need for an index variable or an explicit iterator, making the code cleaner and easier to read.
- **Readability**: It clearly expresses the intent of iterating through each element in a collection or array.
- **Safety**: It reduces the chance of `ArrayIndexOutOfBoundsException` and makes code less error-prone, as it automatically handles the iteration.

### Limitations

- **No Index Access**: You cannot access elements by index with the enhanced `for` loop.
- **Modification**: It’s not suitable for cases where you need to modify the collection during iteration (e.g., adding or removing elements).

---

The enhanced `for` loop is an essential feature in Java that simplifies iteration over arrays and collections, contributing to more maintainable and less error-prone code.