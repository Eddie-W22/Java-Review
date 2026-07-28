package Connect4.src;

public enum Cell{
    EMPTY(0), P1(1), P2(2);

    private final int value;
    private Cell(int n){
        this.value = n;
    }

    public int value(){
        return value;
    }

}
