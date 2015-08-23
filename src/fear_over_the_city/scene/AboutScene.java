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

public class AboutScene extends Scene
{
    private Font font, fontXL;
    private String title, back, text1, text2, text3, text4;
    private BufferedImage bg;
    private int selectedItem = 1;
    private int choice;
    
    public AboutScene()
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontXL = this.font.deriveFont(Font.BOLD, 32.0f);  
            
            this.bg = ImageIO.read(new File("assets/img/bgmenu.jpg"));
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.title = "About Fear over the city";
        this.text1 = "This game was made in 48 hours by COMESSE Mickael";
        this.text2 = "for the Ludum Dare #33 competition.";
        this.text3 = "Edited in 22/23 august 2015.";
        this.text4 = "It is dedicated to my family and my girlfriend <3.";
        this.back = "Back to main";
        
        this.choice = 0;
    }
    
    public Scene update()
    { 
        if(this.choice == 1)
        {
            return new MenuScene();
        }
        
        return this; 
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(this.bg, 0, 0, this);
        
        g.setFont(this.fontXL);
        g.setColor(Color.BLACK);
        
        FontMetrics metrics = g.getFontMetrics(this.fontXL);
        int titlewidth = metrics.stringWidth(this.title);
        g.drawString(this.title, (Configs.SCREEN_WIDTH / 2) - (titlewidth / 2), 90);
        
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        metrics = g.getFontMetrics(this.font);
        int text1w = metrics.stringWidth(this.text1);
        g.drawString(this.text1, (Configs.SCREEN_WIDTH/2) - (text1w/2), 180);
        
        int text2w = metrics.stringWidth(this.text2);
        g.drawString(this.text2, (Configs.SCREEN_WIDTH/2) - (text2w/2), 230);
        
        int text3w = metrics.stringWidth(this.text3);
        g.drawString(this.text3, (Configs.SCREEN_WIDTH/2) - (text3w/2), 300);
        
        int text4w = metrics.stringWidth(this.text4);
        g.drawString(this.text4, (Configs.SCREEN_WIDTH/2)-(text4w/2), 380);
        
        g.setColor(new Color(0, 34, 104));
        int backwidth = metrics.stringWidth(this.back);
        g.drawString(this.back, Configs.SCREEN_WIDTH - 20 - backwidth, Configs.SCREEN_HEIGHT - 40 - this.font.getSize());
    }
    
    public void setEvent(int code){
        
        if(code == Configs.PAUSE)
        {
            this.choice = this.selectedItem;
        }
    }
}
