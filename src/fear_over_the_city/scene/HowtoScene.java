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

public class HowtoScene extends Scene
{
    private BufferedImage bg;
    private Font font, fontXL;
    private String title, back, text1,text2,text3,text4,text5;
    private int selectedItem = 1;
    private int choice;
    
    public HowtoScene()
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontXL = this.font.deriveFont(Font.BOLD, 32.0f); 
            
            this.bg = ImageIO.read(new File("assets/img/bgmenu.jpg"));
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.title = "How to play";
        this.back = "Back to main";
        this.text1 = "Move character with arrows keys,";
        this.text2 = "press SPACE to do scare";
        this.text3 = "press ENTER to pause the game.";
        this.text4 = "Scared all citizens before sunrise to win.";
        this.text5 = "But citizen are scared a short time.";
        
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
        
        g.setFont(this.font);
        metrics = g.getFontMetrics(this.font);
        g.setColor(Color.WHITE);
        int text1w = metrics.stringWidth(this.text1);
        g.drawString(this.text1, (Configs.SCREEN_WIDTH/2)-(text1w/2), 150);
        
        int text2w = metrics.stringWidth(this.text2);
        g.drawString(this.text2, (Configs.SCREEN_WIDTH/2)-(text2w/2), 200);
        
        int text3w = metrics.stringWidth(this.text3);
        g.drawString(this.text3, (Configs.SCREEN_WIDTH/2)-(text3w/2), 250);
        
        int text4w = metrics.stringWidth(this.text4);
        g.drawString(this.text4, (Configs.SCREEN_WIDTH/2)-(text4w/2), 340);
        
        int text5w = metrics.stringWidth(this.text5);
        g.drawString(this.text5, (Configs.SCREEN_WIDTH/2)-(text5w/2), 390);
        
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
