package code.minirts.rts;

import code.maths.*;
import code.maths.geo.*;

public final class RtsDataBase {

    public static final String EMPTY_STRING = "";

    private final Rect soldierPattern;
    private final Rect screen;
    private final Rect multiple;

    public RtsDataBase() {
        soldierPattern = new Rect();
        soldierPattern.setHeight(new Rate(32));
        soldierPattern.setWidth(new Rate(32));
        screen = new Rect();
        screen.setHeight(new Rate(2048));
        screen.setWidth(new Rate(2048));
        multiple = new Rect();
        multiple.setHeight(new Rate(10));
        multiple.setWidth(new Rate(10));
    }

    public Rect getSoldierPattern() {
        return soldierPattern;
    }

    public Rect getScreen() {
        return screen;
    }

    public Rect getMultiple() {
        return multiple;
    }
}
