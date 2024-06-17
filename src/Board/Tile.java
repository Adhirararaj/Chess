package Board;
import Pieces.Piece;

public class Tile {
    private Piece piece = null;
    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
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
