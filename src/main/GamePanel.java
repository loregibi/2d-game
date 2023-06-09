package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    
    // FPS
    int FPS = 60;
    
    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    public PathFinder pFinder = new PathFinder(this);
    SaveLoad saveLoad = new SaveLoad(this);
    Thread gameThread;
    
    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[30];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[50];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int gameWinState = 7;
    
    public boolean lightUpdate = false;
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improve game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be "focused" to receive key input
    }
    
    public void setupGame() {
        
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

        gameState = titleState;
    }
    
    public void resetGame(boolean restart) {
        
        player.setDefaultPositions();
        player.restoreStatus();
        aSetter.setNPC();
        aSetter.setMonster();
        lightUpdate = false;
        
        if(restart == true) {
            player.setDefaultValues();
            aSetter.setObject();
        }
    }

    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override // Abstract method of Runnable
    public void run() {
        
        double drawInterval= 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            lastTime = currentTime;
            
            if(delta >= 1) {
                // 1 UPDATE: update information such as character positions
                update();
                // 2 DRAW: draw the screen width the update information
                repaint();
                delta--;
            }
        }
    }
    
    public void update() {
        
        if(gameState == playState) {
            
            // PLAYER
            player.update();
            
            // NPC
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            
            // MONSTER
            for(int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    if(monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if(monster[i].alive == false) {
                        monster[i] = null;
                    }
                }
            }
        }
        if(gameState == pauseState) {
            // nothing
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // DEBUG
        long drawStart = 0;
        if(keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }
        
        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            tileM.draw(g2);
        
            // ADD ENTITIES TO THE LIST
            entityList.add(player);
            
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            
            for(int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            
            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                
                @Override
                public int compare(Entity e1, Entity e2) {
                    
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                } 
            });
            
            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            
            // EMPTY ENTITY LIST
            entityList.clear();
            
            // ENVIRONMENT
            if(lightUpdate == true) {
                eManager.draw(g2);
            }
            
            // UI
            ui.draw(g2);
        }
        
        // DEBUG
        if(keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            
            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            
            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize, x, y); 
            y+= lineHeight;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y+= lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);
        }
        
        g2.dispose();
    }
    
    public void playMusic(int i) {
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    public void stopMusic() {
      
        music.stop();
    }
    
    public void playSE(int i) {
        
        se.setFile(i);
        se.play();
    }
}
