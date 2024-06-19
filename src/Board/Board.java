package Board;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import Pieces.*;


public class Board extends JPanel {
    private static final int BOARD_SIZE = 8;
    private Tile[][] board = new Tile[BOARD_SIZE][BOARD_SIZE];
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    public Board(){
        this.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeBoard();
    }

    public Tile getTile(int x, int y){
        if(x<0 || x>=BOARD_SIZE || y<0 || y>=BOARD_SIZE) return null;
        return this.board[x][y];
    }

    private void initializeBoard() {
        for(int i = 0; i<BOARD_SIZE; i++){
            for(int j = 0; j<BOARD_SIZE; j++){
                if(board[i][j] == null){
                    board[i][j] = new Tile(i, j);
                }
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                if(i == 1 && j == 2){
                    board[i][j].setPiece(new Bishop(true));
                    button.setIcon(board[i][j].getPiece().image);
                }
                if((i+j)%2 == 0){
                    button.setBackground(new Color(70, 130, 180));
                }
                else{
                    button.setBackground(new Color(245, 245, 220));
                }

                button.addActionListener(new TileButtonListener(board[i][j]));
                this.add(button);
                buttons[i][j] = button;
            }
        }
        board[0][0].setPiece(new Rook(false));
        board[0][1].setPiece(new Knight(false));
        board[0][2].setPiece(new Bishop(false));
        board[0][3].setPiece(new Queen(false));
        board[0][4].setPiece(new King(false));
        board[0][5].setPiece(new Bishop(false));
        board[0][6].setPiece(new Knight(false));
        board[0][7].setPiece(new Rook(false));

        board[7][0].setPiece(new Rook(true));
        board[7][1].setPiece(new Knight(true));
        board[7][2].setPiece(new Bishop(true));
        board[7][3].setPiece(new Queen(true));
        board[7][4].setPiece(new King(true));
        board[7][5].setPiece(new Bishop(true));
        board[7][6].setPiece(new Knight(true));
        board[7][7].setPiece(new Rook(true));

        for(int i = 0; i<BOARD_SIZE; i++){
            board[1][i].setPiece(new Pawn(false));
            board[6][i].setPiece(new Pawn(true));
        }

        updateBoard();
    }

    public JButton getButton(int x, int y){
        return buttons[x][y];
    }

    public void updateBoard(){
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                JButton button = getButton(i, j);
                if(board[i][j].getPiece() == null){
                    button.setIcon(null);
                }
                else{
                    button.setIcon(board[i][j].getPiece().image);
                }
            }
        }
    }

    private class TileButtonListener implements ActionListener{
        private Tile tile;

        TileButtonListener(Tile tile){
            this.tile = tile;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tile.getPiece() instanceof Bishop){
                tile.setPiece(null);
            }
            else{
                tile.setPiece(new King(false));
            }
            updateBoard();
        }

        
    }
}
