package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tomato extends Entity {


    public OBJ_Tomato(GamePanel gp) {
        
        super(gp);
        
        name = "Tomato";
        down1 = setup("objects/tomato",gp.tileSize,gp.tileSize);
        description = "[Tomato]\nEdible red ball.";
    }
}
