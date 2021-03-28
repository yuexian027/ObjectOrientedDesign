
/**
 * Write a description of Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board {
    public Square[][] squares;
    public int size;
    public Board(int x, int y){
        this.squares = new Square[x][y];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                Square temp = new Square(i,j,0);
                squares[i][j] = temp;
            }
        }
        this.size = squares.length;
    }
    public Square[][] getBoard(){
        return squares;
    }
    public void resetBoard(){
        this.squares = null;
    }
}
