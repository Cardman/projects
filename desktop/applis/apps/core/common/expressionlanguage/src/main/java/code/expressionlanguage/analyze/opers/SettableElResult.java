package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public interface SettableElResult {

    void setVariable(boolean _variable);
    void setCatenizeStrings();
    AnaClassArgumentMatching getResultClass();

}
