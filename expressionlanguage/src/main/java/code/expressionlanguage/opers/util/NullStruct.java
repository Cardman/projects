package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
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

    @Override
    public boolean sameReference(Struct _other) {
        return _other == NULL_VALUE;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
