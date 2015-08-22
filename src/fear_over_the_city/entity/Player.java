package fear_over_the_city.entity;

import fear_over_the_city.map.Map;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity
{
    private BufferedImage spritesheet;
    private BufferedImage sprite;
    private int animX;
    private int animY;
    public int dir;
    private int timeanim;
    
    public Player(Map level)
    {
        super(level);
        
        try{
            this.spritesheet = ImageIO.read(new File("assets/img/character.png"));
        }catch(IOException e){
            System.out.println("File not found 'character.png'.");
        }
        
        this.animX = 0;
        this.animY = 0;
        this.dir = 0;
        this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
        this.timeanim = 5;
    }
    
    @Override
    public void move(int x, int y)
    {
        super.move(x, y);
        
        if(this.timeanim == 0)
        {
            this.animY = this.dir * 32;
            this.animX += 32;
            if(this.animX > 96)
            {
                this.animX = 0;
            }
            this.timeanim = 5;
        }
        
        this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
        
        if(this.timeanim > 0){this.timeanim--;}
    }
    
    public void doScare()
    {
        int xa0 = 0;
        int ya0 = 0;
        int xa1 = 0;
        int ya1 = 0;
    }
    
    @Override
    public void render(Graphics g)
    {
        g.drawImage(this.sprite, posX, posY, null);
    }
}
