package com.example.kierki.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static com.example.kierki.server.StartServer.rooms;
import static com.example.kierki.server.StartServer.serverClients;

/**
 * Służy Serwerowi do komunikowania się z Klientami.
 */
public class ClientHandler implements Runnable
{
    /**
     * Lista uzytkowników na serwerze.
     */
    private ArrayList<ClientHandler> clients;
    /**
     * Soket klienta z którym łączy się serwer
     */
    private Socket client;
    /**
     * Strumień wejścia do kontaku z klientem
     */
    public BufferedReader in;
    /**
     * Strumień wyjścia do kontaku z klientem
     */
    public PrintWriter out;
    /**
     * Nazwa użtkownika z którym się kontaktujemy
     */
    public String login;
    /**
     * Numer pokoju w którym obecnie jest użytkownik dla korytarza jest to -1
     */
    public int nrRoom;

    /**
     * Tworzy objekt ClientHandler do porozumiewania się z Klientem
     * @param s Socket klienta z którym ClientHandler będzie się komunikować
     * @param clients Lista wszystkich klientów na serwerze
     */
    public ClientHandler(Socket s, ArrayList<ClientHandler> clients)
    {
        try
        {
            this.nrRoom=-1;
            this.client = s;
            this.clients = clients;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            clients.remove(this);
        }
    }

    /**
     *  Odpowiada za przemieszczanie się pomiędzy Menu a Rejestracją, oraz pomiędzy Menu a Korytarzem.
     */
    @Override
    public void run()
    {
        try
        {
            if(this.login==null)
            {
                chooseCorrectLogin();
                System.out.println("Client: [" + this.login + "] połączony!");
            }
            initListRooms();
            nrRoom = chooseCorrectRoom()-1;
            while (!client.isClosed())
            {
                in.readLine();
                if (roomIsFull(nrRoom))
                {
                    out.println("START");
                    rooms[nrRoom].startGame(this);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client: [" + this.login + "] rozłączył się!");
            deleteUserFromRoom();
            serverClients.remove(this);
        }
    }

    private void deleteUserFromRoom()
    {
        for(int i=0; i<4; i++)
        {
            if(rooms[nrRoom].logins[i].equals(login))
            {
                for(int j=i; j<4; j++)
                {
                    if(j==3 || rooms[nrRoom].logins[j]==null)
                    {
                        rooms[nrRoom].logins[j] = null;
                        return;
                    }
                    else
                    {
                        rooms[nrRoom].logins[j]=rooms[nrRoom].logins[j+1];
                    }
                }
                return;
            }
        }
    }

    private boolean roomIsFull(int nrRoom)
    {
        for(int i=0; i<4 ; i++)
        {
            if(rooms[nrRoom].logins[0]==null)return false;
        }
        return true;
    }

    private void initListRooms()
    {
        for(int j=0; j<8; j++)
        {
            for(int i=0; i<4; i++)
            {
                out.println(rooms[j].logins[i]);
            }
        }
    }

    private void chooseCorrectLogin() throws IOException {

        String password;
        do {
            this.login = in.readLine();
            password = in.readLine();
            if(clientIsInGame())
            {
                out.println("ingame");
                clients.remove(this);
            }
            else if(loginIsCorrect(this.login, password))
            {
                out.println("true");
            }
            else
            {
                out.println("false");
                clients.remove(this);
            }
        }
        while (!loginIsCorrect(this.login, password));
    }

    private boolean clientIsInGame()
    {
        Iterator<ClientHandler> iter = clients.iterator();

        for(int i=0; i<clients.size()-1; i++)
        {
            if(iter.next().login.equals(this.login))
            {
                return true;
            }
        }
        return false;
    }

    private boolean loginIsCorrect(String username, String password)
    {
        try
        {
            File file = new File("src/main/resources/data.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                if(data[0].equals(username)&&data[1].equals(password))
                {
                    return true;
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            clients.remove(this);
        }
        return false;
    }

    private int chooseCorrectRoom() throws IOException
    {
        String roomNumber;
        do
        {
            roomNumber = in.readLine();
            if(roomIsCorrect(roomNumber))
            {
                out.println("true");
                return Integer.parseInt(roomNumber);
            }
            else
            {
                out.println("false");
            }
        }
        while (!roomIsCorrect(roomNumber));
        return -1;
    }

    private boolean roomIsCorrect(String roomNumber)
    {
        int nr = Integer.parseInt(roomNumber);
        for(int i=0 ; i<4 ; i++)
        {
            if(rooms[nr-1].logins[i]==null)
            {
                rooms[nr-1].logins[i] = login;
                out.println(i);
                return true;
            }
        }
        out.println(4);
        return false;
    }
}
