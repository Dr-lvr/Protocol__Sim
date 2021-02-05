package a_provider;

import animation.sprites.Device;

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
    public Vector<Device> getTokenRing(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Vector<Device> theNetwork = new Vector<>();
        theNetwork.add(new Device((int) screenSize.getWidth()/4 - 300, (int) screenSize.getHeight()/2 - 150));
        theNetwork.add(new Device((int) screenSize.getWidth()/2 - 300, (int) (screenSize.getHeight()/4) - 150));
        theNetwork.add(new Device((int) (screenSize.getWidth()/4)*3 - 300, (int) screenSize.getHeight()/2 - 150));
        theNetwork.add(new Device((int) screenSize.getWidth()/2 - 300, (int) (screenSize.getHeight()/4)*3 - 150));
        for(Device tn : theNetwork){
            tn.initWireLock();
        }
        //device, lock, device, lock
        theNetwork.get(0).addConnection(2, theNetwork.get(1).getLocks().get(6));
        theNetwork.get(0).addConnection(4, theNetwork.get(3).getLocks().get(0));

        theNetwork.get(1).addConnection(6, theNetwork.get(0).getLocks().get(2));
        theNetwork.get(1).addConnection(4, theNetwork.get(2).getLocks().get(0));

        theNetwork.get(2).addConnection(0, theNetwork.get(1).getLocks().get(4));
        theNetwork.get(2).addConnection(6, theNetwork.get(3).getLocks().get(2));

        theNetwork.get(3).addConnection(2, theNetwork.get(2).getLocks().get(6));
        theNetwork.get(3).addConnection(0, theNetwork.get(0).getLocks().get(4));

        return theNetwork;
    }
}
