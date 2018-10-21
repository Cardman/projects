package code.expressionlanguage.opers.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.util.ObjectMap;

public class LambdaFieldStruct implements Struct {

    private Argument instanceCall;

    private final String className;
    private final String formClassName;

    private final ClassField fid;

    private final boolean shiftInstance;

    private final int ancestor;
    private final boolean affect;
    private final String returnFieldType;

    public LambdaFieldStruct(String _className,String _formClassName, ClassField _fid,
            boolean _shiftInstance, int _ancestor, boolean _affect,String _returnFieldType) {
        className = _className;
        formClassName = _formClassName;
        fid = _fid;
        shiftInstance = _shiftInstance;
        ancestor = _ancestor;
        affect = _affect;
        returnFieldType = _returnFieldType;
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

    public ClassField getFid() {
        return fid;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public int getAncestor() {
        return ancestor;
    }
    public boolean isAffect() {
        return affect;
    }
    public String getReturnFieldType() {
        return returnFieldType;
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

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

}
