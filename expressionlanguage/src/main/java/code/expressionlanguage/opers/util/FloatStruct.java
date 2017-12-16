package code.expressionlanguage.opers.util;

import code.util.ObjectMap;

public final class FloatStruct implements Struct {

    private final float value;

    public FloatStruct(float _value) {
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
        return Float.class.getName();
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
