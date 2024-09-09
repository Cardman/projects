package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.util.core.StringUtil;

public final class LambdaConstructorStruct extends WithoutParentIdStruct implements LambdaStruct {

    private final Struct instanceCall;

    private final String className;
    private final String formClassName;

    private final boolean shiftInstance;
    private final boolean safeInstance;
    private final Struct metaInfo;

    public LambdaConstructorStruct(Struct _metaInfo, Struct _previous, ExecLambdaCommonContent _common, String _className) {
        metaInfo = _metaInfo;
        formClassName = "";
        instanceCall = ArgumentListCall.getNull(_previous);
        className = StringUtil.nullToEmpty(_className);
        shiftInstance = _common.isShiftArgument();
        safeInstance = _common.isSafeInstance();
    }

    public LambdaConstructorStruct(String _formClassName,Struct _previous, ExecLambdaCommonContent _common, String _className) {
        metaInfo = NullStruct.NULL_VALUE;
        formClassName = StringUtil.nullToEmpty(_formClassName);
        instanceCall = ArgumentListCall.getNull(_previous);
        className = StringUtil.nullToEmpty(_className);
        shiftInstance = _common.isShiftArgument();
        safeInstance = _common.isSafeInstance();
    }

    public String getFormClassName() {
        return formClassName;
    }
    public Struct getInstanceCall() {
        return instanceCall;
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public boolean isSafeInstance() {
        return safeInstance && instanceCall == NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }


}
