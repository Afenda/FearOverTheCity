package fear_over_the_city.map.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile 
{
    public int id;
    protected BufferedImage img, sprite;
    
    public Tile(int id, int x, int y)
    {
        try{
            this.img = ImageIO.read(new File("assets/img/tilemap.png"));
        }catch(IOException e){
            System.out.println("File not found 'tilemap.png'.");
        }
        
        this.id = id;
        this.sprite = this.img.getSubimage(x * 32, y * 32, 32, 32);
        TileAtlas.atlas.add(this);
    }
    
    public boolean canPass()
    {
        return true;
    }
    
    public void render(Graphics g, int x, int y)
    {
        g.drawImage(this.sprite, x, y, null);
    }
}
