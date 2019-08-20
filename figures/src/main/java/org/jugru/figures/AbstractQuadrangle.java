package org.jugru.figures;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractQuadrangle implements Quadrangle {
    @Override
    public String toString() {
        return StringUtils.repeat(new Line(getWidth()).toString(), "\n", getLength());
    }
}
