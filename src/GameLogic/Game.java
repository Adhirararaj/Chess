package GameLogic;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Board.*;
import Pieces.*;
import Players.*;

public class Game {
    private Player[] players = new Player[2];
    private Board board;
    private Player currentTurn;
    private List<Move> movesPlayed;

    public Game(){
        movesPlayed = new ArrayList<Move>();
        players[0] = new HumanPlayer(true);
        players[1] = new HumanPlayer(false);
        board = new Board(this);
        currentTurn = players[0];
        JFrame frame = new JFrame("Chess");
        frame.setSize(800, 800);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.setVisible(true);
    }

    public boolean playerMove(Tile start, Tile end){ 
        Move move = new Move(currentTurn, start, end);
        // System.out.println("hi");
        boolean result = this.makeMove(move, currentTurn);
        System.out.println("hi");
        board.updateBoard();
        return result;  
    } 

    private boolean makeMove(Move move, Player player) {
        System.out.println("hi");
        Piece sourcePiece = move.getStart().getPiece(); 
        if (sourcePiece == null) { 
            return false; 
        }
        System.out.println("hi");
        if (player != currentTurn) { 
            return false; 
        }
        System.out.println("hi");
        if (sourcePiece.isWhite() != player.isWhiteSide()) { 
            return false; 
        }
        System.out.println("hi");
        if (!sourcePiece.isValidMove(board, move.getStart(), move.getEnd())){ 
            return false;
        }
        System.out.println("hi");
        Piece destPiece = move.getStart().getPiece(); 
        if (destPiece != null) { 
            destPiece.setAlive(false); 
            move.setPieceKilled(destPiece); 
        } 
        System.out.println("hi6");
        movesPlayed.add(move);
        System.out.println("hi6");
        move.getEnd().setPiece(move.getStart().getPiece());
        System.out.println("hi6");
        move.getStart().setPiece(null);

        // if (destPiece != null && destPiece instanceof King) { 
        //     if (player.isWhiteSide()) { 
        //         this.setStatus(GameStatus.WHITE_WIN); 
        //     } 
        //     else { 
        //         this.setStatus(GameStatus.BLACK_WIN); 
        //     } 
        // } 
        System.out.println("hi7");
        if (this.currentTurn == players[0]) { 
            this.currentTurn = players[1]; 
        } 
        else { 
            this.currentTurn = players[0]; 
        } 
        System.out.println("hi");
        return true; 
    }
}
