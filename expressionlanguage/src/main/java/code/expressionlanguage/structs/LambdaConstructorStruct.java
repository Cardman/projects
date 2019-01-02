package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ConstructorId;

public class LambdaConstructorStruct implements Struct {

    private Argument instanceCall;

    private final String className;
    private final String formClassName;

    private final ConstructorId fid;

    private final boolean shiftInstance;

    public LambdaConstructorStruct(String _className, String _formClassName, ConstructorId _fid,
            boolean _shiftInstance) {
        className = _className;
        formClassName = _formClassName;
        fid = _fid;
        shiftInstance = _shiftInstance;
    }

    public String getFormClassName() {
        return formClassName;
    }
    public Argument getInstanceCall() {
        return instanceCall;
    }

    public void setInstanceCall(Argument _instanceCall) {
        instanceCall = _instanceCall;
    }

    public ConstructorId getFid() {
        return fid;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

}
