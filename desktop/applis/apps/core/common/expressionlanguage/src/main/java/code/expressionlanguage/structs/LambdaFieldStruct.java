package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.util.core.StringUtil;

public final class LambdaFieldStruct extends WithoutParentIdStruct implements LambdaStruct {

    private Argument instanceCall = Argument.createVoid();

    private final String className;
    private final String ownerType;

    private final ClassField fid;

    private final boolean shiftInstance;

    private final int ancestor;
    private final boolean affect;
    private boolean staticField;
    private boolean safeInstance;
    private final String returnFieldType;
    private Struct metaInfo = NullStruct.NULL_VALUE;

    public LambdaFieldStruct(String _className, String _ownerType,ClassField _fid,
                             boolean _shiftInstance, int _ancestor, boolean _affect, String _returnFieldType) {
        className = StringUtil.nullToEmpty(_className);
        ownerType = StringUtil.nullToEmpty(_ownerType);
        fid = _fid;
        shiftInstance = _shiftInstance;
        ancestor = _ancestor;
        affect = _affect;
        returnFieldType = StringUtil.nullToEmpty(_returnFieldType);
    }

    public Argument getInstanceCall() {
        return instanceCall;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean _staticField) {
        this.staticField = _staticField;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public void setSafeInstance(boolean _safeInstance) {
        safeInstance = _safeInstance;
    }

    public void setInstanceCall(Argument _instanceCall) {
        instanceCall =  Argument.getNullableValue(_instanceCall);
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(Struct _metaInfo) {
        this.metaInfo = _metaInfo;
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
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public String getOwnerType() {
        return ownerType;
    }
}
