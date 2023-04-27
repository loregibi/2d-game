package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Oil extends Entity {

    public OBJ_Oil(GamePanel gp) {
        
        super(gp);

        name = "Oil";
        down1 = setup("objects/oil",gp.tileSize,gp.tileSize);
        description = "[Oil]\nMade with real sicilian\nolives.";
    }
}
