package com.mongodb.SunLabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.SunLabs.Collection.*;

public class Swing {

    public static void main(String[] args) {
        System.out.println(new Timestamp(new Date().getTime()));
        MongoCollection<Document> coll = Collection.getCollection("Users");
        JFrame fin=new JFrame("SunLabs Sign In");//creating instance of JFrame
        JDialog guiDialog = new JDialog();
        JFrame fadmin = new JFrame("SunLabs Admin GUI");
        DefaultTableModel model;
        //Card Reader Input Frame
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fin.setSize(1440, 900);//400 width and 500 height
        fin.setLayout(null);//using no layout managers
        fin.setVisible(true);//making the frame visible


        JLabel labelReader;
        labelReader = new JLabel("Card Reader Input");
        labelReader.setBounds(610,270,200,30);
        fin.add(labelReader);


        JTextField readerTF;
        readerTF = new JTextField("");
        readerTF.setBounds(610,300,200,30);
        fin.add(readerTF);


        JButton signInB;
        signInB = new JButton("Sign in");
        signInB.setBounds(630,400,160,30);
        fin.add(signInB);


        signInB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //needs to check if valid input
                //if valid, whether user, or admin
                //if user, upload to database the id and timestamp
                //if admin, upload to database ^, then offer popup to open gui
                //if not accepted,clear the textfield
                //if accepted, clear textfield, then open fadmin

                String idString = readerTF.getText();

                // send value to database


                if(!idString.isEmpty() && idString.substring(0,2).equalsIgnoreCase("A%")){
                    guiDialog.setVisible(true);
                    fin.setVisible(false);
                    Document document = new Document().append("_id",new ObjectId()).append("UserId",readerTF.getText()).append("TimeStamp",new Timestamp(new Date().getTime()));
                    insertADocIntoDb(coll,document);
                }
                else if(!idString.isEmpty()){
                    Document document = new Document().append("_id",new ObjectId()).append("UserId",readerTF.getText()).append("TimeStamp",new Timestamp(new Date().getTime()));
                    insertADocIntoDb(coll,document);
                }

            }
        });





        //Pop Up
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        guiDialog.setSize(720,480);
        guiDialog.setLocationRelativeTo(null);

        JButton contB = new JButton("Go to GUI");
        contB.setBounds(100,220,200,30);
        contB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiDialog.setVisible(false);
                fadmin.setVisible(true);
                readerTF.setText("");
            }
        });


        JButton refB = new JButton("Go to Sign in");
        refB.setBounds(400,220,200,30);
        refB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiDialog.setVisible(false);
                fin.setVisible(true);
                readerTF.setText("");
            }
        });
        guiDialog.add(contB);
        guiDialog.add(refB);


        guiDialog.setLayout(null);//using no layout managers

        //Admin Frame
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        fadmin.setLayout(new GridLayout(0,1));//using no layout managers


        createCollection("test");

        String data[][] = {{"1","1"},{"!","@"}};
        String column[] = {"UserId","Timestamp"};
        JTable tableDB = new JTable(data,column); // need to fill database info
        JScrollPane spTable = new JScrollPane(tableDB);

        fadmin.add(spTable);
        fadmin.setSize(1440, 900);
    }
}
