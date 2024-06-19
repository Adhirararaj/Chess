package GameLogic;

import Board.Tile;
import Pieces.Piece;
import Players.Player;

public class Move {
    private final Player player;
    private final Tile startTile;
    private final Tile endTile;
    private final Piece pieceMoved;
    private final Piece pieceKilled;

    public Move(Player player, Tile startTile, Tile endTile) {
        this.player = player;
        this.startTile = startTile;
        this.endTile = endTile;
        this.pieceMoved = startTile.getPiece();
        this.pieceKilled = null;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", startTile=" + startTile +
                ", endTile=" + endTile +
                ", pieceMoved=" + pieceMoved +
                ", pieceKilled=" + pieceKilled +
                '}';
    }
}
