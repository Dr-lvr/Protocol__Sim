import animation.sprite.SpriteMover;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SpriteMover ex = new SpriteMover();
            ex.setVisible(true);
        });
    }
}