package animation.sprites;


import a_provider.ConfigProvider;

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
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Timer timer;
    //private Graphical_Computer graphical_computer;
    //private List<Graphical_Computer> theNetwork;
    private Vector<ComputerUnit> graphical_computer;
    private boolean isRunning;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = (int) screenSize.getWidth();
    private final int B_HEIGHT = (int) screenSize.getHeight();
    private final int DELAY = 15;
/*
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
*/
    public Board() {
        initBoard();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        isRunning = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //graphical_computer = new Graphical_Computer(ICRAFT_X, ICRAFT_Y);
        graphical_computer = ConfigProvider.getProviderInstance().getTokenRing();
       // initNetwork();
        timer = new Timer(DELAY, this);
        timer.start();
    }
   // public void initNetwork() {

        //theNetwork = new ArrayList<>();
        //theNetwork = ConfigProvider.getControllerInstance().getTokenRing();
        /*
        for (int[] p : pos) {
            theNetwork.add(new Graphical_Computer(p[0], p[1]));
        }*/
    //}
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
        // draw network
        for(ComputerUnit g_cp : graphical_computer) {
            if (g_cp.isVisible()) {
                g.drawImage(g_cp.getImage(), g_cp.getX(), g_cp.getY(),
                        this);
            }
            List<PackageUnit> ms = g_cp.getPackageOut();
            for (PackageUnit aPackage : ms) {
                if (aPackage.isVisible()) {
                    g.drawImage(aPackage.getImage(), aPackage.getX(),
                            aPackage.getY(), this);
                }
            }
        }
        for (ComputerUnit graphical_computer : graphical_computer) {
            if (graphical_computer.isVisible()) {
                g.drawImage(graphical_computer.getImage(), graphical_computer.getX(), graphical_computer.getY(), this);
            }
        }
        g.setColor(Color.black);
        g.drawString("Computers left: " + graphical_computer.size(), 5, 15);
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
        for(ComputerUnit g_cp : graphical_computer) {
            if (g_cp.isVisible()) {
                g_cp.move();
            }
        }
    }
    private void updatePackage() {
        for(ComputerUnit g_cp : graphical_computer) {
            List<PackageUnit> ms = g_cp.getPackageOut();
            for (int i = 0; i < ms.size(); i++) {
                PackageUnit m = ms.get(i);
                if (m.isVisible()) {
                    m.move();
                } else {
                    ms.remove(i);
                }
            }
        }
    }
    private void updateComputerUnits() {
        if (graphical_computer.isEmpty()) {
            isRunning = false;
            return;
        }
        for (int i = 0; i < graphical_computer.size(); i++) {
            ComputerUnit a = graphical_computer.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                graphical_computer.remove(i);
            }
        }
    }

    public void checkCollisions() {
        /* collision between machines
        Rectangle r3 = graphical_computer.getBounds();

        for (Graphical_Computer computerUnit : theNetwork) {

            Rectangle r2 = computerUnit.getBounds();

            if (r3.intersects(r2)) {

                graphical_computer.setVisible(false);
                computerUnit.setVisible(false);
                isRunning = false;
            }
        }*/

        //Sender -> Package -> Receiver
        for(ComputerUnit g_cp : graphical_computer) {
            List<PackageUnit> ms = g_cp.getPackageOut();
            for (PackageUnit m : ms) {
                Rectangle r1 = m.getBounds();
                for (ComputerUnit computerUnit : graphical_computer) {
                    Rectangle r2 = computerUnit.getBounds();
                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        //computerUnit.setVisible(false);

                        //shunting-sorting-switching packets
                        //Random gen = new Random();
                        computerUnit.fire(m.getDirection());
                    }
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            graphical_computer.get(0).keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            graphical_computer.get(0).keyPressed(e);
        }
    }
}