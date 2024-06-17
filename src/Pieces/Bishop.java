package Pieces;

import Board.Board;
import Board.Tile;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bishop extends Piece{

    public Bishop(boolean white){
        super(white);
        int y = white? 0:this.ImageWidth;
        this.image = new ImageIcon(ImageSheet.getSubimage(3*ImageWidth, y, ImageWidth, ImageWidth).getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if(end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) { 
			return false; 
		} 
        
		int x = start.getX();
		int y = start.getY();
        int newX = end.getX();
        int newY = end.getY();

        if(Math.abs(newX-x) != Math.abs(newY-y)) return false;
        if(newX-x == 0) return false;

        if(newX>x){
            if(newY>y){
                for(int i = 1; i<newX-x; i++){
                    Piece rightNext = board.getTile(x+i, y+i).getPiece();
                    if(rightNext != null) return false;
                }
            }
            else if(newY<y){
                for(int i = 1; i<newX-x; i++){
                    Piece rightNext = board.getTile(x+i, y-i).getPiece();
                    if(rightNext != null) return false;
                }
            }
        }
        else{
            if(newY>y){
                for(int i = -1; i>newX-x; i--){
                    Piece rightNext = board.getTile(x+i, y-i).getPiece();
                    if(rightNext != null) return false;
                }
            }
            else if(newY<y){
                for(int i = -1; i>newX-x; i--){
                    Piece rightNext = board.getTile(x+i, y+i).getPiece();
                    if(rightNext != null) return false;
                }
            }
        }

        return true;
    }
    
}
