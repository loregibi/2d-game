package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hamburger extends Entity {

    public OBJ_Hamburger(GamePanel gp) {
        
        super(gp);
        
        name = "Hamburger";
        down1 = setup("objects/hamburger",gp.tileSize,gp.tileSize);
        description = "[Hamburger]\nMade by the best chef.";
    }
}