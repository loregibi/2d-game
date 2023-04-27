package data;

import entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Bread;
import object.OBJ_Cheese;
import object.OBJ_Key;
import object.OBJ_Lettuce;
import object.OBJ_Meat;
import object.OBJ_Oil;
import object.OBJ_Pepper;
import object.OBJ_Potion_Red;
import object.OBJ_Salt;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tomato;
import object.OBJ_Toque;

public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp) {
        this.gp = gp;  
    }
    
    public Entity getObject(String itemName) {
        
        Entity obj = null;
        
        switch(itemName) {
            case "Woodcutter's Axe": obj = new OBJ_Axe(gp); break;
            case "Bread": obj = new OBJ_Bread(gp); break;
            case "Cheese": obj = new OBJ_Cheese(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            case "Lettuce": obj = new OBJ_Lettuce(gp); break;
            case "Meat": obj = new OBJ_Meat(gp); break;
            case "Oil": obj = new OBJ_Oil(gp); break;
            case "Pepper": obj = new OBJ_Pepper(gp); break;
            case "Red Potion": obj = new OBJ_Potion_Red(gp); break;
            case "Salt": obj = new OBJ_Salt(gp); break;
            case "Blue Shield": obj = new OBJ_Shield_Blue(gp); break;
            case "Wood Shield": obj = new OBJ_Shield_Wood(gp); break;
            case "Normal Sword": obj = new OBJ_Sword_Normal(gp); break;
            case "Tomato": obj = new OBJ_Tomato(gp); break;
            case "Toque Blanche": obj = new OBJ_Toque(gp); break;
        }
        return obj;
    }
    
    public void save() {
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            // PLAYER STATS
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.strenght = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            
            // PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
            }
            
            // PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
                        
            // OBJECTS ON MAP
            ds.mapObjectNames = new String[gp.obj.length];
            ds.mapObjectWorldX = new int[gp.obj.length];
            ds.mapObjectWorldY = new int[gp.obj.length];
              
            for(int i = 0; i < gp.obj.length; i++) {
                    
                if(gp.obj[i] == null) {
                    ds.mapObjectNames[i] = "NA";
                }
                else {
                    ds.mapObjectNames[i] = gp.obj[i].name;
                    ds.mapObjectWorldX[i] = gp.obj[i].worldX;
                    ds.mapObjectWorldY[i] = gp.obj[i].worldY;
                }
            }

            // Write the DataStorage object
            oos.writeObject(ds);
        }
        catch(Exception e) {
            System.out.println("Save Exception!");
        }
    }
    
    public void load() {
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            
            // PLAYER STATUS
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.strength = ds.strenght;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            
            // PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
            }
            
            // PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getPlayerImage();
            
            // OBJECTS ON MAP
            for(int i = 0; i < gp.obj.length; i++) {
                
                if(ds.mapObjectNames[i].equals("NA")) {
                    gp.obj[i] = null;
                }
            }
        }
        catch(Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
