import animation.sprites.PackageSender;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            PackageSender ex = new PackageSender();
            ex.setVisible(true);
        });
    }
}