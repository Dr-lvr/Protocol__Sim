import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            User_Interface ex = new User_Interface();
            ex.setVisible(true);
        });
    }
}