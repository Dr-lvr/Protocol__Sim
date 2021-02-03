package animation.sprites;

import java.awt.*;

public class Graphical_Package extends Sprite {

    private final int BOARD_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int PACKAGE_SPEED = 2;

    public Graphical_Package(int x, int y) {
        super(x, y);

        initPackage();
    }

    private void initPackage() {

        loadImage("src/a_images/Package.png");
        getImageDimensions();
    }

    public void move() {

        x += PACKAGE_SPEED;

        if (x > BOARD_WIDTH)
            visible = false;
    }
}