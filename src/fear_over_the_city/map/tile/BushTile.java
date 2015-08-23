package fear_over_the_city.map.tile;

public class BushTile extends Tile {
    
    public BushTile(int id, int x, int y)
    {
        super(id, x, y);
    }
    
    public boolean canPass()
    {
        return false;
    }
}
