package animation.sprites;

import javax.swing.JFrame;

public class PackageSender extends JFrame {

    public PackageSender() {

        initUI();
    }
    private void initUI() {

        add(new Board());

        setSize(400, 300);
        setResizable(true);

        setTitle("Package Sender");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
