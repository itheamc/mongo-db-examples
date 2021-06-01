package com.itheamc.mongodb;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonArray;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDocument {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("database-name");
        MongoCollection<Document> videos = mongoDatabase.getCollection("collection-name");
	insertWithHashMap(videos);
	insertWithCustomModel(videos);
    }

    /**
     * -----------------------------------------------------------------------------
     * This is the function to insert document on the collection
     * By creating the Map Object
     * @param videos --> it is the mongo collection which stores the documents
     * -----------------------------------------------------------------------------
     */
    private static void insertWithHashMap(MongoCollection<Document> videos) {
        List<BsonString> tags_list = new ArrayList<BsonString>();
        tags_list.add(new BsonString("tag1"));
        tags_list.add(new BsonString("tag2"));
        tags_list.add(new BsonString("tag3"));


        Map<String, Object> docMap = new HashMap<>();
        docMap.put("_id", new BsonObjectId(ObjectId.get()));
        docMap.put("_title", new BsonString("This is title"));
        docMap.put("_desc", new BsonString("this is desc"));
        docMap.put("_tags", new BsonArray(tags_list));
        docMap.put("_url", new BsonString("http://example.com"));
        docMap.put("_added_by", new BsonString("Anonymous"));

        Document document = new Document(docMap);

        try {
            InsertOneResult result = videos.insertOne(document);
        } catch (MongoWriteException e) {
            String err = e.getError().getMessage();
            if (err.toLowerCase().contains("duplicate")) {
                System.out.println("Your are trying to add video that has been already added");
            } else {
                System.out.println(e.getError().getMessage());
            }
        }
    }

    /**
     * -----------------------------------------------------------------------------
     * This is the function to insert document on the collection
     * By Custom Class ---Video
     * @param videos --> it is the mongo collection which stores the documents
     * -----------------------------------------------------------------------------
     */
    private static void insertWithCustomModel(MongoCollection<Document> videos) {
        List<String> strings = new ArrayList<>();
        strings.add("tag1");
        strings.add("tag2");
        strings.add("tag3");

        Data data = new Data(
                "This is title",
                "This is desc",
                strings,
                "https://example.com",
                "Anonymous"
        );

        try {
            InsertOneResult result = videos.insertOne(data.get_document());
        } catch (MongoWriteException e) {
            String err = e.getError().getMessage();
            if (err.toLowerCase().contains("duplicate")) {
                System.out.println("Your are trying to add video that has been already added");
            } else {
                System.out.println(e.getError().getMessage());
            }

        }
    }

}
