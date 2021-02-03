package a_provider;

public class WireLock {

    private Direction relativePosition;
    private int x, y;
    private boolean locked;

    public WireLock(int x, int y, Direction relativePosition) {
        this.x = x;
        this.y = y;
        this.relativePosition = relativePosition;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void setRelativePosition(Direction relativePosition) {
        this.relativePosition = relativePosition;
    }
}
