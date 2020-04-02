/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.FileInputStream;
import java.util.Vector;
import javafx.embed.swing.SwingNode;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Hunter
 */

public class FinalMenu extends Application{
    Font mostWazted;
    
    int length, width;
    Stage Window;
    Scene scene1, scene2, optionScene, scoreScene, menuScene, musicScene, playScene, createScene, startScene, generateScene;
    
    static Clip currentSound, officialSong, officialSong1AS, officialSong2AS, officialSong3AS, officialSong4AS, officialSong5AS, officialSong6AS, officialOk, officialToggle, officialBack;
    
    String officialNonogramName;
    Nonogram officialNonogram;
   
    public static void main(String args[]){
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        try {
            GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            mostWazted = Font.createFont(Font.TRUETYPE_FONT, new File("C://Windows//Fonts/Mostwasted.ttf")).deriveFont(30f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("C://Windows//Fonts/Mostwasted.ttf")));
        } catch (IOException|FontFormatException e) {
            System.out.println(e);
        }
        
        //importing the music
        AudioInputStream officialSong1 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/melee.wav"));
        AudioInputStream officialSong2 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/meee.wav"));
        AudioInputStream officialSong3 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/flat.wav"));
        AudioInputStream officialSong4 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Menu1.wav"));
        AudioInputStream officialSong5 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Boss.wav"));
        AudioInputStream officialSong6 = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/Attack.wav"));
        AudioInputStream toggle = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/menu-toggle.wav"));
        AudioInputStream ok = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/menu-ok.wav"));
        AudioInputStream back = AudioSystem.getAudioInputStream(new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/menu-back.wav"));
        
        //Allowing the music to be played
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
        officialToggle = AudioSystem.getClip();
        officialToggle.open(toggle);
        officialOk = AudioSystem.getClip();
        officialOk.open(ok);
        officialBack = AudioSystem.getClip();
        officialBack.open(back);
        
        //Initial Song set to Flat Zone (officialSong3AS
        officialSong = officialSong3AS;
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
        //Thread.sleep(10000); 
        
        //Holds the Nonogram Data
        Vector<Nonogram> dataHolder = new Vector<Nonogram>();
        Vector<String> NonogramNameHolder = new Vector<String>();
                
        //Opening the file with the presaved Nonograms
        ObjectInputStream fileIn = null;
        try {
            fileIn = new ObjectInputStream(new FileInputStream("output.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Reads in the presaved Nonograms       
        boolean check=true;
        while (check) {
        try{
            Nonogram tempNono = (Nonogram)fileIn.readObject();
            dataHolder.add(tempNono);
            NonogramNameHolder.add(tempNono.getName());
        } 
        catch(EOFException ex){
        check=false;
        }   catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //closes the file
        try {
            fileIn.close();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        //Initial Nonogram is set to the first one in dataHolder
        officialNonogram = dataHolder.get(0);
        
        //Disabling resizing, initializing the primary stage
        Window = primaryStage; 
        primaryStage.setResizable(false);
        
        //Close request
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        //Reading in the backgrounds (as files) to be converted into Backgrounds
        File file = new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/nonogram_promo_bw.jpg"); 
        File file2 = new File("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/background.gif");
        
        //Image Conversion for Program Title
        Image img = new Image(new FileInputStream(file));
        Image NonogramNanic = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdUbUZ1YVdNLC4wAA,,/most-wazted.regular.png");
        ImageView NonogramNanicIV = new ImageView(NonogramNanic);
        NonogramNanicIV.setX(86);
        NonogramNanicIV.setY(450);
        
        //Image Conversion for my name :]
        Image HunterNoey = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5Rbms2SUVoMWJuUmxjaUJPYjJWNS4wAAAA/most-wazted.regular.png");
        ImageView HunterNoeyIV = new ImageView(HunterNoey);
        HunterNoeyIV.setScaleX(.4);
        HunterNoeyIV.setScaleY(.4);
        HunterNoeyIV.setX(86);
        HunterNoeyIV.setY(500);
        
        //Image Conversion for Start Button
        Image Start = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5jM1JoY25RLC4wAAAAAAAAAA,,/most-wazted.regular.png");
        ImageView StartIV = new ImageView(Start);
        StartIV.setScaleX(.4);
        StartIV.setScaleY(.4);
        StartIV.setX(200);
        StartIV.setY(550);
        
        //Formatting the Button for Start
        Button button1 = new Button("", StartIV);
        button1.setLayoutX(200);
        button1.setLayoutY(540);
        button1.setStyle("-fx-background-color: transparent;");
        button1.setMinSize(200, 50);
        
        //Image Conversion for Create Nonogram Button
        Image CreateNono = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RM0psWVhSbElHRWdUbTl1YjJkeVlXMCwuMA,,/most-wazted.regular.png");
        ImageView CreateNonoIV = new ImageView(CreateNono);
        CreateNonoIV.setScaleX(.7);
        CreateNonoIV.setScaleY(.7);
        
        //Formatting the Button for Create Nono
        Button mButton1 = new Button("", CreateNonoIV);
        mButton1.setLayoutX(115);
        mButton1.setLayoutY(100);
        mButton1.setMaxSize(370, 50);
        mButton1.setMinSize(370, 50);
        mButton1.setStyle("-fx-background-color: transparent;");
        
        //Image Conversion for Create Nonogram Button
        Image CreateHeader = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLmZmZmZmZi5SMlZ1WlhKaGRHVWdlVzkxY2lCT2IyNXZaM0poYlNFLC4wAAAA/most-wazted.regular.png");
        ImageView CreateHeaderIV = new ImageView(CreateHeader);
        CreateHeaderIV.setScaleX(.7);
        CreateHeaderIV.setScaleY(.7);
        CreateHeaderIV.setX(200);
        CreateHeaderIV.setY(0);
        
        //Image Conversion for Solve Nonogram Button
        Image SolveNono = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5VMjlzZG1VZ1lTQk9iMjV2WjNKaGJRLCwuMAAA/most-wazted.regular.png");
        ImageView SolveNonoIV = new ImageView(SolveNono);
        SolveNonoIV.setScaleX(.7);
        SolveNonoIV.setScaleY(.7);
        
        //Formatting the Button for Solve Nono
        Button mButton2 = new Button("", SolveNonoIV);
        mButton2.setLayoutX(115);
        mButton2.setLayoutY(210);
        mButton2.setMaxSize(370, 50);
        mButton2.setMinSize(370, 50);
        mButton2.setStyle("-fx-background-color: transparent;");
        
        //Image Conversion for Back Button (sends you baack to start screen)
        Image Back = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RbUZqYXlCMGJ5QlRkR0Z5ZENCVFkzSmxaVzQsLjAAAAAA/most-wazted.regular.png");
        ImageView BackIV = new ImageView(Back);
        BackIV.setScaleX(.7);
        BackIV.setScaleY(.7);
        
        //Image Conversion for Back Button
        Button mButton4 = new Button("", BackIV);
        mButton4.setLayoutX(85);
        mButton4.setLayoutY(450);
        mButton4.setMaxSize(450, 70);
        mButton4.setMinSize(450, 70);
        mButton4.setStyle("-fx-background-color: transparent;");
        
        //Image Conversion for Back Button (sends you baack to main menu)
        Image Back2 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RbUZqYXcsLC4wAAAA/most-wazted.regular.png");
        ImageView BackIV2 = new ImageView(Back2);
        BackIV2.setScaleX(.7);
        BackIV2.setScaleY(.7);
        
        //Image Conversion for Change Music Icon
        Image ChangeMusic = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5RMmhoYm1kbElFMTFjMmxqT2lBLC4wAAAA/most-wazted.regular.png");
        ImageView ChangeMusicIV = new ImageView(ChangeMusic);
        ChangeMusicIV.setScaleX(.4);
        ChangeMusicIV.setScaleY(.4);
        ChangeMusicIV.setX(-50);
        ChangeMusicIV.setY(315); 
        
        //Image Conversion for Song #1 Button
        Image Menu1 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBeC4wAAAA/most-wazted.regular.png");
        ImageView Menu1IV = new ImageView(Menu1);
        Menu1IV.setScaleX(.4);
        Menu1IV.setScaleY(.4);
        
        //Image Conversion for Song #2 Button
        Image Menu2 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBeS4wAAAAAAAA/most-wazted.regular.png");
        ImageView Menu2IV = new ImageView(Menu2);
        Menu2IV.setScaleX(.4);
        Menu2IV.setScaleY(.4);
        
        //Image Conversion for Song #3 Button
        Image Menu3 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5SbXhoZENCYWIyNWwuMAAAAAAA/most-wazted.regular.png");
        ImageView Menu3IV = new ImageView(Menu3);
        Menu3IV.setScaleX(.4);
        Menu3IV.setScaleY(.4);
        
        //Image Conversion for Song #4 Button
        Image Menu4 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UV1Z1ZFNBei4w/most-wazted.regular.png");
        ImageView Menu4IV = new ImageView(Menu4);
        Menu4IV.setScaleX(.4);
        Menu4IV.setScaleY(.4);
        
        //Image Conversion for Song #5 Button
        Image Menu5 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5ZbTl6Y3lFLC4wAAAA/most-wazted.regular.png");
        ImageView Menu5IV = new ImageView(Menu5);
        Menu5IV.setScaleX(.4);
        Menu5IV.setScaleY(.4);
        
        //Image Conversion for Song #6 Button
        Image Menu6 = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5ZWFIwWVdOcklRLCwuMAA,/most-wazted.regular.png");
        ImageView Menu6IV = new ImageView(Menu6);
        Menu6IV.setScaleX(.4);
        Menu6IV.setScaleY(.4);
        
        //Image Conversion for Nonogram Name Header(in Solve Nonogram)
        Image NonoName = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdUbUZ0WlEsLC4w/most-wazted.regular.png");
        ImageView NonoNameIV = new ImageView(NonoName);
        NonoNameIV.setScaleX(.5);
        NonoNameIV.setScaleY(.5);
        NonoNameIV.setX(-70);
        NonoNameIV.setY(50);
        
        //Image Conversion for Nonogram Data Header (in Solve Nonogram)
        Image NonoData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UbTl1YjJkeVlXMGdSR0YwWVEsLC4w/most-wazted.regular.png");
        ImageView NonoDataIV = new ImageView(NonoData);
        NonoDataIV.setScaleX(.5);
        NonoDataIV.setScaleY(.5);
        NonoDataIV.setX(220);
        NonoDataIV.setY(50);
        
        //Image Conversion for Nonogram Length Header (in Solve Nonogram)
        Image LengthData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5UR1Z1WjNSby4w/most-wazted.regular.png");
        ImageView LengthDataIV = new ImageView(LengthData);
        LengthDataIV.setScaleX(.4);
        LengthDataIV.setScaleY(.4);
        LengthDataIV.setX(240);
        LengthDataIV.setY(120);
        
        //Image Conversion for Nonogram Width Header (in Solve Nonogram)
        Image WidthData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5WMmxrZEdnLC4w/most-wazted.regular.png");
        ImageView WidthDataIV = new ImageView(WidthData);
        WidthDataIV.setScaleX(.4);
        WidthDataIV.setScaleY(.4);
        WidthDataIV.setX(240);
        WidthDataIV.setY(150);
        
        //Image Conversion for Hardcore Header (in Solve Nonogram, mode is currently disabled)
        Image HardCoreData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5TR0Z5WkdOdlpHVWdUVzlrWlQ4LC4w/most-wazted.regular.png");
        ImageView HardCoreDataIV = new ImageView(HardCoreData);
        HardCoreDataIV.setScaleX(.5);
        HardCoreDataIV.setScaleY(.5);
        HardCoreDataIV.setX(-70);
        HardCoreDataIV.setY(250);
        
        //Image Conversion for Hardcore Info (in Solve Nonogram, mode is currently disabled)
        Image HardCoreBioData = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5MVmRvWlc0Z2FHRnlaR052Y21VZ2JXOWtaU0JwY3lCbGJtRmliR1ZrSUhsdmRTQnZibXg1SUdkbGRDQXpJR3hwZG1WeklTQSwuMAAAAAAAAAAA/most-wazted.regular.png");
        ImageView HardCoreBioDataIV = new ImageView(HardCoreBioData);
        HardCoreBioDataIV.setScaleX(.3);
        HardCoreBioDataIV.setScaleY(.3);
        HardCoreBioDataIV.setX(-500);
        HardCoreBioDataIV.setY(290);
        
        //Image Conversion for Hardcore disabled (in Solve Nonogram, mode is currently disabled)
        Image disabled = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5MVU4xY25KbGJuUnNlU0JrYVhOaFlteGxaQ0JtYjNJZ1FXeHdhR0VnUW5WcGJHUSwuMAAAAAAAAAAA/most-wazted.regular.png");
        ImageView disabledIV = new ImageView(disabled);
        disabledIV.setScaleX(.3);
        disabledIV.setScaleY(.3);
        disabledIV.setX(-310);
        disabledIV.setY(320);
        
        //Image Conversion for Generating a Nonogram (in Solve Nonogram)
        Image generateNono = new Image("https://txt-dynamic.static.1001fonts.net/txt/dHRmLjcyLjAwMDAwMC5SMlZ1WlhKaGRHVWguMAAA/most-wazted.regular.png");
        ImageView generateNonoIV = new ImageView(generateNono);
        generateNonoIV.setScaleX(.7);
        generateNonoIV.setScaleY(.7);
        
        //Converting img to be the main background(for Generate Nono/Main Menu)
        BackgroundImage bgImg;
        bgImg = new BackgroundImage(img, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background bg = new Background(bgImg);
        Image MainBackground = new Image(new FileInputStream(file2));
        
        //Converting img to be the main background(for Generate Nono/Main Menu)
        BackgroundImage MbgImg;
        MbgImg = new BackgroundImage(MainBackground, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background MenuBackground = new Background(MbgImg);
        
        //Start Screen Layout, creating the Scene
        Pane layout1 = new Pane(); 
        layout1.setBackground(bg);
        layout1.getChildren().addAll(button1); 
        layout1.getChildren().add(NonogramNanicIV);
        layout1.getChildren().add(HunterNoeyIV);
        layout1.getChildren().add(StartIV);
        startScene = new Scene(layout1, 600, 600);
        
        //Menu Screen Layout, creating the Scene
        Pane menuLayout = new Pane(); 
        menuLayout.setBackground(MenuBackground);
        menuLayout.getChildren().add(mButton1); 
        menuLayout.getChildren().add(mButton2); 
        menuLayout.getChildren().add(mButton4);
        
        //Creating the Back Button for the Solve Nonogram Scene
        Button backButton = new Button("", BackIV2);
        backButton.setLayoutX(85);
        backButton.setLayoutY(450);
        backButton.setMaxSize(100, 50);
        backButton.setMinSize(100, 50);
        backButton.setStyle("-fx-background-color: transparent;");
        
        //Creating the Generate Button for the Solve Nonogram Scene
        Button generate = new Button("", generateNonoIV);
        generate.setLayoutX(185);
        generate.setLayoutY(450);
        generate.setMinSize(100, 50);
        generate.setMinSize(100, 50);
        generate.setStyle("-fx-background-color: transparent;");
        
        
        JFrame NonoSelect = new JFrame("Select the Nonogram to Play"); 
        NonoSelect.setLayout(new FlowLayout());
        JLabel select = new JLabel("Select a Nonogram");
        
        //Array with the Nonogram names
        JComboBox NonoChoices = new JComboBox(dataHolder);
        NonoChoices.setFont(mostWazted);
        NonoChoices.setBounds(100,100,250,300);
        NonoChoices.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
        NonoSelect.add(select);
        NonoSelect.add(NonoChoices);
        
        //Array with the Nonogram names added to a swingNode for Scene capadibility 
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(select);
        swingNode.setContent(NonoChoices);
        swingNode.setLayoutX(50);
        swingNode.setLayoutY(150);
        
        //Displaying the Length of the selected Nonogram
        TextField LengthHolder = new TextField(Integer.toString(officialNonogram.getLength()));
        LengthHolder.setLayoutX(390);
        LengthHolder.setLayoutY(140);
        LengthHolder.setEditable(false);
        
        //Displaying the Width of the selected Nonogram
        TextField WidthHolder = new TextField(Integer.toString(officialNonogram.getWidth()));
        WidthHolder.setLayoutX(390);
        WidthHolder.setLayoutY(180);
        WidthHolder.setEditable(false);
        
        //adding the components to the playScene
        Pane playLayout = new Pane(); 
        playLayout.getChildren().addAll(swingNode, NonoNameIV, NonoDataIV, LengthDataIV, WidthDataIV, HardCoreDataIV, HardCoreBioDataIV, disabledIV, backButton, generate, LengthHolder, WidthHolder);
        playLayout.setBackground(MenuBackground);
        playScene = new Scene(playLayout, 600, 600);
        
        //Switches the Nonogram selected from the available Nonogram
        NonoChoices.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                officialNonogram = (Nonogram) NonoChoices.getSelectedItem();
                LengthHolder.setText(Integer.toString(officialNonogram.getLength()));
                WidthHolder.setText(Integer.toString(officialNonogram.getWidth()));
            }
        });
        
        //Switches to the playScene
        mButton2.setOnAction(e-> {Window.setScene(playScene);
            okSound();
        });
        
        //Highlighted button effects
        button1.setOnMouseEntered(e ->
	{  
            buttonHighlightedEffects(button1);
        });
         
	button1.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(button1);
	});
        
        button1.setOnAction(e-> {Window.setScene(menuScene);
            okSound();
            officialSong.loop(Clip.LOOP_CONTINUOUSLY);
        });
        
        //Highlighted button effects
        mButton1.setOnMouseEntered(e ->
	{  
           toggleSound();
           buttonHighlightedEffects(mButton1);
        });
        
        //Leaving the highlighed button effects
	mButton1.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(mButton1);
	});
        
        //Highlighted button effects
        mButton2.setOnMouseEntered(e ->
	{       
            toggleSound();
            buttonHighlightedEffects(mButton2);
        });
	
        //Leaving the highlighed button effects
        mButton2.setOnMouseExited(e ->
	{       
            buttonNegateEffects(mButton2);
	});
        
        //Highlighted button effects
        mButton4.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(mButton4);
        });
        
        //Leaving the highlighed button effects
	mButton4.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(mButton4);
	});
        
        //Highlighted button effects
        Menu1IV.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(Menu1IV);
        });
        
        //Leaving the highlighed button effects
	Menu1IV.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(Menu1IV);
	});
        
        //Highlighted button effects
        backButton.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(backButton);
        });
        
        //Leaving the highlighed button effects
	backButton.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(backButton);
	});
        
        backButton.setOnAction(e-> {Window.setScene(menuScene);
            backSound();
        });
        
        //Highlighted button effects
        generate.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(generate);
        });
        
        //Leaving the highlighed button effects
	generate.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(generate);
	});
        
        //Buttons for the songs
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
        
        //adding the songs to the menu
        menuLayout.getChildren().addAll(ChangeMusicIV, song1, song2, song3, song4, song5, song6);
        
        //Changing song to song1
        song1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okSound();
                changeSong(officialSong1AS);
            }
        });
        
        //Highlighted button effects
        song1.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(song1);
        });
        
        //Leaving the highlighed button effects
	song1.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(song1);
	});
        
        //Changing song to song2
        song2.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              okSound();
              changeSong(officialSong2AS); 
           }
       });
        
        //Highlighted button effects
        song2.setOnMouseEntered(e ->
        {   
           toggleSound();
           buttonHighlightedEffects(song2);
        });
        
        //Leaving the highlighed button effects
        song2.setOnMouseExited(e ->
        { 
            buttonNegateEffects(song2);
        });
        
        //Changing song to song3
        song3.setOnAction(new EventHandler<ActionEvent>() {
        @Override
             public void handle(ActionEvent event) {
                 okSound();
                 changeSong(officialSong3AS);
             }
         });
        
        //Highlighted button effects
        song3.setOnMouseEntered(e ->
        {   
            toggleSound();
            buttonHighlightedEffects(song3);
        });

        song3.setOnMouseExited(e ->
        { 
            buttonNegateEffects(song3);
        });
        
        //Changing song to song4
        song4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okSound();
                changeSong(officialSong4AS);
            }
        });
        
        //Highlighted button effects
        song4.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(song4);
        });
        
        //Leaving the highlighed button effects
	song4.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(song4);
	});
        
        //Changing song to song5
        song5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okSound();
                changeSong(officialSong5AS);
            }
        });
        
        //Highlighted button effects
        song5.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(song5);
        });
        
        //Leaving the highlighed button effects
	song5.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(song5);
	});
        
        //Changing song to song6
        song6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okSound();
                changeSong(officialSong6AS);
            }
        });
        
        //Highlighted button effects
        song6.setOnMouseEntered(e ->
	{   
            toggleSound();
            buttonHighlightedEffects(song6);
        });
        
        //Leaving the highlighed button effects
	song6.setOnMouseExited(e ->
	{ 
            buttonNegateEffects(song6);
	});
        
        //Creating a Nonogram
        mButton1.setOnAction(new EventHandler<ActionEvent>() {
        
        @Override public void handle(ActionEvent e) {
            //Entering a Length
            while(true){
                try{
                    final JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);    
                    length = Integer.parseInt(JOptionPane.showInputDialog(dialog, "Please enter the length of the nanogram (1-25):"));
                    if (length <= 0 || length > 25){
                        JOptionPane.showMessageDialog(null, "The length must be greater than 0 and less then 26. Please enter a valid number:");
                        continue;
                    }
                } 
                catch (Exception ee){
                      JOptionPane.showMessageDialog(null, "Invalid value for length.");
                      continue;
                }
                break;
            }
             
             //Entering a Width
             while(true){
                try {
                    width = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the width of the nanogram (1-25):"));
                    if (width <= 0 || width > 25){
                      JOptionPane.showMessageDialog(null, "The width must be greater than 0 and less then 26. Please enter a valid number:");
                      continue;
                    }
                } 
                catch (Exception ee){
                  JOptionPane.showMessageDialog(null, "Invalid value for width.");
                  continue;
                }
                break;
              }
        
            int wi = width; 
            int le = length;
            int xStart = 500, yStart = 0;

            //used for Nonogram boxes (User interactions)
            JTextField[][] tpFields = new JTextField[le][le];
            JTextField[][] spFields = new JTextField[wi][wi];
            
            //Background for the CreateNonogram Event
            ImageIcon img = new ImageIcon("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/source2.gif");
            JLabel background = new JLabel("", img, JLabel.CENTER);
            background.setSize(600, 600);
       
            //Grid for the Create Nonogram Event
            JFrame testGrid = new JFrame();
            testGrid.setContentPane(background);
            testGrid.setSize(600, 600);
            
            //Array for if a marker is selected. Black = selected, white = not-selected
            boolean[][] Checker = new boolean[wi][le];
            
            //Generating the Nonogram
            for (int i=0;i<width;i++){
                for (int k = 0; k < length; k++){
                    int movementIncrementor = 400;
                    
                    final int ii = i;
                    final int kk = k; 
                    JButton temp = new JButton(); 
                    temp.setForeground(Color.white);    //initial color of marker is white (false)
                    temp.setBackground(Color.white);    //initial color of marker is white (false)
                    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.gray, 1);   //Style
                    temp.setBorder(border);
                    temp.setOpaque(true);
                    int divisor = Math.max(le, wi);     //divisor/size is relevant to the biggest element (length/width)
                    int movement = movementIncrementor/divisor; //movement relevant to size
                    temp.setSize(movementIncrementor/divisor, movementIncrementor/divisor);
                    temp.setLocation(112 + movement*i, 70+ movement*k); //movement relevant to size

                    //movement relevant to size
                    xStart = Math.min(xStart,100 + movement*i);
                    yStart = Math.max(yStart, 100 + movement*k);

                    //Black = selected marker, White = not-selected marker. Flip is the method that changes it.
                    temp.addMouseListener(new MouseAdapter(){
                        public void mouseClicked(MouseEvent e){
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
            testGrid.setResizable(false);
            
            //Button for finishing the Nonogram
            JButton finish = new JButton("Finalize");
            finish.setFont(mostWazted);
            finish.setOpaque(true);
            finish.setLocation(25 + le*70, 55 + wi*70);
            finish.setBounds(225, 520, 150, 30);
            testGrid.add(finish); 
            testGrid.setVisible(true); 
            finish.addActionListener(new ActionListener(){
                
            public void actionPerformed(java.awt.event.ActionEvent event){
               int finalLHolder = 0, finalWHolder = 0;
               
               //generating the amount of markers for length
               for(int i = 0; i < le; i ++){
                   int currentMarkers = 0;
                   int currentLength = 0;

                   for(int j = 0; j < wi; j++){
                       if(Checker[j][i] == true)    //if true, we can increase length of current marker by 1
                          currentLength++;
                       if(Checker[j][i] == false)   //if false, the current streak ends
                          currentLength = 0;
                       if(currentLength == 1)       //if length is 1, ensures we must have a marker
                           currentMarkers++;
                       }
                       finalLHolder = Math.max(finalLHolder, currentMarkers);   //gets max amount of markers
                  }
                   //generating the amount of markers for width
                for(int i = 0; i < wi; i ++){
                    int currentMarkers = 0;
                    int currentLength = 0;

                    for(int j = 0; j < le; j++){
                       if(Checker[i][j] == true) //if true, we can increase length of current marker by 1
                           currentLength++;
                       if(Checker[i][j] == false) //if false, the current streak ends
                           currentLength = 0;
                       if(currentLength == 1)    //if length is 1, ensures we must have a marker  
                           currentMarkers++;
                       }
                       finalWHolder = Math.max(finalWHolder, currentMarkers); //gets max amount of markers
                  }

                   int LData[][] = new int[le][le];     //holds markers for length
                   int WData[][] = new int[wi][wi];     //holds markers for width
                   
                   //Storing the markers in their respectable arrays
                   for(int i = 0; i < le; i++){
                       int leIndex = 0;
                       int currentLength = 0;

                       for(int j = 0; j < wi; j++){
                           if(Checker[j][i] == true)    //currentLenght goes up by 1 if its true
                               currentLength++;
                           else if(Checker[j][i] == false && currentLength > 0){    //if we find a false and have a streak )markers
                               LData[i][leIndex] = currentLength;                   //we store this in the array, incrementor goes up by 1
                               leIndex++;
                               currentLength = 0;
                           }

                           if(j == wi - 1 && currentLength > 0)       //if there is a streak (markers) on the final element of 
                               LData[i][leIndex] = currentLength;     //the array, we must store it
                       }
                  }
                   
                   //We reverse for formatting purposes (as seen in Solve Nonogram) 
                   reverse(LData, le, finalLHolder);                           
                   reverse2(LData, finalLHolder, le);

                   //Store zereos in the back for formatting purposes (as seen in Solve Nonogram)
                   for(int i = 0; i < le; i++)
                       pushZerosToEnd(LData, i, finalLHolder);

                   for(int i = 0; i < wi; i++){
                       int wiIndex = 0;
                       int currentLength = 0;

                       for(int j = 0; j < le; j++){
                           if(Checker[i][j] == true)    //currentLenght goes up by 1 if its true
                               currentLength++;
                           else if(Checker[i][j] == false && currentLength > 0){
                               WData[i][wiIndex] = currentLength;   //if we find a false and have a streak )markers
                               wiIndex++;                           //we store this in the array, incrementor goes up by 1
                               currentLength = 0;
                           }

                           if(j == le - 1 && currentLength > 0)     //if there is a streak (markers) on the final element of
                               WData[i][wiIndex] = currentLength;   //the array, we must store it
                       }
                   }
                   
                   //We reverse for formatting purposes (as seen in Solve Nonogram) 
                   reverse2(WData, finalWHolder, wi);

                   //Store zereos in the back for formatting purposes (as seen in Solve Nonogram)
                   for(int i = 0; i < wi; i++)
                       pushZerosToEnd(WData, i, finalWHolder);

                   String nonoName;
                   
                   //Enter the name of the Nonogram (assuming it's not taken)
                   while(true){
                       try{
                           nonoName = JOptionPane.showInputDialog(null, "Enter the name of the nonogram:");
                           if (NonogramNameHolder.contains(nonoName)){
                               JOptionPane.showMessageDialog(null, "The selected name already exists. Please enter a valid name:");
                               continue;
                           }
                       } 
                       catch (Exception ee){
                           JOptionPane.showMessageDialog(null, "Invalid value for name.");
                           continue;
                       }
                       break;
                   }
                   
                   //Creating the Nonogram
                   Nonogram test = new Nonogram(nonoName, Checker, le, wi, LData, WData, finalLHolder, finalWHolder);
                   
                   //Stores the Nonogram in a file (appends)
                   FileOutputStream fout = null;

                   boolean exists = new File("output.dat").exists();
                   FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream("output.dat", true);
                    } 
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(FinalMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   ObjectOutputStream oos = null;
                    try {
                        oos = exists ? 
                                new ObjectOutputStream(fos) {
                                    protected void writeStreamHeader() throws IOException {
                                        reset();
                                    }
                                }:new ObjectOutputStream(fos);
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(FinalMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   try {
                       oos.writeObject(test);
                   } 
                   catch (IOException ex) {
                       Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  
                   //We only READ from the file once, thus we store it in the vector 
                  dataHolder.add(test);
                  NonogramNameHolder.add(test.getName());
                  testGrid.setVisible(false);
            }
                     
            });
        }
});
        
        //Generating the Nonogram 
        generate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                okSound();
                
                int wi = officialNonogram.getWidth(); 
                int le = officialNonogram.getLength();
                boolean[][] Checker = new boolean[wi][le];
                int xStart = 500, yStart = 0;
                JTextField[][] tpFields = new JTextField[le][le];
                JTextField[][] spFields = new JTextField[wi][wi];
                
                ImageIcon img2 = new ImageIcon("C://Users//Hunter//Desktop//NonogramAlpha//src//menu/showdown3.gif");
                JLabel background = new JLabel("", img2, JLabel.CENTER);
                
                JFrame testGrid = new JFrame("Solve the Nonogram!");
                testGrid.setContentPane(background);
                
                testGrid.setExtendedState(JFrame.NORMAL);
                testGrid.setAlwaysOnTop(true);

                for (int i=0;i<wi;i++){
                    for (int k = 0; k < le; k++){
                        final int ii = i;
                        final int kk = k; 
                        JButton temp = new JButton(); 
                        temp.setForeground(Color.white);
                        temp.setBackground(Color.white);
                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.gray, 1);
                        temp.setBorder(border);
                        temp.setOpaque(true);
                        int divisor = Math.max(le, wi);
                        int movement = 360/divisor;
                        temp.setSize(360/divisor, 360/divisor);
                        temp.setLocation(200 + movement*i, 200 + movement*k);
                        xStart = Math.min(xStart,200 + movement*i);
                        yStart = Math.max(yStart, 200 + movement*k);

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
                
            int divisor = Math.max(le, wi);
            int movement = 360/divisor;
            
            for(int roW = 0; roW < wi; roW++){
                for(int coM = 0; coM < officialNonogram.getMaxWidthHolder(); coM++){
                  spFields[roW][coM] = new JTextField();
                  if(officialNonogram.getWidthValue(roW, coM) > 0)
                  spFields[roW][coM].setText(Integer.toString(officialNonogram.getWidthValue(roW, coM)));
                  spFields[roW][coM].setBackground(Color.white);
                  spFields[roW][coM].setHorizontalAlignment(JTextField.CENTER);
                  javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);
                  spFields[roW][coM].setBorder(border);
                  spFields[roW][coM].setEditable(false);
                  spFields[roW][coM].setBounds(200+((360/divisor)*roW),yStart - (360/divisor)*le- (360/divisor)*coM,360/divisor,360/divisor);
                  testGrid.add(spFields[roW][coM]);
              }
           }
                
            int movementSize = 360/divisor;
                
            for(int coL = 0; coL < officialNonogram.getMaxLengthHolder(); coL++){
               for(int roW = 0; roW < le; roW++){
                    tpFields[roW][coL] = new JTextField();
                    
                    if(officialNonogram.getLengthValue(roW, coL) > 0)
                        tpFields[roW][coL].setText(Integer.toString(officialNonogram.getLengthValue(roW, coL)));
                    
                    tpFields[roW][coL].setBackground(Color.white);
                    tpFields[roW][coL].setHorizontalAlignment(JTextField.CENTER);
                    javax.swing.border.Border border = BorderFactory.createLineBorder(Color.black, 1);
                    tpFields[roW][coL].setBorder(border);
                    tpFields[roW][coL].setEditable(false);
                    testGrid.add(tpFields[roW][coL]);
                    tpFields[roW][coL].setBounds(xStart-(movementSize*coL)-movementSize,(200-movementSize)+(le*movementSize)-movementSize*roW,movementSize,movementSize);
                }
            }
            
            JFrame frame = new JFrame();
            testGrid.setLayout(null); 
            frame.setVisible(false);
            testGrid.setSize(600,650); 
            testGrid.setResizable(false);
            JButton finish = new JButton("Finalize");
            finish.setOpaque(true);
            finish.setLocation(25 + le*70, 55 + wi*70);
            finish.setFont(mostWazted);
            finish.setBounds(225, 590, 150, 30);
            testGrid.add(finish); 
            testGrid.setVisible(true); 
            
            final Nonogram item = officialNonogram;

            finish.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(java.awt.event.ActionEvent e) {
                      if(item.sameAnswer(Checker) == true){
                          final JDialog dialog = new JDialog();
                          dialog.setAlwaysOnTop(true);
                          dialog.setLocationRelativeTo(null);
                          JOptionPane.showMessageDialog(dialog, "You have solved the nonogram!");
                          testGrid.setVisible(false); 
                      }
                      else{
                           final JOptionPane pane = new JOptionPane("Incorrect");
                           final JDialog d = pane.createDialog((JFrame)null, "");
                           d.setLocationRelativeTo(testGrid);
                           d.setAlwaysOnTop(true);
                           d.setVisible(true);
                      }
                  }
            });
        }
    });
        
        mButton4.setOnAction(e-> {Window.setScene(startScene);
            backSound();
            officialSong.setMicrosecondPosition(0);
            officialSong.stop();
        });
       
       menuScene = new Scene(menuLayout, 600, 600);
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
    
    static void buttonHighlightedEffects(Button b){
        b.setScaleX(1.1);
        b.setScaleY(1.1);
        b.setEffect(new DropShadow());
    }
    
    static void buttonHighlightedEffects(ImageView b){
        b.setScaleX(1.1);
        b.setScaleY(1.1);
        b.setEffect(new DropShadow());
    }
    
    static void buttonNegateEffects(Button b){
        b.setScaleX(1);
        b.setScaleY(1);
        b.setEffect(null);
    }
    
    static void buttonNegateEffects(ImageView b){
        b.setScaleX(1);
        b.setScaleY(1);
        b.setEffect(null);
    }
    
    static void toggleSound(){
        //if(officialToggle.isActive() == false)
            officialToggle.setMicrosecondPosition(0);
        officialToggle.start();
    }
    
    static void okSound(){
        //if(officialOk.isActive() == false)
            officialOk.setMicrosecondPosition(0);
        officialOk.start();
    }
    
    static void backSound(){
        //if(officialBack.isActive() == false)
            officialBack.setMicrosecondPosition(0);
        officialBack.start();
    }
    
    static void changeSong(Clip c){
        officialSong.setMicrosecondPosition(0);
        officialSong.stop();
        officialSong = c;
        officialSong.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
