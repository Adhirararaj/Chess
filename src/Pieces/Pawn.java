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
    boolean moved = false;
    @Override 
    public boolean isValidMove(Board board, Tile start, Tile end){
        if( end.getPiece()!= null && end.getPiece().isWhite()== this.isWhite()){
            return false;
        }
        int x = start.getX();
        int y = start.getY();
        int newX = end.getX();
        int newY = end.getY();

        if(!moved){
            return (newY - y == 2 || newY- y ==1);
        }
        else if(moved){
            return (newY - y == 1);
        }
        else if (y<=4 && end.getPiece().isWhite()!= this.isWhite()){
            if(newX-x == newY-y){
                return true;
            }
        }
        return false;
    }
}
