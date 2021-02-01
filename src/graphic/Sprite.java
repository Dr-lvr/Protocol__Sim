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

        //load image in cache
        Image img1 = Toolkit.getDefaultToolkit().getImage("src/a_images/computer-icon.png");

        //create the network configuration
        //draw incon
        g2.drawImage(img1, (this.getWidth()/2)-50, 10, 100, 100, this);
        //set graphical wiring
        g2.drawLine((this.getWidth()/2)-50,(this.getHeight()/4)-50, (this.getWidth()/4)-60, (this.getHeight()/2)-50);

        g2.drawImage(img1, (this.getWidth()/4)-130, (this.getWidth()/2)-110, 100, 100, this);
        g2.drawImage(img1, (this.getWidth()/4)*3, (this.getWidth()/2)-110, 100, 100, this);
        g2.drawImage(img1, (this.getWidth()/2)-50, (this.getWidth()/4)*3-110, 100, 100, this);

        g2.finalize();
    }
}