package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toque extends Entity {
    
    public OBJ_Toque(GamePanel gp) {
        
        super(gp);
        
        setType(type_toque);
        name = "Toque Blanche";
        down1 = setup("objects/toque",gp.tileSize,gp.tileSize);
        speedValue = 6;
        description = "[Toque Blanche]\nThis make me faster.";
    }
}
