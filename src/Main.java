import graphic.Sprite;
import graphic.Win;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Demo");

        //two graphic approach
        //frame.add(new Win()); // draw on canvas
        frame.getContentPane().add(new Sprite()); //use sprites

        frame.setSize(590, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
