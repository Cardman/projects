package code.formathtml.util;

import code.bean.Bean;
import code.expressionlanguage.ExecutableCode;
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
    public String getClassName(ExecutableCode _contextEl) {
        return bean.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return bean;
    }


}
