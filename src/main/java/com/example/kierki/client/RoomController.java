package com.example.kierki.client;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * Obsługuje klienta podczas rogrywki.
 */
public class RoomController implements Initializable
{
    private PrintWriter out;
    private BufferedReader in;
    private int[] myCarts;
    private Button[] buttons;
    private ImageView[] images;
    private ImageView[] imagesOnTable;
    private int myNumber;
    private String[] logins;

    @FXML Label label;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label wlabel;
    @FXML Label wlabel1;
    @FXML Label wlabel2;
    @FXML Label wlabel3;
    @FXML Button button;
    @FXML Button button1;
    @FXML Button button2;
    @FXML Button button3;
    @FXML Button button4;
    @FXML Button button5;
    @FXML Button button6;
    @FXML Button button7;
    @FXML Button button8;
    @FXML Button button9;
    @FXML Button button10;
    @FXML Button button11;
    @FXML Button button12;


    @FXML ImageView image;
    @FXML ImageView image1;
    @FXML ImageView image2;
    @FXML ImageView image3;
    @FXML ImageView image4;
    @FXML ImageView image5;
    @FXML ImageView image6;
    @FXML ImageView image7;
    @FXML ImageView image8;
    @FXML ImageView image9;
    @FXML ImageView image10;
    @FXML ImageView image11;
    @FXML ImageView image12;
    @FXML ImageView user; //TY
    @FXML ImageView user1; //PO LEWEJ
    @FXML ImageView user2; //U GÓRY
    @FXML ImageView user3; //PO PRAWEJ

    @FXML Pane pane;
    @FXML Pane pane1;
    @FXML Pane pane2;
    @FXML Pane pane3;

    @FXML Label pkt;
    @FXML Label pkt1;
    @FXML Label pkt2;
    @FXML Label pkt3;

    private int round;
    private int color;
    private boolean yourTurn;

    @FXML Label winLabel;
    @FXML Pane winPane;

    /**
     * Przygotowywuje pokój na rozpoczącie rozgrywki.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            yourTurn=false;
            color=0;
            round=0;
            prepareRoom();
            setLogins();

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    @FXML Button readyButton;

    /**
     * Odpowiada za całą logikę gry. Po naciśnięciu gra Startuje.
     */
    @FXML
    private void onClickRefresh()
    {
        readyButton.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            if(!yourTurn || noCards())
            {
                refreshData();
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private boolean noCards()
    {
        for(int i=0; i<13; i++)
        {
            if(myCarts[i] != -1)return false;
        }
        return true;
    }

    private void refreshData()
    {
        try
        {
            String str = in.readLine();
            System.out.println(str);
            if(str.equals("Your turn"))
            {
                yourTurn=true;
                color = Integer.parseInt(in.readLine());
            }
            if(str.equals("Card"))
            {
                int card = Integer.parseInt(in.readLine());
                int player = Integer.parseInt(in.readLine());
                File file = new File("src/main/resources/graphic/"+card+".png");
                Image img = new Image(file.toURI().toString());
                imagesOnTable[player].setImage(img);
                if(player<3)changeColorPane(player+1);
                else changeColorPane(0);
            }
            if(str.equals("Next lewa"))
            {
                pkt.setText(in.readLine());
                pkt1.setText(in.readLine());
                pkt2.setText(in.readLine());
                pkt3.setText(in.readLine());
                imagesOnTable[0].setImage(null);
                imagesOnTable[1].setImage(null);
                imagesOnTable[2].setImage(null);
                imagesOnTable[3].setImage(null);
                changeColorPane(Integer.parseInt(in.readLine()));
            }
            if(str.equals("Next LEVEL"))
            {
                try
                {
                    pkt.setText(in.readLine());
                    pkt1.setText(in.readLine());
                    pkt2.setText(in.readLine());
                    pkt3.setText(in.readLine());
                    imagesOnTable[0].setImage(null);
                    imagesOnTable[1].setImage(null);
                    imagesOnTable[2].setImage(null);
                    imagesOnTable[3].setImage(null);
                    round++;
                    downloadrandCards();
                    showCarts();
                    updateWhoStart();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
            if(str.equals("EXIT"))
            {
                winLabel.setText(in.readLine());
                winPane.setVisible(true);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateWhoStart()
    {
        whoStart++;
        if(whoStart==4)whoStart=0;
        changeColorPane(whoStart);
    }

    private void changeColorPane(int player)
    {
        pane.setStyle("-fx-background-color: #005500");
        pane1.setStyle("-fx-background-color: #005500");
        pane2.setStyle("-fx-background-color: #005500");
        pane3.setStyle("-fx-background-color: #005500");
        if(myNumber==0)
        {
            if(player==0)
            {
                pane.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==1)
            {
                pane1.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==2)
            {
                pane2.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==3)
            {
                pane3.setStyle("-fx-background-color: #AA3333");
            }
        }
        if(myNumber==1)
        {
            if(player==1)
            {
                pane.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==2)
            {
                pane1.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==3)
            {
                pane2.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==0)
            {
                pane3.setStyle("-fx-background-color: #AA3333");
            }
        }
        if(myNumber==2)
        {
            if(player==2)
            {
                pane.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==3)
            {
                pane1.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==0)
            {
                pane2.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==1)
            {
                pane3.setStyle("-fx-background-color: #AA3333");
            }
        }
        if(myNumber==3)
        {
            if(player==3)
            {
                pane.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==0)
            {
                pane1.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==1)
            {
                pane2.setStyle("-fx-background-color: #AA3333");
            }
            else if(player==2)
            {
                pane3.setStyle("-fx-background-color: #AA3333");
            }
        }
    }

    /**
     * Sprawdza czy dana karta jest poprawna jeśli tak przenosi ją na stół.
     * @param event Karty w dolnej części ekranu.
     */
    @FXML
    public void onClickCard(ActionEvent event)
    {
        try
        {
            sendClickedButton(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendClickedButton(ActionEvent event) throws IOException
    {
        checkIfYouHaveColor();
        for(int i=0; i<13; i++)
        {
            if(event.getSource().equals(buttons[i])&&myCarts[i]!=-1&&yourTurn)
            {
                if(color==0||(color==1&&myCarts[i]>=1&&myCarts[i]<=13)||(color==2&&myCarts[i]>=14&&myCarts[i]<=26)||(color==3&&myCarts[i]>=27&&myCarts[i]<=39)||(color==4&&myCarts[i]>=40&&myCarts[i]<=52)) //0 - wszystkie kolory
                {
                    out.println(myCarts[i]);
                    myCarts[i] = -1;
                    imagesOnTable[myNumber].setImage(images[i].getImage());
                    images[i].setImage(null);
                    yourTurn=false;
                    if(myNumber<3)changeColorPane(myNumber+1);
                    else changeColorPane(0);
                }
            }
        }
    }

    private void checkIfYouHaveColor()
    {
        boolean have=false;
        for(int i=0; i<13; i++)
        {
            if(color==1 && myCarts[i]>=1 && myCarts[i]<=13)
            {
                have=true;
            }
            if(color==2 && myCarts[i]>=14 && myCarts[i]<=26)
            {
                have=true;
            }
            if(color==3 && myCarts[i]>=27 && myCarts[i]<=39)
            {
                have=true;
            }
            if(color==4 && myCarts[i]>=40 && myCarts[i]<=52)
            {
                have=true;
            }
        }
        if(!have)color=0;
    }

    private void setLogins()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            try {
                downloadLogins();
                setLoginInGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(0);
        timeline.play();
    }
    private void setLoginInGame()
    {
        label.setText(logins[myNumber]);
        wlabel.setText(logins[myNumber]);
        if(myNumber<3)
        {
            label1.setText(logins[myNumber+1]);
            wlabel1.setText(logins[myNumber+1]);
        }
        else
        {
            label1.setText(logins[myNumber-3]);
            wlabel1.setText(logins[myNumber-3]);
        }
        if(myNumber<2)
        {
            label2.setText(logins[myNumber+2]);
            wlabel2.setText(logins[myNumber+2]);
        }
        else
        {
            label2.setText(logins[myNumber-2]);
            wlabel2.setText(logins[myNumber-2]);
        }
        if(myNumber<1)
        {
            label3.setText(logins[myNumber+3]);
            wlabel3.setText(logins[myNumber+3]);
        }
        else
        {
            label3.setText(logins[myNumber-1]);
            wlabel3.setText(logins[myNumber-1]);
        }

    }

    private void downloadLogins() throws IOException
    {
        for(int i=0; i<4; i++)
        {
            logins[i] = in.readLine();
        }
    }
    private void prepareRoom() throws IOException {
        this.out = StartApplication.out;
        this.in = StartApplication.in;
        this.myCarts = new int[13];
        this.logins = new String[4];
        this.logins[0]=null;
        this.winPane.setVisible(false);
        initButtons();
        initImages();
        out.println("START");
        in.readLine();
        downloadrandCards();
        showCarts();
        prepareGame();
        initImagesOnTable();
    }

    private void initImagesOnTable()
    {
        if(myNumber==0)
        {
            imagesOnTable[0] = user;
            imagesOnTable[1] = user1;
            imagesOnTable[2] = user3;
            imagesOnTable[3] = user2;
        }
        if(myNumber==1)
        {
            imagesOnTable[0] = user2;
            imagesOnTable[1] = user;
            imagesOnTable[2] = user1;
            imagesOnTable[3] = user3;
        }
        if(myNumber==2)
        {
            imagesOnTable[0] = user3;
            imagesOnTable[1] = user2;
            imagesOnTable[2] = user;
            imagesOnTable[3] = user1;
        }
        if(myNumber==3)
        {
            imagesOnTable[0] = user1;
            imagesOnTable[1] = user3;
            imagesOnTable[2] = user2;
            imagesOnTable[3] = user;
        }

    }

    private void initImages()
    {
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2)");
        pane1.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2)");
        pane2.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2)");
        pane3.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2)");
        this.images = new ImageView[13];
        this.imagesOnTable = new ImageView[4];
        images[0]= image;
        images[1]= image1;
        images[2]= image2;
        images[3]= image3;
        images[4]= image4;
        images[5]= image5;
        images[6]= image6;
        images[7]= image7;
        images[8]= image8;
        images[9]= image9;
        images[10]= image10;
        images[11]= image11;
        images[12]= image12;
    }

    private void initButtons()
    {
        this.buttons = new Button[13];
        buttons[0] = button;
        buttons[1] = button1;
        buttons[2] = button2;
        buttons[3] = button3;
        buttons[4] = button4;
        buttons[5] = button5;
        buttons[6] = button6;
        buttons[7] = button7;
        buttons[8] = button8;
        buttons[9] = button9;
        buttons[10] = button10;
        buttons[11] = button11;
        buttons[12] = button12;
    }

    private int whoStart;
    private void prepareGame() throws IOException
    {
        myNumber = Integer.parseInt(in.readLine());
        whoStart = Integer.parseInt(in.readLine());
        if(whoStart == myNumber)
        {
            pane.setStyle("-fx-background-color: #AA3333");
        }
        else if(myNumber-whoStart==-1 || myNumber-whoStart==3)
        {
            pane1.setStyle("-fx-background-color: #AA3333");
        }
        else if((myNumber-whoStart==-2) || (myNumber-whoStart==2))
        {
            pane2.setStyle("-fx-background-color: #AA3333");
        }
        else
        {
            pane3.setStyle("-fx-background-color: #AA3333");
        }
    }

    private void showCarts()
    {
        for(int i=0; i<13; i++)
        {
            File file = new File("src/main/resources/graphic/"+(myCarts[i])+".png");
            Image img = new Image(file.toURI().toString());
            images[i].setImage(img);
        }
    }

    private void downloadrandCards() throws IOException
    {
        for(int i=0; i<13; i++)
        {
            myCarts[i] = Integer.parseInt(in.readLine());
        }
    }

}
