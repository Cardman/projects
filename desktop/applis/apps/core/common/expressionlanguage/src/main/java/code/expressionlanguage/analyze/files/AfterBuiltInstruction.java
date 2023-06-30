
package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.BracedBlock;

public final class AfterBuiltInstruction {

    private AbsBk created;
    private BracedBlock parent;

    private String packageName = "";

    public AbsBk getCreated() {
        return created;
    }

    public void setCreated(AbsBk _c) {
        this.created = _c;
    }

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
