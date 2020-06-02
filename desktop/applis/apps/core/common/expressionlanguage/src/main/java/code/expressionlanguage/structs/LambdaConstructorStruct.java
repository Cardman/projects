package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ConstructorId;

public final class LambdaConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

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
    public String getClassName(ContextEl _contextEl) {
        return className;
    }


}
