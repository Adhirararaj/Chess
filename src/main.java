import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import GameLogic.Game;
import Players.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Select Game Mode");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel background = new BackgroundPanel("res/chess.png");
        frame.setContentPane(background);
        background.setLayout(null);

        JLabel titleLabel = new JLabel("CHESS");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 10, 800, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(titleLabel);

        JButton hvhButton = createStyledButton("Human vs Human", 300);
        JButton hvcButton = createStyledButton("Human vs Computer", 500);

        hvhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p1 = new HumanPlayer(true);
                Player p2 = new HumanPlayer(false);
                new Game(p1, p2);
                frame.dispose();
            }
        });

        hvcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                selectLevel();
            }
        });
        
        background.add(hvhButton);
        background.add(hvcButton);
        frame.setVisible(true);
    }

    private static void selectLevel(){
        JFrame frame = new JFrame("Select Game Mode");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel background = new BackgroundPanel("res/chess.png");
        frame.setContentPane(background);
        background.setLayout(null);

        JLabel titleLabel = new JLabel("CHESS");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 10, 800, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(titleLabel);

        JButton easy = createStyledButton("Easy", 150);
        JButton medium = createStyledButton("Medium", 350);
        JButton hard = createStyledButton("Hard", 550);

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p1 = new HumanPlayer(true);
                Player p2 = new ComputerPlayer(false);
                Game.depth = 3;
                new Game(p1, p2);
                frame.dispose();
            }
        });

        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p1 = new HumanPlayer(true);
                Player p2 = new ComputerPlayer(false);
                Game.depth = 5;
                new Game(p1, p2);
                frame.dispose();
            }
        });

        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player p1 = new HumanPlayer(true);
                Player p2 = new ComputerPlayer(false);
                Game.depth = 7;
                new Game(p1, p2);
                frame.dispose();
            }
        });

        background.add(easy);
        background.add(medium);
        background.add(hard);
        frame.setVisible(true);
    }
    
    private static JButton createStyledButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(150, yPosition, 500, 150);
        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(139, 69, 19)); // Dark brown color
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }
}