package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.util.StringMap;

public final class ExecInstancingAnnotContent {
    private final String methodName;

    private final String className;
    private final ExecFormattedRootBlock formattedType;
    private final StringMap<AnnotationTypeInfo> fieldNames;
    public ExecInstancingAnnotContent(AnaInstancingAnnotContent _cont, Forwards _fwd) {
        methodName = _cont.getMethodName();
        className = _cont.getClassName();
        fieldNames = _cont.getFieldNames();
        AnaFormattedRootBlock formattedType_ = _cont.getFormattedType();
        if (formattedType_ != null) {
            formattedType = FetchMemberUtil.fwdFormatType(formattedType_,_fwd);
        } else {
            formattedType = new ExecFormattedRootBlock(null,"");
        }
    }

    public StringMap<AnnotationTypeInfo> getFieldNames() {
        return fieldNames;
    }

    public String getMethodName() {
        return methodName;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public String getClassName() {
        return className;
    }
}
