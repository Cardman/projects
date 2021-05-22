package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.util.core.StringUtil;

public final class LambdaConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Argument instanceCall;

    private final String className;
    private final String formClassName;

    private final boolean shiftInstance;
    private final boolean safeInstance;
    private final Struct metaInfo;

    public LambdaConstructorStruct(Struct _metaInfo, Argument _previous, ExecLambdaCommonContent _common, String _className) {
        metaInfo = _metaInfo;
        formClassName = "";
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        shiftInstance = _common.isShiftArgument();
        safeInstance = _common.isSafeInstance();
    }

    public LambdaConstructorStruct(String _formClassName,Argument _previous, ExecLambdaCommonContent _common, String _className) {
        metaInfo = NullStruct.NULL_VALUE;
        formClassName = StringUtil.nullToEmpty(_formClassName);
        instanceCall = Argument.getNullableValue(_previous);
        className = StringUtil.nullToEmpty(_className);
        shiftInstance = _common.isShiftArgument();
        safeInstance = _common.isSafeInstance();
    }

    public String getFormClassName() {
        return formClassName;
    }
    public Argument getInstanceCall() {
        return instanceCall;
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public boolean isSafeInstance() {
        return safeInstance && instanceCall.isNull();
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }


}
