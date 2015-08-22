package fear_over_the_city.scene;

import fear_over_the_city.Configs;
import fear_over_the_city.entity.Children;
import fear_over_the_city.entity.Player;
import fear_over_the_city.map.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameScene extends Scene
{
    private Map map;
    private Player player;
    public Children children;
    public int score = 0;
    private Font font;
    private int auroraH = 8;
    private int auroraM = 0;
    private int updateTime = 70;
    private BufferedImage spritesheet, spritesun;
    
    public GameScene()
    {
        try{
            this.spritesheet = ImageIO.read(new File("assets/img/gui.png"));
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(18.0f);
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.spritesun = this.spritesheet.getSubimage(0, 0, 32, 32);
        
        this.map = new Map();
        
        this.player = new Player(this.map);
        this.children = new Children(this.map);
    }
    
    public void update()
    {
        if(updateTime == 0)
        {
            this.auroraM--;
            if(auroraM <= 0)
            {
                this.auroraM = 59;
                this.auroraH--;
            }
            updateTime = 70;
        }
        
        this.children.update();
        
        if(updateTime > 0){
            updateTime--;
        }
    }
    
    @Override
    public void paint(Graphics g)
    {   
        this.map.render(g);
        
        this.player.render(g);
        this.children.render(g);
        
        this.renderLight(g);
        
        this.renderGUI(g);
    }
    
    public void renderLight(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);
    }
    
    public void renderGUI(Graphics g)
    {
        g.setColor(new Color(160, 81, 151));
        g.fillRect(0, 0, Configs.SCREEN_WIDTH, 40);
        
        g.setFont(this.font);
        g.setColor(Color.BLACK);
        g.drawString("score : " + score, 10, 10 + this.font.getSize());
        
        String hour = String.format("%02d", this.auroraH) + ":" + String.format("%02d", this.auroraM);
        FontMetrics metrics = g.getFontMetrics(this.font);
        int hourwidth = metrics.stringWidth(hour);
        g.drawString(hour, Configs.SCREEN_WIDTH - 10 - hourwidth, 10 + this.font.getSize());
        
        g.drawImage(this.spritesun, Configs.SCREEN_WIDTH - 52 - hourwidth, 4, this);
    }
    
    public void setEvent(KeyEvent e)
    {
        int posX = this.player.posX;
        int posY = this.player.posY;

        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_Z){ 
            posY -= 2;
            this.player.dir = 3;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){ 
            posY += 2; 
            this.player.dir = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q){  
            posX -= 2; 
            this.player.dir = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){ 
            posX += 2; 
            this.player.dir = 2;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){ this.player.doScare(); }
        
        this.player.move(posX, posY);
    }
}
