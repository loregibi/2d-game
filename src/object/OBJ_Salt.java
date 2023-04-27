package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Salt extends Entity {

    public OBJ_Salt(GamePanel gp) {
        
        super(gp);
        
        name = "Salt";
        down1 = setup("objects/salt",gp.tileSize,gp.tileSize);
        description = "[Salt]\nGoes up, doesn't go\ndown.";
    }
}
