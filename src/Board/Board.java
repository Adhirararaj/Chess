package Board;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel {
    private static final int BOARD_SIZE = 8;
    private Tile[][] board = new Tile[BOARD_SIZE][BOARD_SIZE];
    public Board(){
        this.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeBoard();
    }

    private void initializeBoard() {
        for(int i = 0; i<BOARD_SIZE; i++){
            for(int j = 0; j<BOARD_SIZE; j++){
                if(board[i][j] == null){
                    board[i][j] = new Tile(i, j);
                }
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                if((i+j)%2 == 0){
                    button.setBackground(Color.white);
                }
                else{
                    button.setBackground(Color.darkGray);
                }

                button.addActionListener(new TileButtonListener(board[i][j]));
                this.add(button);
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
            System.out.println("Tile clicked: (" + tile.getX() + ", " + tile.getY() + ")");
        }

        
    }
}
