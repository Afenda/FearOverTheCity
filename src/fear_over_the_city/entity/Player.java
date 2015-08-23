package fear_over_the_city.entity;

import fear_over_the_city.audio.Audio;
import fear_over_the_city.map.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends Entity
{
    private BufferedImage spritesheet;
    private BufferedImage spritesheet2;
    private BufferedImage sprite;
    private int animX;
    private int animY;
    private int timeanim;
    private int atkanim;
    
    public Player(Map map)
    {
        super(map);
        
        try{
            this.spritesheet = ImageIO.read(new File("assets/img/character.png"));
            this.spritesheet2 = ImageIO.read(new File("assets/img/attack.png"));
        }catch(IOException e){
            System.out.println("File not found 'character.png' or 'gui.png'.");
        }
        
        this.animX = 0;
        this.animY = 0;
        this.dir = 0;
        this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
        this.timeanim = 5;
        this.atkanim = 0;
    }
    
    @Override
    public boolean move(int x, int y)
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
        
        return true;
    }
    
    public boolean doScare()
    {   
        ArrayList<Children> childs = this.map.getChilds(this.posX, this.posY);
        
        Audio.doScareClip.play();
        
        for(int i=0;i<childs.size();i++)
        {
            childs.get(i).scare(this);
        }
        this.atkanim = 12;
        
        if(childs.size() > 0)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void render(Graphics g)
    {
        if(this.atkanim > 0)
        {
            //attack animation
            int animX = (int)this.atkanim/4;
            BufferedImage img = this.spritesheet2.getSubimage(animX*48, 0, 48, 48);
            g.drawImage(img, posX - 8, posY - 8, null);
            this.atkanim--;
        }
        
        g.drawImage(this.sprite, posX, posY, null);
    }
}
