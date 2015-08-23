package fear_over_the_city.map.tile;

import fear_over_the_city.Configs;
import java.util.ArrayList;

public class TileAtlas 
{
    public static ArrayList<Tile> atlas = new ArrayList<>();
    
    public static GrassTile grass   = new GrassTile(Configs.GRASS, 0, 0);
    public static RoadTile road     = new RoadTile(Configs.ROAD, 1, 0);
    public static BushTile bush     = new BushTile(Configs.BUSH, 2, 0);
    public static House1Tile house1 = new House1Tile(Configs.HOUSE1, 3, 0);
    public static House2Tile house2 = new House2Tile(Configs.HOUSE2, 0, 1);
    public static Roof1Tile roof1   = new Roof1Tile(Configs.ROOF1, 1, 1);
    public static Roof2Tile roof2   = new Roof2Tile(Configs.ROOF2, 2, 1);
}
