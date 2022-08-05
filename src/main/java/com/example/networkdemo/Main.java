package com.example.networkdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Vector;

public class Main extends Application {

    ObjectOutputStream toServer = null;
    ObjectInputStream fromServer = null;
    MessageHandler handler;
    static Hashtable<String, Board> boardList = new Hashtable<>();

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("AI Controller"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an output stream to send data to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());

            // Create an input stream to receive data from the server
            fromServer = new ObjectInputStream(socket.getInputStream());

            handler = new MessageHandler(toServer, fromServer);

        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }

        Thread sendMessage = new Thread( () -> {
            while (true) {
                try {
                    // read the message sent to this client
                    Object msg = fromServer.readObject();

                    // Downcast message from Object
                    Message messageReceived = (Message)msg;
                    // Downcast humanTypes from Typess
                    HumanTypes messageType = (HumanTypes) messageReceived.getType();

                    System.out.println(messageType);

                    // display the text area
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // Display to the text area
                            ta.appendText(messageReceived.getType().getDescription() + "\n");
                        }
                    });

                    switch (messageType) {

                        case SOLOGAME_CREATED:
                            handler.gameCreatedHandler(messageReceived);
                            break;
                        case JOIN_SUCCESS:
                            //handler.gameCreatedHandler(messageReceived);
                            break;
                        case MAKE_MOVE:
                            handler.makeMoveHandler(messageReceived);
                            break;
//                        case QUIT: handler.quitHandler(messageReceived);
//                            break;
                        case PLAY_AGAIN: handler.rematchAcceptHandler(messageReceived);
                            break;
//                        case REMATCH_REJECT: handler.rematchRejectHandler(messageReceived);
//                            break;
                    }

                } catch (IOException ex) {
                    System.out.println("Invalid input");
                    break;
                }
                catch (ClassNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        });
        sendMessage.start();
    }



    public static void main(String[] args) {
        launch();
    }
}