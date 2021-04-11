package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaAnoContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.util.core.StringUtil;

public final class LambdaMethodStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall;

    private final String className;
    private final String formClassName;

    private final boolean polymorph;

    private final boolean shiftInstance;

    private final int ancestor;

    private final boolean directCast;
    private final boolean safeInstance;
    private final boolean expCast;
    private final MethodAccessKind kind;
    private final String methodName;
    private final Struct metaInfo;

    public LambdaMethodStruct(Struct _metaInfo,Argument _previous, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, String _className, String _formClassName) {
        metaInfo = _metaInfo;
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        formClassName = StringUtil.nullToEmpty(_formClassName);
        polymorph = _meth.isPolymorph();
        shiftInstance = _common.isShiftArgument();
        ancestor = _common.getAncestor();
        safeInstance = _common.isSafeInstance();
        methodName = StringUtil.nullToEmpty(_meth.getMethod().getConstraints().getName());
        kind = _meth.getMethod().getConstraints().getKind();
        directCast = _meth.isDirectCast();
        expCast = _meth.isExpCast();
    }

    public LambdaMethodStruct(Struct _metaInfo,Argument _previous, ExecLambdaCommonContent _common, ExecLambdaAnoContent _meth, String _className, String _formClassName) {
        metaInfo = _metaInfo;
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        formClassName = StringUtil.nullToEmpty(_formClassName);
        polymorph = false;
        shiftInstance = _common.isShiftArgument();
        ancestor = _common.getAncestor();
        safeInstance = _common.isSafeInstance();
        methodName = StringUtil.nullToEmpty(_meth.getMethod().getConstraints().getName());
        kind = _meth.getMethod().getConstraints().getKind();
        directCast = false;
        expCast = false;
    }

    public LambdaMethodStruct(Struct _metaInfo,Argument _previous, ExecLambdaCommonContent _common, MethodId _meth, String _className, String _formClassName) {
        metaInfo = _metaInfo;
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        formClassName = StringUtil.nullToEmpty(_formClassName);
        polymorph = false;
        shiftInstance = _common.isShiftArgument();
        ancestor = _common.getAncestor();
        safeInstance = _common.isSafeInstance();
        methodName = StringUtil.nullToEmpty(_meth.getName());
        kind = _meth.getKind();
        directCast = false;
        expCast = false;
    }
    public Argument getInstanceCall() {
        return instanceCall;
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public String getFormClassName() {
        return formClassName;
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public int getAncestor() {
        return ancestor;
    }

    public boolean isStaticCall() {
        return kind == MethodAccessKind.STATIC_CALL || directCast || expCast;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

}
