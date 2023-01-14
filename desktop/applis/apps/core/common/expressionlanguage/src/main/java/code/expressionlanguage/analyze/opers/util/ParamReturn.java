package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.util.StringMap;

public final class ParamReturn {
    private final String paramType;
    private final String returnType;
    private final StringMap<CommonOperSymbol> mapOperators = new StringMap<CommonOperSymbol>();

    public ParamReturn(String _paramType, String _returnType) {
        paramType = _paramType;
        returnType = _returnType;
    }
    public ParamReturn with(String _op, CommonOperSymbol _symbol) {
        mapOperators.addEntry(_op,_symbol);
        return this;
    }

    public CommonOperSymbol get(String _op) {
        return mapOperators.getVal(_op);
    }

    public String getParamType() {
        return paramType;
    }

    public String getReturnType() {
        return returnType;
    }
}
