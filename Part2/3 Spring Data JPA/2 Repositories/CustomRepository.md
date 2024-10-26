In **Spring Data JPA**, custom repository interfaces allow you to add your own methods beyond those provided by standard repositories. You can also extend base repositories like `CrudRepository`, `PagingAndSortingRepository`, and `JpaRepository` to gain default CRUD functionality, then add custom behaviors as needed.

### 1. **Custom Repository Interfaces**
   - You create a custom interface and add method signatures for any custom queries or operations.
   - Spring Data JPA will automatically recognize the custom repository if it follows the naming convention `<EntityName>Repository`.

   **Example: Creating a Custom Repository**

   ```java
   public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
       // Standard CRUD methods from JpaRepository
       List<User> findByLastName(String lastName);
   }

   public interface UserRepositoryCustom {
       List<User> findActiveUsers();
   }
   ```

### 2. **Implementing Custom Repository Methods**
   - Implement the custom interface in a separate class with the suffix `Impl` (e.g., `UserRepositoryImpl`).
   - Use `@Repository` and `@Transactional` annotations if needed.

   **Example: Custom Implementation**

   ```java
   @Repository
   public class UserRepositoryImpl implements UserRepositoryCustom {

       @PersistenceContext
       private EntityManager entityManager;

       @Override
       public List<User> findActiveUsers() {
           String query = "SELECT u FROM User u WHERE u.active = true";
           return entityManager.createQuery(query, User.class).getResultList();
       }
   }
   ```

### 3. **Extending Base Repositories**
   - By extending a base repository (like `JpaRepository` or `CrudRepository`), you gain built-in CRUD methods.
   - You can add custom methods to this interface, leveraging JPA’s query generation or adding your own.

   **Example: Extending `JpaRepository` with Custom Methods**

   ```java
   public interface OrderRepository extends JpaRepository<Order, Long> {
       // Custom query methods
       List<Order> findByStatus(String status);
       List<Order> findByCustomerId(Long customerId);
   }
   ```

### Putting It All Together
Using custom repository interfaces and implementations allows you to:
1. **Add custom logic** (e.g., complex queries) that goes beyond standard CRUD operations.
2. **Organize code better**, keeping custom behaviors separate from the default repository.
3. **Leverage JPA’s features** efficiently by building on base repository functionality.

Typically, **extending `JpaRepository` and implementing custom repositories** together gives you flexibility and power to handle both standard and specialized operations effectively in Spring Data JPA.