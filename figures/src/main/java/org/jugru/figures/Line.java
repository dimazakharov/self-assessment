package org.jugru.figures;

import org.apache.commons.lang3.StringUtils;

public class Line implements Figure {
    private final int length;
    private final boolean horizontal;

    public Line(int length) {
        this(length, true);
    }

    private Line(int length, boolean horizontal) {
        this.length = length;
        this.horizontal = horizontal;
    }

    @Override
    public String toString() {
        if (horizontal)
            return StringUtils.repeat("*", length);
        else {
            return StringUtils.repeat("*", "\n", length);
        }
    }

    @Override
    public Line rotate() {
        return new Line(length, !horizontal);
    }
}
