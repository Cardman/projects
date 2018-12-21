package code.expressionlanguage.opers;

import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface SettableElResult {

    void setVariable(boolean _variable);
    void setCatenizeStrings();
    boolean resultCanBeSet();
    ClassArgumentMatching getResultClass();

}
