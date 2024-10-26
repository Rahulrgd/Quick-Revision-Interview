In **Spring Data JPA**, the `Pageable` interface is used to handle pagination and sorting efficiently in database queries. The `Pageable` object can be passed to repository methods, making it easy to retrieve a specific page of data sorted by particular fields.

### 1. **Setting Up Pagination and Sorting with `Pageable`**

- **`Pageable`** provides pagination details, including the page number, page size, and sort order.
- You can create a `Pageable` instance using `PageRequest`:
  - **Page Number**: Starts from `0` (first page).
  - **Page Size**: Number of records per page.
  - **Sort Order**: Use `Sort.by()` to specify sorting criteria.

  **Example: Creating a `Pageable` Object**

  ```java
  Pageable pageable = PageRequest.of(0, 10, Sort.by("lastName").ascending());
  ```

### 2. **Using Pageable in Repository Methods**

- `Pageable` can be passed as an argument in repository methods to apply pagination and sorting.
- The repository method should return a `Page<T>` object, which includes the paginated data and additional pagination details like total pages, total elements, and whether there are more pages.

  **Example: Repository Method with Pagination**

  ```java
  public interface UserRepository extends JpaRepository<User, Long> {
      Page<User> findByActive(boolean active, Pageable pageable);
  }
  ```

  **Usage in Service Layer**

  ```java
  @Autowired
  private UserRepository userRepository;

  public Page<User> getActiveUsers(int page, int size) {
      Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
      return userRepository.findByActive(true, pageable);
  }
  ```

### 3. **Handling the `Page` Object**

- The `Page<T>` object returned by repository methods provides details about the data and pagination.
- **Key Methods**:
  - `getContent()`: Returns the list of items in the current page.
  - `getTotalPages()`: Total number of pages available.
  - `getTotalElements()`: Total number of elements across all pages.
  - `hasNext()`, `hasPrevious()`: Check for more pages.

  **Example: Processing the `Page` Result**

  ```java
  Page<User> pageResult = userRepository.findByActive(true, pageable);

  List<User> users = pageResult.getContent(); // List of users on the current page
  int totalPages = pageResult.getTotalPages();
  long totalElements = pageResult.getTotalElements();
  ```

### 4. **Sorting Multiple Fields**

- Sorting by multiple fields is possible by chaining fields with `Sort.by(...)`.

  ```java
  Pageable pageable = PageRequest.of(0, 10, Sort.by("lastName").ascending().and(Sort.by("firstName").descending()));
  ```

### Summary

Using `Pageable` with repository methods allows flexible, efficient pagination and sorting. By adjusting the `PageRequest`, you can control which page of data is returned, the number of items per page, and the sorting criteria, enabling efficient handling of large datasets in applications.