package code.expressionlanguage.opers.util;

public final class ConstructorMetaInfo {

    private final ClassName returnType;

    public ConstructorMetaInfo(ClassName _returnType) {
        returnType = _returnType;
    }
    public ClassName getReturnType() {
        return returnType;
    }

}
