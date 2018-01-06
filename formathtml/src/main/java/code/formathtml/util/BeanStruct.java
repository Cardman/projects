package code.formathtml.util;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.ObjectMap;

public final class BeanStruct implements Struct {

    private Bean bean;

    public BeanStruct(Bean _bean) {
        bean = _bean;
    }

    @Override
    public boolean isNull() {
        return false;
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
    public Bean getInstance() {
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
