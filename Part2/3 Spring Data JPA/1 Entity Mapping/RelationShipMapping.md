In **Spring Data JPA**, relationship mappings (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`) define associations between entities, representing different types of database table relationships. Here’s an overview with examples:

### 1. **@OneToOne**
   - Defines a one-to-one relationship between two entities. For example, a `User` might have a unique `Profile`.

   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToOne(cascade = CascadeType.ALL)
       @JoinColumn(name = "profile_id", referencedColumnName = "id")
       private Profile profile;

       // Getters and setters
   }

   @Entity
   public class Profile {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String bio;

       // Getters and setters
   }
   ```

### 2. **@OneToMany**
   - Defines a one-to-many relationship, where one entity is related to multiple instances of another entity. For example, a `Department` might have multiple `Employees`.

   ```java
   @Entity
   public class Department {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
       private List<Employee> employees;

       // Getters and setters
   }

   @Entity
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToOne
       @JoinColumn(name = "department_id")
       private Department department;

       // Getters and setters
   }
   ```

### 3. **@ManyToOne**
   - Defines a many-to-one relationship, where many instances of one entity relate to a single instance of another. The example above (`Employee` - `Department`) demonstrates this: multiple `Employee` entities belong to one `Department`.

### 4. **@ManyToMany**
   - Defines a many-to-many relationship, where multiple instances of one entity can relate to multiple instances of another. For example, `Student` and `Course` can have a many-to-many relationship.

   ```java
   @Entity
   public class Student {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToMany
       @JoinTable(
           name = "student_course",
           joinColumns = @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id"))
       private Set<Course> courses;

       // Getters and setters
   }

   @Entity
   public class Course {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToMany(mappedBy = "courses")
       private Set<Student> students;

       // Getters and setters
   }
   ```

These relationship mappings help in establishing associations and maintaining database consistency through JPA, ensuring smooth data retrieval and management across related tables.