package animation.sprites;

public class Graphical_Package extends Sprite {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;

    public Graphical_Package(int x, int y) {
        super(x, y);

        initPackage();
    }

    private void initPackage() {

        loadImage("src/a_images/Package.png");
        getImageDimensions();
    }

    public void move() {

        x += MISSILE_SPEED;

        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}
