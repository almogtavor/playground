package flink.playground.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import flink.playground.model.ExampleData;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonDeserializer implements DeserializationSchema<ExampleData> {

    private final Gson gson = new GsonBuilder().create();

    private Class<ExampleData> destinationClass;
    private Type reflectionTypeToken;

    public JsonDeserializer(Class<ExampleData> destinationClass) {
        this.destinationClass = destinationClass;
    }

    public JsonDeserializer(Type reflectionTypeToken) {
        this.reflectionTypeToken = reflectionTypeToken;
    }

    @Override
    public ExampleData deserialize(byte[] bytes) throws IOException {
        if (bytes == null)
            return null;

        try {
            Type type = destinationClass != null ? destinationClass : reflectionTypeToken;
            return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), type);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing message", e);
        }
    }

    @Override
    public boolean isEndOfStream(ExampleData exampleData) {
        return false;
    }

    @Override
    public TypeInformation<ExampleData> getProducedType() {
        return TypeInformation.of(ExampleData.class);
    }
}