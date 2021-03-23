
/**
 * Write a description of Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player {
    public boolean Oside;
    public int piece;
    public Player(boolean isO){
        this.Oside = isO;
        this.piece = isO == true ? 1:-1;
        
    }
    public boolean isOside(){
        return this.Oside;
    }
        
        
}
