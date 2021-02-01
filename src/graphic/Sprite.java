    package graphic;

    import logic.ComputerUnit;
    import logic.Controller;

    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Image;
    import java.awt.Toolkit;
    import java.util.Vector;

    import javax.swing.JComponent;
    import javax.swing.JFrame;

    public class Sprite extends JComponent {

        private static int serial;
        private Vector<ComputerUnit> network;
        private Controller ctrl_MainInstance;

        public Sprite(){
            ++serial;
            ctrl_MainInstance = new Controller();
            network = new Vector<ComputerUnit>();
        }

        public void buildNetwork(int computerNumber){
            for(int i=0; i<computerNumber; ++i) {
                network.add(new ComputerUnit(ctrl_MainInstance));
            }
        }

        public void paint(Graphics g) {

            buildNetwork(4);

            Graphics2D g2 = (Graphics2D) g;

            //load image in cache
            Image img1 = Toolkit.getDefaultToolkit().getImage("src/a_images/computer-icon.png");

            for(int i=0; i<network.size(); ++i) {

                switch (i) {
                    //create the network configuration
                    //draw incon
                    case 0:
                    g2.drawImage(img1, (this.getWidth() / 2) - 50, 10, 100, 100, this);
                    //set graphical wiring
                    g2.drawLine((this.getWidth() / 2) - 50, (this.getHeight() / 4) - 50, (this.getWidth() / 4) - 60, (this.getHeight() / 2) - 50);
                    break;
                    case 1:
                    g2.drawImage(img1, (this.getWidth() / 4) - 130, (this.getWidth() / 2) - 110, 100, 100, this);
                    break;
                    case 2:
                    g2.drawImage(img1, (this.getWidth() / 4) * 3, (this.getWidth() / 2) - 110, 100, 100, this);
                    break;
                    case 3:
                    g2.drawImage(img1, (this.getWidth() / 2) - 50, (this.getWidth() / 4) * 3 - 110, 100, 100, this);
                    break;
                    default:
                    break;

                }
            }

            g2.finalize();
        }
    }