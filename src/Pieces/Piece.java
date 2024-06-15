package Pieces;
import Board.Board;
import Board.Tile;

public abstract class Piece{
    private boolean Alive = true;
    private boolean White = true;

    public Piece(boolean White){
        this.White = White;
    }

    public boolean isWhite(){
        return this.White;
    }

    public boolean isAlive(){
        return this.Alive;
    }

    public void setAlive(boolean Alive){
        this.Alive = Alive;
    }

    public abstract boolean isValidMove(Board board, Tile start, Tile end);
}