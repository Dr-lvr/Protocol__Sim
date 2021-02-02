import animation.fading.FadingJPanel;
import graphic.ControlBar;
import graphic.SpritesPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Demo");

        frame.getContentPane().add(new SpritesPanel()); //use sprites
        frame.getContentPane().add(new ControlBar());
        frame.setLayout(new GridLayout());

        frame.getContentPane().add(new FadingJPanel());
        System.out.println(
                frame.getContentPane().getComponent(2));

        FadingJPanel j = (FadingJPanel) frame.getContentPane().getComponent(2);

        frame.setSize(590, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*
        Image img1 = Toolkit.getDefaultToolkit().getImage("src/a_images/Package.png");
        frame.getContentPane().getComponent(0).getGraphics().drawImage(
                img1,
                300,
                300,
                100, 100, frame.getContentPane().getComponent(0));

 */
        frame.getContentPane().getComponent(0).setBackground(Color.cyan);
        frame.validate();
    }
}
