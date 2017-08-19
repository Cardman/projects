package code.expressionlanguage.opers.util;


public final class MethodMetaInfo {

    private final String className;

    private final MethodModifier modifier;

    private final ClassName returnType;

    public MethodMetaInfo(String _className, MethodModifier _modifier,ClassName _returnType) {
        className = _className;
        modifier = _modifier;
        returnType = _returnType;
    }

    public String getClassName() {
        return className;
    }
    public boolean isStatic() {
        return getModifier() == MethodModifier.STATIC;
    }
    public MethodModifier getModifier() {
        return modifier;
    }
    public ClassName getReturnType() {
        return returnType;
    }

}
