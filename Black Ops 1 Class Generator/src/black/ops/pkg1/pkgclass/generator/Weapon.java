package black.ops.pkg1.pkgclass.generator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hunter
 */
public class Weapon {
    private String name; 
    private int damage;
    private int range;
    private int accuracy;
    private int mobility;
    private int fire_rate; 
    private String[] attachments;
    
    public Weapon(String n, int d, int r, int a, int m, int f, String att[]){
        this.name = n;
        this.damage = d;
        this.range = r;
        this.accuracy = a;
        this.mobility = m;
        this.fire_rate = f;
        this.attachments = att;
    }
    
    String getName(){
        return this.name;
    }
    
     public String getAttachment(){
        int currentIndex = (int)(Math.random() * (attachments.length-1));
        return attachments[currentIndex];
    }
    
}
