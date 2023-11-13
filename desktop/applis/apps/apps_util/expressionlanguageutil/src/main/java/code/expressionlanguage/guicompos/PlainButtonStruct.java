package code.expressionlanguage.guicompos;

import code.gui.AbsButton;

public final class PlainButtonStruct extends AbsButtonStruct {
    private final AbsButton button;
    public PlainButtonStruct(String _className, AbsButton _b) {
        super(_className);
        button = _b;
    }

    @Override
    public AbsButton but() {
        return button;
    }
}
