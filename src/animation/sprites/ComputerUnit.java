package animation.sprites;

import java.awt.event.KeyEvent;
import java.util.*;

public class ComputerUnit extends Sprite {

    private int dx;
    private int dy;
   // private List<PackageUnit> packages;
    private Vector<PackageUnit> packageOut;
    private Deque<PackageUnit> packageIn;//double ended queue

    public ComputerUnit(int x, int y) {
        super(x, y);
        initCraft();
    }
    private void initCraft() {
        //packages = new ArrayList<>();
        packageOut = new Vector<>();
        packageIn = new ArrayDeque<>();
        loadImage("src/a_images/computer-icon.png");
        getImageDimensions();
    }
    public void move() {
        x += dx;
        y += dy;
        if (x < 1) {
            x = 1;
        }
        if (y < 1) {
            y = 1;
        }
    }
    public void fire(int direction) {

        //packages.add(new PackageUnit(x + width, y + height / 2));
        packageOut.add(new PackageUnit(x + width, y + height / 2, direction));
    }
    /*
    public void fire() {

        packages.add(new PackageUnit(x + width, y + height / 2));
    }
    */
    public List<PackageUnit> getPackageOut() {
        return packageOut;
    }
    public void setPackageIn(PackageUnit packageUnit) {
        //set first position
        packageIn.addFirst(packageUnit);
    }
    public PackageUnit getPackageIn() throws NoSuchElementException{
        //get last position
        return packageIn.removeLast();
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //fire packets
        Random gen = new Random();
        if (key == KeyEvent.VK_SPACE) {
            fire(gen.nextInt(3));
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}