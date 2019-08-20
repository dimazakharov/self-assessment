package org.jugru.figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiguresTest {

    @Test
    public void toStringTest() {
        Point point = new Point();
        Line line = new Line(6);
        Square square = new Square(5);
        Rectangle rectangle = new Rectangle(7, 3);

        String expectedPoint = "*";
        String expectedLine = "******";
        String expectedSquare = "*****\n" +
                "*****\n" +
                "*****\n" +
                "*****\n" +
                "*****";
        String expectedRectangle = "*******\n" +
                "*******\n" +
                "*******";


        assertAll(
                () -> assertEquals(expectedPoint, point.toString(), "point"),
                () -> assertEquals(expectedLine, line.toString(), "line"),
                () -> assertEquals(expectedSquare, square.toString(), "square"),
                () -> assertEquals(expectedRectangle, rectangle.toString(), "rectangle")
        );
    }

    @Test
    public void rotateTest() {
        Line line = new Line(3).rotate();
        Rectangle rectangle = new Rectangle(2, 4).rotate();


        String expectedLine = "*\n" +
                "*\n" +
                "*";
        String expectedRectangle = "****\n" +
                "****";

        assertAll(
                () -> assertEquals(expectedLine, line.toString(), "line"),
                () -> assertEquals(expectedRectangle, rectangle.toString(), "rectangle")

        );
    }

    @Test
    public void rectangleWithTest(){
        Rectangle rectangle = new Rectangle(1,1).withWidth(2).withLength(3);


        assertAll(
                () -> assertEquals(2, rectangle.getWidth(), "width"),
                () -> assertEquals(3, rectangle.getLength(), "length")

        );
    }
}
