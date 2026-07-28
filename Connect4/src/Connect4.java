package Connect4.src;
import java.util.Scanner;

public class Connect4{
    public static void main(String[] args){
        Scanner input1 = new Scanner(System.in);
        System.out.println("What size would you like the board? Please enter as (height as int) by (width as int)");
        String boardDimension = input1.nextLine();
        boardDimension = boardDimension.toLowerCase();
        String[] dimensions = new String[2];
        //checks if board is minimum size and other user syntax
        while(true){
            if(boardDimension.contains(" by ")){
                dimensions = boardDimension.split(" by ");
            }else{
                System.out.println("Ensure that \" by \" has been written out correctly. Try Again.");
                boardDimension = input1.nextLine();
                boardDimension = boardDimension.toLowerCase();
                continue;
            }
            try{
                Integer.parseInt(dimensions[0]);
                Integer.parseInt(dimensions[1]);
            }catch (NumberFormatException e){
                System.out.println("Input must be: number by number. No spaces beforehand or extra letters");
                boardDimension = input1.nextLine();
                boardDimension = boardDimension.toLowerCase();
                continue;                    
            }
            if(Integer.parseInt(dimensions[0]) < 4|| Integer.parseInt(dimensions[1]) < 4){
                System.out.println("Board must be a minimum of 4 by 4. Please try again.");
                boardDimension = input1.nextLine();
                boardDimension = boardDimension.toLowerCase();  
                continue;                
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
        //first check if board is full, then plays the turn, and lastly checks to see if that was a winning move for each player's turn
        while(true){
            if(b.isFull()){
                System.out.println("Board is full. Its a tie!");
                break;
            }
            p1.playTurn(b);
            if(b.checkWin() != 0){
                System.out.println("Player 1 has won!");
                break;
            }
            if(b.isFull()){
                System.out.println("Board is full. Its a tie!");
                break;
            }
            p2.playTurn(b);
            if(b.checkWin() != 0){
                System.out.println("Player 2 has won!");
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
            grid[row][col] = Cell.EMPTY.value();
        }
    }
    }

    public int getCell(int r, int c){
        return this.grid[r][c];
    }

    public void setCell(int r, int c, int p){
        this.grid[r][c] = p;
    }

    public String toString(){
        String s = "";
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                s += this.getCell(r,c) + " ";
            }
            if(r != height - 1) s += "\n";
        }
        return s;
    }

    public void showBoard(){
        System.out.println(this.toString());
        this.lineSeperator();
    }
    //makes a line do seperate sections based on width of the board
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
    //iterates through each square checking a horizontal, vertical, or digonal win
    public int checkWin(){
        int cell = Cell.EMPTY.value();
        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                cell = grid[r][c];
                if(cell != Cell.EMPTY.value()){
                try{
                    //checking horizontal
                    if(grid[r][c] == grid[r][c+1] && grid[r][c+1] == grid[r][c+2] && grid[r][c+2] == grid[r][c+3]){
                        System.out.println("Horizontal");
                        return cell;
                    }
                    //checking vertical
                    if(grid[r][c] == grid[r+1][c] && grid[r+1][c] == grid[r+2][c] && grid[r+2][c] == grid[r+3][c]){
                        System.out.println("Vertical");
                        return cell;
                    }
                    //checking first diagonal(downwards to the right)
                    if(grid[r][c] == grid[r+1][c+1] && grid[r+1][c+1] == grid[r+2][c+2] && grid[r+2][c+2] == grid[r+3][c+3]){
                        System.out.println("Diagonal");
                        return cell;
                    }
                    //checking other diagonal(downwards to the left)
                    if(grid[r][c] == grid[r+1][c-1] && grid[r+1][c-1] == grid[r+2][c-2] && grid[r+2][c-2] == grid[r+3][c-3]){
                        System.out.println("Diagonal(2)");
                        return cell;
                    }
                
                }catch(ArrayIndexOutOfBoundsException e){

                }
                }
                cell = Cell.EMPTY.value();
            }
        }
        return cell;
    }
    //checks each empty square, and if there aren't any, the board is then full
    public boolean isFull(){
        boolean full = false;
        int count = 0;
        //checks board from bottom up to count empty spaces
        for(int r = height-1; r >= 0; r--){
            for(int c = width-1; c >= 0;c--){
                if(grid[r][c] == Cell.EMPTY.value()){
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
        //outer loop places piece when inner loop finishes
        while(true){
            //inner loop takes user input and checks if it is a real col
            while(true){
                b.showBoard();
                System.out.println("It is Player " + playerN + "'s turn.");
                System.out.println("Which col do you want to put it in?");
                actualPM  = playerChoice.nextInt();
                pm = actualPM - 1;
                if(actualPM > b.getNumberofCols() || actualPM < 1){
                    System.out.println("Not a proper col on the board. Try again.");
                }else{break;}
            }
            if(placePiece(b, pm))break;
        }

        
        

    }

    public boolean placePiece(Board b, int c){
        Boolean piecePlaced = false;
        //if there is space, put piece in the bottom of the col
        for(int r = b.getNumberofRows()-1; r >= 0;r--){
            if(b.getCell(r, c) == Cell.EMPTY.value()){
                b.setCell(r, c, playerN);
                piecePlaced = true;
                break;
            }
        }
        //if piece not placed, say the col is full
        if(!piecePlaced){
            System.out.println("This colomn is already full. Place somewhere else.");
            b.lineSeperator();
            return piecePlaced;
        }
        return piecePlaced;
    }


}