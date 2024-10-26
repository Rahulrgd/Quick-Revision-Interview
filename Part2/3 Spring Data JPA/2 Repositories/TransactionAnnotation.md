In **Spring Data JPA**, the `@Transactional` annotation manages transaction boundaries to ensure data integrity and consistency during database operations. Transactions are vital to ensure that a series of operations either complete successfully as a whole or roll back in case of an error.

### Key Points About `@Transactional` with Repositories

1. **Automatic Transactions in Spring Data JPA Repositories**:
   - **Spring Data JPA** repositories automatically use transactions for methods that modify data (like `save`, `delete`, etc.) without requiring explicit `@Transactional` annotations.
   - For read-only operations (e.g., `findAll`, `findById`), Spring defaults to non-transactional access unless specified otherwise.

2. **Using `@Transactional` in Custom Methods**:
   - **Custom methods** in repositories or service layers that perform complex operations can use `@Transactional` to explicitly define transaction boundaries.
   - Place `@Transactional` on a method if it involves multiple database operations to ensure all operations succeed or fail together.

3. **Transaction Propagation and Isolation Levels**:
   - Spring’s `@Transactional` supports configuring **propagation** (how transactions interact with existing transactions) and **isolation levels** (how transactions interact with other transactions). These configurations help control transaction behavior in complex scenarios.

### Common `@Transactional` Configurations

1. **Default Usage**: 
   - By default, `@Transactional` starts a new transaction and commits or rolls back on method completion.
   
   ```java
   @Transactional
   public void updateUserProfile(Long userId, Profile newProfile) {
       User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException());
       user.setProfile(newProfile);
       userRepository.save(user);
   }
   ```

2. **Propagation Types**:
   - **REQUIRED** (default): Joins the current transaction or creates a new one if none exists.
   - **REQUIRES_NEW**: Always creates a new transaction, suspending any existing one.
   - **SUPPORTS**: Joins the current transaction if available; executes non-transactionally if not.
   
   ```java
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void createOrder(Order order) {
       orderRepository.save(order);
   }
   ```

3. **Read-Only Transactions**:
   - Mark a method as `@Transactional(readOnly = true)` for non-modifying, read-only operations, optimizing database interaction by reducing lock contention.

   ```java
   @Transactional(readOnly = true)
   public List<User> findActiveUsers() {
       return userRepository.findByActive(true);
   }
   ```

4. **Rollback Rules**:
   - By default, `@Transactional` rolls back for **unchecked exceptions** (like `RuntimeException`) but not for checked exceptions.
   - You can explicitly specify exceptions to roll back with the `rollbackFor` parameter.

   ```java
   @Transactional(rollbackFor = Exception.class)
   public void transferFunds(Account from, Account to, BigDecimal amount) throws Exception {
       if (from.getBalance().compareTo(amount) < 0) {
           throw new Exception("Insufficient funds");
       }
       from.withdraw(amount);
       to.deposit(amount);
       accountRepository.save(from);
       accountRepository.save(to);
   }
   ```

### Summary

- Use `@Transactional` to manage transaction boundaries, ensuring that multiple database operations are either all successful or all rolled back.
- `@Transactional` defaults to transaction management in modifying operations, so only apply it explicitly in custom methods or complex scenarios.
- Customize with propagation, isolation levels, and rollback rules for finer transaction control.