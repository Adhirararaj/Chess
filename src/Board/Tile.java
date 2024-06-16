package Board;
import Pieces.Piece;

public class Tile {
    Piece piece = null;
    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    public Piece getPiece(){
        return this.piece;
    }
}
