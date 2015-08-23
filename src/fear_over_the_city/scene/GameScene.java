package fear_over_the_city.scene;

import fear_over_the_city.Configs;
import fear_over_the_city.entity.Player;
import fear_over_the_city.map.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameScene extends Scene
{
    private Map map;
    private Player player;
    public int score = 0;
    private Font font;
    private int auroraH = 5;
    private int auroraM = 0;
    private int updateTime = 60;
    private BufferedImage spritesheet, spritesun, spritecity1, spritecity2;
    public boolean inPause;
    private PauseScene pausemenu;
    private int lvl;
    
    public GameScene(int lvl)
    {
        try{
            this.spritesheet = ImageIO.read(new File("assets/img/gui.png"));
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(18.0f);
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        System.out.println("level:"+lvl);
        
        this.inPause = false;
        this.lvl = lvl;
        this.spritesun = this.spritesheet.getSubimage(0, 0, 32, 32);
        this.spritecity1 = this.spritesheet.getSubimage(1*32, 0, 32, 32);
        this.spritecity2 = this.spritesheet.getSubimage(2*32, 0, 32, 32);
        
        this.map = new Map(lvl);
        
        this.player = new Player(this.map);
        
        this.map.addPlayer(this.player);
        
        this.pausemenu = new PauseScene();
    }
    
    public Scene update()
    {
        if(!this.inPause)
        {   
            if(updateTime == 0)
            {   
                this.auroraM--;
                if(auroraM <= 0)
                {
                    this.auroraM = 59;
                    this.auroraH--;
                }

                if(this.auroraH < 0)
                {
                    //Looose !!!
                    return new LooseScene();
                }
                
                updateTime = 70;
            }

            if(this.win())
            {
                return new WinScene(this.lvl);
            }
            
            this.map.update();

            if(updateTime > 0){
                updateTime--;
            }
        }
        
        return this;
    }
    
    public boolean win()
    {
        int pop = this.map.getPop();
        int feared = this.map.getFeared();
        if(pop == feared)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void paint(Graphics g)
    {   
        this.map.render(g);
        
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
        
        //Draw fear bar
        g.setColor(Color.BLACK);
        g.fillRect((Configs.SCREEN_WIDTH/2) - 100, 15, 200, 10);
        g.setColor(Color.RED);
        g.fillRect((Configs.SCREEN_WIDTH/2) - 100, 15, (this.map.getFeared() * 200 / this.map.getPop()), 10);
        
        g.drawImage(this.spritecity1, (Configs.SCREEN_WIDTH/2) - 142, 4, this);
        g.drawImage(this.spritecity2, (Configs.SCREEN_WIDTH/2) + 110, 4, this);
        g.drawImage(this.spritesun, Configs.SCREEN_WIDTH - 52 - hourwidth, 4, this);
        
        if(this.inPause)
        {
            this.pausemenu.render(g);
        }
    }
    
    public void setEvent(int code)
    {
        if(!this.inPause)
        {
            int posX = this.player.posX;
            int posY = this.player.posY;

            if(code == Configs.UP){ 
                posY -= 2;
                this.player.dir = Configs.UP;
            }
            if(code == Configs.DOWN){ 
                posY += 2; 
                this.player.dir = Configs.DOWN;
            }
            if(code == Configs.LEFT){  
                posX -= 2; 
                this.player.dir = Configs.LEFT;
            }
            if(code == Configs.RIGHT){ 
                posX += 2; 
                this.player.dir = Configs.RIGHT;
            }
            if(code == Configs.ATTACK){ if(this.player.doScare()){ this.score += 10; } }
            
            this.player.move(posX, posY);
        }
        if(code == Configs.PAUSE){ this.inPause = !(this.inPause); }
    }
}
