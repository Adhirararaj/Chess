import javax.swing.JFrame;

public class main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setSize(800, 800);
        frame.add(new Board());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}