package code.minirts.rts;

import code.maths.*;
import code.maths.geo.*;

public final class RtsDataBase {

    public static final String EMPTY_STRING = "";

    private final Rect soldierPattern;

    public RtsDataBase() {
        soldierPattern = new Rect();
        soldierPattern.setHeight(new Rate(32));
        soldierPattern.setWidth(new Rate(32));
    }

    public Rect getSoldierPattern() {
        return soldierPattern;
    }
}
