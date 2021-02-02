import animation.sprites.Collision;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Collision ex = new Collision();
            ex.setVisible(true);
        });
    }
}