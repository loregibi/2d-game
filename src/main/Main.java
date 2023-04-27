package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Five Ingredients");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        gamePanel.config.loadConfig();
        
        window.pack(); // This window to be sized to fit the preferred size and layouts of its subcomponents (=GamePanel)
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
