package logic;

import logic.Controller;

public class ComputerUnit {

    private Controller myController;

    private static int serial;
    private int mySerial;

    private String packageUnit;

    private String bufferUnit;
    private boolean isRequest;
    private boolean isWriting;
    private boolean isReading;

    public ComputerUnit(Controller singleton) {
        myController = singleton;
        myController.add(this);
        mySerial = serial;
        ++serial;
    }
    public void setRequest(boolean request) {
        isRequest = request;
    }
    public void setWriting(boolean writing) {
        isWriting = writing;
    }
    public boolean isRequest() {
        return isRequest;
    }
    public boolean isWriting() {
        return isWriting;
    }
}
