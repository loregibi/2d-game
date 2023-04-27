package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Meat extends Entity {

    public OBJ_Meat(GamePanel gp) {
        
        super(gp);
        
        name = "Meat";
        down1 = setup("objects/meat",gp.tileSize,gp.tileSize);
        description = "[Meat]\nCooked to perfection.";
    }
}
