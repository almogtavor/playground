package flink.playground.serialization;

import org.bson.Document;

import java.io.IOException;

public class DocumentBulkSerializer {

    public void serde() throws IOException {
        String json =
                "{\n" +
                        "\"name\": \"A Brief History of Time\",\n" +
                        "\"author\": \"Stephen Hawking\",\n" +
                        "\"language\": \"English\",\n" +
                        "\"publication year\": 1988\n" +
                        "}";
        Document document = Document.parse(json);
        DocumentBulk origin = new DocumentBulk();
        origin.add(document);
        DocumentBulkSerializer serializer = DocumentBulkSerializer.INSTANCE;
        byte[] bytes = serializer.serialize(origin);
//        assertEquals(origin, serializer.deserialize(serializer.getVersion(), bytes));
    }

}
