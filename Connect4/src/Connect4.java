package Connect4.src;
import java.util.Scanner;

public class Connect4{
    public static void main(String[] args){
        Scanner input1 = new Scanner(System.in);
        System.out.println("What size would you like the board? Please enter as (height as int) by (width as int)");
        String boardDimension = input1.nextLine();
        boardDimension = boardDimension.toLowerCase();
        String[] dimensions;
        //checks that " by " is written correctly
        while (true) {
            if(boardDimension.contains(" by ")){
                dimensions = boardDimension.split(" by ");
                break;
            }else{
                System.out.println("Ensure that \" by \" has been written out correctly. Try Again.");
                boardDimension = input1.nextLine();
        }
        }

        //checks if board is minimum size and other user syntax
        while(true){

            try{
                int n = Integer.parseInt(dimensions[0]);
            }catch (NumberFormatException e){
                System.out.println("Input must be: number by number. No spaces beforehand or extra letters");
                boardDimension = input1.nextLine();
                dimensions = boardDimension.split(" by ");
            }
            
            if(Integer.parseInt(dimensions[0]) < 4|| Integer.parseInt(dimensions[1]) < 4){
                System.out.println("Board must be a minimum of 4 by 4. Please try again.");
                boardDimension = input1.nextLine();
                dimensions = boardDimension.split(" by ");
            }else{
                break;
            }
        }
    

        Board b = new Board(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
        b.lineSeperator();
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Connect4.play(p1, p2, b);
    }
    
    public static void play(Player p1, Player p2, Board b){
        while(true){
            if(b.isFull()){
                System.out.println("Board is full. Its a tie!");
                break;
            }
            p1.playTurn(b);
            if(b.checkWin() != 0){
                System.out.println("Player 1 + has won!");
                break;
            }
            if(b.isFull()){
                System.out.println("Board is full. Its a tie!");
                break;
            }
            p2.playTurn(b);
            if(b.checkWin() != 0){
                System.out.println("Player 2 + has won!");
                break;
            }

        }
        b.lineSeperator();
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

    public int checkWin(){
        int value = 0;
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                if(value != 0){
                try{
                    //checking horizontal
                    if(grid[r][c] == grid[r][c+1] && grid[r][c+1] == grid[r][c+2] && grid[r][c+2] == grid[r][c+3]){
                        System.out.println("Horizontal");
                        value = grid[r][c];
                        return value;
                    }
                    // //checking vertical
                    if(grid[r][c] == grid[r+1][c] && grid[r+1][c] == grid[r+2][c] && grid[r+2][c] == grid[r+3][c]){
                        System.out.println("Vertical");
                        value = grid[r][c];
                        return value;
                    }
                    // //checking first diagonal(downwards to the right)
                    if(grid[r][c] == grid[r+1][c+1] && grid[r+1][c+1] == grid[r+2][c+2] && grid[r+2][c+2] == grid[r+3][c+3]){
                        System.out.println("Diagonal");
                        value = grid[r][c];
                        return value;
                    }
                    // //checking other diagonal(downwards to the left)
                    if(grid[r][c] == grid[r+1][c-1] && grid[r+1][c-1] == grid[r+2][c-2] && grid[r+2][c-2] == grid[r+3][c-3]){
                        System.out.println("Diagonal(2)");
                        value = grid[r][c];
                        return value;
                    }
                }catch(ArrayIndexOutOfBoundsException e){

                }
                }
            }
        }
        return value;
    }
    public boolean isFull(){
        boolean full = false;
        int count = 0;
        for(int r = height-1; r >= 0; r--){
            for(int c = width-1; c >= 0;c--){
                if(grid[r][c] == 0){
                    count++;
                }
            }
        }
        if(count == 0){
            full = true;
        }
        return full;
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
        boolean fDone = true;
        do{
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
            if(!placePiece(b, pm)){
                fDone = !fDone;
            }
        }while(fDone);

        
        

    }

    public boolean placePiece(Board b, int c){
        Boolean notDone = true;
        for(int r = b.getNumberofRows()-1; r >= 0;r--){
            if(b.getPlace(r, c) == 0 && notDone){
                b.setPlace(r, c, playerN);
                notDone = !notDone;
            }
        }
        if(notDone){
            System.out.println("This colomn is already full. Place somewhere else.");
            b.lineSeperator();
            return notDone;
        }
        return notDone;
    }


}