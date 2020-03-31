/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Font;
import java.awt.*;
//import javafx.scene.text.Font;
//import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sun.audio.AudioStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Hunter
 */

public class FinalMenu extends Application{
    
    int length, width;
    Stage Window;
    Scene scene1, scene2, optionScene, scoreScene, menuScene, musicScene, playScene, createScene, startScene, generateScene;
    
    static AudioStream MenuHolder;
    static Clip officialSong, officialSong1AS, officialSong2AS, officialSong3AS, officialSong4AS, officialSong5AS, officialSong6AS;
   
    public static void main(String args[]){
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AudioPlayer MGP = AudioPlayer.player;
        
        final AudioStream Song1, Song2, Song3;
        
        ArrayList<AudioStream> musicHolder = new ArrayList<AudioStream>();
        ArrayList<AudioInputStream> musicHoilderOfficial = new ArrayList<AudioInputStream>(); 
        Song1 = new AudioStream(new FileInputStream("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/melee.wav"));//enter the sound directory and name here
        Song2 = new AudioStream(new FileInputStream("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/meee.wav"));
        Song3 = new AudioStream(new FileInputStream("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/flat.wav"));
        //AudioData Song1Data = Song1.getData();
        //AudioData Song2Data = Song2.getData();
        //AudioData Song3Data = Song3.getData();
        
        Song1.mark(Song1.getLength());
        Song2.mark(Song2.getLength());
        Song3.mark(Song3.getLength());
        
        musicHolder.add(Song1);
        musicHolder.add(Song2);
        musicHolder.add(Song3);
        
        //MenuHolder = musicHolder.get(0);
        //AudioPlayer.player.start(MenuHolder);
        
        AudioInputStream officialSong1 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/melee.wav"));
        AudioInputStream officialSong2 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/meee.wav"));
        AudioInputStream officialSong3 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/flat.wav"));
        AudioInputStream officialSong4 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Menu1.wav"));
        AudioInputStream officialSong5 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Boss.wav"));
        AudioInputStream officialSong6 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Attack.wav"));
        
        officialSong = AudioSystem.getClip();
        officialSong1AS = AudioSystem.getClip();
        officialSong1AS.open(officialSong1);
        officialSong2AS = AudioSystem.getClip();
        officialSong2AS.open(officialSong2);
        officialSong3AS = AudioSystem.getClip();
        officialSong3AS.open(officialSong3);
        officialSong4AS = AudioSystem.getClip();
        officialSong4AS.open(officialSong4);
        officialSong5AS = AudioSystem.getClip();
        officialSong5AS.open(officialSong5);
        officialSong6AS = AudioSystem.getClip();
        officialSong6AS.open(officialSong6);
        
        //Initial Song
        officialSong = officialSong1AS;
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
        //Thread.sleep(10000); 
        
        Window = primaryStage; 
        primaryStage.setResizable(false);
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            Platform.exit();
            System.exit(0);
        }
        });
        
        File file = new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/nonogram_promo_bw.jpg"); 
        File file2 = new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/background.gif");
        
        Image image = new Image(new FileInputStream(file));
        
        Image img = new Image(new FileInputStream(file));
        Image NonogramNanic = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdUbUZ1YVdNLC4wAA,,/most-wazted.regular.png");
        ImageView NonogramNanicIV = new ImageView(NonogramNanic);
        //NonogramNanicIV.setScaleX(.7);
        //NonogramNanicIV.setScaleY(.7);
        NonogramNanicIV.setX(86);
        NonogramNanicIV.setY(450);
        
        Image HunterNoey = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5Rbms2SUVoMWJuUmxjaUJPYjJWNS4wAAAA/most-wazted.regular.png");
        ImageView HunterNoeyIV = new ImageView(HunterNoey);
        HunterNoeyIV.setScaleX(.4);
        HunterNoeyIV.setScaleY(.4);
        HunterNoeyIV.setX(86);
        HunterNoeyIV.setY(500);
        
        Image Start = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5jM1JoY25RLC4wAAAAAAAAAA,,/most-wazted.regular.png");
        ImageView StartIV = new ImageView(Start);
        StartIV.setScaleX(.4);
        StartIV.setScaleY(.4);
        StartIV.setX(200);
        StartIV.setY(550);
        
        Image CreateNono = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RM0psWVhSbElHRWdUbTl1YjJkeVlXMCwuMA,,/most-wazted.regular.png");
        ImageView CreateNonoIV = new ImageView(CreateNono);
        CreateNonoIV.setScaleX(.7);
        CreateNonoIV.setScaleY(.7);
        
        Image CreateHeader = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLmZmZmZmZi5SMlZ1WlhKaGRHVWdlVzkxY2lCT2IyNXZaM0poYlNFLC4wAAAA/most-wazted.regular.png");
        ImageView CreateHeaderIV = new ImageView(CreateHeader);
        CreateHeaderIV.setScaleX(.7);
        CreateHeaderIV.setScaleY(.7);
        CreateHeaderIV.setX(200);
        CreateHeaderIV.setY(0);
        
        Image SolveNono = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5VMjlzZG1VZ1lTQk9iMjV2WjNKaGJRLCwuMAAA/most-wazted.regular.png");
        ImageView SolveNonoIV = new ImageView(SolveNono);
        SolveNonoIV.setScaleX(.7);
        SolveNonoIV.setScaleY(.7);
        
        Image Options = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UM0IwYVc5dWN3LCwuMAAA/most-wazted.regular.png");
        ImageView OptionsIV = new ImageView(Options);
        OptionsIV.setScaleX(.7);
        OptionsIV.setScaleY(.7);
        
        Image Back = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RbUZqYXlCMGJ5QlRkR0Z5ZENCVFkzSmxaVzQsLjAAAAAA/most-wazted.regular.png");
        ImageView BackIV = new ImageView(Back);
        BackIV.setScaleX(.7);
        BackIV.setScaleY(.7);
        
        Image ChangeMusic = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RMmhoYm1kbElFMTFjMmxqT2lBLC4wAAAA/most-wazted.regular.png");
        ImageView ChangeMusicIV = new ImageView(ChangeMusic);
        ChangeMusicIV.setScaleX(.4);
        ChangeMusicIV.setScaleY(.4);
        ChangeMusicIV.setX(-50);
        ChangeMusicIV.setY(315);
        
        Image Menu1 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBeC4wAAAA/most-wazted.regular.png");
        ImageView Menu1IV = new ImageView(Menu1);
        Menu1IV.setScaleX(.4);
        Menu1IV.setScaleY(.4);
        
         
        Image Menu2 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBeS4wAAAAAAAA/most-wazted.regular.png");
        ImageView Menu2IV = new ImageView(Menu2);
        Menu2IV.setScaleX(.4);
        Menu2IV.setScaleY(.4);
        
        Image Menu3 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5SbXhoZENCYWIyNWwuMAAAAAAA/most-wazted.regular.png");
        ImageView Menu3IV = new ImageView(Menu3);
        Menu3IV.setScaleX(.4);
        Menu3IV.setScaleY(.4);
        
        Image Menu4 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBei4w/most-wazted.regular.png");
        ImageView Menu4IV = new ImageView(Menu4);
        Menu4IV.setScaleX(.4);
        Menu4IV.setScaleY(.4);
        
        Image Menu5 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5ZbTl6Y3lFLC4wAAAA/most-wazted.regular.png");
        ImageView Menu5IV = new ImageView(Menu5);
        Menu5IV.setScaleX(.4);
        Menu5IV.setScaleY(.4);
        
        Image Menu6 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5ZWFIwWVdOcklRLCwuMAA,/most-wazted.regular.png");
        ImageView Menu6IV = new ImageView(Menu6);
        Menu6IV.setScaleX(.4);
        Menu6IV.setScaleY(.4);
        
        Image NonoName = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdUbUZ0WlEsLC4w/most-wazted.regular.png");
        ImageView NonoNameIV = new ImageView(NonoName);
        NonoNameIV.setScaleX(.5);
        NonoNameIV.setScaleY(.5);
        NonoNameIV.setX(-70);
        NonoNameIV.setY(50);
        
        Image NonoData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdSR0YwWVEsLC4w/most-wazted.regular.png");
        ImageView NonoDataIV = new ImageView(NonoData);
        NonoDataIV.setScaleX(.5);
        NonoDataIV.setScaleY(.5);
        NonoDataIV.setX(220);
        NonoDataIV.setY(50);
        
        Image LengthData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UR1Z1WjNSby4w/most-wazted.regular.png");
        ImageView LengthDataIV = new ImageView(LengthData);
        LengthDataIV.setScaleX(.4);
        LengthDataIV.setScaleY(.4);
        LengthDataIV.setX(240);
        LengthDataIV.setY(120);
        
        Image WidthData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5WMmxrZEdnLC4w/most-wazted.regular.png");
        ImageView WidthDataIV = new ImageView(WidthData);
        WidthDataIV.setScaleX(.4);
        WidthDataIV.setScaleY(.4);
        WidthDataIV.setX(240);
        WidthDataIV.setY(150);
        
        Image HardCoreData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5TR0Z5WkdOdlpHVWdUVzlrWlQ4LC4w/most-wazted.regular.png");
        ImageView HardCoreDataIV = new ImageView(HardCoreData);
        HardCoreDataIV.setScaleX(.5);
        HardCoreDataIV.setScaleY(.5);
        HardCoreDataIV.setX(-70);
        HardCoreDataIV.setY(250);
        
        Image HardCoreBioData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5MVmRvWlc0Z2FHRnlaR052Y21VZ2JXOWtaU0JwY3lCbGJtRmliR1ZrSUhsdmRTQnZibXg1SUdkbGRDQXpJR3hwZG1WeklTQSwuMAAAAAAAAAAA/most-wazted.regular.png");
        ImageView HardCoreBioDataIV = new ImageView(HardCoreBioData);
        HardCoreBioDataIV.setScaleX(.3);
        HardCoreBioDataIV.setScaleY(.3);
        HardCoreBioDataIV.setX(-500);
        HardCoreBioDataIV.setY(290);
        
        Image disabled = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5MVU4xY25KbGJuUnNlU0JrYVhOaFlteGxaQ0JtYjNJZ1FXeHdhR0VnUW5WcGJHUSwuMAAAAAAAAAAA/most-wazted.regular.png");
        ImageView disabledIV = new ImageView(disabled);
        disabledIV.setScaleX(.3);
        disabledIV.setScaleY(.3);
        disabledIV.setX(-310);
        disabledIV.setY(320);
        
        
        BackgroundImage bgImg;
        bgImg = new BackgroundImage(img, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        
        Background bg = new Background(bgImg);
        
        Image MainBackground = new Image(new FileInputStream(file2));
        
        BackgroundImage MbgImg;
        MbgImg = new BackgroundImage(MainBackground, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        
        Background MenuBackground = new Background(MbgImg);
        
        Button button1 = new Button("", StartIV);
        button1.setLayoutX(200);
        button1.setLayoutY(540);
        Font h = new Font("TimesRoman", Font.PLAIN, 18);
        //button1.setFont(h);
        button1.setStyle("-fx-background-color: transparent;");
        button1.setMinSize(200, 50);
        //button1.
        
        Pane layout1 = new Pane(); 
        layout1.setBackground(bg);
        layout1.getChildren().addAll(button1); 
        layout1.getChildren().add(NonogramNanicIV);
        layout1.getChildren().add(HunterNoeyIV);
        layout1.getChildren().add(StartIV);
        startScene = new Scene(layout1, 600, 600);
        
        Pane menuLayout = new Pane(); 
        menuLayout.setBackground(MenuBackground);
        Pane createLayout = new Pane();
        createLayout.setScaleX(600);
        createLayout.setScaleY(600);
        
        createScene = new Scene(createLayout, 600, 600);
        
        Button mButton1 = new Button("", CreateNonoIV);
        Button mButton2 = new Button("", SolveNonoIV);
        Button mButton3 = new Button("", OptionsIV);
        Button mButton4 = new Button("", BackIV);
        
        //mButton1.setAlignment(Pos.CENTER);
        mButton1.setLayoutX(115);
        mButton1.setLayoutY(100);
        mButton1.setMaxSize(370, 50);
        mButton1.setMinSize(370, 50);
        mButton1.setStyle("-fx-background-color: transparent;");
        
        mButton2.setLayoutX(115);
        mButton2.setLayoutY(210);
        mButton2.setMaxSize(370, 50);
        mButton2.setMinSize(370, 50);
        mButton2.setStyle("-fx-background-color: transparent;");
        
        mButton3.setLayoutX(205);
        mButton3.setLayoutY(330);
        mButton3.setMinSize(150, 40);
        mButton3.setMaxSize(150, 40);
        mButton3.setStyle("-fx-background-color: transparent;");
        
        mButton4.setLayoutX(85);
        mButton4.setLayoutY(450);
        mButton4.setMaxSize(450, 70);
        mButton4.setMinSize(450, 70);
        mButton4.setStyle("-fx-background-color: transparent;");
        
        menuLayout.getChildren().add(mButton1); 
        menuLayout.getChildren().add(mButton2); 
        //menuLayout.getChildren().add(mButton3); 
        menuLayout.getChildren().add(mButton4);
        
        JFrame NonoSelect = new JFrame("Select the Nonogram to Play"); 
        String NonoCombo[] = { "Test3", "Test4"};
        NonoSelect.setLayout(new FlowLayout());
        JLabel select = new JLabel("Select a Nonogram");
        JComboBox NonoChoices = new JComboBox(NonoCombo);
        NonoChoices.setBounds(100,100,250,300);
        NonoChoices.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
        NonoSelect.add(select);
        NonoSelect.add(NonoChoices);
        //NonoSelect.setSize(200, 200);
        
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(select);
        swingNode.setContent(NonoChoices);
        swingNode.setLayoutX(50);
        swingNode.setLayoutY(150);
        
        
         
        Pane playLayout = new Pane(); 
        playLayout.getChildren().add(swingNode);
        playLayout.getChildren().add(NonoNameIV);
        playLayout.getChildren().add(NonoDataIV);
        playLayout.getChildren().add(LengthDataIV);
        playLayout.getChildren().add(WidthDataIV);
        playLayout.getChildren().add(HardCoreDataIV);
        playLayout.getChildren().add(HardCoreBioDataIV);
        playLayout.getChildren().add(disabledIV);        
        playLayout.setBackground(MenuBackground);
        playScene = new Scene(playLayout, 600, 600);
        
        NonoChoices.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String NonogramName = NonoChoices.getSelectedItem().toString();
                System.out.println(NonogramName);
            }
        });
        
        mButton2.setOnAction(e-> {Window.setScene(playScene);
            
        });
        
         button1.setOnMouseEntered(e ->
	{       
           button1.setScaleX(1.1);
           button1.setScaleY(1.1);
           button1.setEffect(new DropShadow());
        });
	button1.setOnMouseExited(e ->
	{ 
            button1.setScaleX(1);
            button1.setScaleY(1);
            button1.setEffect(null);
	});
        
        button1.setOnAction(e-> {Window.setScene(menuScene);
            
            
            officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            
        });
        
        mButton1.setOnMouseEntered(e ->
	{       
           mButton1.setScaleX(1.1);
           mButton1.setScaleY(1.1);
           mButton1.setEffect(new DropShadow());
        });
	mButton1.setOnMouseExited(e ->
	{ 
            mButton1.setScaleX(1);
            mButton1.setScaleY(1);
            mButton1.setEffect(null);
	});
        
          mButton2.setOnMouseEntered(e ->
	{       
            mButton2.setScaleX(1.1);
            mButton2.setScaleY(1.1);
            mButton2.setEffect(new DropShadow());
        });
	mButton2.setOnMouseExited(e ->
	{       
            mButton2.setScaleX(1);
            mButton2.setScaleY(1);
            mButton2.setEffect(null);
	});
        
        mButton3.setOnMouseEntered(e ->
	{   mButton3.setScaleX(1.1);
            mButton3.setScaleY(1.1);
            mButton3.setEffect(new DropShadow());
        });
	mButton3.setOnMouseExited(e ->
	{   
            mButton3.setScaleX(1);
            mButton3.setScaleY(1);
            mButton3.setEffect(null);
	});
        
        mButton4.setOnMouseEntered(e ->
	{   
            mButton4.setScaleX(1.1);
            mButton4.setScaleY(1.1);
            mButton4.setEffect(new DropShadow());
        });
	mButton4.setOnMouseExited(e ->
	{ 
            mButton4.setScaleX(1);
            mButton4.setScaleY(1);
            mButton4.setEffect(null);
	});
        
         Menu1IV.setOnMouseEntered(e ->
	{   
            Menu1IV.setScaleX(1.1);
            Menu1IV.setScaleY(1.1);
            Menu1IV.setEffect(new DropShadow());
        });
	Menu1IV.setOnMouseExited(e ->
	{ 
            Menu1IV.setScaleX(1);
            Menu1IV.setScaleY(1);
            Menu1IV.setEffect(null);
	});
        
        Pane optionLayout = new Pane();
        optionLayout.getChildren().add(ChangeMusicIV);
        optionLayout.setBackground(MenuBackground);
        Button song1 = new Button("", Menu1IV);
        Button song2 = new Button("", Menu2IV);
        Button song3 = new Button("", Menu3IV);
        Button song4 = new Button("", Menu4IV);
        Button song5 = new Button("", Menu5IV);
        Button song6 = new Button("", Menu6IV);
        
        song1.setLayoutX(240);
        song1.setLayoutY(330);
        song1.setMaxSize(80, 30);
        song1.setMinSize(80, 30);
        song1.setStyle("-fx-background-color: transparent;");
        
        song2.setLayoutX(330);
        song2.setLayoutY(330);
        song2.setMaxSize(80, 30);
        song2.setMinSize(80, 30);
        song2.setStyle("-fx-background-color: transparent;");
        
        song3.setLayoutX(440);
        song3.setLayoutY(330);
        song3.setMaxSize(80, 30);
        song3.setMinSize(80, 30);
        song3.setStyle("-fx-background-color: transparent;");
        
        song4.setLayoutX(240);
        song4.setLayoutY(380);
        song4.setMaxSize(80, 30);
        song4.setMinSize(80, 30);
        song4.setStyle("-fx-background-color: transparent;");
        
        song5.setLayoutX(330);
        song5.setLayoutY(380);
        song5.setMaxSize(80, 30);
        song5.setMinSize(80, 30);
        song5.setStyle("-fx-background-color: transparent;");
        
        song6.setLayoutX(440);
        song6.setLayoutY(380);
        song6.setMaxSize(80, 30);
        song6.setMinSize(80, 30);
        song6.setStyle("-fx-background-color: transparent;");
        
        menuLayout.getChildren().add(ChangeMusicIV);
        menuLayout.getChildren().add(song1);
        menuLayout.getChildren().add(song2);
        menuLayout.getChildren().add(song3);
        menuLayout.getChildren().add(song4);
        menuLayout.getChildren().add(song5);
        menuLayout.getChildren().add(song6);
        
        song1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                officialSong.setMicrosecondPosition(0);
                officialSong.stop();
                officialSong = officialSong1AS;
                officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
        
        song1.setOnMouseEntered(e ->
	{   
            song1.setScaleX(1.1);
            song1.setScaleY(1.1);
            song1.setEffect(new DropShadow());
        });
	song1.setOnMouseExited(e ->
	{ 
            song1.setScaleX(1);
            song1.setScaleY(1);
            song1.setEffect(null);
	});
        
         song2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               officialSong.setMicrosecondPosition(0);
               officialSong.stop();
               officialSong = officialSong2AS;
               officialSong.loop(Clip.LOOP_CONTINUOUSLY); 
            }
        });
        
         song2.setOnMouseEntered(e ->
	{   
            song2.setScaleX(1.1);
            song2.setScaleY(1.1);
            song2.setEffect(new DropShadow());
        });
	song2.setOnMouseExited(e ->
	{ 
            song2.setScaleX(1);
            song2.setScaleY(1);
            song2.setEffect(null);
	});
        
        song3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                officialSong.setMicrosecondPosition(0);
                officialSong.stop();
                officialSong = officialSong3AS;
                officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
        
        song3.setOnMouseEntered(e ->
	{   
            song3.setScaleX(1.1);
            song3.setScaleY(1.1);
            song3.setEffect(new DropShadow());
        });
	song3.setOnMouseExited(e ->
	{ 
            song3.setScaleX(1);
            song3.setScaleY(1);
            song3.setEffect(null);
	});
        
        song4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                officialSong.setMicrosecondPosition(0);
                officialSong.stop();
                officialSong = officialSong4AS;
                officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
        
        song4.setOnMouseEntered(e ->
	{   
            song4.setScaleX(1.1);
            song4.setScaleY(1.1);
            song4.setEffect(new DropShadow());
        });
	song4.setOnMouseExited(e ->
	{ 
            song4.setScaleX(1);
            song4.setScaleY(1);
            song4.setEffect(null);
	});
        
        song5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                officialSong.setMicrosecondPosition(0);
                officialSong.stop();
                officialSong = officialSong5AS;
                officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
        
        song5.setOnMouseEntered(e ->
	{   
            song5.setScaleX(1.1);
            song5.setScaleY(1.1);
            song5.setEffect(new DropShadow());
        });
	song5.setOnMouseExited(e ->
	{ 
            song5.setScaleX(1);
            song5.setScaleY(1);
            song5.setEffect(null);
	});
        
        song6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                officialSong.setMicrosecondPosition(0);
                officialSong.stop();
                officialSong = officialSong6AS;
                officialSong.loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
        
        song6.setOnMouseEntered(e ->
	{   
            song6.setScaleX(1.1);
            song6.setScaleY(1.1);
            song6.setEffect(new DropShadow());
        });
	song6.setOnMouseExited(e ->
	{ 
            song6.setScaleX(1);
            song6.setScaleY(1);
            song6.setEffect(null);
	});
        
        //optionLayout.getChildren().add(song1); 
        //optionLayout.getChildren().add(song2); 
        //optionLayout.getChildren().add(song3); 
        
        mButton1.setOnAction(new EventHandler<ActionEvent>() {
            
        @Override public void handle(ActionEvent e) {
        while(true){
            try{
                length = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the length of the nanogram:"));
                if (length <= 0){
                    JOptionPane.showMessageDialog(null, "The length must be greater than 0. Please enter a valid number:");
                    continue;
                }
             } catch (Exception ee){
                  JOptionPane.showMessageDialog(null, "Invalid value for length.");
                  continue;
               }
                break;
              }
             
             while(true){
                try {
                    width = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the width of the nanogram."));
                    if (width <= 0){
                      JOptionPane.showMessageDialog(null, "The width must be greater than 0. Please enter a valid number:");
                      continue;
                    }
                } catch (Exception ee){
                  JOptionPane.showMessageDialog(null, "Invalid value for width.");
                  continue;
                }
                break;
              }
        
        int wi = width; 
        int le = length;
        int xStart = 500, yStart = 0;
        JTextField[][] tpFields = new JTextField[le][le];
        JTextField[][] spFields = new JTextField[wi][wi];
        
        
       ImageIcon img = new ImageIcon("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/source2.gif");
       JLabel background = new JLabel("", img, JLabel.CENTER);
        
       background.setSize(600, 600);
       
        //BufferedImage myImage = ImageIO.read("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/background.gif");
        JFrame testGrid = new JFrame();
        testGrid.setContentPane(background);
        //ImageIcon img5 = new ImageIcon("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/most-wazted.regular.png");
        //JLabel CreateHeader2 = new JLabel("", img5, JLabel.CENTER);
        //CreateHeader2.setBounds(500, 500, 500, 500);
        //testGrid.add(CreateHeader2);
        //
        
        boolean[][] Checker = new boolean[wi][le];
        
        for (int i=0;i<width;i++){
            for (int k = 0; k < length; k++){
                final int ii = i;
                final int kk = k; 
                JButton temp = new JButton(); 
                temp.setForeground(Color.white);
                temp.setBackground(Color.white);
                javax.swing.border.Border border = BorderFactory.createLineBorder(Color.gray, 1);
                temp.setBorder(border);
                temp.setOpaque(true);
                int divisor = Math.max(le, wi);
                int movement = 256/divisor;
                temp.setSize(256/divisor, 256/divisor);
                temp.setLocation(172 + movement*i, 200 + movement*k);
                xStart = Math.min(xStart,100 + movement*i);
                yStart = Math.max(yStart, 100 + movement*k);
                    
                temp.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        boolean tweak = true;
                        Checker[ii][kk] = flip(Checker[ii][kk]);
                        
                        if (Checker[ii][kk])
                            temp.setBackground(Color.black);
                        else if (!Checker[ii][kk])
                            temp.setBackground(Color.white);
                        }
                   });
                  
                testGrid.add(temp);
            }
        }
             JFrame frame = new JFrame();
             
             testGrid.setLayout(null); 
             frame.setVisible(false);
             testGrid.setSize(600,600); 
             testGrid.setResizable(false);
             JButton finish = new JButton("Finalize");
             finish.setOpaque(true);
             finish.setLocation(25 + le*70, 55 + wi*70);
             //finish.setSize(150, 30);
             finish.setBounds(225, 520, 150, 30);
             testGrid.add(finish); 
             //testGrid.setLocationRelativeTo(frame);
             testGrid.setVisible(true); 
             finish.addActionListener(new ActionListener(){
                
             public void actionPerformed(java.awt.event.ActionEvent event){
                int mostUniqueWidth = 0;
                int mostUniqueLength = 0;
                    
                int finalLHolder = 0;
                int finalWHolder = 0;
                    
                //generating the amount of markers for length
                for(int i = 0; i < le; i ++){
                    int currentMarkers = 0;
                    int currentLength = 0;
                        
                    for(int j = 0; j < wi; j++){
                        if(Checker[j][i] == true){
                           currentLength++;
                        }
                            
                        if(Checker[j][i] == false){
                           currentLength = 0;
                        }
                            
                        if(currentLength == 1)
                            currentMarkers++;
                        }
                        finalLHolder = Math.max(finalLHolder, currentMarkers);
                   }
                    //generating the amount of markers for width
                    for(int i = 0; i < wi; i ++){
                        int currentMarkers = 0;
                        int currentLength = 0;
                        
                    for(int j = 0; j < le; j++){
                        if(Checker[i][j] == true){
                            currentLength++;
                        }
                            
                        if(Checker[i][j] == false){
                            currentLength = 0;
                        }
                            
                        if(currentLength == 1)
                            currentMarkers++;
                        }
                        finalWHolder = Math.max(finalWHolder, currentMarkers);
                   }
                    
                    int LData[][] = new int[le][le];
                    int WData[][] = new int[wi][wi];
                    
                    for(int i = 0; i < le; i++){
                        int leIndex = 0;
                        int currentLength = 0;
                        
                        for(int j = 0; j < wi; j++){
                            if(Checker[j][i] == true){
                                currentLength++;
                            }
                            else if(Checker[j][i] == false && currentLength > 0){
                                LData[i][leIndex] = currentLength;
                                leIndex++;
                                currentLength = 0;
                            }
                            
                            if(j == wi - 1 && currentLength > 0)
                                LData[i][leIndex] = currentLength;
                        }
                   }
                    
                    reverse(LData, le, finalLHolder);
                    reverse2(LData, finalLHolder, le);
                    
                    for(int i = 0; i < le; i++)
                        pushZerosToEnd(LData, i, finalLHolder);
                    
                    for(int i = 0; i < wi; i++){
                        int wiIndex = 0;
                        int currentLength = 0;
                        
                        for(int j = 0; j < le; j++){
                            if(Checker[i][j] == true){
                                currentLength++;
                            }
                            else if(Checker[i][j] == false && currentLength > 0){
                                WData[i][wiIndex] = currentLength;
                                wiIndex++;
                                currentLength = 0;
                            }
                            
                            if(j == le - 1 && currentLength > 0)
                                WData[i][wiIndex] = currentLength;
                        }
                    }
                    
                    reverse2(WData, finalWHolder, wi);
                   
                    for(int i = 0; i < wi; i++)
                        pushZerosToEnd(WData, i, finalWHolder);
                   
                    JOptionPane.showMessageDialog(frame,"We got your input!");
                    
                    Nonogram test = new Nonogram("Test3", Checker, le, wi, LData, WData, finalLHolder, finalWHolder);
                    
                    FileOutputStream fout = null;
                    
                    try {
                        fout = new FileOutputStream("output.dat");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                   ObjectOutputStream oos = null;
                    
                   try {
                        oos = new ObjectOutputStream(fout);
                        } 
                   catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        oos.writeObject(test);
                        //oos.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fout.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   testGrid.setVisible(false);
             }
                     
            });
        }
});
        
//        mButton2.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {
//                //Vector<Nonogram> dataHolder = new Vector<Nonogram>();
//                
//                ObjectInputStream fileIn = null;
//                try {
//                    fileIn = new ObjectInputStream(new FileInputStream("output.dat"));
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                Nonogram tempNono = null;
//                
//                boolean check=true;
//                while (check) {
//                try{
//                    tempNono = (Nonogram)fileIn.readObject();
//                    //dataHolder.add(tempNono);
//                    System.out.println(tempNono.getName());
//                } 
//                catch(EOFException ex){
//                check=false;
//                }   catch (IOException ex) {
//                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (ClassNotFoundException ex) {
//                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//              
//                try {
//                    fileIn.close();
//                } catch (IOException ex) {
//                    System.out.println("We are here!");
//                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                JOptionPane.showMessageDialog(null, "Buggy Beta :]");
//                
//                int wi = tempNono.getWidth(); 
//                int le = tempNono.getLength();
//                boolean[][] Checker = new boolean[wi][le];
//                int xStart = 500, yStart = 0;
//                JTextField[][] tpFields = new JTextField[le][le];
//                JTextField[][] spFields = new JTextField[wi][wi];
//                
//                JFrame testGrid = new JFrame();
//                
//                for (int i=0;i<wi;i++){
//                for (int k = 0; k < le; k++){
//                    final int ii = i;
//                    final int kk = k; 
//                    JButton temp = new JButton(); 
//                    temp.setForeground(Color.white);
//                    temp.setBackground(Color.white);
//                    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.red, 1);
//                    temp.setBorder(border);
//                    temp.setOpaque(true);
//                    int divisor = Math.max(le, wi);
//                    int movement = 200/divisor;
//                    temp.setSize(200/divisor, 200/divisor);
//                    temp.setLocation(240 + movement*i, 300 + movement*k);
//                    xStart = Math.min(xStart,240 + movement*i);
//                    yStart = Math.max(yStart, 300 + movement*k);
//                    
//                    temp.addMouseListener(new MouseAdapter(){
//                       public void mouseClicked(MouseEvent e){
//                           
//                           boolean tweak = true;
//                           Checker[ii][kk] = flip(Checker[ii][kk]);
//                           
//                           if (Checker[ii][kk])
//                               temp.setBackground(Color.black);
//                           else if (!Checker[ii][kk])
//                            temp.setBackground(Color.white);
//                            
//                           
//                            
//                       }
//                   });
//                          
//                    
//                   
//                    testGrid.add(temp);
//            }
//              }
//                
//                int divisor = Math.max(le, wi);
//                int movement = 200/divisor;
//                int iterations = 0;
//                
//                for(int roW = 0; roW < wi; roW++){
//                      for(int coM = 0; coM < tempNono.getMaxWidthHolder(); coM++){
//                    iterations++;
//                    spFields[roW][coM] = new JTextField();
//                    if(tempNono.getWidthValue(roW, coM) > 0)
//                    spFields[roW][coM].setText(Integer.toString(tempNono.getWidthValue(roW, coM)));
//                    //[roW][coM].setForeground(Color.white);
//                    spFields[roW][coM].setBackground(Color.white);
//                    spFields[roW][coM].setHorizontalAlignment(JTextField.CENTER);
//                    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.gray, 1);
//                    spFields[roW][coM].setBorder(border);
//                    spFields[roW][coM].setEditable(false);
//                    spFields[roW][coM].setBounds(240+((200/divisor)*roW),yStart - (200/divisor)*wi- (200/divisor)*coM,200/divisor,200/divisor);
//                    testGrid.add(spFields[roW][coM]);
//                    
//                    System.out.println(iterations);
//                    }
//               }
//                
//                int movementSize = 200/divisor;
//                
//                for(int coL = 0; coL < tempNono.getMaxLengthHolder(); coL++){
//                   for(int roW = 0; roW < le; roW++){
//                        tpFields[roW][coL] = new JTextField();
//                        //System.out.println(tempNono.getLengthValue(roW, coL));
//                        if(tempNono.getLengthValue(roW, coL) > 0){
//                            //System.out.println("YAY");
//                            tpFields[roW][coL].setText(Integer.toString(tempNono.getLengthValue(roW, coL)));
//                        }
//                            
//                        
//                        
//                        tpFields[roW][coL].setBackground(Color.white);
//                    tpFields[roW][coL].setHorizontalAlignment(JTextField.CENTER);
//                    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.gray, 1);
//                    tpFields[roW][coL].setBorder(border);
//                    tpFields[roW][coL].setEditable(false);
//                    testGrid.add(tpFields[roW][coL]);
//                    //yStart - (200/divisor)*wi- (200/divisor)*coM
//                    
//                    //tpFields[roW][coL].setBounds(xStart-(30*coL)-30,70+(le*30)- 30*roW,30,30);
//                    tpFields[roW][coL].setBounds(xStart-(movementSize*coL)-movementSize,(300-movementSize)+(le*movementSize)-movementSize*roW,movementSize,movementSize);
//                    }
//                }
//                
//                
//                JFrame frame = new JFrame();
//             //testGrid.setBackground(Color.gray);
//             testGrid.setLayout(null); 
//             frame.setVisible(false);
//            testGrid.setSize(600,600); 
//            testGrid.setResizable(false);
//              JButton finish = new JButton("Finalize");
//              finish.setOpaque(true);
//              finish.setLocation(25 + le*70, 55 + wi*70);
//              //finish.setSize(150, 30);
//              finish.setBounds(225, 520, 150, 30);
//              testGrid.add(finish); 
//              //testGrid.setLocationRelativeTo(frame);
//              testGrid.setVisible(true); 
//              
//              final Nonogram item = tempNono;
//              
//              finish.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(java.awt.event.ActionEvent e) {
//                        item.getData();
//                        if(item.sameAnswer(Checker) == true){
//                            System.out.println("Winner Winner Chicken Dinner");
//                            System.exit(0);
//                        }
//                    }
//              
//             });
//            }
//        });
        
        mButton3.setOnAction(e-> {Window.setScene(optionScene);
            
        });
        
        mButton4.setOnAction(e-> {Window.setScene(startScene);
            officialSong.setMicrosecondPosition(0);
            officialSong.stop();
            
        });
       
       //generateScene = new Scene(generateLayout, 600, 600); 
       menuScene = new Scene(menuLayout, 600, 600);
       optionScene =  new Scene(optionLayout, 600, 600);
       Window.setScene(startScene);
       Window.setTitle("Nonogram Nanic (A working progress)");
       Window.show();
       
        
    }
    void pushZerosToEnd(int arr[][], int index, int n) { 
    
        int count = n;  // Count of non-zero elements 
  
        // Traverse the array. If element encountered is non- 
        // zero, then replace the element at index 'count'  
        // with this element 
        for (int i = n-1; i >= 0; i--){
            if (arr[index][i] != 0) {
                count--;
                arr[index][count] = arr[index][i]; // here count is 
            }
            
        } 
         
        while (count > 0) {
            count--;
            arr[index][count] = 0;
        }
    } 
    
    void reverse(int[][] arr, int length, int width){
        System.out.println(width);
        System.out.println(length);
        for(int i = 0; i < width; i++){
            for(int k = 0, j = length-1; k < j; k++, j--){
                int temp = arr[k][i];
                arr[k][i] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }
    
    void reverse2(int[][] arr, int length, int width){
        for(int i = 0; i < width; i++){
            for(int k = 0, j = length-1; k < j; k++, j--){
                int temp = arr[i][k];
                arr[i][k] = arr[i][j];
                arr[i][j] = temp;
            }
        }
    }
    
    static boolean flip(boolean flipme){
        if (flipme)
            flipme = false;
        else if (!flipme)
            flipme = true; 
        return flipme; 
    }
}
