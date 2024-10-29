In Kafka Streams, operations can be categorized as **stateless** or **stateful** based on whether they require maintaining and accessing state.

### 1. **Stateless Operations** 
   - These operations process each record independently and do not need to store any state.
   - Common stateless operations include:
     - **filter**: Filters records based on a condition.
     - **map**: Transforms records by applying a function to each.

   **Example of Stateless Operations**:
   ```java
   KStream<String, String> stream = builder.stream("input-topic");

   // Filter records with values longer than 5 characters
   KStream<String, String> filteredStream = stream.filter((key, value) -> value.length() > 5);

   // Map values to uppercase
   KStream<String, String> mappedStream = filteredStream.mapValues(value -> value.toUpperCase());

   mappedStream.to("output-topic");
   ```

### 2. **Stateful Operations**
   - These operations require maintaining state to compute results, often using state stores to keep track of intermediate results.
   - Common stateful operations include:
     - **aggregate**: Combines records over a key, creating a running total or other accumulation.
     - **count**: Counts records per key within a stream or window.
     - **reduce**: Combines multiple records into a single output record.

   **Example of Stateful Operations**:
   ```java
   KStream<String, String> stream = builder.stream("input-topic");

   // Group by key and count occurrences of each key
   KGroupedStream<String, String> groupedStream = stream.groupByKey();
   KTable<String, Long> counts = groupedStream.count(Materialized.as("counts-store"));

   // Group and aggregate values by summing integer values
   KTable<String, Integer> aggregated = groupedStream.aggregate(
       () -> 0, // Initializer
       (key, value, aggregate) -> aggregate + Integer.parseInt(value), // Aggregator
       Materialized.as("aggregated-store")
   );

   // Write the count and aggregation results back to Kafka topics
   counts.toStream().to("count-output-topic", Produced.with(Serdes.String(), Serdes.Long()));
   aggregated.toStream().to("aggregated-output-topic", Produced.with(Serdes.String(), Serdes.Integer()));
   ```

### Summary
- **Stateless**: Independent operations on each record, no state needed (e.g., `filter`, `map`).
- **Stateful**: Requires state for cumulative calculations, uses state stores for fault tolerance (e.g., `aggregate`, `count`, `reduce`).