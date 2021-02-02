package animation.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int DELAY = 10;
    private Timer timer;
    private GraphicalComputer computerUnit;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setFocusable(true);

        int ICRAFT_X = 40;
        int ICRAFT_Y = 60;
        computerUnit = new GraphicalComputer(ICRAFT_X, ICRAFT_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(computerUnit.getImage(), computerUnit.getX(),
                computerUnit.getY(), this);

        List<GraphicalPackage> packages = computerUnit.getPackages();

        for (GraphicalPackage aPackage : packages) {

            g2d.drawImage(aPackage.getImage(), aPackage.getX(),
                    aPackage.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updatePackage();
        updateComputer();

        repaint();
    }

    private void updatePackage() {

        List<GraphicalPackage> packages = computerUnit.getPackages();

        for (int i = 0; i < packages.size(); i++) {

            GraphicalPackage aPackage = packages.get(i);

            if (aPackage.isVisible()) {

                aPackage.move();
            } else {

                packages.remove(i);
            }
        }
    }

    private void updateComputer() {

        computerUnit.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            computerUnit.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            computerUnit.keyPressed(e);
        }
    }
}