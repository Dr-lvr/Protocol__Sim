package graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Sprite extends JComponent {

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Image img1 = Toolkit.getDefaultToolkit().getImage("src/a_images/computer-icon.png");
        g2.drawImage(img1, (this.getWidth()/2)-50, 10, 100, 100, this);
        g2.finalize();
    }
}