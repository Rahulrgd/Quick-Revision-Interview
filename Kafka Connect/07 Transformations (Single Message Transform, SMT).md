### Transformations in Kafka Connect (Single Message Transformations - SMT)

Single Message Transformations (SMTs) in Kafka Connect allow you to modify messages as they flow through the connectors. These transformations can be applied to each individual record, making them powerful for data manipulation and enrichment. 

#### Types of Transformations

1. **Built-in Transformations**:
   - Kafka Connect provides a variety of built-in SMTs that can be used to transform records. Here are some common types:
     - **`ValueToKey`**: Converts the message value into the message key.
     - **`ExtractField`**: Extracts a specified field from the message value.
     - **`Mask`**: Masks sensitive data within the message.
     - **`TimestampConverter`**: Converts timestamps to a specified format.
     - **`InsertField`**: Inserts a new field into the record.

2. **Custom Transformations**:
   - If built-in transformations do not meet your needs, you can implement custom SMTs by extending the `org.apache.kafka.connect.transforms.Transformation` interface.
   - Custom SMTs allow for tailored data processing logic that is specific to your application requirements.

#### Creating a Custom SMT

To create a custom SMT, you need to implement the following methods:

- **`apply`**: The core method where the transformation logic is applied to each record.
- **`config`**: Defines the configuration options for the SMT.
- **`close`** and **`configure`**: Handle initialization and cleanup.

**Example of a Custom SMT**:
Here’s a simple example of a custom SMT that appends a static string to the message value.

```java
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

public class AppendString<R extends ConnectRecord<R>> implements Transformation<R> {
    private String appendString;

    @Override
    public R apply(R record) {
        String newValue = record.value() + appendString;
        return record.newRecord(record.topic(), record.kafkaPartition(), record.keySchema(), record.key(), null, newValue, record.timestamp());
    }

    @Override
    public void configure(Map<String, ?> configs) {
        this.appendString = (String) configs.get("append.string");
    }

    @Override
    public void close() {
        // Cleanup if necessary
    }
}
```

#### Common Use Cases

1. **Data Enrichment**: 
   - Adding metadata to records, such as source information or processing timestamps.
   - Example: Using `InsertField` to add a field that indicates the source system.

2. **Data Filtering**:
   - Removing unnecessary fields or filtering out records based on specific criteria.
   - Example: Using `ExtractField` to keep only the necessary fields in the record.

3. **Data Transformation**:
   - Changing data formats, like converting timestamps or transforming numeric values.
   - Example: Using `TimestampConverter` to convert timestamps from one format to another.

4. **Masking Sensitive Information**:
   - Protecting sensitive data by masking certain fields in the record.
   - Example: Using `Mask` to anonymize personal information before sending it to a destination.

5. **Changing Data Structure**:
   - Modifying the structure of records, such as flattening nested structures or converting arrays to strings.
   - Example: A custom SMT that converts a list of tags into a comma-separated string.

### Summary

Single Message Transformations (SMTs) are a powerful feature of Kafka Connect that enables you to modify records as they flow through the pipeline. By utilizing built-in SMTs or creating custom transformations, you can enrich, filter, transform, or mask data according to your specific requirements. This flexibility allows for seamless integration and processing of data in real-time, enhancing the overall data pipeline functionality.