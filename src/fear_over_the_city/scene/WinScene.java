package fear_over_the_city.scene;

import fear_over_the_city.Configs;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WinScene extends Scene 
{
    private BufferedImage bg;
    private Font font, fontL;
    private String title, text1, text2, text3, back;
    private int choice;
    private int selectedItem = 1;
    private int lvl;
    
    public WinScene(int lvl)
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontL = this.font.deriveFont(Font.BOLD, 28.0f);
            this.bg = ImageIO.read(new File("assets/img/bgmenu.jpg"));
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.lvl = lvl+1;
        this.title = "You Win";
        this.text1 = "Your wickedness makes you become";
        this.text2 = "the master of the place";
        this.text3 = "next level add 1 citizen.";
        this.back = "Next level";
    }
    
    public Scene update()
    {
        if(this.choice == 1)
        {
            return new GameScene(this.lvl);
        }
        
        return this;
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(this.bg, 0, 0, null);
        
        g.setColor(Color.BLACK);
        g.setFont(this.fontL);
        FontMetrics metrics = g.getFontMetrics(this.fontL);
        int titlewidth = metrics.stringWidth(this.title);
        g.drawString(this.title, (Configs.SCREEN_WIDTH / 2) - (titlewidth / 2), 90);
        
        g.setFont(this.font);
        g.setColor(Color.WHITE);
        metrics = g.getFontMetrics(this.font);
        int text1w = metrics.stringWidth(this.text1);
        g.drawString(this.text1, (Configs.SCREEN_WIDTH/2) - (text1w / 2), 210);
        
        int text2w = metrics.stringWidth(this.text2);
        g.drawString(this.text2, (Configs.SCREEN_WIDTH/2) - (text2w / 2), 270);
        
        int text3w = metrics.stringWidth(this.text3);
        g.drawString(this.text3, (Configs.SCREEN_WIDTH/2) - (text3w/2), 360);
        
        g.setColor(new Color(0, 34, 104));
        int backw = metrics.stringWidth(this.back);
        g.drawString(this.back, Configs.SCREEN_WIDTH - 20 - backw, Configs.SCREEN_HEIGHT - 40 - this.font.getSize());
    }
    
    public void setEvent(int code){
        
        if(code == Configs.PAUSE)
        {
            this.choice = this.selectedItem;
        }
    }
}
