package ChessAI;

import java.util.ArrayList;
import Board.Board;
import Board.Tile;
import Pieces.*;
import Players.Player;
import GameLogic.Move;

public class Functions {
    public static int evaluateBoard(Board board) {
        int value = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTile(i, j);
                Piece piece = tile.getPiece();

                if (piece != null) {
                    int index = i * 8 + j;
                    boolean isWhite = piece.isWhite();

                    if (piece instanceof King) {
                        value += isWhite ? -ValueTables.KING_TABLE_WHITE[index] : ValueTables.KING_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Queen) {
                        value += isWhite ? -ValueTables.QUEEN_TABLE_WHITE[index] : ValueTables.QUEEN_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Rook) {
                        value += isWhite ? -ValueTables.ROOK_TABLE_WHITE[index] : ValueTables.ROOK_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Bishop) {
                        value += isWhite ? -ValueTables.BISHOP_TABLE_WHITE[index] : ValueTables.BISHOP_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Knight) {
                        value += isWhite ? -ValueTables.KNIGHT_TABLE_WHITE[index] : ValueTables.KNIGHT_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Pawn) {
                        value += isWhite ? -ValueTables.PAWN_TABLE_WHITE[index] : ValueTables.PAWN_TABLE_BLACK[index];
                    }

                    if (Pieces.Functions.isTileSafe(board, !isWhite, tile)) {
                        value += isWhite ? getPieceValue(piece) : -getPieceValue(piece);
                    }

                }
            }
        }

        return value;
    }

    private static int getPieceValue(Piece piece) {
        if (piece instanceof King) {
            return 200; // Arbitrary high value for king safety
        } else if (piece instanceof Queen) {
            return 90;
        } else if (piece instanceof Rook) {
            return 50;
        } else if (piece instanceof Bishop || piece instanceof Knight) {
            return 30;
        } else if (piece instanceof Pawn) {
            return 10;
        }
        return 0;
    }

    public static ArrayList<Move> getAllPossibleMoves(Board board, boolean isWhite){
        Player player = isWhite? board.game.players[0] : board.game.players[1];
        ArrayList<Move> allMoves = new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Tile startTile = board.getTile(i, j);
                Piece piece = startTile.getPiece();
                if(piece!= null && piece.getPlayer().equals(player)){
                    ArrayList<Move> piecemoves = getPossibleMoves(board,startTile);
                    allMoves.addAll(piecemoves);
                }
            }
        }
        return allMoves;
    }

    

    private static ArrayList<Move> getPossibleMoves(Board board , Tile startTile){
        
        Piece piece = startTile.getPiece();
        ArrayList <Move> moves = new ArrayList<>();
        int x = startTile.getX();
        int y= startTile.getY();
        Player player = piece.getPlayer();
        boolean moved = ((Pawn) piece).moved; 

        if(startTile.getPiece() instanceof Pawn){
            if(piece.isWhite()){
                if(x>0 && board.getTile(x-1, y).isTileEmpty()){
                    moves.add(new Move(player, startTile, board.getTile(x-1, y)));
                }
                if (!moved && x > 1 && board.getTile(x - 1, y).isTileEmpty() && board.getTile(x - 2, y).isTileEmpty()) {
                moves.add(new Move(player, startTile, board.getTile(x - 2, y)));
                }
                if (x > 0 && y > 0 && board.getTile(x - 1, y - 1).getPiece() != null &&
                        !board.getTile(x - 1, y - 1).getPiece().isWhite()) {
                    moves.add(new Move(player, startTile, board.getTile(x - 1, y - 1)));
                }
            }else{
                if (x < 7 && board.getTile(x+1, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x + 1, y)));
                }
                
                if (!moved && x < 6 && board.getTile(x + 1, y).isTileEmpty() && board.getTile(x + 2, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x + 2, y)));
                }
                
                if (x < 7 && y > 0 && board.getTile(x + 1, y - 1).getPiece() != null &&
                        board.getTile(x + 1, y - 1).getPiece().isWhite()) {
                    moves.add(new Move(player, startTile, board.getTile(x + 1, y - 1)));
                }
            }
        }

        if(startTile.getPiece() instanceof Rook){
            for (int i = x + 1; i < 8; i++) {
                if (board.getTile(i, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(i, y)));
                } else {
                    if (board.getTile(i, y).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(i, y)));
                    }
                    break;
                }
            }

            for (int i = x - 1; i >= 0; i--) {
                if (board.getTile(i, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(i, y)));
                } else {
                    if (board.getTile(i, y).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(i, y)));
                    }
                    break;
                }
            }
            
            for (int i = y + 1; i < 8; i++) {
                if (board.getTile(x, i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x, i)));
                } else {
                    if (board.getTile(x, i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x, i)));
                    }
                    break;
                }
            }
            
            for (int i = y - 1; i >= 0; i--) {
                if (board.getTile(x, i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x, i)));
                } else {
                    if (board.getTile(x, i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x, i)));
                    }
                    break;
                }
            }
        }

        if(startTile.getPiece() instanceof Bishop){
            for(int i =1 ; x + i< 8; i++){
                if(board.getTile(x+i,y+i).isTileEmpty()){
                    moves.add(new Move(player, startTile, board.getTile(x+i, y+i)));
                }else {
                    if (board.getTile(x + i, y + i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x + i, y + i)));
                    }
                    break;
                }
            }
            for (int i = 1; x - i >= 0 && y + i < 8; i++) {
                if (board.getTile(x - i, y + i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x - i, y + i)));
                } else {
                    if (board.getTile(x - i, y + i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x - i, y + i)));
                    }
                    break;
                }
            } 

            for (int i = 1; x + i < 8 && y - i >= 0; i++) {
                if (board.getTile(x + i, y - i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x + i, y - i)));
                } else {
                    if (board.getTile(x + i, y - i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x + i, y - i)));
                    }
                    break;
                }
            }

            for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
                if (board.getTile(x - i, y - i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x - i, y - i)));
                } else {
                    if (board.getTile(x - i, y - i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x - i, y - i)));
                    }
                    break;
                }
            }

        }

        if(startTile.getPiece() instanceof Queen){
            for (int i = x + 1; i < 8; i++) {
                if (board.getTile(i, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(i, y)));
                } else {
                    if (board.getTile(i, y).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(i, y)));
                    }
                    break;
                }
            }

            for (int i = x - 1; i >= 0; i--) {
                if (board.getTile(i, y).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(i, y)));
                } else {
                    if (board.getTile(i, y).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(i, y)));
                    }
                    break;
                }
            }
            
            for (int i = y + 1; i < 8; i++) {
                if (board.getTile(x, i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x, i)));
                } else {
                    if (board.getTile(x, i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x, i)));
                    }
                    break;
                }
            }
            
            for (int i = y - 1; i >= 0; i--) {
                if (board.getTile(x, i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x, i)));
                } else {
                    if (board.getTile(x, i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x, i)));
                    }
                    break;
                }
            }

            for(int i =1 ; x + i< 8; i++){
                if(board.getTile(x+i,y+i).isTileEmpty()){
                    moves.add(new Move(player, startTile, board.getTile(x+i, y+i)));
                }else {
                    if (board.getTile(x + i, y + i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x + i, y + i)));
                    }
                    break;
                }
            }
            for (int i = 1; x - i >= 0 && y + i < 8; i++) {
                if (board.getTile(x - i, y + i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x - i, y + i)));
                } else {
                    if (board.getTile(x - i, y + i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x - i, y + i)));
                    }
                    break;
                }
            } 

            for (int i = 1; x + i < 8 && y - i >= 0; i++) {
                if (board.getTile(x + i, y - i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x + i, y - i)));
                } else {
                    if (board.getTile(x + i, y - i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x + i, y - i)));
                    }
                    break;
                }
            }

            for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
                if (board.getTile(x - i, y - i).isTileEmpty()) {
                    moves.add(new Move(player, startTile, board.getTile(x - i, y - i)));
                } else {
                    if (board.getTile(x - i, y - i).getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, board.getTile(x - i, y - i)));
                    }
                    break;
                }
            }
        }

        if(startTile.getPiece() instanceof Knight){
            int[][] knightMoves = {
                {x + 2, y + 1}, {x + 2, y - 1}, {x - 2, y + 1}, {x - 2, y - 1},
                {x + 1, y + 2}, {x + 1, y - 2}, {x - 1, y + 2}, {x - 1, y - 2}
            };
        
            
            for (int[] move : knightMoves) {
                int newX = move[0];
                int newY = move[1];
                
                
                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    Tile destination = board.getTile(newX, newY);
                    
                    
                    if (destination.isTileEmpty() || destination.getPiece().getPlayer() != player) {
                        moves.add(new Move(player, startTile, destination));
                    }
                }
            }
        }

        if(startTile.getPiece() instanceof King){
            int [][] kingMoves = {
                {x+1 , y},{x-1,y},{x,y+1},{x,y-1},
                {x+1 , y+1},{x+1,y-1},{x-1,y+1},{x-1,y-1}
            };
            for(int[] move:kingMoves){
                int newX = move[0];
                int newY = move[1];

                if(newX>0 && newX<8 && newY>=0 && newY<8){
                    Tile destination = board.getTile(newX, newY);
                    if(destination.isTileEmpty()|| destination.getPiece().getPlayer()!=player){
                        moves.add(new Move(player,startTile,destination));
                    }
                }
            }
        }

        return moves;
    }

}
