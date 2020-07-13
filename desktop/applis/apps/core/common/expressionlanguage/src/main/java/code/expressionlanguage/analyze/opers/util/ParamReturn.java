package code.expressionlanguage.analyze.opers.util;

public final class ParamReturn {
    private final String paramType;
    private final String returnType;

    public ParamReturn(String _paramType, String _returnType) {
        paramType = _paramType;
        returnType = _returnType;
    }

    public String getParamType() {
        return paramType;
    }

    public String getReturnType() {
        return returnType;
    }
}
