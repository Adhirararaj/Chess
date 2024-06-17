import javax.swing.JFrame;
import java.awt.*;

import Board.Board;

public class main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setSize(800, 800);
        frame.add(new Board());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.setVisible(true);
    }
}