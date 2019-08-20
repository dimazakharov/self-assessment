package org.jugru.figures;

public class Rectangle extends AbstractQuadrangle implements Quadrangle {
    private final int width;
    private final int length;

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public Rectangle rotate() {
        return new Rectangle(length, width);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Rectangle withWidth(int width) {
        return new Rectangle(width, this.length);
    }

    public Rectangle withLength(int length) {
        return new Rectangle(this.width, length);
    }
}
