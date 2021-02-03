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
        Vector<Device> theNetwork = new Vector<Device>();
        theNetwork.add(new Device((int) screenSize.getWidth()/4, (int) screenSize.getHeight()/2));
        theNetwork.add(new Device((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)));
        theNetwork.add(new Device((int) (screenSize.getWidth()/4)*3, (int) screenSize.getHeight()/2));
        theNetwork.add(new Device((int) screenSize.getWidth()/2, (int) (screenSize.getHeight()/4)*3));
        for(Device tn : theNetwork){
            tn.initWireLock();
        }
        theNetwork.get(0).addConnections(2, theNetwork.get(1).getLocks().get(6));
        theNetwork.get(1).addConnections(6, theNetwork.get(0).getLocks().get(2));
        theNetwork.get(1).addConnections(4, theNetwork.get(2).getLocks().get(0));
        theNetwork.get(2).addConnections(0, theNetwork.get(1).getLocks().get(4));
        theNetwork.get(2).addConnections(6, theNetwork.get(3).getLocks().get(2));
        theNetwork.get(3).addConnections(2, theNetwork.get(2).getLocks().get(6));
        theNetwork.get(3).addConnections(0, theNetwork.get(0).getLocks().get(4));
        theNetwork.get(0).addConnections(4, theNetwork.get(3).getLocks().get(0));
        return theNetwork;
    }
}
