package animation.sprites;

import a_provider.ConnectionMap;
import a_provider.WireLock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class Device extends Sprite {
    private int dx;
    private int dy;

    private Vector<Package> packageOut;
    private Vector<WireLock> wireLocks;
    private ConnectionMap myMap;
    private int sentPackage;//counter

    public Device(int x, int y, String imageSource) {
        super(x, y);
        packageOut = new Vector<>();
        wireLocks = new Vector<>();
        myMap = new ConnectionMap();
        loadImage(imageSource);
        getImageDimensions();
    }
    /* interdict for the executable version DO NOT DELETE
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
     */
    public List<Package> getPackageOut() {

        return packageOut;
    }
    public Vector<WireLock> getLocks(){

        return wireLocks;
    }
    public int getSentPackage() {

        return sentPackage;
    }
    public void addConnection(int nLock, WireLock lock) {
        wireLocks.get(nLock).setLocked(true);
        this.myMap.setTheLock(wireLocks.get(nLock));
        this.myMap.addConnection(lock);
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
        //package out add package(source, destination)
        if(gen.nextBoolean()){
            packageOut.add(new Package(myMap.getTheLock(), myMap.getConnections().get(0)));
        } else {
            packageOut.add(new Package(myMap.getTheLock(), myMap.getConnections().get(1)));
            //andrea
        }
        //andrea
        if(gen.nextBoolean()){
            packageOut.get(packageOut.size()-1).setMessage(true);
        }else{
            packageOut.get(packageOut.size()-1).setMessage(false);
        }//
    }

    public boolean recivePacket(Package packet){
        if(packet.getMessage()) {
            return false;
        }else{
            return true;
        }
    }
    public void initWireLock(){
        //UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
        Rectangle collider = this.getBounds();
        for(int i=0; i<8; ++i){
            switch(i){
                case 0:
                    wireLocks.add(new WireLock(collider.x - 30, collider.y - 18));
                    break;
                case 1:
                    wireLocks.add(new WireLock(collider.x + collider.width/2 - 15, collider.y - 18));
                    break;
                case 2:
                    wireLocks.add(new WireLock(collider.x + collider.width, collider.y - 18));
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