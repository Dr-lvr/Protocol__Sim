package animation.sprites;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.*;

public abstract class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageSource) {

        //default images scaling
        ImageIcon imageIcon = new ImageIcon(imageSource);
        if(this instanceof Device){
            image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
        } else if (this instanceof Package){
            image = imageIcon.getImage().getScaledInstance(30, 18, Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
        }
    }
    public Image getImage() {
        return image;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}