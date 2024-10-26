The **Criteria API** in JPA provides a programmatic way to construct dynamic queries. It allows you to build queries in a type-safe manner using Java code instead of JPQL or SQL strings, making it easier to create complex queries and modify them at runtime.

### Key Components of the Criteria API

1. **CriteriaBuilder**: The main factory for creating `CriteriaQuery`, `CriteriaUpdate`, `CriteriaDelete`, and other query elements.
2. **CriteriaQuery**: Represents a query object, which defines the structure of the query (like selecting entities).
3. **Root**: Represents the root entity in the query from which the selection is made.
4. **Predicate**: Represents a condition in the query, allowing for dynamic filtering.

### Steps to Create Dynamic Queries with Criteria API

1. **Obtain an Instance of `EntityManager`**.
2. **Create a `CriteriaBuilder`** instance from the `EntityManager`.
3. **Create a `CriteriaQuery`** for the desired result type.
4. **Define the root entity** of the query using the `Root` object.
5. **Build predicates** based on the search criteria.
6. **Execute the query** using the `EntityManager`.

### Example: Dynamic Query using Criteria API

#### 1. **Setup Entity**

```java
@Entity
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean active;

    // Getters and setters
}
```

#### 2. **Create a Service Method for Dynamic Query**

```java
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findUsers(String lastName, Integer age, Boolean isActive) {
        // Step 1: Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // Step 3: Define the root entity
        Root<User> userRoot = criteriaQuery.from(User.class);

        // Step 4: Create predicates based on parameters
        List<Predicate> predicates = new ArrayList<>();

        if (lastName != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("lastName"), lastName));
        }
        if (age != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("age"), age));
        }
        if (isActive != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("active"), isActive));
        }

        // Step 5: Combine predicates into a single predicate
        criteriaQuery.select(userRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Step 6: Execute the query
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
```

### Explanation of the Example

1. **Creating a CriteriaBuilder**: The `CriteriaBuilder` is obtained from the `EntityManager`, allowing the creation of `CriteriaQuery` and `Predicate` objects.

2. **Defining the CriteriaQuery**: A `CriteriaQuery<User>` is created to select `User` entities.

3. **Root Definition**: The `Root<User>` represents the main entity in the query, providing access to its attributes.

4. **Building Predicates**: Based on input parameters, predicates are dynamically constructed. Each non-null parameter generates a corresponding predicate that adds conditions to the query.

5. **Combining Predicates**: The `criteriaQuery.where()` method accepts an array of predicates, combining them with logical AND.

6. **Executing the Query**: Finally, the constructed query is executed, and the results are returned.

### Advantages of Using Criteria API

- **Type Safety**: Queries are built using Java objects and types, reducing the risk of errors associated with string-based queries.
- **Dynamic Query Construction**: Easily build and modify queries based on runtime conditions, making it suitable for complex filtering.
- **Readability**: The structure of the code can improve readability compared to constructing JPQL or SQL strings directly.

### Summary

The Criteria API is a powerful tool in JPA for building dynamic, type-safe queries. It enables you to construct complex queries programmatically, making it easier to adapt queries to varying runtime conditions. This flexibility, combined with type safety, makes the Criteria API a preferred choice for complex query requirements in JPA.