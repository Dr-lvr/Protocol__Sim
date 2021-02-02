package animation.sprite;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class AnimatedFrame extends JFrame {

    public AnimatedFrame() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
