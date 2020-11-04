package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.util.core.StringUtil;

public final class LambdaConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private Argument instanceCall = Argument.createVoid();

    private final String className;
    private final String formClassName;

    private final boolean shiftInstance;
    private boolean safeInstance;
    private Struct metaInfo = NullStruct.NULL_VALUE;

    public LambdaConstructorStruct(String _className, String _formClassName,
                                   boolean _shiftInstance) {
        className = StringUtil.nullToEmpty(_className);
        formClassName = StringUtil.nullToEmpty(_formClassName);
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

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(Struct _metaInfo) {
        this.metaInfo = _metaInfo;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public void setSafeInstance(boolean _safeInstance) {
        safeInstance = _safeInstance;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }


}
