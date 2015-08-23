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

public class LooseScene extends Scene 
{
    private BufferedImage bg;
    private Font font, fontL;
    private String title, text1, text2, respawn, back;
    private int choice;
    private int selectedItem = 1;
    
    public LooseScene()
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontL = this.font.deriveFont(Font.BOLD, 28.0f);
            this.bg = ImageIO.read(new File("assets/img/loosemenu.jpg"));
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.title = "You Loose";
        this.text1 = "Sun is up !!";
        this.text2 = "It will completely disappear in a flash.";
        this.back = "Back to main menu";
        this.respawn = "Wait night and try again";
    }
    
    public Scene update()
    {
        if(this.choice == 1)
        {
            return new GameScene(1);
        }
        if(this.choice == 2)
        {
            return new MenuScene();
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
        metrics = g.getFontMetrics(this.font);
        int text1w = metrics.stringWidth(this.text1);
        g.drawString(this.text1, (Configs.SCREEN_WIDTH/2) - (text1w / 2), 150);
        
        int text2w = metrics.stringWidth(this.text2);
        g.drawString(this.text2, (Configs.SCREEN_WIDTH/2) - (text2w / 2), 190);
        
        if(this.selectedItem == 1){ g.setColor(new Color(158, 37, 36)); }else{ g.setColor(Color.BLACK);}
        int respawnw = metrics.stringWidth(this.respawn);
        g.drawString(this.respawn, (Configs.SCREEN_WIDTH/2)-(respawnw / 2), 270);
        
        if(this.selectedItem == 2){ g.setColor(new Color(158, 37, 36)); }else{ g.setColor(Color.BLACK);}
        int backw = metrics.stringWidth(this.back);
        g.drawString(this.back, (Configs.SCREEN_WIDTH / 2) - (backw / 2), 320);
    }
    
    public void setEvent(int code){
        if(code == Configs.DOWN)
        {
            this.selectedItem++;
            if(this.selectedItem > 2){ this.selectedItem = 1;}
        }
        if(code == Configs.UP)
        {
            this.selectedItem--;
            if(this.selectedItem < 1){this.selectedItem = 2;}
        }
        if(code == Configs.PAUSE)
        {
            this.choice = this.selectedItem;
        }
    }
}
