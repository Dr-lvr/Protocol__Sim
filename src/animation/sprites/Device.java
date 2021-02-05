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
        //packageIn = new ArrayDeque<>();
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
    public void setPackageIn(Package packageUnit) {
        //set first position
        packageIn.addFirst(packageUnit);
    }
    public int getSentPackage() {

        return sentPackage;
    }
    public void addConnection(int nLock, WireLock lock) {

        wireLocks.get(nLock).setLocked(true);
        this.connectionMap.put(wireLocks.get(nLock), lock);
    }
    /*
    public Package getPackageOut() throws NoSuchElementException{
        //get last position
        return packageIn.removeLast();
    }
    */
    public List<Package> getPackageOut() {

        return packageOut;
    }
    public Vector<WireLock> getLocks(){

        return wireLocks;
    }
    public WireLock getFirstLock() throws NoSuchElementException{
        for (int i=0; i<wireLocks.size(); ++i){
            if(wireLocks.get(i).isLocked()){
                return wireLocks.get(i);
            }
        }
        throw new NoSuchElementException();
    }
    public WireLock getSecondLock() throws NoSuchElementException{
        for (int i=wireLocks.size()-1; i>=0; --i){
            if(wireLocks.get(i).isLocked()){
                return wireLocks.get(i);
            }
        }
        throw new NoSuchElementException();
    }
    public WireLock getFirstConnection() throws NoSuchElementException{
        for (int i=0; i<wireLocks.size(); ++i){
            if(wireLocks.get(i).isLocked()){
                return connectionMap.get(wireLocks.get(i));
            }
        }
        throw new NoSuchElementException();
    }
    public WireLock getSecondConnection() throws NoSuchElementException{
        for (int i=wireLocks.size()-1; i>=0; --i){
            if(wireLocks.get(i).isLocked()){
                return connectionMap.get(wireLocks.get(i));
            }
        }
        throw new NoSuchElementException();
    }
    public Map<WireLock, WireLock> getConnectionMap() {

        return connectionMap;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //fire packets
        if (key == KeyEvent.VK_SPACE) {
            ++sentPackage;
            sendPacket();
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
    //package spawner
    public void sendPacket() {
        Random gen = new Random();
        if(gen.nextBoolean()){
            packageOut.add(new Package(getFirstLock(), getFirstConnection()));
        } else {
            packageOut.add(new Package(getSecondLock(), getSecondConnection()));
        }
    }
    public void initWireLock(){
        //UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
        Rectangle collider = this.getBounds();
        for(int i=0; i<8; ++i){
            switch(i){
                case 0:
                    wireLocks.add(new WireLock(collider.x - 30, collider.y - 20));
                    break;
                case 1:
                    wireLocks.add(new WireLock(collider.x + collider.width/2 - 15, collider.y - 20));
                    break;
                case 2:
                    wireLocks.add(new WireLock(collider.x + collider.width, collider.y - 20));
                    break;
                case 3:
                    wireLocks.add(new WireLock(collider.x + collider.width, collider.y + collider.height/2));
                    break;
                case 4:
                    wireLocks.add(new WireLock(collider.x + collider.width, collider.y + collider.height));
                    break;
                case 5:
                    wireLocks.add(new WireLock(collider.x + collider.width/2 - 15, collider.y + collider.height));
                    break;
                case 6:
                    wireLocks.add(new WireLock(collider.x -30, collider.y + collider.height));
                    break;
                case 7:
                    wireLocks.add(new WireLock(collider.x - 30, collider.y + collider.height/2));
                    break;
                default:
                    break;
            }
        }
    }
}