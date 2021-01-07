package aiki.beans;

import code.bean.RealInstanceStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.maths.LgInt;

public final class LgIntStruct extends WithoutParentIdStruct implements RealInstanceStruct {

    private final LgInt instance;

    private final String className;

    public LgIntStruct(LgInt _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return getInt();
    }
    public LgInt getInt() {
        return instance;
    }

}
