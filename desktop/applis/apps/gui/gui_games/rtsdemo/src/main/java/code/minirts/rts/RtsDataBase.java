package code.minirts.rts;

import code.gui.TopLeftFrame;

public final class RtsDataBase {

    public static final String EMPTY_STRING = "";

    private final TopLeftFrame soldierPattern;

    public RtsDataBase() {
        soldierPattern = new TopLeftFrame();
        soldierPattern.setHeight(32);
        soldierPattern.setWidth(32);
    }

    public TopLeftFrame getSoldierPattern() {
        return soldierPattern;
    }
}
