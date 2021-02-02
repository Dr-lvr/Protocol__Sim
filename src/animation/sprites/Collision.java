package animation.sprites;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Collision extends JFrame {

    public Collision() {

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
