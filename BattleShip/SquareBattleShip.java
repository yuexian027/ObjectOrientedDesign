
/**
 * Write a description of Square here.
 * represent the square in the board
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SquareBattleShip {
    public int x;
    public int y;
    public int shipLabel;// 0 for empty, 1 for xx, 2for xx
    public boolean hit;
    public String name;
    public SquareBattleShip(int x, int y, int label){
        this.x = x;
        this.y = y;
        this.shipLabel = label;
    }
        
}
