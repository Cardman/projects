package code.expressionlanguage.opers.util;

import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public final class BooleanStruct implements Struct {

    private final boolean value;

    public BooleanStruct(boolean _value) {
        value = _value;
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
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasPrimBoolean();
    }

    @Override
    public Boolean getInstance() {
        return value;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof BooleanStruct)) {
            return false;
        }
        BooleanStruct other_ = (BooleanStruct) _other;
        return getInstance().booleanValue() == other_.getInstance().booleanValue();
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
