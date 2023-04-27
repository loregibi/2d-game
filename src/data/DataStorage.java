package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{

    // PLAYER STATS
    int level;
    int maxLife;
    int life;
    int strenght;
    int dexterity;
    int exp;
    int nextLevelExp;
    
    // PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;
    
    // OBJECT ON MAP
    String mapObjectNames[];
    int mapObjectWorldX[];
    int mapObjectWorldY[];
}
