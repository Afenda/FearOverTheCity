package fear_over_the_city.entity;

import fear_over_the_city.Configs;
import fear_over_the_city.map.Map;
import java.awt.Graphics;
import java.util.Random;

public class Entity 
{
    public Random random;
    public int posX, posY;
    public Map map;
    
    public Entity(Map map)
    {
        this.map = map;
        this.random = new Random();
        this.posX = 0;
        this.posY = 40;
    }
    
    public void update()
    {
        
    }
    
    public void move(int x, int y)
    {
        if(x <= 0) x = 0;
        if(x >= Configs.GAME_ZONE_WIDTH) x = Configs.GAME_ZONE_WIDTH;
        if(y <= 40) y = 40;
        if(y >= Configs.GAME_ZONE_HEIGHT) y = Configs.GAME_ZONE_HEIGHT;
        
        this.posX = x;
        this.posY = y;
    }
    
    public void render(Graphics g)
    {
        
    }
}
