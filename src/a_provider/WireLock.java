package a_provider;

import java.awt.*;

public class WireLock {

    private int x, y;
    private boolean locked;

    public WireLock(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Rectangle getCollider(){
        if(!this.isLocked()){
            return new Rectangle(x, y, 30, 18);
        }
        return new Rectangle(0,0,0,0);//
    }
    @Override
    public String toString(){
        return "WireLock{"+
                "x="+x+
                ", y="+y+
                ", locked="+locked+
                '}';
    }
}
