package animation.sprites;

import a_provider.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class ComputerUnit extends Sprite {

    private int dx;
    private int dy;
   // private List<PackageUnit> packages;
    private Vector<PackageUnit> packageOut;
    private Deque<PackageUnit> packageIn;//double ended queue
    private Vector<WireLock> wireLocks;

    public ComputerUnit(int x, int y) {
        super(x, y);
        initCraft();
    }
    private void initCraft() {
        //packages = new ArrayList<>();
        packageOut = new Vector<>();
        packageIn = new ArrayDeque<>();
        wireLocks = new Vector<>(8);
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
    public void initWireLock(){
//UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
        Rectangle collider = this.getBounds();
        for(int i=0; i< wireLocks.size(); ++i){
            wireLocks.get(i).setLocked(false);
            switch(i){
                case 0:
                    wireLocks.get(i).setX(collider.x);
                    wireLocks.get(i).setY(collider.y);
                    wireLocks.get(i).setRelativePosition(Direction.UP_LEFT);
                    break;
                case 1:
                    wireLocks.get(i).setX(collider.x + collider.width/2);
                    wireLocks.get(i).setY(collider.y);
                    wireLocks.get(i).setRelativePosition(Direction.UP);
                    break;
                case 2:
                    wireLocks.get(i).setX(collider.x + collider.width);
                    wireLocks.get(i).setY(collider.y);
                    wireLocks.get(i).setRelativePosition(Direction.UP_RIGHT);
                    break;
                case 3:
                    wireLocks.get(i).setX(collider.x + collider.width);
                    wireLocks.get(i).setY(collider.y + collider.height/2);
                    wireLocks.get(i).setRelativePosition(Direction.RIGHT);
                    break;
                case 4:
                    wireLocks.get(i).setX(collider.x + collider.width);
                    wireLocks.get(i).setY(collider.y + collider.height);
                    wireLocks.get(i).setRelativePosition(Direction.DOWN_RIGHT);
                    break;
                case 5:
                    wireLocks.get(i).setX(collider.x + collider.width/2);
                    wireLocks.get(i).setY(collider.y + collider.height);
                    wireLocks.get(i).setRelativePosition(Direction.DOWN);
                    break;
                case 6:
                    wireLocks.get(i).setX(collider.x);
                    wireLocks.get(i).setY(collider.y + collider.height);
                    wireLocks.get(i).setRelativePosition(Direction.DOWN_LEFT);
                    break;
                case 7:
                    wireLocks.get(i).setX(collider.x);
                    wireLocks.get(i).setY(collider.y + collider.height/2);
                    wireLocks.get(i).setRelativePosition(Direction.LEFT);
                    break;
            }
        }
    }
    private class WireLock {

        private Direction relativePosition;
        private int x, y;
        private boolean locked;

        public WireLock(int x, int y, boolean locked) {
            this.x = x;
            this.y = y;
            this.locked = locked;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public boolean isLocked() {
            return locked;
        }
        public void setLocked(boolean locked) {
            this.locked = locked;
        }
        public void setRelativePosition(Direction relativePosition) {
            this.relativePosition = relativePosition;
        }
    }
}