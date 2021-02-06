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

    private int globalPackageSent;
    //test
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
        devices = ConfigProvider.getProviderInstance().getTokenRing();
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
        for(Device device : devices) {
            if (device.isVisible()) {
                g.drawImage(device.getImage(), device.getX(), device.getY(),
                        this);
            }
            List<Package> packageOut = device.getPackageOut();
            for (Package aPackage : packageOut) {
                if (aPackage.isVisible()) {
                    g.drawImage(aPackage.getImage(), aPackage.getX(),
                            aPackage.getY(), this);
                }
            }
        }
        for (Device device : devices) {
            if (device.isVisible()) {
                g.drawImage(device.getImage(), device.getX(), device.getY(), this);
            }
        }
        g.setColor(Color.black);
        int sentPackage = devices.get(0).getSentPackage() + globalPackageSent;
        g.drawString("Packages sent:  " + sentPackage, 5, 30);
        g.drawString("Press tab to send Packages", 5, 45);
        g.drawString("Press key to mov your pc", 5, 60);
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
        try{
            updatePackage();
        } catch(NoSuchElementException a){
            a.printStackTrace();
        }
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
        for(Device device : devices) {
            if (device.isVisible()) {
                device.move();
            }
        }
    }
    private void updatePackage() {
        for(Device g_cp : devices) {
            List<Package> packageOut = g_cp.getPackageOut();
            for (int i = 0; i < packageOut.size(); i++) {
                Package aPackage = packageOut.get(i);
                if (aPackage.isVisible()) {
                    try{
                        aPackage.move();
                    } catch(NoSuchElementException e){
                        e.printStackTrace();
                    }
                } else {
                    try{
                        packageOut.remove(i);
                    } catch(NoSuchElementException e){
                        e.printStackTrace();
                    }
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
            Device device = devices.get(i);
            if (device.isVisible()) {
                device.move();
            } else {
                devices.remove(i);
            }
        }
    }
    public void checkCollisions() {
        //Sender -> Package -> Receiver -> LockCollision
        for(Device device : devices) {
            List<Package> packageOut = device.getPackageOut();
            for (Package aPackage : packageOut) {//<---------------
                Rectangle r1 = aPackage.getBounds();
                for (Device device1 : devices) {
                    for (WireLock wireLock : device1.getLocks()){
                        if(r1.intersects(wireLock.getCollider())){
                            //manage packages and send
                            //
                            //
                            aPackage.setVisible(false);
                            device1.sendPacket();
                            ++globalPackageSent;
                        }
                    }
                    Rectangle r2 = device1.getBounds();
                    if (r1.intersects(r2)) {
                        aPackage.setVisible(false);
                        device1.sendPacket();
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