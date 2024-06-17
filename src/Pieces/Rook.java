package Pieces;

import Board.Board;
import Board.Tile;

public class Rook extends Piece {

    public Rook(boolean white){
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

        if(newX == x && newY == y) return false;
        if(newX != x && newY != y) return false;

        if(newX == x){
            if(newY>y){
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
        if(newY == y){
            if(newX>x){
                for(int i = x+1; i<newX; i++){
                    Piece rightNext = board.getTile(i, y).getPiece();
                    if(rightNext != null) return false;
                }
            }
            else if(newX<x){
                for(int i = x-1; i>newX; i--){
                    Piece rightNext = board.getTile(i, y).getPiece();
                    if(rightNext != null) return false;
                }
            }
        }

        return true;
    }
    
}
