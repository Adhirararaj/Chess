package Pieces;

import Board.Board;
import Board.Tile;

public class Bishop extends Piece{

    public Bishop(boolean white){
        super(white);
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
