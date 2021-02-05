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
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private boolean isRunning;
    private final int DELAY = 15;
    private Vector<Device> devices;
    private final int B_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 200;
    private final int B_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Board() {
        initBoard();
    }
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        isRunning = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //inject configuration
        devices= ConfigProvider.getProviderInstance().getTokenRing();
        timer = new Timer(DELAY, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            drawObjects(g);
        } else {
            drawExit(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawObjects(Graphics g) {
        // draw network
        for(Device g_cp : devices) {
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
        for (Device gp : devices) {
            if (gp.isVisible()) {
                g.drawImage(gp.getImage(), gp.getX(), gp.getY(), this);
                for (Map.Entry<WireLock, WireLock> entry : gp.getConnectionMap().entrySet()) {
                    //g.drawLine(entry.getKey().getX(), entry.getKey().getY(), entry.getValue().getX(), entry.getValue().getY());
                }
            }
        }
        g.setColor(Color.black);
        g.drawString("Packages sent:  " + devices.get(0).getSentPackage(), 5, 15);
        g.drawString("Press tab to send Packages", 5, 30);
        g.drawString("Press key to mov your pc", 5, 45);
    }
    private void drawExit(Graphics g) {
        String msg = "Exit";
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
        for(Device g_cp : devices) {
            if (g_cp.isVisible()) {
                g_cp.move();
            }
        }
    }
    private void updatePackage() {
        for(Device g_cp : devices) {
            List<Package> ms = g_cp.getPackageOut();
            for (int i = 0; i < ms.size(); i++) {
                Package m = ms.get(i);
                if (m.isVisible()) {
                    m.move();
                } else {
                    ms.remove(i);
                    g_cp.getPackageOut().remove(i);
                }
            }
        }
    }
    private void updateComputerUnits() {
        if (devices.isEmpty()) {
            isRunning = false;
            return;
        }
        for (int i=0; i < devices.size(); i++) {
            Device a = devices.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                devices.remove(i);
            }
        }
    }
    public void checkCollisions() {
        //Sender -> Package -> Receiver
        for(Device g_cp : devices) {
            List<Package> ms = g_cp.getPackageOut();
            for (Package m : ms) {//<---------------
                Rectangle r1 = m.getBounds();
                for (Device computerUnit : devices) {
                    Rectangle r2 = computerUnit.getBounds();
                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        //computerUnit.setVisible(false);
                        //manage packets then send it
                        //
                        //
                        computerUnit.sendPacket();
                    }
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            devices.get(0).keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            devices.get(0).keyPressed(e);
        }
    }
}