package a_provider;

import animation.sprites.ComputerUnit;

import java.awt.*;
import java.util.*;

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

    public Vector<ComputerUnit> getTokenRing(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Vector<ComputerUnit> theNetwork = new Vector<ComputerUnit>();
        theNetwork.add(new ComputerUnit((int) screenSize.getWidth()/4, (int) screenSize.getHeight()/2));
        theNetwork.add(new ComputerUnit((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)));
        theNetwork.add(new ComputerUnit((int) (screenSize.getWidth()/4)*3, (int) screenSize.getHeight()/2));
        theNetwork.add(new ComputerUnit((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)*3));
        return theNetwork;
    }
}
