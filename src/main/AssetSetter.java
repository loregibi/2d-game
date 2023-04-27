package main;

import entity.NPC_Chef;
import monster.MON_BlackSlime;
import monster.MON_GreenSlime;
import monster.MON_OrangeSlime;
import monster.MON_Orc;
import monster.MON_RedSlime;
import object.OBJ_Axe;
import object.OBJ_Cheese;
import object.OBJ_Door;
import object.OBJ_Lettuce;
import object.OBJ_Key;
import object.OBJ_Meat;
import object.OBJ_Oil;
import object.OBJ_Pepper;
import object.OBJ_Potion_Red;
import object.OBJ_Salt;
import object.OBJ_Shield_Blue;
import object.OBJ_Tomato;
import object.OBJ_Toque;

public class AssetSetter {

    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
        
        int i = 4;
        
        // AREA 2
        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = 51 * gp.tileSize;
        gp.obj[0].worldY = 40 * gp.tileSize;
        
        // AREA 3
        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].direction = "left";
        gp.obj[1].worldX = 81 * gp.tileSize;
        gp.obj[1].worldY = 48 * gp.tileSize;
        
        // AREA 4
        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].direction = "left";
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 51 * gp.tileSize;
        
        // BOSS AREA
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 86 * gp.tileSize;
        gp.obj[3].worldY = 36 * gp.tileSize;
        
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 12 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].direction = "left";
        gp.obj[i].worldX = 7 * gp.tileSize;
        gp.obj[i].worldY = 74 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 10 * gp.tileSize;
        gp.obj[i].worldY = 64 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 17 * gp.tileSize;
        gp.obj[i].worldY = 28 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Pepper(gp);
        gp.obj[i].worldX = 36 * gp.tileSize;
        gp.obj[i].worldY = 74 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Oil(gp);
        gp.obj[i].worldX = 61 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Salt(gp);
        gp.obj[i].worldX = 90 * gp.tileSize;
        gp.obj[i].worldY = 36 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Meat(gp);
        gp.obj[i].worldX = 78 * gp.tileSize;
        gp.obj[i].worldY = 24 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Cheese(gp);
        gp.obj[i].worldX = 86 * gp.tileSize;
        gp.obj[i].worldY = 64 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 81 * gp.tileSize;
        gp.obj[i].worldY = 56 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 45 * gp.tileSize;
        gp.obj[i].worldY = 19 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Tomato(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 22 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 4 * gp.tileSize;
        gp.obj[i].worldY = 63 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Lettuce(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 70 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = 9 * gp.tileSize;
        gp.obj[i].worldY = 28 * gp.tileSize;
        i++;
        
        gp.obj[i] = new OBJ_Toque(gp);
        gp.obj[i].worldX = 32 * gp.tileSize;
        gp.obj[i].worldY = 95 * gp.tileSize;
        i++;
    }
    
    public void setNPC() {

        int i = 0;
        
        gp.npc[i] = new NPC_Chef(gp);
        gp.npc[i].worldX = 49 * gp.tileSize;
        gp.npc[i].worldY = 49 * gp.tileSize;
    }
    
    public void setMonster() {
        
        int i = 0;
        
        // GREEN SLIME (14 TOTAL)
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 39 * gp.tileSize;
        gp.monster[i].worldY = 53 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 66 * gp.tileSize;
        gp.monster[i].worldY = 45 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 68 * gp.tileSize;
        gp.monster[i].worldY = 46 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 39 * gp.tileSize;
        gp.monster[i].worldY = 72 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 42 * gp.tileSize;
        gp.monster[i].worldY = 70 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 40 * gp.tileSize;
        gp.monster[i].worldY = 69 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 69 * gp.tileSize;
        gp.monster[i].worldY = 69 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 66 * gp.tileSize;
        gp.monster[i].worldY = 70 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 66 * gp.tileSize;
        gp.monster[i].worldY = 66 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 51 * gp.tileSize;
        gp.monster[i].worldY = 65 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 59 * gp.tileSize;
        gp.monster[i].worldY = 63 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 50 * gp.tileSize;
        gp.monster[i].worldY = 66 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 64 * gp.tileSize;
        gp.monster[i].worldY = 51 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = 60 * gp.tileSize;
        gp.monster[i].worldY = 49 * gp.tileSize;
        i++;

        //ORANGE SLIME (6 TOTAL)
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 44 * gp.tileSize;
        gp.monster[i].worldY = 19 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 47 * gp.tileSize;
        gp.monster[i].worldY = 19 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 44 * gp.tileSize;
        gp.monster[i].worldY = 28 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 47 * gp.tileSize;
        gp.monster[i].worldY = 29 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 60 * gp.tileSize;
        gp.monster[i].worldY = 23 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_OrangeSlime(gp);
        gp.monster[i].worldX = 64 * gp.tileSize;
        gp.monster[i].worldY = 23 * gp.tileSize;
        i++;
        
        //RED SLIME (10 TOTAL)
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 94 * gp.tileSize;
        gp.monster[i].worldY = 37 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 91 * gp.tileSize;
        gp.monster[i].worldY = 39 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 94 * gp.tileSize;
        gp.monster[i].worldY = 41 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 84 * gp.tileSize;
        gp.monster[i].worldY = 48 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 86 * gp.tileSize;
        gp.monster[i].worldY = 49 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 87 * gp.tileSize;
        gp.monster[i].worldY = 66 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 88 * gp.tileSize;
        gp.monster[i].worldY = 63 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 75 * gp.tileSize;
        gp.monster[i].worldY = 62 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 77 * gp.tileSize;
        gp.monster[i].worldY = 59 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_RedSlime(gp);
        gp.monster[i].worldX = 81 * gp.tileSize;
        gp.monster[i].worldY = 60 * gp.tileSize;
        i++;
        
        // BLACK SLIME
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 17 * gp.tileSize;
        gp.monster[i].worldY = 22 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 32 * gp.tileSize;
        gp.monster[i].worldY = 32 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 33 * gp.tileSize;
        gp.monster[i].worldY = 35 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 6 * gp.tileSize;
        gp.monster[i].worldY = 32 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 8 * gp.tileSize;
        gp.monster[i].worldY = 36 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 16 * gp.tileSize;
        gp.monster[i].worldY = 48 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 22 * gp.tileSize;
        gp.monster[i].worldY = 50 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 5 * gp.tileSize;
        gp.monster[i].worldY = 68 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 27 * gp.tileSize;
        gp.monster[i].worldY = 65 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 8 * gp.tileSize;
        gp.monster[i].worldY = 80 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 11 * gp.tileSize;
        gp.monster[i].worldY = 78 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 21 * gp.tileSize;
        gp.monster[i].worldY = 86 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 28 * gp.tileSize;
        gp.monster[i].worldY = 90 * gp.tileSize;
        i++;
        
        gp.monster[i] = new MON_BlackSlime(gp);
        gp.monster[i].worldX = 29 * gp.tileSize;
        gp.monster[i].worldY = 85 * gp.tileSize;
        i++;
        
        // FINAL BOSS
        gp.monster[i] = new MON_Orc(gp);
        gp.monster[i].worldX = 73 * gp.tileSize;
        gp.monster[i].worldY = 33 * gp.tileSize;
        i++;
    }
}
