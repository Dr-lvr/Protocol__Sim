package logic__NotUsed;

public class ComputerLogic {

    private Controller myController;

    private static int serial;
    private int mySerial;

    public ComputerLogic(Controller singleton) {
        myController = singleton;
        myController.add(this);
        mySerial = serial;
        ++serial;
    }
}
