
/**
 * Write a description of Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship {
    public int size;
    public int label;
    public int startX;
    public int startY;
    public boolean direction;
    //0 for horizontal;
    //1 for vertical;
    public Ship(int size, int label, boolean direction, int x, int y){
        this.size = size;
        this.label = label;
        this.startX = x;
        this.startY = y;
    
    }
}
