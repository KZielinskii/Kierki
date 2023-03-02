package com.example.kierki.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static com.example.kierki.server.StartServer.rooms;
import static com.example.kierki.server.StartServer.serverClients;
import static java.lang.Thread.sleep;

/**
 * Służy do komunikacji z Klientem w danym pokoju.
 */
public class RoomHandler implements Runnable
{
    private int nrRoom;
    private ClientHandler clientHandler;
    /**
     * Lista z loginami klientów.
     */
    public String[] logins;
    private String login;
    private PrintWriter out;
    private BufferedReader in;
    private ClientHandler[] clients;
    private int[][] carts;
    private boolean nextLevel;
    public boolean exit;

    /**
     * Kopiuje ClientHandler i dodaje numer pokoju do komunikacji z klientem.
     * @param nr Numer pokoju w którym jest klient.
     */
    RoomHandler(int nr)
    {
        initLogins(nr);
        clients = new ClientHandler[4];
    }

    private void initLogins(int nr)
    {
        nextLevel=true;
        this.exit=false;
        this.nrRoom = nr;
        logins = new String[4];
        for(int i=0; i < 4; i++)
        {
            logins[i] = null;
        }
    }

    /**
     * Tworzy nową grę.
     */
    @Override
    public void run()
    {
        if(nextLevel)
        {
            carts = new int[4][13];
            shufflingCards();
            nextLevel=false;
        }
    }

    private void shufflingCards()
    {
        int[] array = new int[52];
        for(int i=0; i<52; i++)array[i] = i+1;

        for(int i=0; i<100; i++)
        {
            int index1 = new Random().nextInt(52);
            int index2 = new Random().nextInt(52);
            int tmp = array[index1];
            array[index1] = array[index2];
            array[index2] = tmp;
        }
        int k=0;
        for(int j=0; j<13; j++)
        {
            for(int i=0; i<4; i++)
            {
                carts[i][j] = array[k];
                k++;
            }
        }
    }

    /**
     * Kopiuje ClientHandler i dodaje numer pokoju do komunikacji z klientem.
     * @param clientHandler Parametry do komunikacji z klientem.
     */
    public void startGame(ClientHandler clientHandler)
    {
        try
        {
            initHandler(clientHandler);
            sendCards();
            checkAndChangeLevel();
            if(login.equals(logins[0]))randFirstPlayer();
            sendNumberOfPlayerAndWhoStart();
            sendLogins();
            if(login.equals(logins[3]))
            {
                for(int i=0; i<4; i++)pkt[i]=0;
                round1();
                round2();
                round3();
                round4();
                round5();
                round6();
                round7();
            }
        }
        catch (IOException e)
        {
            System.out.println("Client: [" + this.login + "] rozłączył się!");
            deleteUserFromRoom();
            serverClients.remove(this.clientHandler);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] lewa = new int[4];
    private int[] pkt = new int[4];
    boolean[] inGame = new boolean[4];
    private int whoTakeLewa;
    private int whoStart;
    private void round1() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa1(0,1,2,3);
            else if(whoTakeLewa==1) nextLewa1(1,2,3,0);
            else if(whoTakeLewa==2) nextLewa1(2,3,0,1);
            else if(whoTakeLewa==3) nextLewa1(3,0,1,2);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void setStartPlayer()
    {
        whoStart++;
        if(whoStart==4)whoStart=0;
        whoTakeLewa=whoStart;
    }

    private void nextLewa1(int i1, int i2, int i3, int i4) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt1(nextColor);
    }

    private void updatePkt1(int color)
    {
        checkInGame(color);
        int index = 0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
        }
        pkt[index]-=20;
        whoTakeLewa=index;
    }

    private void round2() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa2(0,1,2,3);
            else if(whoTakeLewa==1) nextLewa2(1,2,3,0);
            else if(whoTakeLewa==2) nextLewa2(2,3,0,1);
            else if(whoTakeLewa==3) nextLewa2(3,0,1,2);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa2(int i1, int i2, int i3, int i4) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt2(nextColor);
        sendPkt();
    }

    private void updatePkt2(int color)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
            if(lewa[i]>=27 && lewa[i]<=39)inRoundPkt+=20;
        }
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }

    private void round3() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa3(0,1,2,3);
            else if(whoTakeLewa==1) nextLewa3(1,2,3,0);
            else if(whoTakeLewa==2) nextLewa3(2,3,0,1);
            else if(whoTakeLewa==3) nextLewa3(3,0,1,2);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa3(int i1, int i2, int i3, int i4) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt3(nextColor);
        sendPkt();
    }

    private void updatePkt3(int color)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
            if(lewa[i]==11 || lewa[i]==24 || lewa[i]==37 || lewa[i]==50)inRoundPkt+=60;
        }
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }

    private void round4() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa4(0,1,2,3);
            else if(whoTakeLewa==1) nextLewa4(1,2,3,0);
            else if(whoTakeLewa==2) nextLewa4(2,3,0,1);
            else if(whoTakeLewa==3) nextLewa4(3,0,1,2);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa4(int i1, int i2, int i3, int i4) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt4(nextColor);
        sendPkt();
    }

    private void updatePkt4(int color)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
            if(lewa[i]==10 || lewa[i]==12 || lewa[i]==23 || lewa[i]==25 || lewa[i]==36 || lewa[i]==38 || lewa[i]==49 || lewa[i]==51)inRoundPkt+=30;
        }
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }

    private void round5() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa5(0,1,2,3);
            else if(whoTakeLewa==1) nextLewa5(1,2,3,0);
            else if(whoTakeLewa==2) nextLewa5(2,3,0,1);
            else if(whoTakeLewa==3) nextLewa5(3,0,1,2);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa5(int i1, int i2, int i3, int i4) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt5(nextColor);
        sendPkt();
    }

    private void updatePkt5(int color)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
            if(lewa[i]==38)inRoundPkt+=150;
        }
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }
    private void round6() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa6(0,1,2,3,i);
            else if(whoTakeLewa==1) nextLewa6(1,2,3,0,i);
            else if(whoTakeLewa==2) nextLewa6(2,3,0,1,i);
            else if(whoTakeLewa==3) nextLewa6(3,0,1,2,i);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa6(int i1, int i2, int i3, int i4, int nrLewa) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt6(nextColor,nrLewa);
        sendPkt();
    }

    private void updatePkt6(int color, int nrLewa)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=0;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
        }
        if(nrLewa==6 || nrLewa==11)inRoundPkt+=75;
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }
    private void round7() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            if(whoTakeLewa==0) nextLewa7(0,1,2,3,i);
            else if(whoTakeLewa==1) nextLewa7(1,2,3,0,i);
            else if(whoTakeLewa==2) nextLewa7(2,3,0,1,i);
            else if(whoTakeLewa==3) nextLewa7(3,0,1,2,i);
            if(i<12) sendPkt(); else sendEndPkt();
        }
        shufflingCards();
        sendCardsToAll();
        setStartPlayer();
    }

    private void nextLewa7(int i1, int i2, int i3, int i4, int nrLewa) throws IOException
    {
        int nextColor = sendTurn(i1, 0);
        sendTurn(i2, nextColor);
        sendTurn(i3, nextColor);
        sendTurn(i4, nextColor);
        updatePkt7(nextColor,nrLewa);
        sendPkt();
    }

    private void updatePkt7(int color, int nrLewa)
    {
        checkInGame(color);
        int index = 0;
        int inRoundPkt=20;
        for(int i=0; i<4; i++)
        {
            if(lewa[index]<lewa[i] && inGame[i])index=i;
            if(lewa[i]>=27 && lewa[i]<=39)inRoundPkt+=20;
            if(nrLewa==6 || nrLewa==11)inRoundPkt+=75;
            if(lewa[i]==11 || lewa[i]==24 || lewa[i]==37 || lewa[i]==50)inRoundPkt+=60;
            if(lewa[i]==10 || lewa[i]==12 || lewa[i]==23 || lewa[i]==25 || lewa[i]==36 || lewa[i]==38 || lewa[i]==49 || lewa[i]==51)inRoundPkt+=30;
            if(lewa[i]==38)inRoundPkt+=150;
        }
        if(nrLewa==6 || nrLewa==11)inRoundPkt+=75;
        pkt[index]-=inRoundPkt;
        whoTakeLewa=index;
    }

    private void randFirstPlayer()
    {
        Random rand = new Random();
        whoTakeLewa = rand.nextInt(4);
        whoStart = whoTakeLewa;
    }

    private void checkInGame(int color)
    {
        for(int i=0; i<4; i++)
        {
            if(color==1 && lewa[i]>=1 && lewa[i]<=13)
            {
                inGame[i]=true;
            }
            else if(color==2 && lewa[i]>=14 && lewa[i]<=26)
            {
                inGame[i]=true;
            }
            else if(color==3 && lewa[i]>=27 && lewa[i]<=39)
            {
                inGame[i]=true;
            }
            else if(color==4 && lewa[i]>=40 && lewa[i]<=52)
            {
                inGame[i]=true;
            }
            else
            {
                inGame[i]=false;
            }
        }
    }

    private void sendEndPkt()
    {
        for(int i=0; i<4; i++)
        {
            clients[i].out.println("Next LEVEL");
            if(i==0)
            {
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
            }
            if(i==1)
            {
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
            }
            if(i==2)
            {
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
            }
            if(i==3)
            {
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
            }
        }
    }
    private void sendPkt()
    {
        for(int i=0; i<4; i++)
        {
            clients[i].out.println("Next lewa");
            if(i==0)
            {
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
            }
            if(i==1)
            {
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
            }
            if(i==2)
            {
                clients[i].out.println(pkt[2]);
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
            }
            if(i==3)
            {
                clients[i].out.println(pkt[3]);
                clients[i].out.println(pkt[0]);
                clients[i].out.println(pkt[1]);
                clients[i].out.println(pkt[2]);
            }
            clients[i].out.println(whoTakeLewa);
        }
    }

    private int sendTurn(int player, int color) throws IOException
    {
        if(exit)
        {
            int win=0;
            for(int i=0; i<4; i++)
            {
                if(pkt[win]<pkt[i])win=i;
                clients[i].out.println("EXIT");
            }
            for(int i=0; i<4; i++)
            {
                clients[i].out.println(logins[win]);
            }


        }
        clients[player].out.println("Your turn");
        clients[player].out.println(color);
        String card = checkStreamIn(player);
        int intCart = Integer.parseInt(card);
        int nextColor = checkColorCard(intCart);
        lewa[player] = intCart;
        for(int i=0; i<4; i++)
        {
            if(i!=player)
            {
                clients[i].out.println("Card");
                clients[i].out.println(card);
                clients[i].out.println(player);
            }
        }
        return nextColor;
    }

    private int checkColorCard(int card)
    {
        if(card>=1&&card<=13)
        {
            return 1;
        }
        if(card>=14&&card<=26)
        {
            return 2;
        }
        if(card>=27&&card<=39)
        {
            return 3;
        }
        if(card>=40&&card<=52)
        {
            return 4;
        }
        return -1;
    }

    private String checkStreamIn(int i) throws IOException  //TODO wyszcyść cały pokój i wywal wszystkich do corridora
    {
        String str = clients[i].in.readLine();
        if(str.equals("Exit"))
        {
            rooms[nrRoom-1].logins[Integer.parseInt(in.readLine())]=null;
            clientHandler.run();
        }
        return str;
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

    private void sendLogins() throws InterruptedException
    {
        if(login.equals(logins[3]))
        {
            for(int i=0; i<4; i++)
            {
                for(int j=0; j<4; j++)
                {
                    clients[j].out.println(logins[i]);
                }
            }
        }
    }

    private void sendNumberOfPlayerAndWhoStart()
    {
        for(int i=0; i<4; i++)
        {
            if(login.equals(logins[i]))
            {
                out.println(i);
                out.println(whoTakeLewa);
            }
        }
    }

    private void checkAndChangeLevel()
    {
        if(nextLevel)
        {
            if(login.equals(logins[0]))
            {
                shufflingCards();
            }
            nextLevel=false;
            sendCards();
        }
    }

    private void sendCards()
    {
        for(int i=0; i<4; i++)
        {
            if(login.equals(logins[i]))
            {
                for(int j=0; j<13; j++)
                {
                    out.println(carts[i][j]);
                }
            }
        }
    }

    private void sendCardsToAll()
    {
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<13; j++)
            {
                clients[i].out.println(carts[i][j]);
            }
        }
    }

    private void initHandler(ClientHandler clientHandler)
    {
        this.clientHandler = clientHandler;
        login = clientHandler.login;
        out = clientHandler.out;
        in = clientHandler.in;
        for(int i=0; i<4; i++)
        {
            if(clients[i]==null)
            {
                clients[i] = clientHandler;
                break;
            }
        }
    }
}
