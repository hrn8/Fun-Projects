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

/**
 *
 * @author Hunter
 */
public class Nonogram implements Serializable{
    private static final long serialVersionUID = 1847759230349428690L;
    private boolean[][] markers;
    private String name;
    Nonogram(String s, boolean [][] temp){
        this.name = s;
        this.markers = temp;
    }

    Nonogram() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void readFromFile(){
        
        
        try
        {
            FileInputStream fileIn = new FileInputStream("output.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Nonogram deserializedUser = (Nonogram)in.readObject();
            in.close();
            fileIn.close();
 
            System.out.println(deserializedUser.markers);
            System.out.println(deserializedUser.name);
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        catch (ClassNotFoundException cnfe) 
        {
            cnfe.printStackTrace();
        }
    }
    
    
}
