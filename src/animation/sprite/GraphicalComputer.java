package animation.sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GraphicalComputer extends Sprite {

    private int dx;
    private int dy;
    private List<GraphicalPackage> packages;

    public GraphicalComputer(int x, int y) {
        super(x, y);

        initGraphicalComputer();
    }

    private void initGraphicalComputer() {

        packages = new ArrayList<>();

        loadImage("src/a_images/computer-icon.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public List<GraphicalPackage> getPackages() {
        return packages;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
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

    public void fire() {
        packages.add(new GraphicalPackage(x + width, y + height / 2));
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
}