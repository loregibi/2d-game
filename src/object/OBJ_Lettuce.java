package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lettuce extends Entity {

    public OBJ_Lettuce(GamePanel gp) {
        
        super(gp);
        
        name = "Lettuce";
        down1 = setup("objects/lettuce",gp.tileSize,gp.tileSize);
        description = "[Lettuce]\nFor vegetarians only.";
    }
}
