package aiki.beans;

import code.bean.RealInstanceStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.maths.Rate;

public final class RateStruct extends WithoutParentIdStruct implements RealInstanceStruct {

    private final Rate instance;

    private final String className;

    public RateStruct(Rate _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Object getInstance() {
        return getRate();
    }
    public Rate getRate() {
        return instance;
    }

}
