package code.expressionlanguage.opers.util;


public final class MethodMetaInfo {

    private final String className;

    private final MethodId realId;

    private final MethodModifier modifier;

    private final String returnType;

    public MethodMetaInfo(String _className, MethodId _realId, MethodModifier _modifier,String _returnType) {
        className = _className;
        realId = _realId;
        modifier = _modifier;
        returnType = _returnType;
    }

    public String getClassName() {
        return className;
    }
    public MethodId getRealId() {
        return realId;
    }
    public boolean isStatic() {
        return getModifier() == MethodModifier.STATIC;
    }
    public MethodModifier getModifier() {
        return modifier;
    }
    public String getReturnType() {
        return returnType;
    }

}
