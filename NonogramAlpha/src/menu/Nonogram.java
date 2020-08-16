/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Hunter
 */
public class Nonogram implements Serializable{
    private static final long serialVersionUID = 1847759230349428690L;
    private boolean[][] markers;
    private String name;
    private int length = 0;
    private int width = 0;
    private int LengthData[][], WidthData[][], finalLengthHolder, finalWidthHolder;
    
    Nonogram(String s, boolean [][] temp, int le, int wi, int LData[][], int WData[][], int finalLHolder, int finalWHolder){
        this.name = s;
        this.markers = temp;
        this.length = le;
        this.width = wi;
        this.LengthData = LData;
        this.WidthData = WData;
        this.finalLengthHolder = finalLHolder;
        this.finalWidthHolder = finalWHolder;
    }

    Nonogram() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean sameAnswer(boolean Checker[][]){
        if(Arrays.deepEquals(this.markers, Checker))
            return true;
        
        return false;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getLength(){
        return this.length;
    }

    public int getMaxWidthHolder(){
        return this.finalWidthHolder;
    }
    
    public int getMaxLengthHolder(){
        return this.finalLengthHolder;
    }
    
    public int getWidthValue(int widthIndex, int rowIndex){
        return this.WidthData[widthIndex][rowIndex];
    }
    
    public int getLengthValue(int rowIndex, int colIndex){
        return this.LengthData[rowIndex][colIndex];
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    public boolean getValue(int lengthIndex, int widthIndex){
        return this.markers[lengthIndex][widthIndex];
    }
 }
