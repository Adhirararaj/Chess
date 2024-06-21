package ChessAI;

import Board.Board;
import Board.Tile;
import Pieces.*;

public class Functions {
    public int evaluateBoard(Board board, boolean currentTurn) {
        int value = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = board.getTile(i, j);
                Piece piece = tile.getPiece();

                if (piece != null && piece.isWhite() == currentTurn) {
                    int index = i * 8 + j;

                    if (piece instanceof King) {
                        value += currentTurn ? ValueTables.KING_TABLE_WHITE[index] : ValueTables.KING_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Queen) {
                        value += currentTurn ? ValueTables.QUEEN_TABLE_WHITE[index] : ValueTables.QUEEN_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Rook) {
                        value += currentTurn ? ValueTables.ROOK_TABLE_WHITE[index] : ValueTables.ROOK_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Bishop) {
                        value += currentTurn ? ValueTables.BISHOP_TABLE_WHITE[index] : ValueTables.BISHOP_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Knight) {
                        value += currentTurn ? ValueTables.KNIGHT_TABLE_WHITE[index] : ValueTables.KNIGHT_TABLE_BLACK[index];
                    }
                    else if (piece instanceof Pawn) {
                        value += currentTurn ? ValueTables.PAWN_TABLE_WHITE[index] : ValueTables.PAWN_TABLE_BLACK[index];
                    }
                }
            }
        }

        return value;
    }
}
