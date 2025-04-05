public class Move {
    public int row;
    public int col;

    public Move(int row, int col){
        this.row =  row;
        this.col = col;
    }

    @Override public String toString(){
        char colChar = (char) ('a' + col); // Convert column number to letter
        int rowNumber = row + 1;
        return "" + colChar + rowNumber;
    }
}
