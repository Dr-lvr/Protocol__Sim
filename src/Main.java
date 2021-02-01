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

        frame.setSize(590, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
