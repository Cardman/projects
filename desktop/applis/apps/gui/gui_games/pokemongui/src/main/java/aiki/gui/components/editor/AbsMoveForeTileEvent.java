package aiki.gui.components.editor;

import aiki.util.*;
import code.gui.events.*;

public abstract class AbsMoveForeTileEvent implements AbsActionListener {
    private final ContentComponentModelLevel level;
    protected AbsMoveForeTileEvent(ContentComponentModelLevel _g) {
        level = _g;
    }

    @Override
    public void action() {
        if (level.canContains()) {
            move();
        }
    }

    protected abstract void move();

    public static void moveNullablePoint(NullablePoint _n, Point _previous, Point _next) {
        if (Point.eq(_n,_previous)) {
            _n.setPoint(_next);
        }

    }
}
