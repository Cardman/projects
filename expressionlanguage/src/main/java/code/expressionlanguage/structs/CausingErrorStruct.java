package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

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
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasErrorInitClass();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    public String getMessage() {
        return message;
    }

}
