package Board;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import Pieces.Bishop;

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
                tile.setPiece(new Bishop(true));
            }
            updateBoard();
        }

        
    }
}
