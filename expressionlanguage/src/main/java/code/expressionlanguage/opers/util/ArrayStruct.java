package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;

public final class ArrayStruct implements Struct {

    private final Struct[] instance;

    private final String className;

    public ArrayStruct(Struct[] _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return getInstance() == _other.getInstance();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Struct[] getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField,Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return true;
    }
}
