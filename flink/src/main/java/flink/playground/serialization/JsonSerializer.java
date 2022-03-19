package flink.playground.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import flink.playground.model.ExampleData;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.kafka.common.errors.SerializationException;

import java.nio.charset.StandardCharsets;

public class JsonSerializer implements SerializationSchema<ExampleData> {
    private final Gson gson = new GsonBuilder().create();

    // default constructor needed by Flink
    public JsonSerializer(Class<ExampleData> exampleDataClass) {
    }

    @Override
    public void open(InitializationContext context) throws Exception {
        SerializationSchema.super.open(context);
    }

    @Override
    public byte[] serialize(ExampleData data) {
        if (data == null)
            return null;

        try {
            return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }
}