package monster;

import entity.Entity;
import main.GamePanel;

public final class MON_Orc extends Entity {

    GamePanel gp;
    
    public MON_Orc(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        
        setType(type_monster);
        name = "Orc";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 20;
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 30;
        motion2_duration = 75;
        
        getImage();
        getAttackImage();
    }
    
    public void getImage() {
        
        up1 = setup("monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("monster/orc_right_2",gp.tileSize,gp.tileSize);
    }
    
    public void getAttackImage() {
        
        attackUp1 = setup("monster/orc_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("monster/orc_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setup("monster/orc_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("monster/orc_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("monster/orc_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("monster/orc_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("monster/orc_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("monster/orc_attack_right_2",gp.tileSize*2,gp.tileSize);
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
        
        // Check if it attacks
        if(attacking == false) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
        }
    }
    
    @Override
    public void damageReaction() {
        
        actionLockCounter = 0;
        onPath = true;
    }
}
