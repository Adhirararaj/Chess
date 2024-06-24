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

    public void setMoved(boolean moved){
        this.moved = moved;
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
                setMoved(true);
                if((x - newX == 2 || x - newX ==1) && y == newY){
                    return true;
                }
            }
            else if(moved){
                if(x - newX == 1 && y == newY){
                    return true;
                }
            }

            if(end.getPiece() != null && end.getPiece().isWhite()!= this.isWhite()){
                if((x - newX) == 1 && Math.abs(y - newY) == 1){
                    return true;
                }
            }

        }
        else{
            if(!moved){
                setMoved(true);
                if((newX - x == 2 || newX - x ==1) && y == newY){
                    return true;
                }
            }
            else if(moved){
                if(newX - x == 1 && y == newY){
                    return true;
                }
            }

            if(end.getPiece() != null && end.getPiece().isWhite()!= this.isWhite()){
                if((x - newX) == -1 && Math.abs(y - newY) == 1){
                    return true;
                }
            }
        }
        return false;
    }
    
}
