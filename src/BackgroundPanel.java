import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            backgroundImage = icon.getImage();
        } else {
            System.out.println("Image not found at: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
