package main;

import entity.Entity;

public final class EventHandler{

    GamePanel gp;
    EventRect eventRect[][];
    Entity eventMaster;
    
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        
        eventMaster = new Entity(gp);
        
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            
            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        } 
        setDialogue();
    }
    
    public void setDialogue() {
        
        eventMaster.dialogues[0][0] = "You fall into the sea!\nStart again.";
        
        eventMaster.dialogues[1][0] = "You drink the water.\nYour life has been recovered.\n"
                + "(The progress has been saved)";
        
        eventMaster.dialogues[2][0] = "Leave all hope you who pass!";
        
        eventMaster.dialogues[3][0] = "You are venturing into a dangerous\narea!";
        
        eventMaster.dialogues[4][0] = "Teleport!";
    }
    
    public void checkEvent() {
        
        // SAVE GAME
        if(hit(55,52,"up") || hit(56,52,"up")) {
            healingPool(gp.dialogueState);
        }
        
        // MAZE 1
        if(hit(26,41,"up") || hit(12,25,"down")) {
            gp.lightUpdate = true;
            gp.eManager.setup();
        }
        if(hit(26,40,"up")){
            darkAlert(26,40,gp.dialogueState);
        }
        if(hit(26,41,"down") || hit(12,24,"up")) {
            gp.lightUpdate = false;
            gp.eManager.setup();
        }
        
        // MAZE 2
        if(hit(8,57,"down") || hit(7,74,"right")) {
            gp.lightUpdate = true;
            gp.eManager.setup();
        }
        if(hit(8,58,"down")) {
            darkAlert(8,58,gp.dialogueState);
        }
        if(hit(8,57,"up") || hit(6,74,"left")) {
            gp.lightUpdate = false;
            gp.eManager.setup();
        }
        
        // ALERT BRIDGE
        if(hit(70,47,"right") || hit(70,48,"right") || hit(70,49,"right")) {
            bridgeAlert(70,47,gp.dialogueState);
            bridgeAlert(70,48,gp.dialogueState);
            bridgeAlert(70,49,gp.dialogueState);
        }
        
        // BRIDGE EVENT
        if(hit(72,47,"right") || hit(72,48,"any") || hit(74,48,"any") ||
            hit(74,49,"any") || hit(75,49,"any") || hit(76,47,"right") ||
            hit(76,49,"any") || hit(77,49,"any") || hit(78,48,"any") ||
            hit(78,49,"any")) {
            damagePit(gp.dialogueState);
        }
    }
    
    public boolean hit(int col, int row, String reqDirection) {
        
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
        
        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        
        return hit;
    }
    
    public void teleport() {

        eventMaster.startDialogue(eventMaster,4);
        gp.player.worldX = gp.tileSize * 88;
        gp.player.worldY = gp.tileSize * 29;
    }
    
    public void damagePit(int gameState) {
        
        gp.gameState = gameState;
        gp.playSE(6);
        eventMaster.startDialogue(eventMaster,0);
        gp.player.life -= 2;
        gp.player.worldX = gp.tileSize * 69;
        gp.player.worldY = gp.tileSize * 48;
        gp.player.direction = "right";
    }
    
    public void healingPool(int gameState) {
        
        if(gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster,1);
            gp.player.life = gp.player.maxLife;
            //gp.aSetter.setMonster(); // to respawn monster
            gp.saveLoad.save();
        }
    }
    
    public void bridgeAlert(int col, int row, int gameState) {
        
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,2);
        eventRect[col][row].eventDone = true;
    }
    
    public void darkAlert(int col, int row, int gameState) {
        
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster,3);
        eventRect[col][row].eventDone = true;
    }
}
