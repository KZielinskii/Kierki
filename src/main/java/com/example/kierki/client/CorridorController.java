package com.example.kierki.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Obsługuje klienta w korytarzu.
 */
public class CorridorController
{
    /**
     * Strumień wejściowy od Serwera. Służy do komunikacji z serwerem. (Odbiór wiadomości).
     */
    public BufferedReader in;
    /**
     * Strumień wyjściowy do Serwera. Służy do komunikacji z serwerem. (Wysyłka wiadomości).
     */
    public PrintWriter out;

    @FXML HBox room1;
    @FXML HBox room2;
    @FXML HBox room3;
    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    @FXML Label user1;
    @FXML Label user2;
    @FXML Label user3;
    @FXML Label user4;
    @FXML Label user5;
    @FXML Label user6;
    @FXML Label user7;
    @FXML Label user8;
    @FXML Label user9;
    @FXML Label user10;
    @FXML Label user11;
    @FXML Label user12;
    @FXML Label user13;
    @FXML Label user14;
    @FXML Label user15;
    @FXML Label user16;
    @FXML Label user17;
    @FXML Label user18;
    @FXML Label user19;
    @FXML Label user20;
    @FXML Label user21;
    @FXML Label user22;
    @FXML Label user23;
    @FXML Label user24;
    @FXML Label user25;
    @FXML Label user26;
    @FXML Label user27;
    @FXML Label user28;
    @FXML Label user29;
    @FXML Label user30;
    @FXML Label user31;
    @FXML Label user32;

    /**
     * Inicjuje korytarz z pokojami.
     */
    @FXML
    public void initialize()
    {
        this.out = StartApplication.out;
        this.in = StartApplication.in;
        try
        {
            checkUser(user1);
            checkUser(user2);
            checkUser(user3);
            checkUser(user4);
            checkUser(user5);
            checkUser(user6);
            checkUser(user7);
            checkUser(user8);
            checkUser(user9);
            checkUser(user10);
            checkUser(user11);
            checkUser(user12);
            checkUser(user13);
            checkUser(user14);
            checkUser(user15);
            checkUser(user16);
            checkUser(user17);
            checkUser(user18);
            checkUser(user19);
            checkUser(user20);
            checkUser(user21);
            checkUser(user22);
            checkUser(user23);
            checkUser(user24);
            checkUser(user25);
            checkUser(user26);
            checkUser(user27);
            checkUser(user28);
            checkUser(user29);
            checkUser(user30);
            checkUser(user31);
            checkUser(user32);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void checkUser(Label user) throws IOException
    {
        String str = in.readLine();
        if(str.equals("null"))
        {
            user.setText("#####");
        }
        else
        {
            user.setText(str);
        }
    }

    /**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 1.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom1(ActionEvent event)
    {
        try
        {
            out.println("1");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 2.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom2(ActionEvent event)
    {
        try
        {
            out.println("2");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 3.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom3(ActionEvent event)
    {
        try
        {
            out.println("3");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }/**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 4.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom4(ActionEvent event)
    {
        try
        {
            out.println("4");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }/**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 5.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom5(ActionEvent event)
    {
        try
        {
            out.println("5");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }/**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 6.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom6(ActionEvent event)
    {
        try
        {
            out.println("6");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }/**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 7.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom7(ActionEvent event)
    {
        try
        {
            out.println("7");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }/**
     * Przenosi użytkownika do wybranego pokoju. W tym przypadku pokój 8.
     * @param event Event przycisku dołączenia do pokoju.
     */
    @FXML
    public void joinToRoom8(ActionEvent event)
    {
        try
        {
            out.println("8");
            out.flush();
            int nrInRoom = Integer.parseInt(in.readLine());
            String isNotFull = in.readLine();
            StartApplication menu = new StartApplication();
            if(isNotFull.equals("true"))
            {
                switch (nrInRoom)
                {
                    case 0:
                        user1.setText(StartApplication.username);
                        break;
                    case 1:
                        user2.setText(StartApplication.username);
                        break;
                    case 2:
                        user3.setText(StartApplication.username);
                        break;
                    case 3:
                        user4.setText(StartApplication.username);
                        break;
                }
                menu.changeScene("Room.fxml");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
