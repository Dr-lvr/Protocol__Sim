import graphic.Sprite;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Demo");

        frame.getContentPane().add(new Sprite()); //use sprites

        frame.setSize(590, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
