package com.mongodb.SunLabs;

import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.lt;




public class Collection {
    public static void createCollection(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above

            MongoClient db = MongoClients.create("mongodb+srv://<Program>:<programPassword>@cluster0.tllz1of.mongodb.net/SunLabsSignIn");

            // Get the database instance
            MongoDatabase database = db.getDatabase("SunLabsSignIn");

            // Create the collection
            database.createCollection(collectionName);
            System.out.println(
                    "Collection created Successfully");
        }
        catch (Exception e) {
            System.out.println(
                    "Collection creation failed");
            System.out.println(e);
        }
    }

    public static MongoCollection<Document> getCollection(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above
            String uri = "mongodb+srv://cluster0.tllz1of.mongodb.net/SunLabsSignIns";

            MongoClient db = MongoClients.create(uri);

            // Get the database instance
            MongoDatabase database = db.getDatabase("SunLabsSignIns");

            // Retrieve the collection
            MongoCollection<Document> collection = database.getCollection(collectionName);

            System.out.println("Collection retrieved Successfully");
            return collection;
        }
        catch (Exception e) {
            System.out.println("Collection retrieval failed");
            System.out.println(e);
        }
        return null;
    }

    public static void insertADocIntoDb(MongoCollection<Document> coll,Document document)
    {
        try {
            // Insert the document
            coll.insertOne(document);

            System.out.println(
                    "Document inserted Successfully");
        }
        catch (Exception e) {
            System.out.println(
                    "Document insertion failed");
            System.out.println(e);
        }
    }

    // Function to insert multiple
    // documents in to the MongoDB
    public static void insertManyDocsIntoDb(MongoCollection<Document> coll)
    {
        try {
            // establishConnections() Code
            // is defined above
            // Creating the document
            // to be inserted
            Document document = new Document("title", "MongoDB").append("about", "Open-Source database");
            Document document1 = new Document("title", "retrieveDb").append("about", "Open-source database");

            // Adding the documents into a list
            List<Document> dblist = new ArrayList<Document>();
            dblist.add(document);
            dblist.add(document1);

            // Insert the list of documents into DB
            coll.insertMany(dblist);

            System.out.println("Documents inserted Successfully");
        }
        catch (Exception e) {
            System.out.println("Documents insertion failed");
            System.out.println(e);
        }
    }

    public static void displayDocuments(MongoCollection<Document> coll)
    {

        try {
            // establishConnections() Code
            // is defined above

            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/SunLabsSignIns");

            // Get the database instance
            MongoDatabase database = db.getDatabase("SunLabsSignIns");
            MongoCollection<Document> collection = database.getCollection("Users");
            System.out.println("Displaying the list of Documents");

            Bson projectionFields = Projections.fields(Projections.include("x"),Projections.excludeId());

            MongoCursor<Document> cursor = collection.find(lt("runtime",15)).projection(projectionFields).sort(Sorts.descending("x")).iterator();
            try{
                while(cursor.hasNext()){
                    System.out.println(cursor.next().toJson());
                }
            }finally{
                cursor.close();
            }
        }
        catch (Exception e) {
            System.out.println("Could not find the documents or No document exists");
            System.out.println(e);
        }
    }
/*
    public static void updateDocuments(MongoCollection<Document> coll)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

            coll.updateOne(Filters.eq("title", "MongoDB"), Updates.set("about", "Database"));

            System.out.println("Successfully updated" + " the document");
        }
        catch (Exception e) {
            System.out.println("Updation failed");
            System.out.println(e);
        }
    }

    public static void deleteDocuments(MongoCollection<Document> coll)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

            coll.deleteOne(Filters.eq("title", "Open-Source Database"));
            System.out.println("Successfully deleted" + " the document");
        }
        catch (Exception e) {
            System.out.println("Deletion failed");
            System.out.println(e);
        }
    }
*/
}
