package code.formathtml.util;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.ObjectMap;
import code.util.StringMapObject;

public final class StringMapObjectStruct implements Struct {

    private StringMapObject bean;

    public StringMapObjectStruct(StringMapObject _bean) {
        bean = _bean;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public boolean isNull() {
        return false;
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
    public StringMapObject getInstance() {
        return bean;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
