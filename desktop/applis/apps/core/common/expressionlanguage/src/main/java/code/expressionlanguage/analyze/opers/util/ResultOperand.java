package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class ResultOperand {

    private AnaClassArgumentMatching result;

    private boolean catString;
    public AnaClassArgumentMatching getResult() {
        return result;
    }
    public void setResult(AnaClassArgumentMatching _result) {
        result = _result;
    }
    public boolean isCatString() {
        return catString;
    }
    public void setCatString(boolean _catString) {
        catString = _catString;
    }
}
