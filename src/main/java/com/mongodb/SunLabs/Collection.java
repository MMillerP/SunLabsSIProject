package com.mongodb.SunLabs;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.SunLabs.ConnectionDB.*;


public class Collection {
    public static void createCollection(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();
            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

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
            establishConnections();
            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

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

    public static void insertADocIntoDb(String collectionName)
    {
        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            // Creating the document
            // to be inserted
            Document document = new Document("title", "MyDatabase").append("about", "Open-Source database");

            // Insert the document
            getCollection(collectionName).insertOne(document);

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
    public static void insertManyDocsIntoDb(String collectionName)
    {
        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            // Creating the document
            // to be inserted
            Document document = new Document("title", "MongoDB").append("about", "Open-Source database");
            Document document1 = new Document("title", "retrieveDb").append("about", "Open-source database");

            // Adding the documents into a list
            List<Document> dblist = new ArrayList<Document>();
            dblist.add(document);
            dblist.add(document1);

            // Insert the list of documents into DB
            getCollection(collectionName).insertMany(dblist);

            System.out.println("Documents inserted Successfully");
        }
        catch (Exception e) {
            System.out.println("Documents insertion failed");
            System.out.println(e);
        }
    }

    public static void displayDocuments(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            System.out.println("Displaying the list" + " of Documents");

            // Get the list of documents from the DB
            FindIterable<Document> iterobj = getCollection(collectionName).find();

            // Print the documents using iterators
            Iterator itr = iterobj.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        }
        catch (Exception e) {
            System.out.println("Could not find the documents " + "or No document exists");
            System.out.println(e);
        }
    }

    public static void updateDocuments(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

            MongoCollection<Document> collection = database.getCollection("collectionName");

            collection.updateOne(Filters.eq("title", "MongoDB"), Updates.set("about", "Database"));

            System.out.println("Successfully updated" + " the document");
        }
        catch (Exception e) {
            System.out.println("Updation failed");
            System.out.println(e);
        }
    }

    public static void deleteDocuments(String collectionName)
    {

        try {
            // establishConnections() Code
            // is defined above
            establishConnections();

            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");
            // Get the database instance
            MongoDatabase database = db.getDatabase("MyDatabase");

            MongoCollection<Document> collection = database.getCollection(collectionName);

            collection.deleteOne(Filters.eq("title", "Open-Source Database"));
            System.out.println("Successfully deleted" + " the document");
        }
        catch (Exception e) {
            System.out.println("Deletion failed");
            System.out.println(e);
        }
    }

}
