package aiki.gui.components.editor;

import aiki.map.characters.*;
import aiki.util.*;

public final class PointPersonMapper implements AbsPersonMapper<Point> {
    @Override
    public Point map(Point _t) {
        return new Point(_t);
    }
}
