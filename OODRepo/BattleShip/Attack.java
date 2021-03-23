
/**
 * Write a description of Move here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attack {
    public Player player;
    //attack target
    public Square destination;
    public Attack(Player player, Square destination){
        this.player = player;
        this.destination = destination;
    }
        
}
