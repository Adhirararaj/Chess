package Pieces;

import Board.*;

public class Queen extends Piece{

    public Queen(boolean white){
        super(white);
    }

    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) { 
			return false; 
		} 
        
		int x = start.getX();
		int y = start.getY();
        int newX = end.getX();
        int newY = end.getY();

        if(newX==x){
            if(newY==y){
                return false;
            }
            else if(newY>y){
                for(int i = y+1; i<newY; i++){
                    Piece rightNext = board.getTile(x, i).getPiece();
                    if(rightNext != null) return false;
                }
            }
            else if(newY<y){
                for(int i = y-1; i>newY; i--){
                    Piece rightNext = board.getTile(x, i).getPiece();
                    if(rightNext != null) return false;
                }
            }
        }
        else if(newX>x){
            if(newY==y){
                for(int i = x+1; i<newX; i++){
                    Piece rightNext = board.getTile(i, y).getPiece();
                    if(rightNext != null) return false;
                }
            }
            if(Math.abs(newX-x) != Math.abs(newY-y)) return false;
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
            if(newY==y){
                for(int i = x-1; i>newX; i--){
                    Piece rightNext = board.getTile(i, y).getPiece();
                    if(rightNext != null) return false;
                }
            }
            if(Math.abs(newX-x) != Math.abs(newY-y)) return false;
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