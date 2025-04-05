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
abstract public class Pawn {
    protected String name;
    protected char symbol;
    protected Move move;

    public Pawn(String name,char symbol) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){return move;}
}