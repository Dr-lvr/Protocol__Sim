package animation.sprites;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Graphical_Computer extends Sprite {

    private int dx;
    private int dy;
    private List<Graphical_Package> packages;

    public Graphical_Computer(int x, int y) {
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

    public List<Graphical_Package> getPackages() {
        return packages;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            sendPackage();
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

    public void sendPackage() {
        packages.add(new Graphical_Package(x + width, y + height / 2));
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