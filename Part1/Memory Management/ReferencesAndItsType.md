In Java, references are used to interact with objects in memory. Java provides four types of references—**Strong**, **Weak**, **Soft**, and **Phantom**—to give more control over how objects are managed by the Garbage Collector (GC).

---

### 1. Strong Reference
   - **Definition**: A strong reference is the standard reference type created by simply assigning an object to a variable.
   - **Behavior**: An object with a strong reference is not eligible for garbage collection until the reference is explicitly set to `null`. This ensures that strongly-referenced objects remain in memory as long as they are reachable.
   - **Example**:
     ```java
     String str = new String("Strong Reference"); // Strong reference to the String object
     ```

   - **Use Case**: Strong references are used by default in Java and are suitable for essential objects that must stay in memory.

### 2. Weak Reference
   - **Definition**: A weak reference does not prevent its referent from being collected by the GC. If only weak references point to an object, it can be reclaimed by the GC.
   - **Behavior**: Weak references are useful for caching or situations where you want objects to be collectible if memory is needed.
   - **Example**:
     ```java
     WeakReference<String> weakRef = new WeakReference<>(new String("Weak Reference"));
     ```
   - **Use Case**: Commonly used in caching to allow GC to reclaim memory if it’s low, ensuring memory efficiency without strong hold on the object.

### 3. Soft Reference
   - **Definition**: A soft reference is similar to a weak reference but is more GC-friendly; it is only reclaimed when the JVM is running low on memory.
   - **Behavior**: Softly-referenced objects are not immediately collected and can be used in scenarios where the object is useful but non-essential.
   - **Example**:
     ```java
     SoftReference<String> softRef = new SoftReference<>(new String("Soft Reference"));
     ```
   - **Use Case**: Often used for memory-sensitive caching where you want to retain data for as long as possible, but allow GC to free memory when needed.

### 4. Phantom Reference
   - **Definition**: Phantom references provide the most flexibility, as they are used to track when an object has been finalized and is about to be collected.
   - **Behavior**: Unlike weak and soft references, a phantom reference does not allow access to the referent. It’s only used to determine when the object is ready for finalization, and it’s commonly used with a `ReferenceQueue`.
   - **Example**:
     ```java
     PhantomReference<String> phantomRef = new PhantomReference<>(new String("Phantom Reference"), new ReferenceQueue<>());
     ```
   - **Use Case**: Primarily used for resource cleanup or finalization processes, allowing additional actions to be taken before the GC reclaims the memory.

---

### Summary Table

| Reference Type  | Eligibility for GC         | Accessibility    | Common Use |
|-----------------|----------------------------|------------------|------------|
| **Strong**      | Never, unless set to null  | Fully accessible| Regular object references |
| **Weak**        | Always eligible            | Accessible until GC reclaims | Caching, memory-sensitive storage |
| **Soft**        | GC when memory is low      | Accessible until GC reclaims | Longer-lived caches, memory-friendly retention |
| **Phantom**     | Collected after finalization| Not accessible  | Resource cleanup, finalization actions |

---

By using these reference types appropriately, Java developers can fine-tune memory usage, optimizing application performance and stability.