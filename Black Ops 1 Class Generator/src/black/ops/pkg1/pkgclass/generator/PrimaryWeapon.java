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

enum TYPE {
  ASSAULT,
  SUMBACHINE,
  MACHINE,
  SNIPER,
  SHOTGUN
}

public class PrimaryWeapon extends Weapon{
    private String camo;
    
    public PrimaryWeapon(String n, int d, int r, int a, int m, int f, String c, String[] at){
        super(n, d, r, a, m, f, at);
        
    }
    
   
    
    
}
