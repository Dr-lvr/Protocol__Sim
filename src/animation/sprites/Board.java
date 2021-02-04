package animation.sprites;


import a_provider.ConfigProvider;
import a_provider.WireLock;

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
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Timer timer;
    private Vector<Device> graphical_computer;
    private boolean isRunning;
    private final int B_WIDTH = (int) screenSize.getWidth()- 200;
    private final int B_HEIGHT = (int) screenSize.getHeight();
    private final int DELAY = 15;

    public Board() {
        initBoard();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        isRunning = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        graphical_computer = ConfigProvider.getProviderInstance().getTokenRing();
        timer = new Timer(DELAY, this);
        timer.start();
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
        // draw network
        for(Device g_cp : graphical_computer) {
            if (g_cp.isVisible()) {
                g.drawImage(g_cp.getImage(), g_cp.getX(), g_cp.getY(),
                        this);
            }
            List<Package> ms = g_cp.getPackageOut();
            for (Package aPackage : ms) {
                if (aPackage.isVisible()) {
                    g.drawImage(aPackage.getImage(), aPackage.getX(),
                            aPackage.getY(), this);
                }
            }
        }
        for (Device gp : graphical_computer) {
            if (gp.isVisible()) {
                g.drawImage(gp.getImage(), gp.getX(), gp.getY(), this);
                for (Map.Entry<WireLock, WireLock> entry : gp.getConnectionMap().entrySet()) {
                    //g.drawLine(entry.getKey().getX(), entry.getKey().getY(), entry.getValue().getX(), entry.getValue().getY());
                }
            }
        }
        g.setColor(Color.black);
        g.drawString("Packages sent:  " + graphical_computer.get(0).getSentPackage(), 5, 15);
        g.drawString("Press tab to send Packages", 5, 30);
        g.drawString("Press key to mov your pc", 5, 45);
    }
    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.BLACK);
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
        try {
            checkCollisions();
        } catch(ConcurrentModificationException cm){
            cm.printStackTrace();
        }
            repaint();
    }
    private void running() {
        if (!isRunning) {
            timer.stop();
        }
    }
    private void updateNetwork() {
        for(Device g_cp : graphical_computer) {
            if (g_cp.isVisible()) {
                g_cp.move();
            }
        }
    }
    private void updatePackage() {
        for(Device g_cp : graphical_computer) {
            List<Package> ms = g_cp.getPackageOut();
            for (int i = 0; i < ms.size(); i++) {
                Package m = ms.get(i);
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
            Device a = graphical_computer.get(i);
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
        for(Device g_cp : graphical_computer) {
            List<Package> ms = g_cp.getPackageOut();
            for (Package m : ms) {//<---------------
                Rectangle r1 = m.getBounds();
                for (Device computerUnit : graphical_computer) {
                    Rectangle r2 = computerUnit.getBounds();
                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        //computerUnit.setVisible(false);
                        //manage packets then send it
                        //
                        //
                        if(m.getDirection()<4){
                            computerUnit.fire(new WireLock(m.getDestination().getX()*-1, m.getDestination().getY()*-1));
                        } else {
                            computerUnit.fire(new WireLock(m.getDestination().getX()*-1, m.getDestination().getY()*-1));
                        }
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