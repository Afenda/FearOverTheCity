package fear_over_the_city.entity;

import fear_over_the_city.Configs;
import fear_over_the_city.map.Map;
import fear_over_the_city.map.tile.TileAtlas;
import java.awt.Graphics;
import java.util.Random;

public class Entity 
{
    public Random random;
    public int posX, posY;
    public Map map;
    public int dir;
    
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
    
    public boolean move(int x, int y)
    {   
        if(x < 0) x = 0;
        if(x > Configs.GAME_ZONE_WIDTH - 32) x = Configs.GAME_ZONE_WIDTH - 32;
        if(y < 40) y = 40;
        if(y > Configs.GAME_ZONE_HEIGHT - 32) y = Configs.GAME_ZONE_HEIGHT - 32;
        
        int y0 = y - 40;
        
        switch(this.dir)
        {
            case 0:
                //DOWN
                if(!TileAtlas.atlas.get(this.map.bottom.getTile((x+1)/32, (y0+31)/32)).canPass() || !TileAtlas.atlas.get(this.map.bottom.getTile((x+31)/32, (y0+31)/32)).canPass())
                    return false;
                break;
            case 1:
                //LEFT
                if(!TileAtlas.atlas.get(this.map.bottom.getTile((x+1)/32, (y0+1)/32)).canPass() || !TileAtlas.atlas.get(this.map.bottom.getTile((x+1)/32, (y0+31)/32)).canPass())
                    return false;
                break;
            case 2:
                //RIGHT
                if(!TileAtlas.atlas.get(this.map.bottom.getTile((x+31)/32, (y0+1)/32)).canPass() || !TileAtlas.atlas.get(this.map.bottom.getTile((x+31)/32, (y0+31)/32)).canPass())
                    return false;
                break;
            case 3:
                //UP
                if(!TileAtlas.atlas.get(this.map.bottom.getTile((x+1)/32, (y0+1)/32)).canPass() || !TileAtlas.atlas.get(this.map.bottom.getTile((x+31)/32, (y0+1)/32)).canPass())
                    return false;
                break;
        }
        
        this.posX = x;
        this.posY = y;
        
        return true;
    }
    
    public void render(Graphics g)
    {
        
    }
}
