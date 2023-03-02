package com.example.kierki.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Startuje aplikacje Klienta.
 */
public class StartApplication extends Application
{
    private static Stage stage;
    /**
     * Socket aktualnego klienta do komunikacji z serwerem.
     */
    public static Socket s;
    /**
     * Strumień wejścia do komunikacji z serwerem.
     */
    public static BufferedReader in;
    /**
     * Strumień wyjścia do komunikacji z serwerem.
     */
    public static PrintWriter out;
    /**
     * Login akutalnego klienta.
     */
    public static String username;

    /**
     * Tworzy początkową secene z Menu logowania.
     * @param primaryStage Początkowe okno.
     */
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            stage = primaryStage;
            primaryStage.setResizable(false);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("Menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Kierki");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Zmienia widok u klienta na Rejestrację, Menu, lub Korytarz.
     * @param fxml
     * @throws IOException
     */
    public void changeScene(String fxml) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    /**
     * Startuje aplikację klienta do gry w Kierki.
     * @param args Argumenty funkcji main nie są używane.
     */
    public static void main(String[] args) {
        launch();
    }

    static boolean successfulConnectWithServer()
    {
        try
        {
            s = new Socket("localhost", 1234);
            createTools(s);
            if(s.isConnected())
            {
                return true;
            }
        }
        catch(IOException e)
        {
            System.out.println("Serwer nie jest włączony!");
        }
        return false;
    }
    private static void createTools(Socket socket) throws IOException
    {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}