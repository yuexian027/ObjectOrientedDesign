
/**
 * Write a description of TieTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class BattleShip{
    public enum GameState{
        Tie,Redwin,Bluewin, Continue
    }
    public PlayerBattleShip[] players = new PlayerBattleShip[2];
    public BoardBattleShip[] boardBattleShips = new BoardBattleShip[2];
    public BoardBattleShip[] attackBoards = new BoardBattleShip[2];
    public int[][] fleets = new int[2][];
    public BoardBattleShip redBoard;
    public BoardBattleShip redAttack;
    public BoardBattleShip blueBoardBattleShip;
    public BoardBattleShip blueAttack;
    public PlayerBattleShip currTurn;
    public GameState status = GameState.Continue;
    public List<Attack> attacksPlayed = new ArrayList<>();
    //count ships for each player
    public int[]redFleet;
    public int[]blueFleet;
    
   
    public void initialize(PlayerBattleShip p1, PlayerBattleShip p2, int x, int y, int[] shipInfo){
        players[0] = p1;
        players[1] = p2;
        redBoard = new BoardBattleShip(x,y);
        blueBoardBattleShip = new BoardBattleShip(x,y);
        redAttack = new BoardBattleShip(x,y);
        blueAttack = new BoardBattleShip(x,y);
        boardBattleShips[0] = redBoard;
        boardBattleShips[1] = blueBoardBattleShip;
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
    public boolean arrangeShips(PlayerBattleShip playerBattleShip, Ship ship){
        int ind = 0;
        if(playerBattleShip.color == 0){
            ind = 0;
        }
        else ind = 1;
       int xo = ship.startX;
       int yo = ship.startY;
       int size = ship.size;
        if(ship.direction ){
            
            //check whether the ship proposed intersected with other ships
            for(int i = 0; i < size; i++){
                if(boardBattleShips[ind].squareBattleShips[xo][yo + i].shipLabel != 0){
                    System.out.println(playerBattleShip.color + "  Invalid Ship " + boardBattleShips[ind].squareBattleShips[xo][yo + i].shipLabel);
                    return false;
                }
            }
            
            for(int i = 0; i < size; i++){
                boardBattleShips[ind].squareBattleShips[xo][yo + i].shipLabel = ship.label;
            }
        }
        if(!ship.direction ){
            
            for(int i = 0; i < size; i++){
               if(boardBattleShips[ind].squareBattleShips[xo + i][yo].shipLabel != 0){
                    System.out.println(playerBattleShip.color + "  Invalid Ship");
                    return false;
                }
            }
            for(int i = 0; i < size; i++){
                boardBattleShips[ind].squareBattleShips[xo + i][yo].shipLabel = ship.label;
            }
        }
        return true;
    }
    public boolean playerAttack(PlayerBattleShip playerBattleShip, int x, int y){
        int ind = 0;
        if(playerBattleShip.color == 0){
            ind = 0;
        }
        else ind = 1;
        
            SquareBattleShip target = attackBoards[ind].squareBattleShips[x][y];
            Attack attack = new Attack(playerBattleShip, target);
            boolean res = this.tryAttack(attack, playerBattleShip);
            if(!res){System.out.println(playerBattleShip.color + " " + "invalid attack,retry");res = false;}
            if(res){System.out.println(playerBattleShip.color + " " + "valid attack");}
            if(res){
               boolean hit = this.makeAttack(attack, playerBattleShip);
               if(hit){
                System.out.println(playerBattleShip.color + " " + "successful attack on");
                }
               else{
                System.out.println(playerBattleShip.color + " " + "miss");
                }
            }
            return true;
    }
    private boolean tryAttack(Attack attack, PlayerBattleShip playerBattleShip){
        //如果这个格子已经被攻击了返回false
        if(attack.destination.hit == true) return false;
        if(!playerBattleShip.equals(currTurn)) return false;
        return true;
    }
    private boolean makeAttack(Attack attack, PlayerBattleShip playerBattleShip){
        int ind = 0;
        if(playerBattleShip.color == 0){
            ind = 1;
        }
        else ind = 0;
        attacksPlayed.add(attack);
        //assume current player is red
        //update curr fleet info for blue and put a hit lable in bludBoard if hit successfully
        int x = attack.destination.x;
        int y = attack.destination.y;
        boolean hit = false;
            if(boardBattleShips[ind].squareBattleShips[x][y].shipLabel != 0){
                boardBattleShips[ind].squareBattleShips[x][y].hit = true;
                fleets[ind][boardBattleShips[ind].squareBattleShips[x][y].shipLabel - 1]--;
                if(fleets[ind][boardBattleShips[ind].squareBattleShips[x][y].shipLabel - 1] == 0){
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
    public SquareBattleShip[][]getBoard(PlayerBattleShip playerBattleShip){
            int ind = 0;
            if(playerBattleShip.color == 0){
                ind = 1;
            }
            else ind = 1;
            return boardBattleShips[ind].getBoard();
    }

    
       
    
    public static void main(){
        BattleShip Game = new BattleShip();
        //Assigned red to Human player
        PlayerBattleShip playerBattleShip = new PlayerBattleShip(0);
        //Assigned blue to cpu player;
        PlayerBattleShip cpu = new PlayerBattleShip(1);
        //1: Carrier 3 2: Submarine2 3: Destroyer 1
        int[] shipInfo = new int[]{2};
        Game.initialize(playerBattleShip, cpu, 10,10,shipInfo);
        
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
        boolean ans = Game.arrangeShips(playerBattleShip, redShip);
        
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
                res = Game.playerAttack(playerBattleShip,x,y);
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
        
    
    
    

