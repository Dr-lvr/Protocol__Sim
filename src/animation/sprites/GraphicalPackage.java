package animation.sprites;

public class GraphicalPackage extends Sprite {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 2;

    public GraphicalPackage(int x, int y) {
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
