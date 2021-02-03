package animation.sprites;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ConfigProvider {

    private static ConfigProvider theProvider;

    public static ConfigProvider getControllerInstance() {
        if (theProvider == null) {
            synchronized (ConfigProvider.class) {
                if (theProvider == null) {
                    theProvider = new ConfigProvider();
                }
            }
        }
        return theProvider;
    }

    public Vector<Graphical_Computer> getTokenRing(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Vector<Graphical_Computer> theNetwork = new Vector<Graphical_Computer>();
        theNetwork.add(new Graphical_Computer((int) screenSize.getWidth()/4, (int) screenSize.getHeight()/2));
        theNetwork.add(new Graphical_Computer((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)));
        theNetwork.add(new Graphical_Computer((int) (screenSize.getWidth()/4)*3, (int) screenSize.getHeight()/2));
        theNetwork.add(new Graphical_Computer((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)*3));
        return theNetwork;
    }
}
