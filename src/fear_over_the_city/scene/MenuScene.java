package fear_over_the_city.scene;

import fear_over_the_city.Configs;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuScene extends Scene
{
    private BufferedImage bg;
    private Font font, fontXL, fontXS;
    private String title, newgame, htp , about;
    private int selectedItem = 1;
    private int choice;
    
    public MenuScene()
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.fontXS = this.font.deriveFont(13.0f);
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontXL = this.font.deriveFont(Font.BOLD, 32.0f); 
            
            this.bg = ImageIO.read(new File("assets/img/mainmenu.jpg"));
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.title = "Fear over the city";
        this.newgame = "New game";
        this.htp = "How to Play";
        this.about = "About";
        
        this.choice = 0;
    }
    
    public Scene update()
    {
        switch(this.choice)
        {
            case 1:
                return new GameScene(1);
            case 2:
                return new HowtoScene();
            case 3:
                return new AboutScene();
        }
        return this;
    }
    
    public void paint(Graphics g)
    {
        g.drawImage(this.bg, 0, 0,this);
        
        g.setFont(this.fontXL);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics(this.fontXL);
        int titlewidth = metrics.stringWidth(this.title);
        g.drawString(this.title, (Configs.SCREEN_WIDTH / 2) - (titlewidth / 2), 90);
        
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        metrics = g.getFontMetrics(this.font);
        int newgamewidth = metrics.stringWidth(this.newgame);
        if(this.selectedItem == 1){ g.setColor(new Color(0, 34, 104)); }else{ g.setColor(Color.WHITE); }
        g.drawString(this.newgame, (Configs.SCREEN_WIDTH / 2) - (newgamewidth / 2), 180);
        
        int htpw = metrics.stringWidth(this.htp);
        if(this.selectedItem == 2){ g.setColor(new Color(0, 34, 104)); }else{ g.setColor(Color.WHITE); }
        g.drawString(this.htp, (Configs.SCREEN_WIDTH / 2) - (htpw / 2), 230);
        
        int creditwidth = metrics.stringWidth(this.about);
        if(this.selectedItem == 3){ g.setColor(new Color(0, 34, 104)); }else{ g.setColor(Color.WHITE); }
        g.drawString(this.about, (Configs.SCREEN_WIDTH / 2) - (creditwidth / 2), 280);
        
        g.setColor(Color.WHITE);
        g.setFont(this.fontXS);
        metrics = g.getFontMetrics(this.fontXS);
        int versionwidth = metrics.stringWidth(Configs.VERSION);
        g.drawString(Configs.VERSION, Configs.SCREEN_WIDTH - 20 - versionwidth, Configs.SCREEN_HEIGHT - 40 - this.fontXS.getSize());
    }
    
    public void setEvent(int code)
    {
        if(code == Configs.DOWN)
        {
            this.selectedItem++;
            if(this.selectedItem > 3){ this.selectedItem = 1;}
        }
        if(code == Configs.UP)
        {
            this.selectedItem--;
            if(this.selectedItem < 1){this.selectedItem = 3;}
        }
        if(code == Configs.PAUSE)
        {
            this.choice = this.selectedItem;
        }
    }
}
