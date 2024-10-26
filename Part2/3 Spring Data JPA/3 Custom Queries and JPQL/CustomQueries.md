In **Spring Data JPA**, the `@Query` annotation allows you to write custom queries using JPQL (Java Persistence Query Language) or native SQL, providing more control over complex data retrieval. This is particularly useful for queries that cannot be generated automatically by Spring Data JPA’s query derivation.

### 1. **Basic JPQL Custom Query with `@Query`**

   - **JPQL** is a query language similar to SQL but operates on entity objects instead of database tables.
   - To define a custom query, place `@Query` above a repository method and specify the JPQL query as a string.
   
   **Example: Fetch Users by Active Status**

   ```java
   public interface UserRepository extends JpaRepository<User, Long> {
       
       @Query("SELECT u FROM User u WHERE u.active = :status")
       List<User> findByActiveStatus(@Param("status") boolean status);
   }
   ```

   - In this example, `:status` is a named parameter mapped to the method argument `status`.

### 2. **Using Native SQL Queries with `@Query(nativeQuery = true)`**

   - For queries that require native SQL, set `nativeQuery = true`.
   - Native queries are directly executed on the database and not parsed by the JPA provider, allowing for more complex SQL statements.

   **Example: Fetch Users with Native SQL**

   ```java
   @Query(value = "SELECT * FROM users WHERE active = :status", nativeQuery = true)
   List<User> findUsersByStatus(@Param("status") boolean status);
   ```

### 3. **Parameter Binding in Custom Queries**

   - **Named Parameters**: Use `:paramName` in the query and bind it using `@Param`.
   - **Positional Parameters**: Use `?1`, `?2`, etc., based on parameter order.

   **Example: Using Named and Positional Parameters**

   ```java
   @Query("SELECT u FROM User u WHERE u.lastName = :lastName AND u.age > ?1")
   List<User> findUsersByLastNameAndMinAge(@Param("lastName") String lastName, int age);
   ```

### 4. **Modifying Queries with `@Modifying`**

   - Use `@Modifying` for queries that modify data (like `UPDATE` or `DELETE` statements).
   - Marking it as `@Transactional` is recommended to ensure data consistency.

   **Example: Update User Status**

   ```java
   @Modifying
   @Transactional
   @Query("UPDATE User u SET u.active = :status WHERE u.id = :userId")
   int updateUserStatus(@Param("userId") Long userId, @Param("status") boolean status);
   ```

### 5. **Pagination and Sorting in Custom Queries**

   - Add `Pageable` to method parameters to enable pagination in custom queries.
   - The method should return a `Page<T>` object.

   **Example: Custom Query with Pagination**

   ```java
   @Query("SELECT u FROM User u WHERE u.lastName = :lastName")
   Page<User> findUsersByLastName(@Param("lastName") String lastName, Pageable pageable);
   ```

### 6. **Complex Queries with `JOIN` and Aggregate Functions**

   - You can use joins and aggregate functions like `COUNT`, `SUM`, etc., in custom queries.

   **Example: Find Users with Associated Orders**

   ```java
   @Query("SELECT u FROM User u JOIN u.orders o WHERE o.total > :total")
   List<User> findUsersWithOrdersAbove(@Param("total") double total);
   ```

### Summary

- Use `@Query` for complex queries not covered by method name query derivation.
- Specify `nativeQuery = true` for native SQL.
- Use `@Modifying` and `@Transactional` for update or delete operations.
- For pagination, add `Pageable` to the query method and return `Page<T>`.