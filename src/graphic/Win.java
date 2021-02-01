package graphic;

import logic.ComputerUnit;
import logic.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.util.EventListener;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

//manage graphics behaviours between controller intermediation

public class Win extends JPanel implements EventListener {

    private static int serial;
    private Vector<ComputerUnit> network;
    private Controller ctrl_MainInstance;

    public Win(){
        ++serial;
        ctrl_MainInstance = new Controller();
        network = new Vector<ComputerUnit>();
    }

    public void buildNetwork(int computerNumber){
        for(int i=0; i<computerNumber; ++i) {
            network.add(new ComputerUnit(ctrl_MainInstance));
        }
    }
    @Override
    public void paint(Graphics g) {

        buildNetwork(4);

        Graphics2D graphic2d;
        graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);

        //code the visualization scheme this is going to become a TOKEN::FUNCTION exchanger
        graphic2d.drawString("TokenRing Algorithm", 10, 20);
        for(int i=0; i< ctrl_MainInstance.getNetworkSize(); ++i){
            switch(i){
                case 0:
                    //Absolute coordinates are evil stuff, Bad practice - just for sketch purpose!!
                    graphic2d.drawRect((this.getWidth()/2)-50,(this.getHeight()/4)-100 , 80, 100);
                    graphic2d.drawLine((this.getWidth()/2)-50,(this.getHeight()/4)-50, (this.getWidth()/4)-60, (this.getHeight()/2)-50);
                    break;
                case 1:
                    graphic2d.drawRect((this.getWidth()/2)-50,((this.getHeight()/4)*3),80,100);
                    break;
                case 2:
                    graphic2d.drawRect((this.getWidth()/4)-100,(this.getHeight()/2)-50 , 80, 100);
                    break;
                case 3:
                    graphic2d.drawRect((this.getWidth()/4)*3,(this.getHeight()/2)-50,80,100);
                    break;
                default:
                    break;
            }
        }
    }
}