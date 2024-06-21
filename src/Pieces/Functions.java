package Pieces;

import Board.Board;
import Board.Tile;

public class Functions {
    public static boolean isTileSafe(Board board, boolean opponentColor, Tile tile) {
        if (isUnderPawnAttack(board, opponentColor, tile)) {
            return false;
        }

        if (isUnderKnightAttack(board, opponentColor, tile)) {
            return false;
        }

        if (isUnderDiagonalAttack(board, opponentColor, tile)) {
            return false;
        }

        if (isUnderStraightAttack(board, opponentColor, tile)) {
            return false;
        }

        if (isUnderKingAttack(board, opponentColor, tile)) {
            return false;
        }

        return true;
    }

    private static boolean isUnderPawnAttack(Board board, boolean opponentColor, Tile tile){
        int x = tile.getX();
        int y = tile.getY();

        if(opponentColor){
            if(x-1>=0 && y+1<8){
                Piece rightNext = board.getTile(x-1, y+1).getPiece();
                if(rightNext != null && rightNext.isWhite() && rightNext instanceof Pawn){
                    return true;
                }
            }
            if(x-1>=0 && y-1>=0){
                Piece leftNext = board.getTile(x-1, y-1).getPiece();
                if(leftNext != null && leftNext.isWhite() && leftNext instanceof Pawn){
                    return true;
                }
            }
        }
        else{
            if(x+1<8 && y+1<8){
                Piece rightNext = board.getTile(x+1, y+1).getPiece();
                if(rightNext != null && !rightNext.isWhite() && rightNext instanceof Pawn){
                    return true;
                }
            }
            if(x+1<8 && y-1>=0){
                Piece leftNext = board.getTile(x+1, y-1).getPiece();
                if(leftNext != null && !leftNext.isWhite() && leftNext instanceof Pawn){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isUnderKnightAttack(Board board, boolean opponentColor, Tile tile){
        int x = tile.getX();
        int y = tile.getY();
        int[] dir1 = {2, -2};
        int[] dir2 = {1, -1};

        for(int i: dir1){
            for(int j: dir2){
                int newX = x+i;
                int newY = y+j;
                if(newX>=0 && newX<8 && newY>=0 && newY<8){
                    Piece p = board.getTile(newX, newY).getPiece();
                    if(p!= null && p.isWhite() == opponentColor && p instanceof Knight){
                        return true;
                    }
                }
            }
        }
        for(int i: dir2){
            for(int j: dir1){
                int newX = x+i;
                int newY = y+j;
                if(newX>=0 && newX<8 && newY>=0 && newY<8){
                    Piece p = board.getTile(newX, newY).getPiece();
                    if(p!= null && p.isWhite() == opponentColor && p instanceof Knight){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isUnderDiagonalAttack(Board board, boolean opponentColor, Tile tile){
        int x = tile.getX();
        int y = tile.getY();

        int newX = x+1, newY = y+1;
        while(newX<8 && newY<8){
            Piece p = board.getTile(newX, newY).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Bishop)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Bishop)){
                return true;
            }
            newX++;
            newY++;
        }
        newX = x+1;
        newY = y-1;
        while(newX<8 && newY>=0){
            Piece p = board.getTile(newX, newY).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Bishop)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Bishop)){
                return true;
            }
            newX++;
            newY--;
        }
        newX = x-1;
        newY = y-1;
        while(newX>=0 && newY>=0){
            Piece p = board.getTile(newX, newY).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Bishop)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Bishop)){
                return true;
            }
            newX--;
            newY--;
        }
        newX = x-1;
        newY = y+1;
        while(newX>=0 && newY<8){
            Piece p = board.getTile(newX, newY).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Bishop)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Bishop)){
                return true;
            }
            newX--;
            newY++;
        }

        return false;
    }

    private static boolean isUnderStraightAttack(Board board, boolean opponentColor, Tile tile){
        int x = tile.getX();
        int y = tile.getY();

        for(int i = x+1; i<8; i++){
            Piece p = board.getTile(i, y).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Rook)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Rook)){
                return true;
            }
        }
        for(int i = x-1; i>=0; i--){
            Piece p = board.getTile(i, y).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Rook)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Rook)){
                return true;
            }
        }
        for(int i = y+1; i<8; i++){
            Piece p = board.getTile(x, i).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Rook)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Rook)){
                return true;
            }
        }
        for(int i = y-1; i>=0; i--){
            Piece p = board.getTile(x, i).getPiece();
            if(p != null && p.isWhite() != opponentColor){
                break;
            }
            else if(p != null && !(p instanceof Queen || p instanceof Rook)){
                break;
            }
            else if(p != null && (p instanceof Queen || p instanceof Rook)){
                return true;
            }
        }

        return false;
    }

    private static boolean isUnderKingAttack(Board board, boolean opponentColor, Tile tile){
        int x = tile.getX();
        int y = tile.getY();
        int[] dir = {0, -1, +1};

        for(int i: dir){
            for(int j: dir){
                if(i+j == 0) continue;
                int newX = x+i, newY = y+j;
                if(newX>=0 && newX<8 && newY>=0 && newY<8){
                    Piece p = board.getTile(newX, newY).getPiece();
                    if(p != null && p.isWhite() == opponentColor && p instanceof King){
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
