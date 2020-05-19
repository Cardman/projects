package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;

public class LambdaMethodStruct implements Struct {

    private Argument instanceCall;

    private final String className;
    private final String formClassName;

    private final MethodId fid;

    private final boolean polymorph;

    private final boolean shiftInstance;

    private final int ancestor;

    private final boolean abstractMethod;

    private boolean directCast;
    private boolean expCast;
    public LambdaMethodStruct(String _className,String _formClassName, MethodId _fid,
            boolean _polymorph, boolean _shiftInstance, int _ancestor, boolean _abstractMethod) {
        className = _className;
        formClassName = _formClassName;
        fid = _fid;
        polymorph = _polymorph;
        shiftInstance = _shiftInstance;
        ancestor = _ancestor;
        abstractMethod = _abstractMethod;
    }

    public Argument getInstanceCall() {
        return instanceCall;
    }

    public void setInstanceCall(Argument _instanceCall) {
        instanceCall = _instanceCall;
    }

    public String getFormClassName() {
        return formClassName;
    }

    public MethodId getFid() {
        return fid;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public int getAncestor() {
        return ancestor;
    }
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isStaticCall() {
        return fid.getKind() == MethodAccessKind.STATIC_CALL || directCast || expCast;
    }
    public boolean isDirectCast() {
        return directCast;
    }

    public void setDirectCast(boolean _directCast) {
        directCast = _directCast;
    }

    public void setExpCast(boolean expCast) {
        this.expCast = expCast;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }


}
