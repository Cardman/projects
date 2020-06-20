package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.inherits.ClassArgumentMatching;

public interface SettableElResult {

    void setVariable(boolean _variable);
    void setCatenizeStrings();
    ClassArgumentMatching getResultClass();

}
