/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fear_over_the_city.map.tile;

/**
 *
 * @author Mickaël
 */
public class House1Tile extends Tile{
    
    public House1Tile(int id, int x, int y)
    {
        super(id, x, y);
    }
    
    public boolean canPass()
    {
        return false;
    }
}
