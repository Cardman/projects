package code.expressionlanguage.opers.util;

public final class ConstructorMetaInfo {

    private final String returnType;

    public ConstructorMetaInfo(String _returnType) {
        returnType = _returnType;
    }
    public String getReturnType() {
        return returnType;
    }

}
