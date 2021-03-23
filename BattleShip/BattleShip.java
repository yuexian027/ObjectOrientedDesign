
/**
 * Write a description of TieTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Random;
public class BattleShip{
    public enum GameState{
        Tie,Redwin,Bluewin, Continue
    }
    public Player[] players = new Player[2];
    public Board[] boards = new Board[2];
    public Board[] attackBoards = new Board[2];
    public int[][] fleets = new int[2][];
    public Board redBoard;
    public Board redAttack;
    public Board blueBoard;
    public Board blueAttack;
    public Player currTurn;
    public GameState status = GameState.Continue;
    public List<Attack> attacksPlayed = new ArrayList<>();
    //count ships for each player
    public int[]redFleet;
    public int[]blueFleet;
    
   
    public void initialize(Player p1, Player p2, int x, int y, int[] shipInfo){
        players[0] = p1;
        players[1] = p2;
        redBoard = new Board(x,y);
        blueBoard = new Board(x,y);
        redAttack = new Board(x,y);
        blueAttack = new Board(x,y);
        boards[0] = redBoard;
        boards[1] = blueBoard;
        attackBoards[0] = redAttack;
        attackBoards[1] = blueAttack;
        redFleet = new int[shipInfo.length];
        blueFleet = new int[shipInfo.length];
        fleets[0] = redFleet;
        fleets[1] = blueFleet;
        if(p1.color == 0){
            this.currTurn = p1;
        }
        else
            this.currTurn = p2;
        //shipInfo is the array : index is ship type label and value is size of the ship
        for(int i = 0; i < shipInfo.length;i++){
            redFleet[i] = shipInfo[i];
            blueFleet[i] = shipInfo[i];
        }
            
        attacksPlayed.clear();
    }
    public boolean arrangeShips(Player player, Ship ship){
        int ind = 0;
        if(player.color == 0){
            ind = 0;
        }
        else ind = 1;
       int xo = ship.startX;
       int yo = ship.startY;
       int size = ship.size;
        if(ship.direction ){
            
            //check whether the ship proposed intersected with other ships
            for(int i = 0; i < size; i++){
                if(boards[ind].squares[xo][yo + i].shipLabel != 0){
                    System.out.println(player.color + "  Invalid Ship " + boards[ind].squares[xo][yo + i].shipLabel);
                    return false;
                }
            }
            
            for(int i = 0; i < size; i++){
                boards[ind].squares[xo][yo + i].shipLabel = ship.label;
            }
        }
        if(!ship.direction ){
            
            for(int i = 0; i < size; i++){
               if(boards[ind].squares[xo + i][yo].shipLabel != 0){
                    System.out.println(player.color + "  Invalid Ship");
                    return false;
                }
            }
            for(int i = 0; i < size; i++){
                boards[ind].squares[xo + i][yo].shipLabel = ship.label;
            }
        }
        return true;
    }
    public boolean playerAttack(Player player, int x, int y){
        int ind = 0;
        if(player.color == 0){
            ind = 0;
        }
        else ind = 1;
        
            Square target = attackBoards[ind].squares[x][y];
            Attack attack = new Attack(player, target);
            boolean res = this.tryAttack(attack,player);
            if(!res){System.out.println(player.color + " " + "invalid attack,retry");res = false;}
            if(res){System.out.println(player.color + " " + "valid attack");}
            if(res){
               boolean hit = this.makeAttack(attack,player);
               if(hit){
                System.out.println(player.color + " " + "successful attack on");
                }
               else{
                System.out.println(player.color + " " + "miss");
                }
            }
            return true;
    }
    private boolean tryAttack(Attack attack, Player player){
        //如果这个格子已经被攻击了返回false
        if(attack.destination.hit == true) return false;
        if(!player.equals(currTurn)) return false;
        return true;
    }
    private boolean makeAttack(Attack attack, Player player){
        int ind = 0;
        if(player.color == 0){
            ind = 1;
        }
        else ind = 0;
        attacksPlayed.add(attack);
        //assume current player is red
        //update curr fleet info for blue and put a hit lable in bludBoard if hit successfully
        int x = attack.destination.x;
        int y = attack.destination.y;
        boolean hit = false;
            if(boards[ind].squares[x][y].shipLabel != 0){
                boards[ind].squares[x][y].hit = true;
                fleets[ind][boards[ind].squares[x][y].shipLabel - 1]--;
                if(fleets[ind][boards[ind].squares[x][y].shipLabel - 1] == 0){
                    System.out.println("a ship of  "+ players[ind] + "  sink");
                }
                hit = true;
            }
        //check win or continue
        boolean win = true;
        for(int i = 0; i < fleets[ind].length; i++){
            if(fleets[ind][i] != 0) win = false;
        }
        if(win){
           if(currTurn.color == 0) status = GameState.Redwin;
           else status = GameState.Bluewin;
        }
        if(currTurn.equals(players[0])) currTurn = players[1];
        else currTurn = players[0];
        return hit; 
        }
    public Square[][]getBoard(Player player){
            int ind = 0;
            if(player.color == 0){
                ind = 1;
            }
            else ind = 1;
            return boards[ind].getBoard();
    }

    
       
    
    public static void main(){
        BattleShip Game = new BattleShip();
        //Assigned red to Human player
        Player player = new Player(0);
        //Assigned blue to cpu player;
        Player cpu = new Player(1);
        //1: Carrier 3 2: Submarine2 3: Destroyer 1
        int[] shipInfo = new int[]{2};
        Game.initialize(player, cpu, 10,10,shipInfo);
        
        // arrange red fleet
        System.out.println("Arrage your fleets:");
        Scanner scan0 = new Scanner(System.in);
        System.out.println("Input Ship Type");
        int label = scan0.nextInt();
        
        System.out.println("Input Ship size");
        int size = scan0.nextInt();
        
        System.out.println("Input Ship direction");
        boolean direction = scan0.nextBoolean();
        
        System.out.println("Input Ship x0");
        int x0 = scan0.nextInt();
        
        System.out.println("Input Ship y0");
        int y0 = scan0.nextInt();
        
        Ship redShip = new Ship(size, label, direction,x0,y0);
        boolean ans = Game.arrangeShips(player, redShip);
        
        // arrange blue fleet
        System.out.println("Arrage your fleets:");
        scan0 = new Scanner(System.in);
        System.out.println("Input Ship Type");
        label = scan0.nextInt();
        
        System.out.println("Input Ship size");
        size = scan0.nextInt();
        
        System.out.println("Input Ship direction");
        direction = scan0.nextBoolean();
        
        System.out.println("Input Ship x0");
        x0 = scan0.nextInt();
        
        System.out.println("Input Ship y0");
        y0 = scan0.nextInt();
        
        Ship blueShip = new Ship(size, label, direction,x0,y0);
        ans = Game.arrangeShips(cpu,blueShip);
        
        
        
        boolean res = false;
        while(Game.status == GameState.Continue){
            Scanner scan = new Scanner(System.in);
            res = false;
            while(!res){
                System.out.println("Enter your target:");
                int x = scan.nextInt();
                System.out.println("Enter your position:");
                int y = scan.nextInt();
                res = Game.playerAttack(player,x,y);
            }
        
            if(Game.status == GameState.Redwin) {
                System.out.println(Game.currTurn + " "+"win");
                break;
            }
            res = false;
            while(!res){
            System.out.println("Enter your position:");
            int x2 = scan.nextInt();
            System.out.println("Enter your position:");
            int y2 = scan.nextInt();
            res = Game.playerAttack(cpu,x2,y2);
            }
            if(Game.status == GameState.Bluewin) {
                System.out.println(Game.currTurn + " "+"win");
                break;
              }
        }
        System.out.println("Game is " + Game.status);
        }
    }
        
    
    
    

