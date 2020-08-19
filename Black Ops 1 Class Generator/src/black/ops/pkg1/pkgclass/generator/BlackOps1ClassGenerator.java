/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black.ops.pkg1.pkgclass.generator;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Hunter
 */
public class BlackOps1ClassGenerator {
    
    //JFrame elements
    private JButton generate;
    private JButton buttonForPicture;
    private JButton primaryWeaponTitle;
    private JPanel panel;  
    private JButton at1;
    private JButton at2;
    private JButton sec_att;
    private JButton sec_att2;
    private JButton secondaryWeaponTitle;
    private JButton lethal;
    private JButton LethalTitle;
    private JButton TacticalTitle;
    private JButton EquipmentTitle;
    private JButton tactical;
    private JButton equipment;
    private JButton perk1;
    private JButton perk2;
    private JButton perk3;
    private JButton Perk1Title;
    private JButton Perk2Title;
    private JButton Perk3Title;
    private JButton KS1Title;
    private JButton KS2Title;
    private JButton KS3Title;
    private JButton ks1;
    private JButton ks2;
    private JButton ks3;
    private JFrame frame;
    private JButton secondaryPictureButton;
    
    //holder for weapon data/organized killstreaks/weapons
    private HashMap<String, ImageIcon> imageHolder;
    private HashMap<Integer, String[]> killstreaks;
    private PrimaryWeapon[][] weaponList;
    private Weapon[][] weaponList_secondary;
    
    //Arrays for easy access towards setting perks/lethal (we will use an index)
    public String [] Lethal = {"Semtex", "Frag", "Tomahawk"};
    public String [] Tactical = {"Willy Pete", "Nova Gas", "Flashbang", "Concussion", "Decoy"};
    public String [] Equipment = {"Claymore", "C4", "Camera Spike", "Tactical Insertion", "Jammer", "Motion Sensor"};
    public String[] Perk1 = {"Lightweight", "Scavenger", "Ghost", "Hardline", "Flak Jacket"};
    public String[] Perk2 = {"Hardened", "Scout", "Steady Aim", "Sleight of Hand", "Warlord"};
    public String[] Perk3 = {"Tactical Mask", "Marathon", "Ninja", "Second Chance", "Hacker"};
    
    //Fonts
    private Font blackOps;
    private Font blackOpsSmall;
    
    public static void main(String[] args) throws MalformedURLException {
        BlackOps1ClassGenerator Woods = new BlackOps1ClassGenerator();
        Woods.populateMeteData();
        Woods.initializeData();
    }
    
    public void initializeData(){
        
        initializeFont();
        formatWindow();
        
        frame.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                System.out.println("Buster!"); 
                changeEquipmentPerks();
                changeWeaponLoadOut();
                changeKillstreaks();
            } 
        }); 
        
        createWindow();
    }
    
    //Putting all of the images in the program
    public void populateImages() throws MalformedURLException{
        //initialize imageHolder
        imageHolder = new HashMap<String, ImageIcon>();
        
        //Guns
        imageHolder.put("M-16", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/8/8a/M16_ELITE.png/revision/latest?cb=20130204231104")));
        imageHolder.put("Enfield", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/6c/ELITE_Enfield.png/revision/latest?cb=20120106112205")));
        imageHolder.put("M14", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/42/ELITE_M14.png/revision/latest?cb=20120106104354")));
        imageHolder.put("Famas", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/2d/ELITE_Famas.png/revision/latest?cb=20120106111729")));
        imageHolder.put("Galil", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/68/ELITE_Galil.png/revision/latest?cb=20120106110812")));
        imageHolder.put("AUG", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/43/ELITE_AUG.png/revision/latest?cb=20120106112750")));
        imageHolder.put("FN-FAL", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/28/ELITE_FN_FAL.png/revision/latest?cb=20120106111422")));
        imageHolder.put("AK-47", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f4/ELITE_AK-47.png/revision/latest?cb=20120106113032")));
        imageHolder.put("Commando", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/28/Commando_menu_icon_BO.png/revision/latest?cb=20120120043231")));
        imageHolder.put("MP5K", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/65/MP5K_menu_icon.png/revision/latest/zoom-crop/width/360/height/360?cb=20120120043427")));
        imageHolder.put("Skorpion", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/c5/Skorpion_menu_icon_BO.png/revision/latest?cb=20120120043514")));
        imageHolder.put("G11", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/8/8c/ELITE_G11.png/revision/latest?cb=20120106111109")));
        imageHolder.put("MAC11", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/5/53/MAC11_menu_icon_BO.png/revision/latest?cb=20120120043409")));
        imageHolder.put("AK74u", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/1/12/Menu_mp_weapons_ak74u.png/revision/latest?cb=20120120042903")));
        imageHolder.put("Uzi", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d2/Uzi_menu_icon_BO.png/revision/latest?cb=20120120043543")));
        imageHolder.put("PM63", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/92/PM63_menu_icon_BO.png/revision/latest?cb=20170816225001")));
        imageHolder.put("MPL", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/b/be/ELITE_MPL.png/revision/latest?cb=20120106123648")));
        imageHolder.put("Spectre", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/5/57/ELITE_Spectre.png/revision/latest?cb=20120106124200")));
        imageHolder.put("Kiparis", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d5/Kiparis_Menu_BO.png/revision/latest?cb=20170816225056")));
        imageHolder.put("Olympia", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/20/ELITE_Olympia.png/revision/latest?cb=20120106121745")));
        imageHolder.put("Stakeout", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/cf/Stakeout_menu_icon_BO.png/revision/latest?cb=20140404185013")));
        imageHolder.put("SPAS-12", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/91/ELITE_Spas.png/revision/latest?cb=20120106122109")));
        imageHolder.put("HS10", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/b/b4/HS10_menu_icon_BO.png/revision/latest?cb=20170816225106")));
        imageHolder.put("Dragunov", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/4e/Dragunov_Menu_Icon_Black_Ops.png/revision/latest?cb=20120120043248")));
        imageHolder.put("WA2000", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/46/WA2000_menu_icon_BO.png/revision/latest?cb=20170908123106")));
        imageHolder.put("L96A1", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/66/Menu_mp_weapons_l96a1.png/revision/latest/top-crop/width/360/height/450?cb=20120120043345")));
        imageHolder.put("PSG1", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/73/PSG1_menu_icon_BO.png/revision/latest?cb=20170908122513")));
        imageHolder.put("HK21", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/e/e8/HK21_Menu_Icon_BO.png/revision/latest?cb=20170816225114")));
        imageHolder.put("RPK", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f3/RPK_menu_icon_BO.png/revision/latest?cb=20170816224838")));
        imageHolder.put("M60", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/df/M60_menu_icon_BO.png/revision/latest?cb=20170816225054")));
        imageHolder.put("Stoner63", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/e/e3/ELITE_Stoner63.png/revision/latest?cb=20120106114520")));
        imageHolder.put("ASP", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/77/ASP_menu_icon_BO.png/revision/latest?cb=20120120042909")));
        imageHolder.put("M1911", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/5/55/Menu_mp_weapons_colt.png/revision/latest?cb=20120218215747")));
        imageHolder.put("Makarov", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/71/Makarov_menu_icon_BO.png/revision/latest?cb=20120120043415")));
        imageHolder.put("Python", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/8/89/Python_menu_icon_BO.png/revision/latest?cb=20120120043450")));
        imageHolder.put("CZ75", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a2/CZ75_menu_icon_BO.png/revision/latest?cb=20120120043242")));
        imageHolder.put("M72 LAW", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/cb/M72_LAW_menu_icon_BO.png/revision/latest?cb=20170908123200")));
        imageHolder.put("RPG", new ImageIcon(new URL("https://cod7.weebly.com/uploads/8/1/0/1/8101565/3405518.png")));
        imageHolder.put("Strela-3", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/3/31/Streal-3_menu_icon_BO.png/revision/latest?cb=20170908123040")));
        imageHolder.put("China Lake", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/75/ELITE_China_Lake.png/revision/latest?cb=20120106103012")));
        imageHolder.put("Ballistic Knife", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d9/ELITE_Ballistic_Knife.png/revision/latest?cb=20120106102406")));
        imageHolder.put("Crossbow", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/28/ELITE_Crossbow.png/revision/latest?cb=20120106102659")));
        
        //Equipment/Tactical/Lethal
        imageHolder.put("Semtex", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/26/Semtex_menu_icon_BO.png/revision/latest/zoom-crop/width/360/height/360?cb=20170908122131")));
        imageHolder.put("Frag", new ImageIcon(new URL("https://cod7.weebly.com/uploads/8/1/0/1/8101565/2257729.png")));
        imageHolder.put("Tomahawk", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/e/ea/Tomahawk.png/revision/latest/top-crop/width/360/height/360?cb=20170908122320")));
        imageHolder.put("Willy Pete", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d2/Willy_Pete_menu_icon_BO.PNG/revision/latest/top-crop/width/360/height/450?cb=20120118055805")));
        imageHolder.put("Nova Gas", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/df/Hud_tabun_gasgrenade.png/revision/latest?cb=20120119082750")));
        imageHolder.put("Flashbang", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f2/ELITE_Flashbang.png/revision/latest?cb=20120106131928")));
        imageHolder.put("Concussion", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/0/03/Concussion_Grenade_menu_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20120119082803")));
        imageHolder.put("Decoy", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/1/19/Decoy_Grenade_menu_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20120119082742")));
        imageHolder.put("Claymore", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/da/Claymore_menu_icon_BO.png/revision/latest?cb=20120106135140")));
        imageHolder.put("C4", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d0/C4_menu_icon_BO.png/revision/latest?cb=20120106134504")));
        imageHolder.put("Camera Spike", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/e/e2/Camera_Spike_menu_icon_BO.png/revision/latest/top-crop/width/360/height/360?cb=20120106134046")));
        imageHolder.put("Tactical Insertion", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/1/1c/Tactical_Insertion_menu_icon_BO.png/revision/latest/zoom-crop/width/360/height/360?cb=20120106134657")));
        imageHolder.put("Jammer", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f6/Jammer_menu_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20120106134847")));
        imageHolder.put("Motion Sensor", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/8/8f/Motion_Sensor_menu_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20120106134251")));
        
        //perks
        imageHolder.put("Lightweight", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f8/Perk_lightweight_256_pro.png/revision/latest/scale-to-width-down/360?cb=20120120172601")));
        imageHolder.put("Scavenger", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/3/35/BO_Perk_Scavenger.png/revision/latest?cb=20120117044838")));
        imageHolder.put("Ghost", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/97/Perk_ghost_256.png/revision/latest?cb=20120120172513")));
        imageHolder.put("Hardline", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/1/10/BO_Perk_Hardline.png/revision/latest/scale-to-width-down/360?cb=20120117044813")));
        imageHolder.put("Flak Jacket", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/61/BO_Perk_Flak_Jacket.png/revision/latest/scale-to-width-down/360?cb=20120117044802")));
        imageHolder.put("Hardened", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/0/02/BO_hardened_p3rk.png/revision/latest?cb=20120117045605")));
        imageHolder.put("Scout", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/dc/BO_scout_p3rk.png/revision/latest/scale-to-width-down/360?cb=20120117045954")));
        imageHolder.put("Steady Aim", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/cf/Perk_steady_aim_256_pro.png/revision/latest?cb=20120120172818")));
        imageHolder.put("Sleight of Hand", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/20/Perk_sleight_of_hand_256_pr.png/revision/latest/scale-to-width-down/360?cb=20120120172756")));
        imageHolder.put("Warlord", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/c8/Warlord.png/revision/latest/scale-to-width-down/360?cb=20120122162409")));
        imageHolder.put("Tactical Mask", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/97/Perk_tactical_mask_256.png/revision/latest/scale-to-width-down/360?cb=20120120172829")));
        imageHolder.put("Marathon", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/93/MarathonBO.png/revision/latest/scale-to-width-down/360?cb=20120120023154")));
        imageHolder.put("Ninja", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/21/Ninja.png/revision/latest?cb=20120120095049")));
        imageHolder.put("Second Chance", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/8/8a/Perk_second_chance_256.png/revision/latest/scale-to-width-down/360?cb=20120120172723")));
        
        //killstreaks
        imageHolder.put("Hacker", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/f/f7/BO_hacker_p3rk.png/revision/latest?cb=20120117045552")));
        imageHolder.put("Spy Plane", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/5/5e/Spy_plane_large.png/revision/latest?cb=20160623094037")));
        imageHolder.put("RC-XD", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d4/RC-XD_render_Elite_BO.png/revision/latest?cb=20110925082929")));
        imageHolder.put("Counter-Spy Plane", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/69/Counter-Spy_Plane_HUD_icon_BO.png/revision/latest?cb=20160622062217")));
        imageHolder.put("SAM Turret", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/7b/Sam_turret_drop_large.png/revision/latest?cb=20110925083253")));
        imageHolder.put("Care Package", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/c1/Care_Package_HUD_icon_BO.png/revision/latest?cb=20160622044921")));
        imageHolder.put("Napalm Strike", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/c6/Napalm_large.png/revision/latest?cb=20110925083458")));
        imageHolder.put("Sentry Gun", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a9/Sentry_Gun_HUD_icon_BO.png/revision/latest?cb=20160623102910")));
        imageHolder.put("Mortar Team", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/b/b4/Mortar_Team_HUD_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20160622050240")));
        imageHolder.put("Attack Helicopter", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/5/5b/ELITE_Attack_Helicopter.png/revision/latest?cb=20120106033827")));
        imageHolder.put("Valkyrie Rockets", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/7/75/Valkyrie_Rockets_HUD_icon_BO.png/revision/latest/zoom-crop/width/360/height/360?cb=20160702035612")));
        imageHolder.put("Blackbird", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/69/ELITE_SR-71.png/revision/latest?cb=20120106032607")));
        imageHolder.put("Rolling Thunder", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a1/Rolling_Thunder_HUD_icon_BO.png/revision/latest?cb=20160619111352")));
        imageHolder.put("Chopper Gunner", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/ad/Chopper_Gunner_HUD_icon_BO.png/revision/latest/zoom-crop/width/360/height/360?cb=20160624115137")));
        imageHolder.put("Attack Dogs", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/64/Attack_dogs_ELITE.png/revision/latest?cb=20110925082619")));
        imageHolder.put("Gunship", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a5/Gunship_HUD_icon_BO.png/revision/latest/top-crop/width/360/height/450?cb=20160703040110")));
        imageHolder.put("Extended Mag", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/aa/Extended_Mags_menu_icon_CoDO.png/revision/latest?cb=20150802221032")));
        imageHolder.put("Dual Mag", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a8/Menu_mp_weapons_attach_dual.png/revision/latest?cb=20180204145658")));
        imageHolder.put("Suppressor", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/3/3c/Suppressor_Menu_icon_BOII.png/revision/latest?cb=20121222135658")));
        imageHolder.put("SCOPE ACOG", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/66/ELITE_ACOG_Scope.png/revision/latest?cb=20120106031456")));
        imageHolder.put("SCOPE Red Dot Sight", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/4c/ELITE_Red_Dot_Sight.png/revision/latest?cb=20120105154241")));
        imageHolder.put("SCOPE Reflex Sight", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/0/0a/ELITE_Reflex_Sight.png/revision/latest?cb=20120105153434")));
        imageHolder.put("SCOPE Infrared Scope", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/ab/ELITE_Infrared_Scope.png/revision/latest?cb=20120118193502")));
        imageHolder.put("DAMAGE Flamethrower", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/c/c3/ELITE_Flamethrower.png/revision/latest?cb=20120105152416")));
        imageHolder.put("DAMAGE Masterkey", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/4/4d/Menu_mp_weapons_attach_mast.png/revision/latest?cb=20120120042948")));
        imageHolder.put("DAMAGE Grenade Launcher", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/6/61/Grenade_Launcher_menu_icon_MW3.png/revision/latest?cb=20120308145400")));
        imageHolder.put("Rapid Fire", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/2/2e/Rapid_Fire_menu_icon_MW3.png/revision/latest?cb=20120112172821")));
        imageHolder.put("Grip", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/b/b8/ELITE_Grip.png/revision/latest?cb=20120106030638")));
        imageHolder.put("Full Auto Upgrade", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/d/d4/ELITE_Rapid_and_Auto.png/revision/latest?cb=20120105154614")));
        imageHolder.put("Dual Wield", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/1/12/ELITE_Dual_Wield.png/revision/latest?cb=20120105150005")));
        imageHolder.put("SCOPE Upgraded Iron Sights", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/9/90/ELITE_Upgraded_Iron_Sights.png/revision/latest?cb=20120105145528")));
        imageHolder.put("Speed Reloader", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a3/ELITE_Speed_Reloader_BO.png/revision/latest?cb=20120105151511")));
        imageHolder.put("Snub Nose", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/a/a1/Menu_mp_weapons_attach_snub.png/revision/latest/top-crop/width/360/height/450?cb=20120120043004")));
        imageHolder.put("SCOPE Variable Zoom", new ImageIcon(new URL("https://vignette.wikia.nocookie.net/callofduty/images/0/02/ELITE_Variable_Zoom.png/revision/latest?cb=20120106025851")));
    }   //SCOPE Infared Scope
    
    public void populateKillstreaks(){
        killstreaks = new HashMap<Integer, String[]>();
        killstreaks.put(3, new String[]{"Spy Plane", "RC-XD"});
        killstreaks.put(4, new String[]{"Counter-Spy Plane", "SAM Turret"});
        killstreaks.put(5, new String[]{"Care Package", "Napalm Strike"});
        killstreaks.put(6, new String[]{"Sentry Gun", "Mortar Team"});
        killstreaks.put(7, new String[]{"Attack Helicopter", "Valkyrie Rockets"});
        killstreaks.put(8, new String[]{"Blackbird", "Rolling Thunder"});
        killstreaks.put(9, new String[]{"Chopper Gunner"});
        killstreaks.put(11, new String[]{"Attack Dogs", "Gunship"});
    }
    
    public void populatePrimaryWeapons(){
        weaponList = new PrimaryWeapon[5][10];
        String[] attachmentStandard = {"Extended Mag", "Dual Mag", "Suppressor", "SCOPE ACOG", "SCOPE Red Dot Sight",
        "SCOPE Reflex Sight", "SCOPE Infrared Scope", "DAMAGE Flamethrower", "DAMAGE Masterkey", "DAMAGE Grenade Launcher",""};
        String [] subMachine1 = {"Extended Mag", "SCOPE ACOG", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Suppressor", "Rapid Fire",""};
        String [] AK74u = {"Dual Mag", "Extended Mag", "SCOPE ACOG", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Rapid Fire", "DAMAGE Grenade Launcher", "Grip", "Suppressor",""};
        
        //Assault Rifles
        weaponList[0][0] = new PrimaryWeapon("M-16", 65, 80, 85, 90, 30, "NA", attachmentStandard);
        weaponList[0][1] = new PrimaryWeapon("Enfield", 60, 80, 70, 90, 70, "NA", attachmentStandard);
        weaponList[0][2] = new PrimaryWeapon("M14", 80, 80, 60, 90, 10, "NA", attachmentStandard);
        weaponList[0][3] = new PrimaryWeapon("Famas", 60, 80, 70, 90, 80, "NA", attachmentStandard);
        weaponList[0][4] = new PrimaryWeapon("Galil", 60, 80, 70, 90, 70, "NA", attachmentStandard);
        weaponList[0][5] = new PrimaryWeapon("AUG", 60, 80, 70, 90, 80, "NA", attachmentStandard);
        weaponList[0][6] = new PrimaryWeapon("FN-FAL", 85, 80, 60, 90, 10, "NA", attachmentStandard);
        weaponList[0][7] = new PrimaryWeapon("AK-47", 65, 80, 60, 90, 70, "NA", attachmentStandard);
        weaponList[0][8] = new PrimaryWeapon("Commando", 65, 80, 70, 90, 70, "NA", attachmentStandard);
        weaponList[0][9] = new PrimaryWeapon("G11", 60, 80, 90, 90, 30, "NA", new String[]{"Low Power Scope", "Variable Zoom",""});
        
        //Submachine Guns
        weaponList[1][0] = new PrimaryWeapon("MP5K", 70, 70, 85, 100, 80, "NA", subMachine1);
        weaponList[1][1] = new PrimaryWeapon("Skorpion", 80, 40, 60, 100, 80, "NA", new String[]{"Extended Mag", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        weaponList[1][2] = new PrimaryWeapon("MAC11", 80, 40, 70, 100, 80, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        weaponList[1][3] = new PrimaryWeapon("AK74u", 70, 70, 60, 100, 70, "NA", AK74u);
        weaponList[1][4] = new PrimaryWeapon("Uzi", 60, 70, 60, 100, 80, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        weaponList[1][5] = new PrimaryWeapon("PM63", 70, 70, 85, 100, 80, "NA", new String[]{"Extended Mag", "Grip", "Dual Wield", "Rapid Fire"});
        weaponList[1][6] = new PrimaryWeapon("MPL", 70, 70, 85, 100, 80, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        weaponList[1][7] = new PrimaryWeapon("Spectre", 70, 70, 70, 100, 80, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        weaponList[1][8] = new PrimaryWeapon("Kiparis", 70, 70, 70, 100, 80, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "SCOPE ACOG", "Grip", "Dual Wield", "Suppressor", "Rapid Fire",""});
        
        //Shotguns
        weaponList[2][0] = new PrimaryWeapon("Olympia", 90, 40, 60, 100, 20, "NA", new String[]{""});
        weaponList[2][1] = new PrimaryWeapon("Stakeout", 85, 20, 40, 100, 21, "NA", new String[]{"Grip",""});
        weaponList[2][2] = new PrimaryWeapon("SPAS-12", 70, 30, 70, 100, 10, "NA", new String[]{"Suppressor",""});
        weaponList[2][3] = new PrimaryWeapon("HS10", 85, 30, 70, 100, 10, "NA", new String[]{"Dual Wield",""});
        
        //Snipers
        weaponList[3][0] = new PrimaryWeapon("Dragunov", 70, 90, 65, 90, 10, "NA", new String[]{"Extended Mag", "SCOPE ACOG", "SCOPE Infrared Scope", "Suppressor", "SCOPE Variable Zoom",""});
        weaponList[3][1] = new PrimaryWeapon("WA2000", 75, 90, 70, 90, 10, "NA", new String[]{"Extended Mag", "SCOPE ACOG", "SCOPE Infrared Scope", "Suppressor", "SCOPE Variable Zoom",""});
        weaponList[3][2] = new PrimaryWeapon("L96A1", 90, 90, 75, 90, 12, "NA", new String[]{"Extended Mag", "SCOPE ACOG", "SCOPE Infrared Scope", "Suppressor", "SCOPE Variable Zoom",""});
        weaponList[3][3] = new PrimaryWeapon("PSG1", 80, 90, 80, 90, 10, "NA", new String[]{"Extended Mag", "SCOPE ACOG", "SCOPE Infrared Scope", "Suppressor", "SCOPE Variable Zoom",""});
        
        //Machine Guns
        weaponList[4][0] = new PrimaryWeapon("HK21", 65, 75, 65, 80, 65, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "SCOPE Infrared Scope",""});
        weaponList[4][1] = new PrimaryWeapon("RPK", 65, 80, 70, 80, 80, "NA", new String[]{"Extended Mag", "Dual Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "SCOPE Infrared Scope",""});
        weaponList[4][2] = new PrimaryWeapon("M60", 80, 65, 50, 80, 40, "NA", new String[]{"Extended Mag", "Grip", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "SCOPE Infrared Scope",""});
        weaponList[4][3] = new PrimaryWeapon("Stoner63", 65, 80, 80, 80, 70, "NA", new String[]{"Extended Mag", "SCOPE Red Dot Sight", "SCOPE Reflex Sight", "SCOPE Infrared Scope",""});
    }
    
    public void populateSecondaryWeapons(){
        weaponList_secondary = new Weapon[3][5];
        
        //Pistols
        weaponList_secondary[0][0] = new Weapon("ASP", 40, 50, 85, 100, 10, new String[]{"Dual Wield", ""});
        weaponList_secondary[0][1] = new Weapon("M1911", 50, 50, 85, 100, 10, new String[]{"Dual Wield", "SCOPE Upgraded Iron Sights", "Suppressor", "Extended Mag", ""});
        weaponList_secondary[0][2] = new Weapon("Makarov", 55, 55, 65, 100, 10, new String[]{"Dual Wield", "SCOPE Upgraded Iron Sights", "Suppressor", "Extended Mag", ""});
        weaponList_secondary[0][3] = new Weapon("Python", 85, 85, 40, 100, 13, new String[]{"Dual Wield", "SCOPE ACOG", "Snub Nose", "Speed Reloader", ""});
        weaponList_secondary[0][4] = new Weapon("CZ75", 65, 60, 70, 100, 10, new String[]{"Dual Wield", "SCOPE Upgraded Iron Sights", "Suppressor", "Extended Mag", "Full Auto Upgrade", ""});
        
        //Launchers
        weaponList_secondary[1][0] = new Weapon("M72 LAW", 100, 50, 40, 80, 14, new String[]{""});
        weaponList_secondary[1][1] = new Weapon("RPG", 100, 50, 40, 80, 15, new String[]{""});
        weaponList_secondary[1][2] = new Weapon("Strela-3", 100, 100, 100, 80, 16, new String[]{""});
        weaponList_secondary[1][3] = new Weapon("China Lake", 100, 50, 50, 80, 17, new String[]{""});
        
        //Specials
        weaponList_secondary[2][0] = new Weapon("Ballistic Knife", 100, 30, 50, 80, 18, new String[]{""});
        weaponList_secondary[2][1] = new Weapon("Crossbow", 100, 50, 50, 80, 18, new String[]{""});
    }
    
    public void populateMeteData() throws MalformedURLException{
        
        //initializing
        populateKillstreaks();
        populatePrimaryWeapons();
        populateSecondaryWeapons();
        populateImages();
    }
    
    public void changeEquipmentPerks(){
        
            //I do index first because the attachments are dependent on Warlord Perk
            int index = (int)(Math.random()*(Lethal.length));
            LethalTitle.setText(Lethal[index]);
            Image primary_image = imageHolder.get(Lethal[index]).getImage();
            Image primary_image2 = primary_image.getScaledInstance(lethal.getWidth(), lethal.getHeight(), Image.SCALE_SMOOTH);
            lethal.setIcon(new ImageIcon(primary_image2));

            index = (int)(Math.random()*(Tactical.length));
            TacticalTitle.setText(Tactical[index]);
            primary_image = imageHolder.get(Tactical[index]).getImage();
            primary_image2 = primary_image.getScaledInstance(tactical.getWidth(), tactical.getHeight(), Image.SCALE_SMOOTH);
            tactical.setIcon(new ImageIcon(primary_image2));

            index = (int)(Math.random()*(Equipment.length));
            EquipmentTitle.setText(Equipment[index]);
            primary_image = imageHolder.get(Equipment[index]).getImage();
            primary_image2 = primary_image.getScaledInstance(equipment.getWidth(), equipment.getHeight(), Image.SCALE_SMOOTH);
            equipment.setIcon(new ImageIcon(primary_image2));

            index = (int)(Math.random()*(Perk1.length));
            Perk1Title.setText(Perk1[index]);
            primary_image = imageHolder.get(Perk1[index]).getImage();
            primary_image2 = primary_image.getScaledInstance(perk1.getWidth(), perk1.getHeight(), Image.SCALE_SMOOTH);
            perk1.setIcon(new ImageIcon(primary_image2));

            index = (int)(Math.random()*(Perk2.length));
            Perk2Title.setText(Perk2[index]);
            primary_image = imageHolder.get(Perk2[index]).getImage();
            primary_image2 = primary_image.getScaledInstance(perk2.getWidth(), perk2.getHeight(), Image.SCALE_SMOOTH);
            perk2.setIcon(new ImageIcon(primary_image2));

            index = (int)(Math.random()*(Perk3.length));
            Perk3Title.setText(Perk3[index]);
            primary_image = imageHolder.get(Perk3[index]).getImage();
            primary_image2 = primary_image.getScaledInstance(perk3.getWidth(), perk3.getHeight(), Image.SCALE_SMOOTH);
            perk3.setIcon(new ImageIcon(primary_image2));
    }
    
    public void changeWeaponLoadOut(){
        //While I am aware it can generate 5 (which is an invalid parameter), I use it to up the consistency of index 4
        int classIndex = (int)(Math.random()*5);
        if(classIndex == 5) classIndex--;

        int gunIndex = 0;
            switch(classIndex){
                case 0:
                    gunIndex = (int)(Math.random()*(weaponList[classIndex].length-1));
                    break;
                case 1:
                    gunIndex = (int)(Math.random()*(weaponList[classIndex].length-2));
                    break;
                default:
                    gunIndex = (int)(Math.random()*(weaponList[classIndex].length-7));
                    break;
            }
            
            

            PrimaryWeapon temp = weaponList[classIndex][gunIndex];
            primaryWeaponTitle.setText(temp.getName());
            Image primary_image = imageHolder.get(temp.getName()).getImage();
            Image primary_image2 = primary_image.getScaledInstance(buttonForPicture.getWidth(), buttonForPicture.getHeight(), Image.SCALE_SMOOTH);
            buttonForPicture.setIcon(new ImageIcon(primary_image2));

            String attachment1 = weaponList[classIndex][gunIndex].getAttachment();
            String attachment2; 

            do{
                attachment2 = weaponList[classIndex][gunIndex].getAttachment();
            }while((attachment1.contains("SCOPE") && attachment2.contains("SCOPE")) || (attachment1.contains("DAMAGE") && attachment2.contains("DAMAGE")) || (attachment1.contains("Mag") && attachment2.contains("Mag")));

            //basic swap
            if(attachment2 != "" && attachment1 == ""){
                String tempHolder = attachment1;
                attachment1 = attachment2;
                attachment2  = tempHolder;
            }

            //Remove duplicates
            if(attachment1 == attachment2){
                attachment2 = "";
            }

            //removes the picture if there is no attachment
            if(attachment1 == "")
                at1.setIcon(null);
            else{
                primary_image = imageHolder.get(attachment1).getImage();
                primary_image2 = primary_image.getScaledInstance(at1.getWidth(), at1.getHeight(), Image.SCALE_SMOOTH);
                at1.setIcon(new ImageIcon(primary_image2));
            }

            //We can only have multiple attachments with Warlord perk
            if(attachment2 == "" || Perk2Title.getText() != "Warlord")
                at2.setIcon(null);
            else{
                primary_image = imageHolder.get(attachment2).getImage();
                primary_image2 = primary_image.getScaledInstance(at2.getWidth(), at2.getHeight(), Image.SCALE_SMOOTH);
                at2.setIcon(new ImageIcon(primary_image2));
            }

            classIndex = (int)(Math.random()*3);
            
            if(classIndex == 3) classIndex--;
            
            gunIndex = 0;

            switch(classIndex){
                case 0:
                    gunIndex = (int)(Math.random()*(weaponList_secondary[classIndex].length));
                    break;
                case 1:
                    gunIndex = (int)(Math.random()*(weaponList_secondary[classIndex].length-1));
                    break;
                default:
                    gunIndex = (int)(Math.random()*(weaponList_secondary[classIndex].length-3));
                    break;
            }

            Weapon tempWeapon = weaponList_secondary[classIndex][gunIndex];
            secondaryWeaponTitle.setText(tempWeapon.getName());

            primary_image = imageHolder.get(tempWeapon.getName()).getImage();
            primary_image2 = primary_image.getScaledInstance(secondaryPictureButton.getWidth(), secondaryPictureButton.getHeight(), Image.SCALE_SMOOTH);
            secondaryPictureButton.setIcon(new ImageIcon(primary_image2));

            attachment1 = weaponList_secondary[classIndex][gunIndex].getAttachment();

            do{
                attachment2 = weaponList_secondary[classIndex][gunIndex].getAttachment();
            }while((attachment1.contains("SCOPE") && attachment2.contains("SCOPE")) || (attachment1.contains("DAMAGE") && attachment2.contains("DAMAGE")) || (attachment1.contains("Mag") && attachment2.contains("Mag")));

            //basic swap
            if(attachment2 != "" && attachment1 == ""){
                String tempHolder = attachment1;
                attachment1 = attachment2;
                attachment2  = tempHolder;
            }

            if(attachment1 == attachment2){
                attachment2 = "";
            }

            if(attachment1 == "")
                sec_att.setIcon(null);
            else{
                primary_image = imageHolder.get(attachment1).getImage();
                primary_image2 = primary_image.getScaledInstance(sec_att.getWidth(), sec_att.getHeight(), Image.SCALE_SMOOTH);
                sec_att.setIcon(new ImageIcon(primary_image2));
            }

            if(attachment2 == "" || Perk2Title.getText() != "Warlord")
                sec_att2.setIcon(null);
            else{
                primary_image = imageHolder.get(attachment2).getImage();
                primary_image2 = primary_image.getScaledInstance(sec_att.getWidth(), sec_att.getHeight(), Image.SCALE_SMOOTH);
                sec_att2.setIcon(new ImageIcon(primary_image2));
            }
    }
    
    public void changeKillstreaks(){
        TreeMap<Integer, String> killstreakOfficial = new TreeMap<Integer, String>();
            
        int officialSize = 0;

        while(officialSize < 3){
            int amountOfKills = (int)(Math.random()*8)+3;

            while(amountOfKills == 10){
                amountOfKills = (int)(Math.random()*8)+3;
            }

            if(!killstreakOfficial.containsKey(amountOfKills)){
                String[] holder = killstreaks.get(amountOfKills);

                killstreakOfficial.put(amountOfKills, holder[(int)(Math.random()*holder.length)]);
                officialSize++;
            }
        }

        String[] officialKSHolder = new String[3];
        int currentArrayIndex = 0;

        for (Map.Entry<Integer, String> entry : killstreakOfficial.entrySet()){
             officialKSHolder[currentArrayIndex] = entry.getValue();
             currentArrayIndex++;
        } 

        currentArrayIndex = 0;

        Image primary_image = imageHolder.get(officialKSHolder[currentArrayIndex]).getImage();
        KS1Title.setText(officialKSHolder[currentArrayIndex]);
        Image primary_image2 = primary_image.getScaledInstance(ks1.getWidth(), ks1.getHeight(), Image.SCALE_SMOOTH);
        ks1.setIcon(new ImageIcon(primary_image2));

        currentArrayIndex++;

        KS2Title.setText(officialKSHolder[currentArrayIndex]);
        primary_image = imageHolder.get(officialKSHolder[currentArrayIndex]).getImage();
        primary_image2 = primary_image.getScaledInstance(ks2.getWidth(), ks2.getHeight(), Image.SCALE_SMOOTH);
        ks2.setIcon(new ImageIcon(primary_image2));

        currentArrayIndex++;

        KS3Title.setText(officialKSHolder[currentArrayIndex]);
        primary_image = imageHolder.get(officialKSHolder[currentArrayIndex]).getImage();
        primary_image2 = primary_image.getScaledInstance(ks3.getWidth(), ks3.getHeight(), Image.SCALE_SMOOTH);
        ks3.setIcon(new ImageIcon(primary_image2));
    }
    
    public void createWindow(){
        frame.add(buttonForPicture);
        frame.add(primaryWeaponTitle);
        frame.add(panel);  
        frame.setSize(600, 600);  
        frame.setResizable(false);
        frame.setLayout(null);
        frame.add(at1);
        frame.add(at2);
        frame.add(sec_att);
        frame.add(sec_att2);
        frame.add(secondaryWeaponTitle);
        frame.add(lethal);
        frame.add(LethalTitle);
        frame.add(TacticalTitle);
        frame.add(EquipmentTitle);
        frame.add(tactical);
        frame.add(equipment);
        frame.add(perk1);
        frame.add(perk2);
        frame.add(perk3);
        frame.add(Perk1Title);
        frame.add(Perk2Title);
        frame.add(Perk3Title);
        frame.add(KS1Title);
        frame.add(KS2Title);
        frame.add(KS3Title);
        frame.add(ks1);
        frame.add(ks2);
        frame.add(ks3);
        frame.add(secondaryPictureButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void initializeFont(){
        blackOps = null;
        blackOpsSmall = null;
        
        try {
            GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
            blackOps = Font.createFont(Font.TRUETYPE_FONT, getResourceAsStream("BlackOpsOne-Regular.ttf")).deriveFont(25f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getResourceAsStream("BlackOpsOne-Regular.ttf")));
            blackOpsSmall = blackOps.deriveFont(20f);
        } catch (IOException|FontFormatException e) {
            System.out.println(e);
        }
    }
    
    
    public void formatWindow(){
        frame = new JFrame("Black Ops 1 Class Generator"); 
        panel = new JPanel(); 
        
        buttonForPicture = new JButton("AK-47");
        buttonForPicture.setVerticalAlignment(SwingConstants.BOTTOM);
        buttonForPicture.setBorder(new EmptyBorder(0, 0, 0, 0));
        buttonForPicture.setOpaque(true);
        buttonForPicture.setContentAreaFilled(false);
        buttonForPicture.setSize(250, 114);
        
        secondaryPictureButton = new JButton("");
        secondaryPictureButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        secondaryPictureButton.setOpaque(false);
        secondaryPictureButton.setContentAreaFilled(false);
        secondaryPictureButton.setSize(200, 84);
        secondaryPictureButton.setLocation(350, 40);
        
        at1 = new JButton("");
        at1.setBorder(new EmptyBorder(0, 0, 0, 0));
        at1.setOpaque(false);
        at1.setContentAreaFilled(false);
        at1.setSize(50, 50);
        
        at2 = new JButton("");
        at2.setBorder(new EmptyBorder(0, 0, 0, 0));
        at2.setOpaque(false);
        at2.setContentAreaFilled(false);
        at2.setSize(50, 50);
        
        sec_att = new JButton("");
        sec_att.setBorder(new EmptyBorder(0, 0, 0, 0));
        sec_att.setOpaque(false);
        sec_att.setContentAreaFilled(false);
        sec_att.setSize(50, 50);
        
        sec_att2 = new JButton("");
        sec_att2.setBorder(new EmptyBorder(0, 0, 0, 0));
        sec_att2.setOpaque(false);
        sec_att2.setContentAreaFilled(false);
        sec_att2.setSize(50, 50);
        
        lethal = new JButton("");
        lethal.setBorder(new EmptyBorder(0, 0, 0, 0));
        lethal.setOpaque(false);
        lethal.setContentAreaFilled(false);
        lethal.setSize(80, 80);
        
        tactical = new JButton("");
        tactical.setBorder(new EmptyBorder(0, 0, 0, 0));
        tactical.setOpaque(false);
        tactical.setContentAreaFilled(false);
        tactical.setSize(80, 80);
        
        Image tactical_image = imageHolder.get("Flashbang").getImage();
        Image tactical_image2 = tactical_image.getScaledInstance(tactical.getWidth(), tactical.getHeight(), Image.SCALE_SMOOTH);
        tactical.setIcon(new ImageIcon(tactical_image2));
        tactical.setLocation(260, 204);
        
        equipment = new JButton("");
        equipment.setBorder(new EmptyBorder(0, 0, 0, 0));
        equipment.setOpaque(false);
        equipment.setContentAreaFilled(false);
        equipment.setSize(80, 80);
        
        Image equipment_image = imageHolder.get("Claymore").getImage();
        Image equipment_image2 = equipment_image.getScaledInstance(equipment.getWidth(), equipment.getHeight(), Image.SCALE_SMOOTH);
        equipment.setIcon(new ImageIcon(equipment_image2));
        equipment.setLocation(430, 204);
        
        perk1 = new JButton("");
        perk1.setBorder(new EmptyBorder(0, 0, 0, 0));
        perk1.setOpaque(false);
        perk1.setContentAreaFilled(false);
        perk1.setSize(80, 80);
        
        Image perk1_image = imageHolder.get("Ghost").getImage();
        Image perk1_image2 = perk1_image.getScaledInstance(perk1.getWidth(), perk1.getHeight(), Image.SCALE_SMOOTH);
        perk1.setIcon(new ImageIcon(perk1_image2));
        perk1.setLocation(90, 324);
        
        perk2 = new JButton("");
        perk2.setBorder(new EmptyBorder(0, 0, 0, 0));
        perk2.setOpaque(false);
        perk2.setContentAreaFilled(false);
        perk2.setSize(80, 80);
        
        Image perk2_image = imageHolder.get("Warlord").getImage();
        Image perk2_image2 = perk2_image.getScaledInstance(perk2.getWidth(), perk2.getHeight(), Image.SCALE_SMOOTH);
        perk2.setIcon(new ImageIcon(perk2_image2));
        perk2.setLocation(260, 324);
        
        perk3 = new JButton("");
        perk3.setBorder(new EmptyBorder(0, 0, 0, 0));
        perk3.setOpaque(false);
        perk3.setContentAreaFilled(false);
        perk3.setSize(80, 80);
        
        Image perk3_image = imageHolder.get("Ninja").getImage();
        Image perk3_image2 = perk3_image.getScaledInstance(perk3.getWidth(), perk3.getHeight(), Image.SCALE_SMOOTH);
        perk3.setIcon(new ImageIcon(perk3_image2));
        perk3.setLocation(430, 324);
        
        ks1 = new JButton("");
        ks1.setBorder(new EmptyBorder(0, 0, 0, 0));
        ks1.setOpaque(false);
        ks1.setContentAreaFilled(false);
        ks1.setSize(80, 80);
        
        Image ks1_image = imageHolder.get("Spy Plane").getImage();
        Image ks1_image2 = ks1_image.getScaledInstance(ks1.getWidth(), ks1.getHeight(), Image.SCALE_SMOOTH);
        ks1.setIcon(new ImageIcon(ks1_image2));
        ks1.setLocation(90, 444);
        
        ks2 = new JButton("");
        ks2.setBorder(new EmptyBorder(0, 0, 0, 0));
        ks2.setOpaque(false);
        ks2.setContentAreaFilled(false);
        ks2.setSize(80, 80);
        
        Image ks2_image = imageHolder.get("Napalm Strike").getImage();
        Image ks2_image2 = ks2_image.getScaledInstance(ks2.getWidth(), ks2.getHeight(), Image.SCALE_SMOOTH);
        ks2.setIcon(new ImageIcon(ks2_image2));
        ks2.setLocation(260, 444);
        
        ks3 = new JButton("");
        ks3.setBorder(new EmptyBorder(0, 0, 0, 0));
        ks3.setOpaque(false);
        ks3.setContentAreaFilled(false);
        ks3.setSize(80, 80);
        
        Image ks3_image = imageHolder.get("Attack Helicopter").getImage();
        Image ks3_image2 = ks3_image.getScaledInstance(ks3.getWidth(), ks3.getHeight(), Image.SCALE_SMOOTH);
        ks3.setIcon(new ImageIcon(ks3_image2));
        ks3.setLocation(430, 444);
        
        generate = new JButton("Generate");
        generate.setBorder(new EmptyBorder(0, 0, 0, 0));
       // generate.setOpaque(false);
        //generate.setContentAreaFilled(false);
        generate.setSize(80, 40);
        generate.setLocation(260, 520);
        
        primaryWeaponTitle = new JButton("AK-47");
        primaryWeaponTitle.setFont(blackOps);
        primaryWeaponTitle.setForeground(Color.WHITE);
        primaryWeaponTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        primaryWeaponTitle.setOpaque(false);
        primaryWeaponTitle.setContentAreaFilled(false);
        primaryWeaponTitle.setSize(250, 30);
        primaryWeaponTitle.setLocation(40, 134);
        
        secondaryWeaponTitle = new JButton("Python");
        secondaryWeaponTitle.setFont(blackOps);
        secondaryWeaponTitle.setForeground(Color.WHITE);
        secondaryWeaponTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        secondaryWeaponTitle.setOpaque(false);
        secondaryWeaponTitle.setContentAreaFilled(false);
        secondaryWeaponTitle.setSize(200, 30);
        secondaryWeaponTitle.setLocation(370, 134);
        
        LethalTitle = new JButton("Tomahawk");
        LethalTitle.setFont(blackOpsSmall);
        LethalTitle.setForeground(Color.WHITE);
        LethalTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        LethalTitle.setOpaque(false);
        LethalTitle.setContentAreaFilled(false);
        LethalTitle.setHorizontalAlignment(SwingConstants.CENTER);
        LethalTitle.setSize(160, 20);
        LethalTitle.setLocation(50, 284);
        //LethalTitle.setEnabled(false);
        
        TacticalTitle = new JButton("Flashbang");
        TacticalTitle.setFont(blackOpsSmall);
        TacticalTitle.setForeground(Color.WHITE);
        TacticalTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        TacticalTitle.setOpaque(false);
        TacticalTitle.setContentAreaFilled(false);
        TacticalTitle.setHorizontalAlignment(SwingConstants.CENTER);
        TacticalTitle.setSize(160, 20);
        TacticalTitle.setLocation(220, 284);
        //TacticalTitle.setEnabled(false);
        
        EquipmentTitle = new JButton("Claymore");
        EquipmentTitle.setFont(blackOpsSmall);
        EquipmentTitle.setForeground(Color.WHITE);
        EquipmentTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
        EquipmentTitle.setOpaque(false);
        EquipmentTitle.setContentAreaFilled(false);
        EquipmentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        EquipmentTitle.setSize(160, 20);
        EquipmentTitle.setLocation(390, 284);
        
        Perk1Title = new JButton("Ghost");
        Perk1Title.setFont(blackOpsSmall);
        Perk1Title.setForeground(Color.WHITE);
        Perk1Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        Perk1Title.setOpaque(false);
        Perk1Title.setContentAreaFilled(false);
        Perk1Title.setHorizontalAlignment(SwingConstants.CENTER);
        Perk1Title.setSize(160, 20);
        Perk1Title.setLocation(50, 404);
        
        Perk2Title = new JButton("Warlord");
        Perk2Title.setFont(blackOpsSmall);
        Perk2Title.setForeground(Color.WHITE);
        Perk2Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        Perk2Title.setOpaque(false);
        Perk2Title.setContentAreaFilled(false);
        Perk2Title.setHorizontalAlignment(SwingConstants.CENTER);
        Perk2Title.setSize(160, 20);
        Perk2Title.setLocation(220, 404);
        //TacticalTitle.setEnabled(false);
        
        Perk3Title = new JButton("Ninja");
        Perk3Title.setFont(blackOpsSmall);
        Perk3Title.setForeground(Color.WHITE);
        Perk3Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        Perk3Title.setOpaque(false);
        Perk3Title.setContentAreaFilled(false);
        Perk3Title.setHorizontalAlignment(SwingConstants.CENTER);
        Perk3Title.setSize(160, 20);
        Perk3Title.setLocation(390, 404);
        
        KS1Title = new JButton("Spy Plane");
        KS1Title.setFont(blackOpsSmall);
        KS1Title.setForeground(Color.WHITE);
        KS1Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        KS1Title.setOpaque(false);
        KS1Title.setContentAreaFilled(false);
        KS1Title.setHorizontalAlignment(SwingConstants.CENTER);
        KS1Title.setSize(160, 20);
        KS1Title.setLocation(50, 524);
        
        KS2Title = new JButton("Napalm Strike");
        KS2Title.setFont(blackOpsSmall);
        KS2Title.setForeground(Color.WHITE);
        KS2Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        KS2Title.setOpaque(false);
        KS2Title.setContentAreaFilled(false);
        KS2Title.setHorizontalAlignment(SwingConstants.CENTER);
        KS2Title.setSize(160, 20);
        KS2Title.setLocation(220, 524);
        //TacticalTitle.setEnabled(false);
        
        KS3Title = new JButton("Attack Helecopter");
        KS3Title.setFont(blackOpsSmall);
        KS3Title.setForeground(Color.WHITE);
        KS3Title.setBorder(new EmptyBorder(0, 0, 0, 0));
        KS3Title.setOpaque(false);
        KS3Title.setContentAreaFilled(false);
        KS3Title.setHorizontalAlignment(SwingConstants.CENTER);
        KS3Title.setSize(160, 20);
        KS3Title.setLocation(390, 524);
        
        ImageIcon img2 = new ImageIcon("victory3.jpg");
        JLabel background = new JLabel("", img2, JLabel.LEFT);
        frame.setContentPane(background);
        
        Image primary_image = imageHolder.get("AK-47").getImage();
        Image primary_image2 = primary_image.getScaledInstance(buttonForPicture.getWidth(), buttonForPicture.getHeight(), Image.SCALE_SMOOTH);
        buttonForPicture.setIcon(new ImageIcon(primary_image2));
        buttonForPicture.setText("AK-476");
        buttonForPicture.setSize(buttonForPicture.getWidth(), 114);
        buttonForPicture.setLocation(40, 20);
        
        Image secondary_image = imageHolder.get("Python").getImage();
        Image secondary_image2 = secondary_image.getScaledInstance(secondaryPictureButton.getWidth(), secondaryPictureButton.getHeight(), Image.SCALE_SMOOTH);
        secondaryPictureButton.setIcon(new ImageIcon(secondary_image2));
        
        Image at1_image = imageHolder.get("SCOPE Red Dot Sight").getImage();
        Image at1_image2 = at1_image.getScaledInstance(at1.getWidth(), at1.getHeight(), Image.SCALE_SMOOTH);
        at1.setIcon(new ImageIcon(at1_image2));
        at1.setLocation(40, 124);
        
        Image at2_image = imageHolder.get("Suppressor").getImage();
        Image at2_image2 = at2_image.getScaledInstance(at2.getWidth(), at2.getHeight(), Image.SCALE_SMOOTH);
        at2.setIcon(new ImageIcon(at2_image2));
        at2.setLocation(240, 124);
        
        Image secat_image = imageHolder.get("Snub Nose").getImage();
        Image secat_image2 = secat_image.getScaledInstance(sec_att.getWidth(), sec_att.getHeight(), Image.SCALE_SMOOTH);
        sec_att.setIcon(new ImageIcon(secat_image2));
        sec_att.setLocation(350, 124);
        
        Image secat2_image = imageHolder.get("Speed Reloader").getImage();
        Image secat2_image2 = secat2_image.getScaledInstance(sec_att2.getWidth(), sec_att2.getHeight(), Image.SCALE_SMOOTH);
        sec_att2.setIcon(new ImageIcon(secat2_image2));
        sec_att2.setLocation(534, 124);
        
        Image lethal_image = imageHolder.get("Tomahawk").getImage();
        Image lethal_image2 = lethal_image.getScaledInstance(lethal.getWidth(), lethal.getHeight(), Image.SCALE_SMOOTH);
        lethal.setIcon(new ImageIcon(lethal_image2));
        lethal.setLocation(90, 204);
    }
 }

