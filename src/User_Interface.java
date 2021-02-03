import animation.sprites.Board;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class User_Interface extends JFrame {

    public User_Interface() {

        initUI();
    }
    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Collision");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
