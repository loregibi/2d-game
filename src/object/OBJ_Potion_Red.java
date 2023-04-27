package object;

import entity.Entity;
import main.GamePanel;

public final class OBJ_Potion_Red extends Entity{

    GamePanel gp;
    int value = 5;
    
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        setType(type_consumable);
        name = "Red Potion";
        down1 = setup("objects/potion_red",gp.tileSize,gp.tileSize);
        description = "[Red Potion]\nHeals your life by " + value + ".";
        
        setDialogue();
    }
    
    public void setDialogue() {
        
        dialogues[0][0] = "You drink the " + name + "!\n"
                + "Your life has been recovered by " + value + ".";
    }
    
    @Override
    public void use(Entity entity) {
        
        startDialogue(this,0);
        
        entity.life += value;
        
        if(gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);            
    }
}
