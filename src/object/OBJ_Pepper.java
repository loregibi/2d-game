package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pepper extends Entity {

    public OBJ_Pepper(GamePanel gp) {
        
        super(gp);

        name = "Pepper";
        down1 = setup("objects/pepper",gp.tileSize,gp.tileSize);
        description = "[Pepper]\nIt's the non spicy one.";
    }
}
