package com.example.kierki.server;

import java.util.Scanner;

import static com.example.kierki.server.StartServer.rooms;

/**
 * Służy do wglądu w aktualny stan pokoi na serwerze.
 */
public class GameHandler implements Runnable
{
    /**
     * Tworzy obiekt GameHandler do wyświetlania listy graczy w pokojach
     */
    public GameHandler() {}

    /**
     * Wyświetla liste graczy w danym pokoju.
     */
    @Override
    public void run()
    {
        displayInfo();
    }

    private void displayInfo()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Podaj nr pokoju który chcesz zobaczyć: ");
            int nr = scanner.nextInt();
            if(nr>=0&&nr<=8) displayUsersInRoom(nr);
            if(nr>=10&&nr<=18) exitUsersInRoom(nr);
        }
    }

    private void exitUsersInRoom(int nr)
    {
        nr-=10;
        rooms[nr].exit=true;
    }

    private void displayUsersInRoom(int nr)
    {
        for(int i=0; i<4; i++)
        {
            System.out.println("***********************");
            System.out.println(rooms[nr].logins[i]);
            System.out.println("***********************");
        }
    }

}
