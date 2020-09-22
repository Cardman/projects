
package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.BracedBlock;

public final class AfterBuiltInstruction {

    private int index;

    private BracedBlock parent;

    private String packageName = "";

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String _packageName) {
        packageName = _packageName;
    }
}
