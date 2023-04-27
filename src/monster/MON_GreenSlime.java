package monster;

import entity.Entity;
import main.GamePanel;

public final class MON_GreenSlime extends Entity{

    GamePanel gp;
    
    public MON_GreenSlime(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        
        setType(type_monster);
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage() {
        
        up1 = setup("monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("monster/greenslime_down_2",gp.tileSize,gp.tileSize);
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
