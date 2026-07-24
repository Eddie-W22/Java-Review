package Connect4.src;
import java.util.Scanner;

public class Connect4{
    public static void main(String[] args){
        Scanner input1 = new Scanner(System.in);
        System.out.println("What size would you like the board? Please enter as (height as int) by (width as int)");
        String boardDimension = input1.nextLine();
        String[] dimensions = boardDimension.split(" by ");
        Board b = new Board(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
        System.out.println(b);
        b.lineSeperator();
        System.out.println(b.getNumberofCols());
        System.out.println(b.getNumberofRows());
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        p1.playTurn(b);
        p2.playTurn(b);
        b.showBoard();
        

    }
}

class Board{
    private int[][] grid;
    private int width;
    private int height;

    public Board(int h, int w){
    grid = new int[h][w];
    width = w;
    height = h;

    for(int row = 0; row < height;row++){
        for(int col = 0; col < width;col++ ){
            grid[row][col] = 0;
        }
    }
    }

    public int getPlace(int r, int c){
        return this.grid[r][c];
    }

    public void setPlace(int r, int c, int p){
        this.grid[r][c] = p;
    }

    public String toString(){
        String s = "";
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                s += this.getPlace(r,c) + " ";
            }
            if(r != height - 1) s += "\n";
        }
        return s;
    }

        public void showBoard(){
        String s = "";
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                s += this.getPlace(r,c) + " ";
            }
            if(r != height - 1) s += "\n";
        }
        System.out.println(s);
        this.lineSeperator();
    }

    public void lineSeperator(){
        String l = "";
        for(int x = 0; x < this.width; x++){
            l = l + "==";
        }
        System.out.println(l);
    }

    public int getNumberofRows(){
        return grid.length;
    }

    public int getNumberofCols(){
        return grid[0].length;
    }

}

class Player{
    private int playerN;
    private Scanner playerChoice = new Scanner(System.in);
    private int pm;
    private int actualPM;

    public Player(int playerNumber){
        playerN = playerNumber;
    }

    public void playTurn(Board b){
        boolean done = false;
        
            do{
                b.showBoard();
                System.out.println("It is Player " + playerN + "'s turn.");
                System.out.println("Which col do you want to put it in?");
                actualPM  = playerChoice.nextInt();
                pm = actualPM - 1;
                if(actualPM > b.getNumberofCols() || actualPM < 1){
                    System.out.println("Not a proper value, will break game. Try again.");
                }else{
                    done = true;
                }
            }while(!done);
            //need to check against the actual number of col that exist
            if(placePiece(b, pm)){System.out.println("Col is full. Try another.")};

        
        

    }

    public boolean placePiece(Board b, int c){
        Boolean notDone = true;
        for(int r = b.getNumberofRows()-1; r > 0;r--){
            if(b.getPlace(r, c) == 0 && notDone){
                b.setPlace(r, c, playerN);
                notDone = !notDone;
            }
        }
        if(notDone){
            System.out.println("This colomn is already full. Place somewhere else.");
            return notDone;
            // then play the round over again
        }
        return notDone;
    }


}