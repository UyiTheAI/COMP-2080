/*
First Name: Sara
Last Name: Mosquera
Student ID: 101512887


First Name: Luna
Last Name: Ortega
Student ID: 101477171

First Name: Andre
Last Name: Alvares
Student ID: 101512582

First Name: Judene
Last Name: Brown
Student ID: 101503637

First Name: Omoruyi
Last Name: Oredia
Student ID: 101496942

*/
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
