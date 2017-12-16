package code.expressionlanguage.opers.util;

import code.util.ObjectMap;

public final class IntStruct implements Struct {

    private final int value;

    public IntStruct(int _value) {
        value = _value;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Boolean isJavaObject() {
        return true;
    }

    @Override
    public String getClassName() {
        return Integer.class.getName();
    }

    @Override
    public String getRealClassName() {
        return getClassName();
    }

    @Override
    public Object getInstance() {
        return value;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
