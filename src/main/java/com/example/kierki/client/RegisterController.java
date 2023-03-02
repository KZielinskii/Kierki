package com.example.kierki.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Scanner;
/**
 * Obsługuje klienta podczas rejestracji.
 */
public class RegisterController
{
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password1;
    @FXML
    private Label wrongRegister;

    /**
     * Sprawdza czy wprowadzone dane są poprawne jak tak to tworzy konto klienta.
     * @param event
     */
    @FXML
    public void onActionButtonRegister(ActionEvent event)
    {
        checkRegister();
    }
    private void checkRegister()
    {
        try
        {
            StartApplication menu = new StartApplication();
            if(loginExists())
            {
                wrongRegister.setText("Podany login już istnieje!");
            }
            else if(passwordCorrect()!=0)
            {
                if(passwordCorrect()==1)
                {
                    wrongRegister.setText("Wprowadzone hasło nie może zawierać białych znaków!");
                }
                else if(passwordCorrect()==2)
                {
                    wrongRegister.setText("Wprowadzone hasło musi być dłuższe niż 5 znaków!");
                }
            }
            else if(!passwordsMatch())
            {
                wrongRegister.setText("Wprowadzone hasła muszą być identyczne!");
            }
            else
            {
                saveNewUser();
                wrongRegister.setText("Zarejestrowano");
                menu.changeScene("Menu.fxml");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void saveNewUser()
    {
        try
        {
            FileWriter file = new FileWriter("src/main/resources/data.txt", true);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(username.getText().toString()+" "+password.getText().toString()+'\n');
            writer.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    private boolean loginExists()
    {
        try
        {
            File file = new File("src/main/resources/data.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                if(data[0].equals(username.getText().toString()))
                {
                    return true;
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    private int passwordCorrect()
    {
        String pass = password.getText().toString();
        int length1 = pass.length();
        pass = pass.trim();
        int length2 = pass.length();
        if(length1!=length2) return 1;
        if(length2<5) return 2;
        return 0;
    }
    private boolean passwordsMatch()
    {
        String pass1 = password.getText().toString();
        String pass2 = password1.getText().toString();
        return pass1.equals(pass2);
    }
}