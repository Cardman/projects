package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public final class NullStruct extends Struct {

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
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return null;
    }
}
