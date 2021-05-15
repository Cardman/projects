package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.util.StringMap;

public final class AnaInstancingAnnotContent {
    private String methodName;

    private AnaFormattedRootBlock formattedType;
    private String className;
    private final StringMap<AnnotationTypeInfo> fieldNames = new StringMap<AnnotationTypeInfo>();
    private boolean array;

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean _array) {
        this.array = _array;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String _methodName) {
        this.methodName = _methodName;
    }

    public StringMap<AnnotationTypeInfo> getFieldNames() {
        return fieldNames;
    }

}
