package code.expressionlanguage.opers.util;

import code.util.ObjectMap;

public final class NullStruct implements Struct {

    public static final NullStruct NULL_VALUE = new NullStruct();

    private NullStruct() {
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public Boolean isJavaObject() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public String getRealClassName() {
        return null;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }
}
