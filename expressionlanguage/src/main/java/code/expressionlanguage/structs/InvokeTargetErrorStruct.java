package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class InvokeTargetErrorStruct implements Struct {

    private final Struct cause;
    private final String message;

    public InvokeTargetErrorStruct() {
        this("");
    }

    public InvokeTargetErrorStruct(Struct _cause) {
        this("", _cause);
    }

    public InvokeTargetErrorStruct(String _message) {
        this(_message, NullStruct.NULL_VALUE);
    }

    public InvokeTargetErrorStruct(String _message, Struct _cause) {
        message = _message;
        cause = _cause;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public Struct getCause() {
        return cause;
    }


    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasInvokeTarget();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }


}
