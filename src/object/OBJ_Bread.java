package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bread extends Entity {

    public OBJ_Bread(GamePanel gp) {
        super(gp);
        
        name = "Bread";
        down1 = setup("objects/bread",gp.tileSize,gp.tileSize);
        description = "[Bread]\nBite me or give me to\nthe chef.";
    }
}
