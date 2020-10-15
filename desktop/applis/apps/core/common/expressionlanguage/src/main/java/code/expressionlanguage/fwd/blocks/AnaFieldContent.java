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

    public void setValueOffset(int _valueOffset) {
        this.valueOffset = _valueOffset;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean _staticField) {
        this.staticField = _staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public void setFinalField(boolean _finalField) {
        this.finalField = _finalField;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public void setAccess(AccessEnum _access) {
        this.access = _access;
    }
}
