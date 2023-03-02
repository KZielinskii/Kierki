package com.example.kierki.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

/**
 * Obsługuje klienta w Menu.
 */
public class MenuController
{
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLogin;
    /**
     * Sprawdza czy wprowadzone dane są poprawne jeśli tak to zatwierdza i pozwala klientowi wejść do korytarza.
     */
    @FXML
    public void onActionButtonLogin(ActionEvent event)
    {
        if(StartApplication.successfulConnectWithServer())
        {
            checkLogin();
        }
        else
        {
            wrongLogin.setText("Nie można połączyć się z serwerem!");
        }
    }

    /**
     * Przenosi klienta do panelu rejestracji.
     * @param event Event przycisku rejestracji.
     */
    @FXML
    public void onActionButtonRegister(ActionEvent event)
    {
        changeSceneToRegister();
    }

    private void checkLogin()
    {
        StartApplication menu = new StartApplication();
        StartApplication.username = username.getText().toString();
        BufferedReader in = StartApplication.in;
        PrintWriter out = StartApplication.out;
        try
        {
            out.println(username.getText().toString());
            out.println(password.getText().toString());
            out.flush();
            String isCorrect = in.readLine();
            if(isCorrect.equals("true"))
            {
                wrongLogin.setText("Zalogowano");
                changeSizeOfStage();
                menu.changeScene("Corridor.fxml");
            }
            else if(username.getText().isEmpty() || password.getText().isEmpty())
            {
                wrongLogin.setText("Nie wypełniono wszystkich pól logowania!");
            }
            else if(isCorrect.equals("ingame"))
            {
                wrongLogin.setText("Podany urzytkownik jest już w grze!");
            }
            else
            {
                wrongLogin.setText("Nieprawodłowy login lub hasło!");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void changeSizeOfStage()
    {
        Stage stage = (Stage)wrongLogin.getScene().getWindow();
        stage.setHeight(720);
        stage.setWidth(1080);
        stage.setX(0);
        stage.setY(0);
    }
    private void changeSceneToRegister()
    {
        try
        {
            StartApplication menu = new StartApplication();
            menu.changeScene("Register.fxml");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}