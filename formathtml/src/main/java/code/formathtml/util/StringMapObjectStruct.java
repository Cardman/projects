package code.formathtml.util;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMapObject;

public final class StringMapObjectStruct implements RealInstanceStruct {

    private StringMapObject bean;

    public StringMapObjectStruct(StringMapObject _bean) {
        bean = _bean;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        BeanLgNames stds_ = (BeanLgNames) _contextEl.getStandards();
        return stds_.getAliasStringMapObject();
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
