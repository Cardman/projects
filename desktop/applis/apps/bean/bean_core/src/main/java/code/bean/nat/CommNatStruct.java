package code.bean.nat;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public abstract class CommNatStruct extends WithoutParentIdStruct {
    private final String className;

    protected CommNatStruct(String _className) {
        this.className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
}
