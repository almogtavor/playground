//package flink.playground.connector;
//
//import com.mongodb.client.model.WriteModel;
//import com.mongodb.internal.bulk.WriteRequest;
//import org.apache.flink.annotation.PublicEvolving;
//
//import software.amazon.awssdk.services.dynamodb.model.WriteRequest;
//
//import java.io.Serializable;
//import java.util.Objects;
//
///**
// * Represents a single MongoDb {@link com.mongodb.client.model.WriteModel}. Contains the name of the MongoDb collection name
// * to write to as well as the {@link com.mongodb.client.model.WriteModel}
// */
//public class MongoDbWriteRequest implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private final String tableName;
//    private final WriteModel<?> writeRequest;
//
//    public MongoDbWriteRequest(String tableName, WriteModel<?> writeRequest) {
//        this.tableName = tableName;
//        this.writeRequest = writeRequest;
//    }
//
//    public String getTableName() {
//        return tableName;
//    }
//
//    public WriteModel<?> getWriteRequest() {
//        return writeRequest;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        MongoDbWriteRequest that = (MongoDbWriteRequest) o;
//        return Objects.equals(tableName, that.tableName)
//                && Objects.equals(writeRequest, that.writeRequest);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(tableName, writeRequest);
//    }
//}
//}
