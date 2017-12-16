package code.expressionlanguage.opers.util;

import code.util.ObjectMap;

public final class CharStruct implements Struct {

    private final char value;

    public CharStruct(char _value) {
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
        return Character.class.getName();
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
