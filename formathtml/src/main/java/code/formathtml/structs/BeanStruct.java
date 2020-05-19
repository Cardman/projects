package code.formathtml.structs;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class BeanStruct implements RealInstanceStruct {

    private Bean bean;

    public BeanStruct(Bean _bean) {
        bean = _bean;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return getBean();
    }

    public Bean getBean() {
        return bean;
    }
}
