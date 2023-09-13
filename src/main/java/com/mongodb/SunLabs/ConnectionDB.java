package com.mongodb.SunLabs;

import com.mongodb.MongoCredential;
import com.mongodb.client.*;

public class ConnectionDB {
    public static void establishConnections()
    {

        try {
            MongoClient db = MongoClients.create("mongodb+srv://cluster0.tllz1of.mongodb.net/MyDatabase");

            MongoCredential credential;
            credential = MongoCredential.createCredential("apmiller111", "MyDatabase", "Bellum223%G4".toCharArray());
            System.out.println("Successfully Connected" + " to the database");

            MongoDatabase database = db.getDatabase("MyDatabase");
            System.out.println("Credentials are: " + credential);
        }
        catch (Exception e) {
            System.out.println("Connection establishment failed");
            System.out.println(e);
        }
    }
}
