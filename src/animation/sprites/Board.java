package animation.sprites;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Timer timer;
    private Graphical_Computer graphical_computer;
    private List<Graphical_Computer> theNetwork;
    private boolean isRunning;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = (int) screenSize.getWidth();
    private final int B_HEIGHT = (int) screenSize.getHeight();
    private final int DELAY = 15;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        isRunning = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        graphical_computer = new Graphical_Computer(ICRAFT_X, ICRAFT_Y);

        initNetwork();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initNetwork() {

        theNetwork = new ArrayList<>();

        for (int[] p : pos) {
            theNetwork.add(new Graphical_Computer(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isRunning) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (graphical_computer.isVisible()) {
            g.drawImage(graphical_computer.getImage(), graphical_computer.getX(), graphical_computer.getY(),
                    this);
        }

        List<Graphical_Package> ms = graphical_computer.getPackages();

        for (Graphical_Package aPackage : ms) {
            if (aPackage.isVisible()) {
                g.drawImage(aPackage.getImage(), aPackage.getX(),
                        aPackage.getY(), this);
            }
        }

        for (Graphical_Computer graphical_computer : theNetwork) {
            if (graphical_computer.isVisible()) {
                g.drawImage(graphical_computer.getImage(), graphical_computer.getX(), graphical_computer.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Computers left: " + theNetwork.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        running();

        updateNetwork();
        updatePackage();
        updateComputerUnits();

        checkCollisions();

        repaint();
    }

    private void running() {

        if (!isRunning) {
            timer.stop();
        }
    }

    private void updateNetwork() {

        if (graphical_computer.isVisible()) {

            graphical_computer.move();
        }
    }

    private void updatePackage() {

        List<Graphical_Package> ms = graphical_computer.getPackages();

        for (int i = 0; i < ms.size(); i++) {

            Graphical_Package m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateComputerUnits() {

        if (theNetwork.isEmpty()) {

            isRunning = false;
            return;
        }

        for (int i = 0; i < theNetwork.size(); i++) {

            Graphical_Computer a = theNetwork.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                theNetwork.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = graphical_computer.getBounds();

        for (Graphical_Computer alien : theNetwork) {

            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {

                graphical_computer.setVisible(false);
                alien.setVisible(false);
                isRunning = false;
            }
        }

        List<Graphical_Package> ms = graphical_computer.getPackages();

        for (Graphical_Package m : ms) {

            Rectangle r1 = m.getBounds();

            for (Graphical_Computer alien : theNetwork) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            graphical_computer.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            graphical_computer.keyPressed(e);
        }
    }
}