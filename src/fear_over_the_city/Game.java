package fear_over_the_city;

import fear_over_the_city.scene.GameScene;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener
{
    private boolean isRunning = false;
    private final int width;
    private final int height;
    private Thread threadG;
    private final GameScene curr_scene;
    
    public Game(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        this.setMaximumSize(new Dimension(this.width, this.height));
        this.setMinimumSize(new Dimension(this.width, this.height));
        this.setPreferredSize(new Dimension(this.width, this.height));
        
        addKeyListener(this);
        
        this.curr_scene = new GameScene();
        this.add(curr_scene);
    }
    
    public void start()
    {
        this.isRunning = true;
        this.threadG = new Thread(this, "GameThread");
        this.threadG.start();
    }   
    
    public void stop()
    {
        this.isRunning = false;
    }
    
    @Override
    public void run() 
    {
        long startTime = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        int frame = 0;
        double nsms = 1000000000 / 60;
        requestFocus();
        boolean update = true;
        
        while(this.isRunning)
        {
            long current = System.nanoTime();            
            
            try{
                Thread.sleep(2);
            }
            catch(InterruptedException e){}
            update = false;
            if((current - lastTime) / nsms  >= 1)
            {           
                frame++;
                lastTime = current;
                update = true;
            }
            
            if(update)
            {
                this.curr_scene.update();
                this.repaint();
            }
            
            if(System.currentTimeMillis() - startTime >= 1000)
            {
                System.out.println("Frame : " + frame);
                frame = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        this.curr_scene.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) { 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.curr_scene.setEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
