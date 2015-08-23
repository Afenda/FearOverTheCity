package fear_over_the_city;

import fear_over_the_city.scene.GameScene;
import fear_over_the_city.scene.MenuScene;
import fear_over_the_city.scene.Scene;
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
    private Scene curr_scene;
    public boolean[] keys;
    
    public Game(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        this.keys = new boolean[KeyEvent.KEY_LAST];
        
        for(int i = 0;i<KeyEvent.KEY_LAST;i++)
        {
            this.keys[i] = false;
        }
        
        this.setMaximumSize(new Dimension(this.width, this.height));
        this.setMinimumSize(new Dimension(this.width, this.height));
        this.setPreferredSize(new Dimension(this.width, this.height));
        
        addKeyListener(this);
        
        this.curr_scene = new MenuScene();
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
                if(this.keys[KeyEvent.VK_DOWN] || this.keys[KeyEvent.VK_S])
                {
                    this.curr_scene.setEvent(Configs.DOWN);
                }
                else if(this.keys[KeyEvent.VK_UP] || this.keys[KeyEvent.VK_Z])
                {
                    this.curr_scene.setEvent(Configs.UP);
                }
                else if(this.keys[KeyEvent.VK_LEFT] || this.keys[KeyEvent.VK_Q])
                {
                    this.curr_scene.setEvent(Configs.LEFT);
                }
                else if(this.keys[KeyEvent.VK_RIGHT] || this.keys[KeyEvent.VK_D])
                {
                    this.curr_scene.setEvent(Configs.RIGHT);
                }
                
                this.curr_scene = this.curr_scene.update();
                
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
        requestFocus();
        
        //Clear Screen
        g.setColor(Color.WHITE);
        
        g.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
        this.curr_scene.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) { 
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if( this.curr_scene instanceof GameScene)
        {
            this.keys[e.getKeyCode()] = true;
        }
        else
        {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_Z)
            {
                this.curr_scene.setEvent(Configs.UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            {
                this.curr_scene.setEvent(Configs.DOWN);
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.curr_scene.setEvent(Configs.PAUSE);
        }
        if(this.keys[KeyEvent.VK_SPACE])
        {
            this.curr_scene.setEvent(Configs.ATTACK);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }
}
