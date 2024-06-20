package GameLogic;

import Board.Tile;
import Pieces.Piece;
import Players.Player;

public class Move {
    protected Player player;
    protected Tile startTile;
    protected Tile endTile;
    protected Piece pieceMoved;
    protected Piece pieceKilled;

    public Move(Player player, Tile startTile, Tile endTile) {
        this.player = player;
        this.startTile = startTile;
        this.endTile = endTile;
        this.pieceMoved = startTile.getPiece();
        this.pieceKilled = null;
    }

    public Tile getStart(){
        return this.startTile;
    }

    public Tile getEnd(){
        return this.endTile;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Piece getPieceMoved(){
        return this.pieceMoved;
    }

    public Piece getPieceKilled(){
        return this.pieceKilled;
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
