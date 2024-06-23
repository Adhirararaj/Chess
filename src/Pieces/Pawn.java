package Pieces;
import javax.swing.ImageIcon;
import java.awt.*;
import Board.Board;
import Board.Tile;


public class Pawn extends Piece {

    
    public Pawn (boolean white){
        super(white);
        int y = white? 0:this.ImageWidth;
        this.image = new ImageIcon(ImageSheet.getSubimage(5*ImageWidth, y, ImageWidth, ImageWidth).getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    }
    public boolean moved = false;

    private void setMoved(){
        moved = true;
    }
    @Override 
    public boolean isValidMove(Board board, Tile start, Tile end){
        if( end.getPiece()!= null && end.getPiece().isWhite()== this.isWhite()){
            return false;
        }
        int x = start.getX();
        int y = start.getY();
        int newX = end.getX();
        int newY = end.getY();

        if(this.isWhite()){
            if(!moved){
                setMoved();
                return (x - newX == 2 || x - newX ==1);
            }
            else if(moved){
                return (x - newX == 1);
            }

            if(end.getPiece() != null && end.getPiece().isWhite()!= this.isWhite()){
                return ((x - newX) == 1 && Math.abs(y - newY) == 1);
            }

        }else{
            if(!moved){
                setMoved();
                return (newX - x == 2 || newX - x ==1);
            }
            else if(moved){
                return (newX - x == 1);
            }

            if(end.getPiece() != null && end.getPiece().isWhite()!= this.isWhite()){
                return ((x - newX) == -1 && Math.abs(y - newY) == 1);
            }
        }
        return false;
    }
    
}
