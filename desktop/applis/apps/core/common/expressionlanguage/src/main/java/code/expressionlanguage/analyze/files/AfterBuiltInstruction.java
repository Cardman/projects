
package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.BracedBlock;

public final class AfterBuiltInstruction {

    private BracedBlock parent;

    private String packageName = "";

    public BracedBlock getParent() {
        return parent;
    }

    public void setParent(BracedBlock _parent) {
        parent = _parent;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String _packageName) {
        packageName = _packageName;
    }
}
