import animation.controlPanel.FadingJPanel;
import animation.sprites.Board;
import graphic.ControlPanel;

import java.awt.*;
import javax.swing.*;

public class User_Interface extends JFrame {

    public User_Interface() {

        initUI();
    }
    private void initUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-400, Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setLayout(new BorderLayout());
        setResizable(true);
        this.setVisible(true);
        add(new Board());

        JPanel container = new JPanel();

        ControlPanel fd = new ControlPanel();
        Board b = new Board();

        container.setLayout(new GridLayout(1, 2));
        container.add(new Board());
        container.add(fd);

        this.add(container);
        //pack();
    }
}
