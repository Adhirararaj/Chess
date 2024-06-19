package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;
import Board.Board;
import Board.Tile;


public class Knight extends Piece {

    public Knight(boolean white){
        super(white);
        int y = white? 0:this.ImageWidth;
        this.image = new ImageIcon(ImageSheet.getSubimage(3*ImageWidth, y, ImageWidth, ImageWidth).getScaledInstance(80, 80, Image.SCALE_SMOOTH));
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end){
        if(end.getPiece()!= null && end.getPiece().isWhite() == this.isWhite())
            return false;
        
        
        int x = Math.abs(start.getX() - end.getX()); 
        int y = Math.abs(start.getY() - end.getY()); 
        return x * y == 2; 

    }

    
}
