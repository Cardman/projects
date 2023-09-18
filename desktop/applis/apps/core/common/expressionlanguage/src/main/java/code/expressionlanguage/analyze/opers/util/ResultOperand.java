package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;

public final class ResultOperand {

    private AnaClassArgumentMatching result;
    private CommonOperSymbol symbol;
    private boolean defConcat;
    private String first="";
    private String second="";
    private String sgn="";

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

    public String getFirst() {
        return first;
    }

    public void setFirst(String _f) {
        this.first = _f;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String _s) {
        this.second = _s;
    }

    public String getSgn() {
        return sgn;
    }

    public void setSgn(String _s) {
        this.sgn = _s;
    }
}
