package code.expressionlanguage.opers.util;

import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public final class InvokeTargetErrorStruct implements Struct {

    private final Struct cause;
    private final CustomError error;

    public InvokeTargetErrorStruct() {
        this("");
    }

    public InvokeTargetErrorStruct(Struct _cause) {
        this("", _cause);
    }

    public InvokeTargetErrorStruct(String _message) {
        this(_message, NullStruct.NULL_VALUE);
    }

    public InvokeTargetErrorStruct(String _message,Struct _cause) {
        error = new CustomError(_message);
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
        return _contextEl.getStandards().getAliasInvokeTarget();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return error;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

}
