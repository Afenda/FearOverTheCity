/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fear_over_the_city.entity;

import fear_over_the_city.Configs;
import fear_over_the_city.map.Map;
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
    
    private BufferedImage spritesheet;
    private int walkingTime;
    private int dirX, dirY;
    private boolean isScared;
    
    public Children(Map map)
    {
        super(map);
        
        try{
            this.spritesheet = ImageIO.read(new File("assets/img/child.png"));
        }catch(IOException e){
            System.out.println("File not found 'child.png'.");
        }
        
        this.isScared = false;
        
        this.posX = random.nextInt(Configs.SCREEN_WIDTH - 32);
        this.posY = 40 + random.nextInt(Configs.SCREEN_HEIGHT - 32 - 40);

        this.walkingTime = 0;
        this.dirX = this.dirY = 0;
    }
    
    public void update()
    {
        if(this.walkingTime == 0)
        {
            this.dirX = 0;
            this.dirY = 0;
            int dir = random.nextInt(4);
            switch(dir)
            {
                case 1:
                    this.dirY = -1;
                    break;
                case 2:
                    this.dirY = 1;
                    break;
                case 3:
                    this.dirX = -1;
                    break;
                case 4:
                    this.dirX = 1;
                    break;
            }
            this.walkingTime = 50;
        }
        
        this.move(this.posX + dirX, this.posY + dirY);
        
        if(this.walkingTime > 0)
        {
            this.walkingTime--;
        }
    }
    
    public void render(Graphics g)
    {
        g.drawImage(this.spritesheet, posX, posY, null);
    }
    
    public void scare(Player player)
    {
        System.out.println("isScared");
        this.isScared = true;
    }
}
