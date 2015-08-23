package fear_over_the_city.audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Audio
{   
    public static Audio doScareClip = new Audio("assets/sounds/doscare.wav");
    public static Audio cry = new Audio("assets/sounds/cry.wav");
    
    public String path;
    
    private Audio(String path)
    {
        this.path = path;
    }
    
    public void play()
    {
        try{
            InputStream in = new FileInputStream(path);
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
