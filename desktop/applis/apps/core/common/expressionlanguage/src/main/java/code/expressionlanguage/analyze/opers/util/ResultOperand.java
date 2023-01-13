package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;

public final class ResultOperand {

    private AnaClassArgumentMatching result;
    private CommonOperSymbol symbol;
    private boolean defConcat;

    public AnaClassArgumentMatching getResult() {
        return result;
    }
    public void setResult(AnaClassArgumentMatching _result) {
        result = _result;
    }

    public CommonOperSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(CommonOperSymbol _s) {
        this.symbol = _s;
    }

    public boolean isDefConcat() {
        return defConcat;
    }

    public void setDefConcat(boolean _c) {
        this.defConcat = _c;
    }

}
