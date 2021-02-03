package animation.sprites;

import a_provider.Direction;

import java.awt.*;
import java.util.Random;

public class PackageUnit extends Sprite {

    private final int BOARD_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int PACKAGE_SPEED = 2;

    private int direction;//must be Enum or a kind of
    //this is a temporary solution, movement must be relative
    //implemented by vectors ex. pack.go(a, b)

    public PackageUnit(int x, int y) {
        super(x, y);
        initPackage();
    }
    public PackageUnit(int x, int y, int direction) {
        super(x, y);
        initPackage();
        this.direction = direction;
    }
    private void initPackage() {
        loadImage("src/a_images/Package.png");
        getImageDimensions();
    }
    public void move() {

        Random gen = new Random();
        switch(getDirection()){
            case 0:
                moveRightUp();
                break;
            case 1:
                moveRight();
                break;
            default:
                moveRightDown();
                break;
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
    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}