package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class ResultOperand {

    private ClassArgumentMatching result;

    private boolean catString;
    public ClassArgumentMatching getResult() {
        return result;
    }
    public void setResult(ClassArgumentMatching _result) {
        result = _result;
    }
    public boolean isCatString() {
        return catString;
    }
    public void setCatString(boolean _catString) {
        catString = _catString;
    }
}
