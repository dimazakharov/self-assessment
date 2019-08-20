package org.jugru.figures;

public class Square extends AbstractQuadrangle implements  Quadrangle {

    private final int size;

    public Square(int size) {
        this.size = size;
    }

    @Override
    public Square rotate() {
        return new Square(size);
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getLength() {
        return size;
    }
}
