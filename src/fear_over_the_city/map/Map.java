package fear_over_the_city.map;

import fear_over_the_city.Configs;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel
{
    private int nbTilew = 20;
    private int nbTileh = 15;
    private BufferedImage img;
    public Map()
    {
        super();
        try{
            this.img = ImageIO.read(new File("assets/img/grass.jpg"));
        }catch(IOException e){
            System.out.println("File not found 'grass.jpg'.");
        }
    }
    
    public void render(Graphics g)
    {
        for(int i=0;i < this.nbTilew;i++)
        {
            for(int j=0;j < this.nbTileh;j++)
            {
                g.drawImage(this.img, i*32, j*32 + 40, this);
            }
        }
    }
}
