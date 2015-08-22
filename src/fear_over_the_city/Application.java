package fear_over_the_city;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Application
{
    public static void main(String[] args)
    {
        Game g = new Game(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        
        JFrame f = new JFrame(Configs.NAME);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.WHITE);
        f.setPreferredSize(new Dimension(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT));
        f.add(g);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        g.start();
    }
    
}
