
/**
 * Write a description of TieTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TieTacToe {
    public enum GameState{
        Tie,Owin,Xwin, Continue
    }
    public Player[] players = new Player[2];
    public Board board;
    public Player currTurn;
    public GameState status = GameState.Continue;
    public List<Move> movesPlayed = new ArrayList<>();
    public int[] rowSum ;
    public int[] colSum;
    public int[] diagSum;
   
    public void initialize(Player p1, Player p2, int x, int y){
        players[0] = p1;
        players[1] = p2;
        board = new Board(x,y);
        if(p1.Oside){
            this.currTurn = p1;
        }
        else
            this.currTurn = p2;
        rowSum = new int[x];
        colSum = new int[x];
        diagSum = new int[x];
        movesPlayed.clear();
    }
    public boolean playerMove(Player player, int x, int y){
        Square end = board.squares[x][y];
        Move move = new Move(player, end);
        boolean res = this.makeMove(move, player);
        if(!res) {System.out.println(player + " " + "invalid move,retry");}
        if(res){System.out.println(player + " " +"move successfully to" + x + " " + y);}
        return res;
    }
    private boolean makeMove(Move move, Player player){
        if(move.destination.piece != 0) return false;
        if(!player.equals(currTurn)) return false;
        movesPlayed.add(move);
        rowSum[move.destination.x] += currTurn.piece;
        colSum[move.destination.y] += currTurn.piece;
        //update square piece
        move.destination.piece = player.piece;
        //check win or continue
        if(rowSum[move.destination.x] == board.squares.length || colSum[move.destination.y] == board.squares.length){
            if(currTurn.Oside) status = GameState.Owin;
            else status = GameState.Xwin;
        }
        
        if(movesPlayed.size() == board.size * board.size) status = GameState.Tie;
        if(currTurn.equals(players[0])) currTurn = players[1];
        else currTurn = players[0];
        return true;
    }    
    
    public static void main(){
        TieTacToe Game = new TieTacToe();
        Player player = new Player(true);
        Player cpu = new Player(false);
        Game.initialize(player, cpu, 3,3);
        boolean res = false;
        while(Game.status == GameState.Continue){
            Scanner scan = new Scanner(System.in);
            res = false;
            while(!res){
            System.out.println("Enter your position:");
            int x = scan.nextInt();
            System.out.println("Enter your position:");
            int y = scan.nextInt();
            res = Game.playerMove(player,x,y);
        }
        if(Game.status == GameState.Xwin) {
            System.out.println(Game.currTurn + " "+"win");
            break;
        }
            res = false;
            while(!res){
            System.out.println("Enter your position:");
            int x2 = scan.nextInt();
            System.out.println("Enter your position:");
            int y2 = scan.nextInt();
            res = Game.playerMove(cpu,x2,y2);
        }
        if(Game.status == GameState.Owin) {
            System.out.println(Game.currTurn + " "+"win");
            break;
        }
        }
        System.out.println("Game is " + Game.status);
        
    }
    
    }
        
    
    
    

