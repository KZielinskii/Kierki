package com.example.kierki.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Startuje aplikacje Serwera.
 */
public class StartServer
{
    /**
     * Lista klientów połączonych z serwerem.
     */
    public static ArrayList<ClientHandler> serverClients = new ArrayList<>();
    /**
     * Lista pokoi na serwerze.
     */
    public static RoomHandler[] rooms;

    /**
     * Startuje serwer dzięki któremu Klienci będą mogli się ze sobą komunikować.
     * @param args Argumenty funkcji main. (Nie są wykorzystywane w rozgrywce)
     */
    public static void main(String[] args)
    {
        try
        {
            ServerSocket s = new ServerSocket(1234);
            createGameHandler();
            createRooms();
            while(true)
            {
                createNewClient(s);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void createRooms()
    {
        rooms = new RoomHandler[10];
        for(int i=0; i<10; i++)
        {
            rooms[i] = new RoomHandler(i+1);
            Thread t = new Thread(rooms[i]);
            t.start();
        }
    }

    private static void createNewClient(ServerSocket s) throws IOException
    {
        Socket client = s.accept();
        ClientHandler clientThread = new ClientHandler(client, serverClients);
        serverClients.add(clientThread);
        Thread t = new Thread(clientThread);
        t.start();
    }

    private static void createGameHandler()
    {
        GameHandler gameHandler = new GameHandler();
        Thread gameHandlerThread = new Thread(gameHandler);
        gameHandlerThread.start();
    }
}
