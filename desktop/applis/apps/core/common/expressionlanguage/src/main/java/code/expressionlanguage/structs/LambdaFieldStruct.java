package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.util.core.StringUtil;

public final class LambdaFieldStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall;

    private final String className;
    private final String ownerType;

    private final boolean shiftInstance;

    private final int ancestor;
    private final boolean affect;
    private final boolean staticField;
    private final boolean safeInstance;
    private final boolean toStrField;
    private final boolean rdCodField;
    private final boolean instanceField;
    private final Struct metaInfo;

    public LambdaFieldStruct(Struct _metaInfo,Argument _previous, ExecLambdaCommonContent _common, ExecLambdaFieldContent _field, String _className, String _ownerType) {
        metaInfo = _metaInfo;
        instanceCall =  Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        ownerType = StringUtil.nullToEmpty(_ownerType);
        shiftInstance = _common.isShiftArgument();
        ancestor = _common.getAncestor();
        affect = _field.isAffField();
        staticField = _field.isStaticField();
        toStrField = _field.isToStrField();
        rdCodField = _field.isRdCodField();
        instanceField = _field.isInstanceField();
        safeInstance = _common.isSafeInstance();
    }

    public Argument getInstanceCall() {
        return instanceCall;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public boolean isSafeInstance() {
        return safeInstance && instanceCall.isNull();
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public boolean isToStrField() {
        return toStrField;
    }

    public boolean isRdCodField() {
        return rdCodField;
    }

    public boolean isInstanceField() {
        return instanceField;
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

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public String getOwnerType() {
        return ownerType;
    }
}
