package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.common.AccessEnum;

public final class AnaFieldContent {

    private int valueOffset;

    private boolean staticField;

    private boolean finalField;

    private AccessEnum access;

    public int getValueOffset() {
        return valueOffset;
    }

    public void setValueOffset(int valueOffset) {
        this.valueOffset = valueOffset;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean staticField) {
        this.staticField = staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public void setFinalField(boolean finalField) {
        this.finalField = finalField;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public void setAccess(AccessEnum access) {
        this.access = access;
    }
}
