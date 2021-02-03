    package animation.sprites;

    import a_provider.Direction;
    import a_provider.WireLock;

    import java.awt.*;
    import java.awt.event.KeyEvent;
    import java.util.*;
    import java.util.List;

        public class ComputerUnit extends Sprite {
            private int dx;
            private int dy;
           // private List<PackageUnit> packages;
            private Vector<PackageUnit> packageOut;
            private Deque<PackageUnit> packageIn;//double ended queue
            private Vector<WireLock> wireLocks;

            //private Vector<ComputerUnit> connectionMap;//
            private Map<WireLock, WireLock> connectionMap;

            public ComputerUnit(int x, int y) {
                super(x, y);
                initCraft();
            }
            private synchronized void initCraft() {
                //packages = new ArrayList<>();
                packageOut = new Vector<>();
                packageIn = new ArrayDeque<>();
                wireLocks = new Vector<>();
                connectionMap = new HashMap<>();
                loadImage("src/a_images/computer-icon.png");
                getImageDimensions();
            }
            public synchronized void move() {
                x += dx;
                y += dy;
                if (x < 1) {
                    x = 1;
                }
                if (y < 1) {
                    y = 1;
                }
            }
            //package spawner
            public synchronized void fire(int direction) {
                //packages.add(new PackageUnit(x + width, y + height / 2));
                switch(direction){
                    //30x18
                    case 0:
                        packageOut.add(new PackageUnit(x - 30, y - 20, direction));
                    break;
                    case 1:
                        packageOut.add(new PackageUnit(x + width/2 - 15, y - 20, direction));
                        break;
                    case 2:
                        packageOut.add(new PackageUnit(x + width, y - 20, direction));
                        break;
                    case 3:
                        packageOut.add(new PackageUnit(x + width, y + height/2, direction));
                        break;
                    case 4:
                        packageOut.add(new PackageUnit(x + width, y + height, direction));
                        break;
                    case 5:
                        packageOut.add(new PackageUnit(x + width/2 - 15, y + height, direction));
                        break;
                    case 6:
                        packageOut.add(new PackageUnit(x -30, y + height, direction));
                        break;
                    case 7:
                        packageOut.add(new PackageUnit(x - 30, y + height/2, direction));
                        break;
                    default:
                        //packageOut.add(new PackageUnit(x + width, y + height / 2, direction));
                        break;
                }
            }
            /*
            public void fire() {

                packages.add(new PackageUnit(x + width, y + height / 2));
            }
            */
            public synchronized List<PackageUnit> getPackageOut() {
                return packageOut;
            }
            public synchronized void setPackageIn(PackageUnit packageUnit) {
                //set first position
                packageIn.addFirst(packageUnit);
            }
            public synchronized PackageUnit getPackageIn() throws NoSuchElementException{
                //get last position
                return packageIn.removeLast();
            }
            public synchronized void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                //fire packets
                Random gen = new Random();
                if (key == KeyEvent.VK_SPACE) {
                    fire(gen.nextInt(8));
                }
                if (key == KeyEvent.VK_LEFT) {
                    dx = -1;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    dx = 1;
                }
                if (key == KeyEvent.VK_UP) {
                    dy = -1;
                }
                if (key == KeyEvent.VK_DOWN) {
                    dy = 1;
                }
            }
            public synchronized void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    dx = 0;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    dx = 0;
                }
                if (key == KeyEvent.VK_UP) {
                    dy = 0;
                }
                if (key == KeyEvent.VK_DOWN) {
                    dy = 0;
                }
            }
            public synchronized Map<WireLock, WireLock> getConnectionMap() {
                return connectionMap;
            }
            public synchronized void addConnections(int nLock, WireLock lock) {

                this.connectionMap.put(wireLocks.get(nLock), lock);
            }
            public synchronized Vector<WireLock> getLocks(){
                return wireLocks;
            }
            public synchronized void initWireLock(){
        //UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT
                Rectangle collider = this.getBounds();
                for(int i=0; i<8; ++i){
                    switch(i){
                        case 0:
                            wireLocks.add(
                                    new WireLock(collider.x, collider.y, Direction.UP_LEFT)
                            );
                            break;
                        case 1:
                                wireLocks.add(
                                        new WireLock(collider.x + collider.width/2, collider.y, Direction.UP)
                                );
                            break;
                        case 2:
                            wireLocks.add(
                                    new WireLock(collider.x + collider.width, collider.y, Direction.UP_RIGHT)
                            );
                            break;
                        case 3:
                            wireLocks.add(
                                    new WireLock(collider.x + collider.width, collider.y + collider.height/2, Direction.RIGHT)
                            );
                            break;
                        case 4:
                            wireLocks.add(
                                    new WireLock(collider.x + collider.width, collider.y + collider.height, Direction.DOWN_RIGHT)
                            );
                            break;
                        case 5:
                            wireLocks.add(
                                    new WireLock(collider.x + collider.width/2, collider.y + collider.height, Direction.DOWN)
                            );
                            break;
                        case 6:
                            wireLocks.add(
                                    new WireLock(collider.x, collider.y + collider.height, Direction.DOWN_LEFT)
                            );
                            break;
                        case 7:
                            wireLocks.add(
                                    new WireLock(collider.x, collider.y + collider.height/2, Direction.LEFT)
                            );
                            break;
                        default:
                            break;
                    }
                }
            }
        }