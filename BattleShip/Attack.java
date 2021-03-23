
/**
 * Write a description of Move here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attack {
    public PlayerBattleShip playerBattleShip;
    //attack target
    public SquareBattleShip destination;
    public Attack(PlayerBattleShip playerBattleShip, SquareBattleShip destination){
        this.playerBattleShip = playerBattleShip;
        this.destination = destination;
    }
        
}
