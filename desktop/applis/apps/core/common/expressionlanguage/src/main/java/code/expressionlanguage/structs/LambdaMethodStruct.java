package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.util.core.StringUtil;

public final class LambdaMethodStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall;

    private final String className;

    private final boolean polymorph;

    private final boolean shiftInstance;

    private final int ancestor;

    private final boolean safeInstance;
    private final MethodAccessKind kind;
    private final String methodName;
    private final Struct metaInfo;
    private final CommonOperSymbol operSymbol;

    public LambdaMethodStruct(Struct _metaInfo, Argument _previous, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, String _className) {
        this(_metaInfo,_previous,_common,_meth,_className,null);
    }
    public LambdaMethodStruct(Struct _metaInfo, Argument _previous, ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, String _className, CommonOperSymbol _com) {
        this(_metaInfo, _previous, _common, _meth.getMethod(), _className, _meth.isPolymorph(), _com);
    }

    public LambdaMethodStruct(Struct _metaInfo, Argument _previous, ExecLambdaCommonContent _common, MethodId _meth, String _className, boolean _polymorph) {
        this(_metaInfo,_previous,_common,_meth,_className,_polymorph,null);
    }
    public LambdaMethodStruct(Struct _metaInfo, Argument _previous, ExecLambdaCommonContent _common, MethodId _meth, String _className, boolean _polymorph, CommonOperSymbol _com) {
        metaInfo = _metaInfo;
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        polymorph = _polymorph;
        shiftInstance = _common.isShiftArgument();
        ancestor = _common.getAncestor();
        safeInstance = _common.isSafeInstance();
        methodName = StringUtil.nullToEmpty(_meth.getName());
        kind = _meth.getKind();
        operSymbol = _com;
    }
    public Argument getInstanceCall() {
        return instanceCall;
    }

    public Struct getMetaInfo() {
        return metaInfo;
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

    public CommonOperSymbol getOperSymbol() {
        return operSymbol;
    }

    public boolean isSafeInstance() {
        return safeInstance && instanceCall.isNull();
    }

    public int getAncestor() {
        return ancestor;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

}
