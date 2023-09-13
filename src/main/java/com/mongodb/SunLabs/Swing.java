package com.mongodb.SunLabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Swing {
    public static void main(String[] args) {
        JFrame fin=new JFrame("SunLabs Sign In");//creating instance of JFrame
        JDialog guiDialog = new JDialog();
        JFrame fadmin = new JFrame("SunLabs Admin GUI");
        DefaultTableModel model;

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
                //if accepted, close fin, then open fadmin


                String idString = readerTF.getText();
                // send value to database


                if(idString.substring(0,2).equalsIgnoreCase("A%")){
                    //create popup prompting to go to GUI
                    guiDialog.setVisible(true);
                    fin.setVisible(false);
                }

            }
        });






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


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        fadmin.setSize(1440, 900);//400 width and 500 height
        fadmin.setLayout(null);//using no layout managers

        MongoCollection<Document> coll = Collection.getCollection("ID");

        JTable tableDB = new JTable(); // need to fill database info
        tableDB.setBounds(300,200,400,300);
        fadmin.add(tableDB);
    }
}
