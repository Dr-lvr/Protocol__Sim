package animation.sprites;

import a_provider.WireLock;

import java.awt.*;

public class Package extends Sprite {

    private WireLock source;
    private WireLock destination;
    private final int PACKAGE_SPEED = 2;
    private final int BOARD_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    public Package(WireLock source, WireLock destination) {
        //to avoid ambiguity the package is constructible only with start and destination locks
        super(source.getX(), source.getY());
        this.destination = destination;
        initPackage();
    }
    private void initPackage() {
        loadImage("src/a_images/Package.png");
        getImageDimensions();
    }
    public void move() {
        switch(getBehaviour()){
            case 0:
                moveLeftUp();
                break;
            case 1:
                moveUp();
                break;
            case 2:
                moveRightUp();
                break;
            case 3:
                moveRight();
                break;
            case 4:
                moveRightDown();
                break;
            case 5:
                moveDown();
                break;
            case 6:
                moveLeftDown();
                break;
            case 7:
                moveLeft();
                break;
            default:
                break;
        }
    }
    public void moveLeftUp() {
        x -= PACKAGE_SPEED;
        y -= PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveUp() {
        y -= PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveRightUp() {
        x += PACKAGE_SPEED;
        y -= PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveRight() {
        x += PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveRightDown() {
        x += PACKAGE_SPEED;
        y += PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveDown() {
        y += PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveLeftDown() {
        x -= PACKAGE_SPEED;
        y += PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public void moveLeft() {
        x -= PACKAGE_SPEED;
        if (x > BOARD_WIDTH){
            visible = false;
        }
    }
    public int getBehaviour(){
        //the package is at the bottom right of the destination
        if(x > destination.getX() && y > destination.getY()){
            return 0;
        }
        //the package is at the bottom orthogonal to the destination
        if(x == destination.getX() && y > destination.getY()){
            return 1;
        }
        //the package is at the bottom left of the destination
        if(x < destination.getX() && y > destination.getY()){
            return 2;
        }
        //the package on the left orthogonal of the destination
        if(x < destination.getX() && y == destination.getY()){
            return 3;
        }
        //the package is on the hi left of the destination
        if(x < destination.getX() && y < destination.getY()){
            return 4;
        }
        //the package is at the top, orthogonal to the destination
        if(x == destination.getX() && y < destination.getY()){
            return 5;
        }
        //the package is at the top right to the destination
        if(x > destination.getX() && y < destination.getY()){
            return 6;
        }
        //the package on the right orthogonal to the destination
        if(x > destination.getX() && y == destination.getY()){
            return 7;
        }
        return -1;
    }
}