package ChessAI;

import Board.Board;
import Board.Tile;
import Pieces.*;

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
}
