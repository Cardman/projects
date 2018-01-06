package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.ObjectMap;


public final class CustCharSequenceStruct extends CharSequenceStruct {

    private CharSequence sequence;

    private String className;

    public CustCharSequenceStruct(CharSequence _sequence, String _className) {
        sequence = _sequence;
        className = _className;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public CharSequence getInstance() {
        return sequence;
    }

}
