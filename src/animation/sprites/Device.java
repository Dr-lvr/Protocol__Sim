package animation.sprites;

import a_provider.WireLock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class Device extends Sprite {
    private int dx;
    private int dy;

    private Vector<Package> packageOut;
    private Deque<Package> packageIn;//double ended queue
    private Vector<WireLock> wireLocks;
    private Map<WireLock, WireLock> connectionMap;

    private int sentPackage;

    public Device(int x, int y) {
        super(x, y);
        initCraft();
    }
    private void initCraft() {
        //packages = new ArrayList<>();
        packageOut = new Vector<>();
        packageIn = new ArrayDeque<>();
        wireLocks = new Vector<>();
        connectionMap = new HashMap<>();
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
    //package spawner
    public void fire(WireLock destination) {
        //packages.add(new PackageUnit(x + width, y + height / 2));
        switch(new Package(0, 0, destination).getBehaviour()){
            case 0:
                packageOut.add(new Package(x - 30, y - 20, destination));
                break;
            case 1:
                packageOut.add(new Package(x + width/2 - 15, y - 20, destination));
                break;
            case 2:
                packageOut.add(new Package(x + width, y - 20, destination));
                break;
            case 3:
                packageOut.add(new Package(x + width, y + height/2, destination));
                break;
            case 4:
                packageOut.add(new Package(x + width, y + height, destination));
                break;
            case 5:
                packageOut.add(new Package(x + width/2 - 15, y + height, destination));
                break;
            case 6:
                packageOut.add(new Package(x -30, y + height, destination));
                break;
            case 7:
                packageOut.add(new Package(x - 30, y + height/2, destination));
                break;
            default:
                break;
        }
    }
    /*
    //package spawner
    public void fire(int direction) {
        //packages.add(new PackageUnit(x + width, y + height / 2));
        switch(direction){
            case 0:
                packageOut.add(new Package(x - 30, y - 20, direction));
                break;
            case 1:
                packageOut.add(new Package(x + width/2 - 15, y - 20, direction));
                break;
            case 2:
                packageOut.add(new Package(x + width, y - 20, direction));
                break;
            case 3:
                packageOut.add(new Package(x + width, y + height/2, direction));
                break;
            case 4:
                packageOut.add(new Package(x + width, y + height, direction));
                break;
            case 5:
                packageOut.add(new Package(x + width/2 - 15, y + height, direction));
                break;
            case 6:
                packageOut.add(new Package(x -30, y + height, direction));
                break;
            case 7:
                packageOut.add(new Package(x - 30, y + height/2, direction));
                break;
            default:
                break;
        }
    }
    */
    public List<Package> getPackageOut() {

        return packageOut;
    }
    public void setPackageIn(Package packageUnit) {
        //set first position
        packageIn.addFirst(packageUnit);
    }
    public Package getPackageIn() throws NoSuchElementException{
        //get last position
        return packageIn.removeLast();
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //fire packets
        Random gen = new Random();
        if (key == KeyEvent.VK_SPACE) {
            ++sentPackage;
            fire(new WireLock(gen.nextInt(100), gen.nextInt(100)));
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
    public int getSentPackage() {
        return sentPackage;
    }
    public Map<WireLock, WireLock> getConnectionMap() {
        return connectionMap;
    }
    public void addConnections(int nLock, WireLock lock) {
        this.connectionMap.put(wireLocks.get(nLock), lock);
    }
    public Vector<WireLock> getLocks(){
        return wireLocks;
    }
    public void initWireLock(){
        //UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
        Rectangle collider = this.getBounds();
        for(int i=0; i<8; ++i){
            switch(i){
                case 0:
                    wireLocks.add(new WireLock(collider.x, collider.y));
                    break;
                case 1:
                    wireLocks.add(new WireLock(collider.x+collider.width/2, collider.y));
                    break;
                case 2:
                    wireLocks.add(new WireLock(collider.x+collider.width, collider.y));
                    break;
                case 3:
                    wireLocks.add(new WireLock(collider.x+collider.width, collider.y+collider.height/2));
                    break;
                case 4:
                    wireLocks.add(new WireLock(collider.x+collider.width, collider.y+collider.height));
                    break;
                case 5:
                    wireLocks.add(new WireLock(collider.x+collider.width/2, collider.y+collider.height));
                    break;
                case 6:
                    wireLocks.add(new WireLock(collider.x, collider.y+collider.height));
                    break;
                case 7:
                    wireLocks.add(new WireLock(collider.x, collider.y+collider.height/2));
                    break;
                default:
                    break;
            }
        }
    }
}