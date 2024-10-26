Here’s an expanded answer with code examples to illustrate Java reference types:

---

In Java, **reference types** are used to store references to objects rather than primitive values. Key reference types include **String**, **arrays**, and **objects**.

### 1. **String**
Strings are sequences of characters stored as objects. Strings are immutable, meaning any modification creates a new object.

```java
String greeting = "Hello, World!";
System.out.println(greeting); // Output: Hello, World!
```

If you try to modify the `greeting` string, like adding `" Welcome!"`, Java creates a new `String` object:

```java
String newGreeting = greeting + " Welcome!";
System.out.println(newGreeting); // Output: Hello, World! Welcome!
```

### 2. **Arrays**
Arrays store multiple elements of the same type, which can be either primitive or reference types. Their size is fixed at the time of creation.

```java
// Array of integers
int[] numbers = {1, 2, 3, 4, 5};
System.out.println(numbers[0]); // Output: 1

// Array of strings
String[] names = {"Alice", "Bob", "Charlie"};
System.out.println(names[1]); // Output: Bob
```

Arrays can also hold objects, allowing them to store complex data types.

### 3. **Objects**
Objects are instances of classes. Each object has attributes (fields) and behaviors (methods) defined by the class.

```java
class Person {
    String name;
    int age;

    // Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void introduce() {
        System.out.println("Hi, I'm " + name + " and I'm " + age + " years old.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an object of type Person
        Person person = new Person("Rahul", 25);
        person.introduce(); // Output: Hi, I'm Rahul and I'm 25 years old.
    }
}
```

In the example above, `person` is a reference type variable that stores the memory address of a `Person` object. The object `person` has fields (`name`, `age`) and a method (`introduce`), which are accessed using the dot `.` operator.

---

These examples help clarify how reference types differ from primitives in storing data and demonstrate how they’re used in real-world Java applications.