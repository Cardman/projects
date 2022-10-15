package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public abstract class AbNullStruct extends WithoutParentIdStruct {
    @Override
    public String getClassName(ContextEl _contextEl) {
        return "";
    }
}
