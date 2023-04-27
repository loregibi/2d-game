package monster;

import entity.Entity;
import main.GamePanel;

public final class MON_OrangeSlime extends Entity {

    GamePanel gp;
    
    public MON_OrangeSlime(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        
        setType(type_monster);
        name = "Orange Slime";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 4;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage() {
        
        up1 = setup("monster/orangeslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("monster/orangeslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("monster/orangeslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("monster/orangeslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("monster/orangeslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("monster/orangeslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("monster/orangeslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("monster/orangeslime_down_2",gp.tileSize,gp.tileSize);
    }
    
    @Override
    public void setAction() {
        
        if(onPath == true) {
            
            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);
            
            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else {
            // Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            
            // Get a random direction 
            getRandomDirection();
        }
    }
    
    @Override
    public void damageReaction() {
        
        actionLockCounter = 0;
        onPath = true;
    }
    
}
