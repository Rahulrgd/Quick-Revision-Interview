In Kafka Streams, **Exactly Once Semantics (EOS)** ensures that each record is processed only once, even in cases of retries due to failures. This is crucial in applications where data accuracy and consistency are critical. EOS is achieved through idempotence and transactional support in Kafka Streams.

### Key Aspects of EOS in Kafka Streams:

1. **Idempotence**:
   - Ensures that even if a message is sent multiple times, it is processed only once.
   - Kafka’s idempotent producer helps achieve this by tracking messages and deduplicating duplicates.

2. **Transactions**:
   - Kafka Streams uses transactions to atomically commit all operations (reads, writes, state updates) for each processing task.
   - If a task fails, all its changes can be rolled back, ensuring no partial processing.
   
3. **How Exactly Once Works in Kafka Streams**:
   - When enabled, each processing step is treated as a single, atomic transaction.
   - Data changes are applied only if the entire transaction succeeds.
   - If there’s a failure, Kafka Streams retries the transaction without duplicating previous results.

4. **Configuration**:
   - To enable EOS, set the configuration `processing.guarantee` to `"exactly_once"` or `"exactly_once_beta"` (the newer version with improved performance).
   
   **Example**:
   ```java
   Properties props = new Properties();
   props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-streams-app");
   props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
   props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);  // Enables exactly-once semantics

   KafkaStreams streams = new KafkaStreams(builder.build(), props);
   streams.start();
   ```

### Summary:
- **Idempotence** ensures each record is processed only once even with retries.
- **Transactions** provide atomic processing of read/write operations.
- Together, they guarantee **Exactly Once Processing** for accurate, reliable stream processing in Kafka Streams.