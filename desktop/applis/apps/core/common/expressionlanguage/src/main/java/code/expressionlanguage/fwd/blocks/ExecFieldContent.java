package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.common.AccessEnum;

public final class ExecFieldContent {

    private final int valueOffset;

    private final boolean staticField;

    private final boolean finalField;

    private final AccessEnum access;
    public ExecFieldContent(AnaFieldContent _cont) {
        valueOffset = _cont.getValueOffset();
        staticField = _cont.isStaticField();
        finalField = _cont.isFinalField();
        access = _cont.getAccess();
    }

    public boolean isFinalField() {
        return finalField;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public AccessEnum getAccess() {
        return access;
    }
}
