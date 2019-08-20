package org.jugru.figures;

public class Point implements Figure {
    @Override
    public String toString() {
        return "*";
    }

    @Override
    public Point rotate() {
        return new Point();
    }
}
