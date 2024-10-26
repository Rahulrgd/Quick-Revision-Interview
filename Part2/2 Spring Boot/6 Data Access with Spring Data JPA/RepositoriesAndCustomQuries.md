### Understanding Repositories and Custom Queries in Spring Data JPA

In Spring Data JPA, repositories are a key component that facilitates data access and manipulation in a database. They abstract away the complexities of database interactions and provide a simple interface for performing CRUD operations. Additionally, Spring Data JPA allows you to define custom queries for more complex operations. Here’s a breakdown of repositories and how to implement custom queries.

#### 1. What is a Repository?

A repository in Spring Data JPA is an interface that provides methods to interact with the underlying database. By extending interfaces like `JpaRepository`, `CrudRepository`, or `PagingAndSortingRepository`, you can access various built-in methods for CRUD operations without needing to implement them yourself.

**Example Repository Interface:**

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Custom query method
}
```

#### 2. Built-in Methods

By extending `JpaRepository`, your repository inherits several built-in methods for data manipulation, including:

- `save(S entity)`: Saves a given entity.
- `findById(ID id)`: Retrieves an entity by its ID.
- `findAll()`: Returns all entities.
- `deleteById(ID id)`: Deletes the entity with the given ID.
- `count()`: Returns the number of entities.

These methods greatly reduce boilerplate code, allowing you to focus on business logic.

#### 3. Custom Query Methods

Spring Data JPA allows you to define custom query methods by following a naming convention. The method name should start with a keyword that specifies the operation (e.g., `find`, `read`, `get`, `delete`) followed by the field name in CamelCase.

**Example of Custom Query Method:**

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email); // Finds users by email
    List<User> findByAgeGreaterThanEqual(int age); // Finds users aged greater than or equal to the specified age
}
```

#### 4. Using `@Query` Annotation

For more complex queries or when the naming convention does not suffice, you can use the `@Query` annotation to define JPQL (Java Persistence Query Language) or native SQL queries directly in your repository interface.

**Example of JPQL Query:**

```java
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.age > ?1")
    List<User> findUsersOlderThan(int age); // JPQL query

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsernameNative(String username); // Native SQL query
}
```

#### 5. Parameters in Custom Queries

You can pass parameters to your custom queries using positional parameters (`?1`, `?2`, etc.) or named parameters (`:paramName`).

**Example with Named Parameters:**

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
```

#### 6. Pagination and Sorting

Spring Data JPA supports pagination and sorting out of the box. You can add `Pageable` or `Sort` parameters to your repository methods.

**Example with Pagination:**

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByAgeGreaterThanEqual(int age, Pageable pageable); // Returns a paginated list of users
}
```

### Summary

- **Repositories**: Interfaces that provide methods for CRUD operations without boilerplate code.
- **Custom Query Methods**: Methods can be defined using naming conventions to perform specific queries.
- **@Query Annotation**: Allows the definition of custom JPQL or native SQL queries.
- **Parameters**: Use positional or named parameters to pass data into queries.
- **Pagination and Sorting**: Built-in support for paginated and sorted queries.

By leveraging Spring Data JPA repositories and custom queries, you can efficiently manage database interactions while keeping your code clean and maintainable.