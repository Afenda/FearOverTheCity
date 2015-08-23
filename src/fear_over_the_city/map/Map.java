package fear_over_the_city.map;

import fear_over_the_city.entity.Children;
import fear_over_the_city.entity.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Map extends JPanel
{
    private BufferedImage img;
    public ArrayList<Children> childs;
    public Player player;
    private int pop;
    public Layer bottom, top;
    
    public Map(int lvl)
    {
        super();
        this.childs = new ArrayList<>();
        
        this.bottom = new Layer(0,20, 15);
        this.top = new Layer(1, 20, 15);
        
        for(int i=0;i<3+lvl;i++)
        {
            this.childs.add(new Children(this, i%2));
        }
        this.pop = 3+lvl;
    }
    
    public void addPlayer(Player player)
    {
        this.player = player;
    }
    
    public void update()
    {
        for(int i=0;i<this.childs.size();i++)
        {
            this.childs.get(i).update();
        }
    }
    
    public void render(Graphics g)
    {
        this.bottom.render(g);
        
        for(int i=0;i<this.childs.size();i++)
        {
            this.childs.get(i).render(g);
        }
        
        this.player.render(g);
        
        this.top.render(g);
        
    }
    
    public int getFeared(){
        int nbFeared = 0;
        
        for(int i=0;i<this.childs.size();i++)
        {
            Children c = this.childs.get(i);
            if(c.isScared())
            {
                nbFeared++;
            }
        }
        return nbFeared;
    }
    
    public ArrayList<Children> getChilds(int x, int y)
    {   
        ArrayList<Children> res = new ArrayList<>();
        
        for(int i=0;i<this.childs.size();i++)
        {
            Children c = this.childs.get(i);
            if(!c.isScared())
            {
                int xc0 = c.posX;
                int yc0 = c.posY;
                int xc1 = xc0 + 32;
                int yc1 = yc0 + 32;

                if(!(x + 32 + 8 < xc0 || y + 32 + 8 < yc0 || x - 8 > xc1 || y - 8 > yc1))
                {
                    res.add(c);
                }
            }
        }
        
        return res;
    }
    
    public int getPop()
    {
        return this.pop;
    }
    
    public void add(Children c)
    {
        this.childs.add(c);
    }
}
