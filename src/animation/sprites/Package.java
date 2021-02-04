package animation.sprites;

import java.awt.*;

public class Package extends Sprite {

    private final int BOARD_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int PACKAGE_SPEED = 2;

    private int direction;//must be Enum or a kind of
    //this is a temporary solution, movement must be relative
    //implemented by vectors ex. pack.go(a, b)
    public Package(int x, int y) {
        super(x, y);
        initPackage();
    }
    public Package(int x, int y, int direction) {
        super(x, y);
        initPackage();
        this.direction = direction;
    }
    private void initPackage() {
        loadImage("src/a_images/Package.png");
        getImageDimensions();
    }
    public void move() {
        switch(getDirection()){
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
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}