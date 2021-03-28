
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
    public String name;
    //true for horizontal;
    //false for vertical;
    public Ship(int size, int label, boolean direction, int x, int y, String name){
        this.size = size;
        this.label = label;
        this.direction = direction;
        this.startX = x;
        this.startY = y;
        this.name = name;
    }
}
