In Java, **Stack** and **Heap** memory are two important areas of memory management that serve different purposes:

---

### 1. Stack Memory
   - **Purpose**: The stack is used for storing local variables and method calls.
   - **Structure**: Follows a Last-In-First-Out (LIFO) structure, meaning that the last method call or variable to be added is the first to be removed.
   - **Data Stored**: Stores primitive data types (e.g., `int`, `float`) and references to objects in the heap.
   - **Lifetime**: Each time a method is called, a new block (stack frame) is created on top of the stack to hold that method’s variables. Once the method finishes execution, the stack frame is removed.
   - **Thread-Safety**: Stack memory is unique to each thread, making it inherently thread-safe.
   - **Size Limit**: Typically limited in size and can lead to a `StackOverflowError` if the stack grows too large (e.g., from too many nested method calls or infinite recursion).

   **Example**:
   ```java
   public void calculateSum() {
       int a = 5; // Stored in stack
       int b = 10; // Stored in stack
       int sum = a + b; // Stored in stack
   }
   ```

### 2. Heap Memory
   - **Purpose**: The heap is used for storing objects and instance variables.
   - **Structure**: Heap memory is a larger, more flexible area of memory, allowing dynamic memory allocation for objects at runtime.
   - **Data Stored**: Stores all objects and arrays. The actual object data resides in the heap, and references to these objects are held in the stack.
   - **Lifetime**: Objects in the heap live as long as they are reachable. When they’re no longer accessible by any part of the program, they become eligible for **garbage collection**.
   - **Thread-Safety**: Heap memory is shared among all threads, which requires synchronization to avoid concurrency issues.
   - **Size Limit**: Heap size can be adjusted, but if memory usage exceeds available heap space, a `OutOfMemoryError` occurs.

   **Example**:
   ```java
   public class Person {
       String name = "Rahul"; // Stored in heap
       int age = 25;          // Stored in heap
   }

   public static void main(String[] args) {
       Person p = new Person(); // p reference in stack; Person object in heap
   }
   ```

---

### Key Differences
| Feature            | Stack Memory                                    | Heap Memory                                    |
|--------------------|-------------------------------------------------|------------------------------------------------|
| **Purpose**        | Stores method calls and local variables         | Stores all objects and instance variables      |
| **Lifetime**       | Managed by the stack frame                      | Managed by garbage collection                  |
| **Size**           | Typically smaller, limited                      | Larger, expandable                             |
| **Access Speed**   | Fast (LIFO access)                              | Slower (dynamic allocation)                    |
| **Thread Safety**  | Inherently thread-safe                          | Shared among threads (requires synchronization)|
| **Error Handling** | `StackOverflowError` if limit is exceeded       | `OutOfMemoryError` if heap limit is exceeded   |

---

In summary, the **stack** is for method execution and local variables, while the **heap** is for objects and global access. Each serves a unique role in Java’s memory management, balancing speed and flexibility.