package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.AnnotationTypeInfo;
import code.util.StringMap;

public final class ExecInstancingAnnotContent {
    private final String methodName;

    private final String className;
    private final StringMap<AnnotationTypeInfo> fieldNames;
    public ExecInstancingAnnotContent(AnaInstancingAnnotContent _cont) {
        methodName = _cont.getMethodName();
        className = _cont.getClassName();
        fieldNames = _cont.getFieldNames();
    }

    public StringMap<AnnotationTypeInfo> getFieldNames() {
        return fieldNames;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
