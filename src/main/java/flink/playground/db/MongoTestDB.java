package flink.playground.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 * Base class for tests based on embedded MongoDB.
 **/
public class MongoTestDB {

    protected MongodExecutable mongodExe;
    protected MongodProcess mongod;
    protected MongoClient mongo;

    // these can be overridden by subclasses
    protected static String HOST = "127.0.0.1";
    protected static int PORT = 27018;
    protected static String DATABASE_NAME = "testdb";
    protected static String COLLECTION = "testcoll";
    protected static String CONNECT_STRING = String.format("mongodb://%s:%d/%s", HOST, PORT, DATABASE_NAME);

    public MongoTestDB() throws Exception {
        init();
    }

    public void init() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodConfig mongodConfig = MongodConfig.builder()
                .version(Version.Main.V4_4)
                .net(new Net(HOST, PORT, Network.localhostIsIPv6()))
                .build();
        this.mongodExe = starter.prepare(mongodConfig);
        this.mongod = mongodExe.start();
        this.mongo = MongoClients.create(CONNECT_STRING);
    }


    public void close() throws Exception {
        if (this.mongo != null) {
            mongo.getDatabase(DATABASE_NAME).getCollection(COLLECTION).drop();
            mongo.close();
        }
        if (this.mongod != null) {
            this.mongod.stop();
            this.mongodExe.stop();
        }
    }
}

