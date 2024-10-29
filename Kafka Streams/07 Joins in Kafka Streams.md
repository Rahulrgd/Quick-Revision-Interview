In Kafka Streams, joins allow combining data from multiple sources, enabling enriched and complex event processing. Here’s a summary of the different types of joins:

### 1. **Stream-Stream Joins**
   - Joins two `KStream` instances, typically within a **time window** since streams are continuous and can produce unbounded data.
   - The window defines how long two records can be apart and still be joined.
   - Commonly used to correlate events arriving in different streams.

   **Example**: Joining two streams with a 5-minute window.
   ```java
   KStream<String, String> stream1 = builder.stream("input-topic1");
   KStream<String, String> stream2 = builder.stream("input-topic2");

   KStream<String, String> joinedStream = stream1.join(
       stream2,
       (value1, value2) -> value1 + " - " + value2,   // Join logic
       JoinWindows.of(Duration.ofMinutes(5))           // 5-minute window
   );

   joinedStream.to("joined-output-topic");
   ```

### 2. **Stream-Table Joins**
   - Joins a `KStream` with a `KTable`, where the table provides the current state or enriched data for each key.
   - Since tables represent a snapshot of data, the stream record is joined with the latest value in the table for the matching key.
   - Useful for enriching streaming data with static or slowly changing information (e.g., looking up user details or reference data).

   **Example**: Enriching a stream with data from a table.
   ```java
   KStream<String, String> ordersStream = builder.stream("orders");
   KTable<String, String> customerTable = builder.table("customers");

   KStream<String, String> enrichedStream = ordersStream.leftJoin(
       customerTable,
       (orderValue, customerValue) -> orderValue + " for customer: " + customerValue
   );

   enrichedStream.to("enriched-output-topic");
   ```

### 3. **Table-Table Joins**
   - Joins two `KTable` instances to create a new table that represents a continuously updated view of the join result.
   - Typically used to combine related data from two tables (e.g., merging customer and order details).
   - Joins are updated whenever there is a change in either table, as tables are a snapshot of the latest state.

   **Example**: Joining two tables to create a combined view.
   ```java
   KTable<String, String> customersTable = builder.table("customers");
   KTable<String, String> ordersTable = builder.table("orders");

   KTable<String, String> joinedTable = customersTable.join(
       ordersTable,
       (customerValue, orderValue) -> customerValue + " ordered " + orderValue
   );

   joinedTable.toStream().to("joined-table-output-topic");
   ```

### Summary
- **Stream-Stream Join**: Joins two streams within a time window for correlating events.
- **Stream-Table Join**: Joins a stream with a table for enriching stream data with table values.
- **Table-Table Join**: Joins two tables to create an updated view of combined data for each key.