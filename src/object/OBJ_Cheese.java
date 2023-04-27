package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cheese extends Entity {

    public OBJ_Cheese(GamePanel gp) {

        super(gp);
        
        name = "Cheese";
        down1 = setup("objects/cheese",gp.tileSize,gp.tileSize);
        description = "[Cheese]\nMice don't like it.";
    }
}
