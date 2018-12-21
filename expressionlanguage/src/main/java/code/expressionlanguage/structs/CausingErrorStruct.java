package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.util.ObjectMap;

public final class CausingErrorStruct implements Struct {

    private final Struct cause;
    private final String message;

    public CausingErrorStruct() {
        this("");
    }

    public CausingErrorStruct(Struct _cause) {
        this("", _cause);
    }

    public CausingErrorStruct(String _message) {
        this(_message, NullStruct.NULL_VALUE);
    }

    public CausingErrorStruct(String _message,Struct _cause) {
        message = _message;
        cause = _cause;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public Struct getCause() {
        return cause;
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
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasErrorInitClass();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return this;
    }

    public String getMessage() {
        return message;
    }
    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

}
