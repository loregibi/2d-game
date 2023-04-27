package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Bread;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public final class Player extends Entity {

    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    private int standCounter = 0;
    private int hasKey = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    private final int maxInventorySize = 20;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        
        super(gp);
        
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        // SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
    }
    
    public void setDefaultValues() {
        
        defaultSpeed = 4;
        speed = defaultSpeed;
        
        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1; // The more strength he has, the more damage he gives
        dexterity = 1; // The more dexterity he has, the less damage he receives
        exp = 0;
        nextLevelExp = 20;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon
        defense = getDefense(); // The total defense value is decided by dexterity and shield
        
        setDefaultPositions();
        getPlayerImage(toque);
        getPlayerImage();
        setItems();
        setDialogue();
    }
    
    public void setDefaultPositions() {
        
        worldX = gp.tileSize * 50;
        worldY = gp.tileSize * 50;
        direction = "down";
    }
    
    public void setDialogue() {
        
        dialogues[0][0] = "You are level " + level + " now!";
        
        dialogues[1][0] = "New area unlocked!";
    }
    
    public void restoreStatus() {
        
        life = maxLife;
        invincible = false;
        attacking = false;
        knockBack = false;
    }
    
    public void setItems() {
        
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    
    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }
    
    public int getToque() {
        
        if(toque == true) {
            getPlayerImage(toque);
            getPlayerImage();
            return speed = currentToque.speedValue;
        }
        else {
            getPlayerImage(toque);
            getPlayerImage();
            return speed = defaultSpeed;
        }
    }
    
    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    
    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }
    
    public void getPlayerImage(boolean toque) {

        if(toque == true) {
            up1 = setup("player/boy_up_toque_1",gp.tileSize,gp.tileSize);
            up2 = setup("player/boy_up_toque_2",gp.tileSize,gp.tileSize);
            down1 = setup("player/boy_down_toque_1",gp.tileSize,gp.tileSize);
            down2 = setup("player/boy_down_toque_2",gp.tileSize,gp.tileSize);
            left1 = setup("player/boy_left_toque_1",gp.tileSize,gp.tileSize);
            left2 = setup("player/boy_left_toque_2",gp.tileSize,gp.tileSize);
            right1 = setup("player/boy_right_toque_1",gp.tileSize,gp.tileSize);
            right2 = setup("player/boy_right_toque_2",gp.tileSize,gp.tileSize);
        }
        else {
            up1 = setup("player/boy_up_1",gp.tileSize,gp.tileSize);
            up2 = setup("player/boy_up_2",gp.tileSize,gp.tileSize);
            down1 = setup("player/boy_down_1",gp.tileSize,gp.tileSize);
            down2 = setup("player/boy_down_2",gp.tileSize,gp.tileSize);
            left1 = setup("player/boy_left_1",gp.tileSize,gp.tileSize);
            left2 = setup("player/boy_left_2",gp.tileSize,gp.tileSize);
            right1 = setup("player/boy_right_1",gp.tileSize,gp.tileSize);
            right2 = setup("player/boy_right_2",gp.tileSize,gp.tileSize);
        }
    }
    
    public void getPlayerImage() {
        
        if(currentWeapon.getType() == type_sword) {

            if(toque == true) {
                attackUp1 = setup("player/boy_attack_up_toque_1",gp.tileSize,gp.tileSize*2);
                attackUp2 = setup("player/boy_attack_up_toque_2",gp.tileSize,gp.tileSize*2);
                attackDown1 = setup("player/boy_attack_down_toque_1",gp.tileSize,gp.tileSize*2);
                attackDown2 = setup("player/boy_attack_down_toque_2",gp.tileSize,gp.tileSize*2);
                attackLeft1 = setup("player/boy_attack_left_toque_1",gp.tileSize*2,gp.tileSize);
                attackLeft2 = setup("player/boy_attack_left_toque_2",gp.tileSize*2,gp.tileSize);
                attackRight1 = setup("player/boy_attack_right_toque_1",gp.tileSize*2,gp.tileSize);
                attackRight2 = setup("player/boy_attack_right_toque_2",gp.tileSize*2,gp.tileSize);
            }
            else {
                attackUp1 = setup("player/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
                attackUp2 = setup("player/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
                attackDown1 = setup("player/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
                attackDown2 = setup("player/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
                attackLeft1 = setup("player/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
                attackLeft2 = setup("player/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
                attackRight1 = setup("player/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
                attackRight2 = setup("player/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
            }
        }
        if(currentWeapon.getType() == type_axe) {
            
            if(toque == true) {
                attackUp1 = setup("player/boy_axe_up_toque_1",gp.tileSize,gp.tileSize*2);
                attackUp2 = setup("player/boy_axe_up_toque_2",gp.tileSize,gp.tileSize*2);
                attackDown1 = setup("player/boy_axe_down_toque_1",gp.tileSize,gp.tileSize*2);
                attackDown2 = setup("player/boy_axe_down_toque_2",gp.tileSize,gp.tileSize*2);
                attackLeft1 = setup("player/boy_axe_left_toque_1",gp.tileSize*2,gp.tileSize);
                attackLeft2 = setup("player/boy_axe_left_toque_2",gp.tileSize*2,gp.tileSize);
                attackRight1 = setup("player/boy_axe_right_toque_1",gp.tileSize*2,gp.tileSize);
                attackRight2 = setup("player/boy_axe_right_toque_2",gp.tileSize*2,gp.tileSize);
            }  
            else {
                attackUp1 = setup("player/boy_axe_up_1",gp.tileSize,gp.tileSize*2);
                attackUp2 = setup("player/boy_axe_up_2",gp.tileSize,gp.tileSize*2);
                attackDown1 = setup("player/boy_axe_down_1",gp.tileSize,gp.tileSize*2);
                attackDown2 = setup("player/boy_axe_down_2",gp.tileSize,gp.tileSize*2);
                attackLeft1 = setup("player/boy_axe_left_1",gp.tileSize*2,gp.tileSize);
                attackLeft2 = setup("player/boy_axe_left_2",gp.tileSize*2,gp.tileSize);
                attackRight1 = setup("player/boy_axe_right_1",gp.tileSize*2,gp.tileSize);
                attackRight2 = setup("player/boy_axe_right_2",gp.tileSize*2,gp.tileSize);
            }
        }
    }
    
    @Override
    public void update() {
        
        if(attacking == true) {
            
            attacking();
        }
        
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
            || keyH.rightPressed == true || keyH.enterPressed == true) {
            
            if(keyH.upPressed == true) {
                direction = "up";    
            }
            else if(keyH.downPressed == true) {
                direction = "down";  
            }
            else if(keyH.leftPressed == true) {
                direction = "left";  
            }
            else if(keyH.rightPressed == true) {
                direction = "right";  
            }
            
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            // CHECK EVENT
            gp.eHandler.checkEvent();
            
            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.enterPressed == false) {
                
                switch(direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            
            if(keyH.enterPressed == true && attackCanceled == false) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            
            attackCanceled = false;
            gp.keyH.enterPressed = false;
        
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            
            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        
        // This needs to be outside of key if statement
        if(invincible == true) {
            invincibleCounter++;
            
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(10);
        }
    }
    
    public void pickUpObject(int i) {
        
        if(i != 999) {
            
            String text = "";
            int temp = 0;
            
            if(inventory.size() != maxInventorySize) {
                
                String objectName = gp.obj[i].name;
                
                switch(objectName) {
                    case "Key":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "Got a " + objectName + "!";
                        hasKey++;
                        break;
                    case "Door":
                        
                        temp = 1;
                        
                        if(hasKey > 0) {
                            gp.playSE(3);
                            
                            for(int itemIndex = 0; itemIndex < inventory.size(); itemIndex++) {
                                
                                Entity selectedItem = inventory.get(itemIndex);
                                if(selectedItem.getType() == type_key) {
                                    inventory.remove(itemIndex);
                                }
                            }
                            
                            text = "You open a " + objectName + "!";
                            temp = 2;
                            hasKey--;
                        }
                        else {
                            gp.playSE(3);
                        }
                        break;
                    case "Oil":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Pepper":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Salt":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Lettuce":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Tomato":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Cheese":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                        break;
                    case "Woodcutter's Axe":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "Got a " + objectName + "!";
                        break;
                    case "Blue Shield":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "Got a " + objectName + "!";
                        break;
                    case "Toque Blanche":
                        inventory.add(gp.obj[i]);
                        gp.playSE(2);
                        text = "Got a " + objectName + "!";
                        break;
                    case "Red Potion":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "Got a " + objectName + "!";
                        break;
                    case "Meat":
                        inventory.add(gp.obj[i]);
                        gp.playSE(1);
                        text = "You found " + objectName + "!";
                }
            }
            else {
                text = "You cannot carry any more!";
            }
            gp.ui.addMessage(text);
            
            // DEBUG DOOR
            if(temp == 2) { gp.obj[i] = null; } // If it is a door and I have key
            if(temp == 1) { } // If it is door and I haven't key
            if(temp == 0) { gp.obj[i] = null; } // If it is not door
        }
    }
    
    public void interactNPC(int i) {
        
        if(i != 999) {
            
            if(gp.keyH.enterPressed == true) {
                attackCanceled = true;
                gp.npc[i].speak();
                
                gp.npc[i].dialogueSet = 0;
                
                if(gp.player.life <= 3) {
                    gp.npc[i].dialogueSet = 1;
                }
                
                int topping = 0;
                int ingredient = 0;

                for(int itemIndex = 0; itemIndex < inventory.size(); itemIndex++) {
                                
                    Entity selectedItem = inventory.get(itemIndex);
                    
                    if("Oil".equals(selectedItem.name) || "Pepper".equals(selectedItem.name) ||
                        "Salt".equals(selectedItem.name)) {
                        
                        topping++;
                        if(topping >= 3) {
                            gp.npc[i].dialogueSet = 2;
                        }
                    }
                    if("Bread".equals(selectedItem.name) || "Cheese".equals(selectedItem.name) ||
                        "Lettuce".equals(selectedItem.name) || "Tomato".equals(selectedItem.name)) {
                        
                        ingredient++;
                        
                        if(ingredient == 4) {
                            if(gp.keyH.tempTeleport == 0) {
                                gp.npc[i].dialogueSet = 3;
                                gp.keyH.tempTeleport = 1;
                            }
                            else {
                                gp.eHandler.teleport();
                                gp.keyH.tempTeleport = 0;
                            }
                        }
                    }
                    if("Meat".equals(selectedItem.name)) {
                        gp.playSE(4);
                        gp.gameState = gp.gameWinState;
                    }
                }
            }
        }
    }
    
    public void contactMonster(int i) {
        
        if(i != 999) {
            
            if(invincible == false && gp.monster[i].dying == false) {
                gp.playSE(6);
                
                int damage = gp.monster[i].attack - defense;
                if(damage < 0) {
                    damage = 0;
                }
                life -= damage;
                
                invincible = true;
            }
        }
    }
    
    public void damageMonster(int i, Entity attacker, int knockBackPower) {
        
        if(i != 999) {
        
            if(gp.monster[i].invincible == false) {
                
                gp.playSE(5);
                
                if(knockBackPower > 0) {
                    setKnockBack(gp.monster[i], attacker, knockBackPower);
                }

                int damage = attack - gp.monster[i].defense;
                if(damage < 0) {
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                
                gp.ui.addMessage(damage + " damage!");
                
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
                
                if(gp.monster[i].life <= 0) {
                    monsterCounter++;
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                    
                    // UNLOCK THE AREAS
                    if(monsterCounter == 14) {
                        if(gp.obj[0] != null) {
                            startDialogue(this,1);
                            gp.obj[0] = null; 
                        }
                    }
                    if(monsterCounter == 20) {
                        if(gp.obj[1] != null) {
                            startDialogue(this,1);
                            gp.obj[1] = null;
                        }
                    }
                    if(monsterCounter == 30) {
                        if(gp.obj[2] != null) {
                            startDialogue(this,1);
                            gp.obj[2] = null;
                        }
                    }
                    
                    if(monsterCounter == 44) {
                        inventory.add(new OBJ_Bread(gp));
                    }
                    
                    if("Orc".equals(gp.monster[i].name)) {
                        gp.obj[3] = null;
                    }
                }
            }
        }
    }
    
    public void checkLevelUp() {
        
        if(exp >= nextLevelExp) {
            
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack(); // recalculation attack
            defense = getDefense(); // recalculation defense
            
            gp.playSE(8);
            setDialogue();
            startDialogue(this,0);
        }
    }
    
    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlot();
        
        if(itemIndex < inventory.size()) {
            
            Entity selectedItem = inventory.get(itemIndex);
            
            if(selectedItem.getType() == type_sword || selectedItem.getType() == type_axe) {
                
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerImage(); 
            }
            
            if(selectedItem.getType() == type_shield) {
                
                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.getType() == type_consumable) {
                
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
            
            if(selectedItem.getType() == type_toque) {
                if(gp.keyH.tempToque == 0) {
                    currentToque = selectedItem;
                    toque = true;
                    getToque();
                    gp.keyH.tempToque = 1;
                }
                else {
                    currentToque = null;
                    toque = false;
                    getToque();
                    gp.keyH.tempToque = 0;
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g2) {
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch(direction) {
            case "up":
                if(attacking == false) {
                    if(spriteNum == 1) { image = up1; }
                    if(spriteNum == 2) { image = up2; }
                }
                if(attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) { image = attackUp1; }
                    if(spriteNum == 2) { image = attackUp2; }
                }
                break;
            case "down":
                if(attacking == false) {
                    if(spriteNum == 1) { image = down1; }
                    if(spriteNum == 2) { image = down2; }
                }
                if(attacking == true) {
                    if(spriteNum == 1) { image = attackDown1; }
                    if(spriteNum == 2) { image = attackDown2; }
                }
                break;
            case "left":
                if(attacking == false) {
                    if(spriteNum == 1) { image = left1; }
                    if(spriteNum == 2) { image = left2; }
                }
                if(attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) { image = attackLeft1; }
                    if(spriteNum == 2) { image = attackLeft2; }
                }  
                break;
            case "right":
                if(attacking == false) {
                    if(spriteNum == 1) { image = right1; }
                    if(spriteNum == 2) { image = right2; }
                }
                if(attacking == true) {
                    if(spriteNum == 1) { image = attackRight1; }
                    if(spriteNum == 2) { image = attackRight2; }
                }
                break;
        }
        
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        // Reset Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
