package GameLogic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Board.*;
import Pieces.*;
import Players.*;
// import ChessAI.*;

public class Game {
    public Player[] players = new Player[2];
    private Board board;
    public boolean currentTurn;
    private List<Move> movesPlayed;
    public static int depth;
    public enum GameStatus{ 
        ACTIVE, 
        BLACK_WIN, 
        WHITE_WIN, 
        FORFEIT, 
        STALEMATE, 
        RESIGNATION 
    }
    private GameStatus gameStatus;

    public void setStatus(GameStatus status) {
        this.gameStatus = status;
    }

    public boolean isGameOver() {
        return this.gameStatus != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return this.gameStatus;
    }

    public Game(Player p1, Player p2){
        movesPlayed = new ArrayList<Move>();
        players[0] = p1;
        players[1] = p2;
        gameStatus = GameStatus.ACTIVE;
        board = new Board(this);
        currentTurn = true;
        
        JFrame frame = new JFrame("Chess");
        frame.setSize(800, 800);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(85, 60, 42));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(board, gbc);

        JButton undoButton = new JButton("Undo");
        undoButton.setFocusable(false);
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoMove();
                board.updateBoard();
            }
        });

        gbc.gridy = 1;
        frame.add(undoButton, gbc);

        frame.setVisible(true);
    }

    public boolean playerMove(Tile start, Tile end){
        if(!currentTurn && players[1] instanceof ComputerPlayer) return false;
        Player player = (currentTurn)? players[0] : players[1];
        Move move = new Move(player, start, end);
        boolean result = this.makeMove(move, currentTurn);
        board.updateBoard();
        
        return result;  
    } 

    public boolean makeMove(Move move, boolean isWhite){
        if(move == null) return false;
        Player player = isWhite? players[0] : players[1];
        Piece sourcePiece = move.getStart().getPiece(); 
        if (sourcePiece == null || player.isWhiteSide() != currentTurn || 
            sourcePiece.isWhite() != player.isWhiteSide() ||
            !sourcePiece.isValidMove(board, move.getStart(), move.getEnd())){
            return false;
        }
        
        Piece destPiece = move.getEnd().getPiece();
        if (destPiece != null) {
            destPiece.setAlive(false); 
            move.setPieceKilled(destPiece); 
        }
        
        move.getEnd().setPiece(sourcePiece);
        move.getStart().setPiece(null);

        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                Piece p = board.getTile(i, j).getPiece();
                if(p != null && p instanceof King && p.isWhite() == currentTurn){
                    if(Pieces.Functions.isTileSafe(board, !p.isWhite(), board.getTile(i, j))){
                        movesPlayed.add(move);
                    }
                    else{
                        move.getEnd().setPiece(destPiece);
                        move.getStart().setPiece(sourcePiece);
                        return false;
                    }
                }
            }
        }

        if(sourcePiece instanceof Pawn){
            if((sourcePiece.isWhite() && move.getEnd().getX() == 0) || (!sourcePiece.isWhite() && move.getEnd().getX() == 7)){
                Piece newPiece = null;
                if(players[1] instanceof ComputerPlayer && !currentTurn){
                    newPiece = new Queen(false);
                }
                else{
                    String[] options = { "Queen", "Rook", "Bishop", "Knight" };
                    int choice = JOptionPane.showOptionDialog(null,
                            "Select a piece to promote your pawn to:", "Pawn Promotion",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            options, options[0]);

                    switch (choice) {
                        case 0:
                            newPiece = new Queen(sourcePiece.isWhite());
                            break;
                        case 1:
                            newPiece = new Rook(sourcePiece.isWhite());
                            break;
                        case 2:
                            newPiece = new Bishop(sourcePiece.isWhite());
                            break;
                        case 3:
                            newPiece = new Knight(sourcePiece.isWhite());
                            break;
                        default:
                            newPiece = new Queen(sourcePiece.isWhite());
                            break;
                    }
                }
                
                move.getEnd().setPiece(newPiece);
            }
        }
        
        if (destPiece != null && destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }

            board.handleGameOver(player.isWhiteSide());
        }
        
        currentTurn = !currentTurn;
        System.out.println(ChessAI.Functions.evaluateBoard(board));
        
        return true; 
    }

    public void undoMove() {
        if(!movesPlayed.isEmpty()){
            Move move = movesPlayed.get(movesPlayed.size()-1);
            movesPlayed.remove(movesPlayed.size()-1);
            move.startTile.setPiece(move.pieceMoved);
            move.endTile.setPiece(move.pieceKilled);
            if(move.pieceMoved instanceof Pawn){
                Pawn pawn = (Pawn) move.pieceMoved;
                if(pawn.isWhite() && move.startTile.getX() == 6){
                    pawn.setMoved(false);
                }
                else if(!pawn.isWhite() && move.startTile.getX() == 1){
                    pawn.setMoved(false);
                }
            }
            currentTurn = !currentTurn;
        }
    }
}
