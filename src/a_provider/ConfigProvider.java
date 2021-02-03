package a_provider;

import animation.sprites.ComputerUnit;

import java.awt.*;
import java.util.*;

public class ConfigProvider {

    private static ConfigProvider theProvider;

    public static ConfigProvider getProviderInstance() {
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

        for(int i=0; i<theNetwork.size(); ++i){
            theNetwork.get(i).initWireLock();
                switch(i) {
                    case 0:
                        theNetwork.get(i).getLocks().get(2).setLocked(true);
                        theNetwork.get(i).addConnections(theNetwork.get(1));
                        theNetwork.get(i).getLocks().get(4).setLocked(true);
                        theNetwork.get(i).addConnections(theNetwork.get(3));
                        break;
                    case 1:
                        theNetwork.get(i).getLocks().get(6).setLocked(true);
                        theNetwork.get(i).getLocks().get(4).setLocked(true);
                        break;
                    case 2:
                        theNetwork.get(i).getLocks().get(0).setLocked(true);
                        theNetwork.get(i).getLocks().get(6).setLocked(true);
                        break;
                    case 3:
                        theNetwork.get(i).getLocks().get(0).setLocked(true);
                        theNetwork.get(i).getLocks().get(2).setLocked(true);
                        break;
                    default:
                        break;

                }
        }
        return theNetwork;
    }
}
