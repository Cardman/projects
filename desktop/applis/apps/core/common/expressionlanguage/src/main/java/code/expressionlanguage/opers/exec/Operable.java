package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface Operable {

    Argument getArgument();
    ClassArgumentMatching getResultClass();
    int getOrder();
    int getIndexChild();
    int getIndexBegin();
    int getIndexInEl();


}
