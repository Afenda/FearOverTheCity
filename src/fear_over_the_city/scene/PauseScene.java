package fear_over_the_city.scene;

import fear_over_the_city.Configs;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class PauseScene extends Scene
{
    private Font font, fontL;
    private String pausetxt;
    
    public PauseScene()
    {
        try{
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/AnarchySans.otf"));
            this.font = this.font.deriveFont(Font.BOLD, 18.0f);
            this.fontL = this.font.deriveFont(Font.BOLD, 28.0f);            
        }catch(FontFormatException|IOException e){
            System.out.println("File not found 'AnarchySans.otf'.");
        }
        
        this.pausetxt = "Pause";
    }
    
    public Scene update()
    {
        return this;
    }
    
    public void render(Graphics g)
    {
        g.setColor(new Color(255, 255, 255, 128));
        g.fillRect(0, 0, Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT);

        g.setFont(this.fontL);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics(this.fontL);
        int pausewidth = metrics.stringWidth(pausetxt);
        g.drawString(this.pausetxt, (Configs.SCREEN_WIDTH / 2) - (pausewidth / 2), 120);
    }
}
