package code.expressionlanguage.opers;

import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface SettableElResult extends Operable {

    void setVariable(boolean _variable);
    void setCatenizeStrings();


}
