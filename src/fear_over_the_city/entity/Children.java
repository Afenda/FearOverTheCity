/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fear_over_the_city.entity;

import fear_over_the_city.Configs;
import fear_over_the_city.audio.Audio;
import fear_over_the_city.map.Map;
import fear_over_the_city.map.tile.TileAtlas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mickaël
 */
public class Children extends Entity{
    
    private BufferedImage spritesheet, spritesheetscared, sprite;
    private int walkingTime;
    private int dirX, dirY;
    private boolean isScared;
    private int timeanim, animX, animY;
    public int timeScared;
    public int sex;
    
    public Children(Map map, int sex)
    {
        super(map);
        
        try{
            if(sex == 0)
            {
                this.spritesheet = ImageIO.read(new File("assets/img/child.png"));
                this.spritesheetscared = ImageIO.read(new File("assets/img/childscared.png"));
            }
            else
            {
                this.spritesheet = ImageIO.read(new File("assets/img/child2.png"));
                this.spritesheetscared = ImageIO.read(new File("assets/img/childscared2.png"));
            }
        }catch(IOException e){
            System.out.println("File not found 'child.png'.");
        }
        
        this.isScared = false;
        
        boolean find = false;
        while(!find)
        {
            this.posX = random.nextInt(Configs.GAME_ZONE_WIDTH - 32);
            this.posY = 40 + random.nextInt(Configs.GAME_ZONE_HEIGHT - 32 - 40);
            if(TileAtlas.atlas.get(this.map.bottom.getTile(this.posX/32, this.posY/32)).canPass())
            {
                find = true;
            }
        }
        
        this.walkingTime = 0;
        this.dirX = this.dirY = 0;
        
        this.timeanim = 5;
        this.animX = 0;
        this.animY = 0;
        this.dir = Configs.DOWN;
        this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
        
        this.timeScared = 420;
    }
    
    public void update()
    {
        if(this.isScared && this.timeScared == 0)
        {
            this.isScared = false;
        }
        if(this.walkingTime == 0)
        {
            this.dirX = 0;
            this.dirY = 0;
            int dir = random.nextInt(5);
            int speed = 0;
            
            if(this.isScared){
                speed = 2;
            }
            
            switch(dir)
            {
                case 1:
                    this.dirY = -1 - speed;
                    this.dir = Configs.UP;
                    break;
                case 2:
                    this.dirY = 1 + speed;
                    this.dir = Configs.DOWN;
                    break;
                case 3:
                    this.dirX = -1 - speed;
                    this.dir = Configs.LEFT;
                    break;
                case 4:
                    this.dirX = 1 + speed;
                    this.dir = Configs.RIGHT;
                    break;
            }
            this.walkingTime = 50;
        }
        
        if(this.dirX == 0 && this.dirY == 0)
        {
            this.animY = this.dir * 32;
            this.animX = 0;
            if(this.isScared)
            {
                this.sprite = this.spritesheetscared.getSubimage(this.animX, this.animY, 32, 32);
            }
            else
            {
                this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
            }
        }
        else
        {
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

            if(this.isScared)
            {
                this.sprite = this.spritesheetscared.getSubimage(this.animX, this.animY, 32, 32);
            }
            else
            {
                this.sprite = this.spritesheet.getSubimage(this.animX, this.animY, 32, 32);
            }

            if(this.timeanim > 0){this.timeanim--;}
        }
        
        this.move(this.posX + dirX, this.posY + dirY);
        
        if(this.walkingTime > 0)
        {
            this.walkingTime--;
        }
        
        if(this.timeScared > 0)
        {
            this.timeScared--;
        }
    }
    
    public boolean isScared()
    {
        return this.isScared;
    }
    
    public void render(Graphics g)
    {
        g.drawImage(this.sprite, posX, posY, null);
    }
    
    public void scare(Player player)
    {      
        Audio.cry.play();        
        this.isScared = true;
        this.timeScared = 420;
    }
}
