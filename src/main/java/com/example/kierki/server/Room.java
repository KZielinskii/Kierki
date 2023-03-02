package com.example.kierki.server;

/**
 * Objekt Pokoju w którym grają Klienci.
 */
public class Room implements Runnable
{
    /**
     * Numer pokoju.
     */
    private int nrRoom;
    /**
     * Lista graczy w tym pokoju.
     */
    public String[] login;

    /**
     * Tworzy objekt Pokoju do któregą będą dołączać gracze.
     * @param nr Ustawia numer pokoju.
     */
    Room(int nr)
    {
        this.nrRoom = nr;
        login = new String[4];
        for(int i=0; i < 4; i++)
        {
            login[i] = null;
        }
    }

    @Override
    public void run()
    {

    }
}
