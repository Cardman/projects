package code.expressionlanguage;

import code.expressionlanguage.structs.WithoutParentIdStruct;

public abstract class WithoutParentIdClassStruct extends WithoutParentIdStruct {
    private final String className;

    protected WithoutParentIdClassStruct(String _cl) {
        this.className = _cl;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
}
