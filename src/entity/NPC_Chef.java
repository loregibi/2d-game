package entity;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

public final class NPC_Chef extends Entity{
    
    public NPC_Chef(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        dialogueSet = -1;
        
        getImage();
        setDialogue();
    }
    
    public void getImage() {

        up1 = setup("npc/chef_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("npc/chef_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("npc/chef_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("npc/chef_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("npc/chef_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("npc/chef_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("npc/chef_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("npc/chef_right_2",gp.tileSize,gp.tileSize);

    }
    
    public void setDialogue() {
        
        dialogues[0][0] = "Hello, aspiring chef.";
        dialogues[0][1] = "So you've come to this island to cook \nthe best hamburger?";
        dialogues[0][2] = "Look for the five ingredients to make \nit up.";
        dialogues[0][3] = "And remember... An hamburger is \nnothing without the three toppings.";
        dialogues[0][4] = "Well, good luck on you.";
        
        dialogues[1][0] = "If you become tired, rest at the water.";
        
        dialogues[2][0] = "You found the three toppings. \nKeep it up!";
        dialogues[2][1] = "When you have all the ingredients \ncome back to me!";
        
        dialogues[3][0] = "You found all the ingredients \nfor the last stage! \nPress ENTER two times...";
    }
    
    @Override
    public void setAction() {
        
        if(onPath == true) {
            
            int goalCol = 49;
            int goalRow = 49;
            
            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;
        
            if(actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100)+1; // Pick up a number from 1 to 100
        
                if(i <= 25) {
                    direction = "up";
                }
                if(i > 25 && i <= 50) {
                    direction = "down";
                }
                if(i > 50 && i <= 75) {
                    direction = "left";
                }
                if(i > 75 && i <= 100) {
                    direction = "right";
                }
            
                actionLockCounter = 0;
            }
        }
    }
    
    @Override
    public void speak() {

        // Do this character specific stuff
        facePlayer();
        startDialogue(this,dialogueSet);
        
        //onPath = true;
    }
}
