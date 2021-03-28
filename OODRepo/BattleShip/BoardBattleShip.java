
/**
 * Write a description of Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoardBattleShip {
    public SquareBattleShip[][] squareBattleShips;
    public int size;
    public BoardBattleShip(int x, int y){
        this.squareBattleShips = new SquareBattleShip[x][y];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                SquareBattleShip temp = new SquareBattleShip(i,j,0);
                squareBattleShips[i][j] = temp;
            }
        }
        this.size = squareBattleShips.length;
    }
    public SquareBattleShip[][] getBoard(){
        return squareBattleShips;
    }
    public void resetBoard(){
        this.squareBattleShips = null;
    }
}
