
package code.expressionlanguage.files;

import code.expressionlanguage.methods.BracedBlock;

public final class AfterBuiltInstruction {

    private int index;

    private BracedBlock parent;

    private boolean enabledEnumHeader;

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public BracedBlock getParent() {
        return parent;
    }

    public void setParent(BracedBlock _parent) {
        parent = _parent;
    }

    public boolean isEnabledEnumHeader() {
        return enabledEnumHeader;
    }

    public void setEnabledEnumHeader(boolean _enabledEnumHeader) {
        enabledEnumHeader = _enabledEnumHeader;
    }

}
