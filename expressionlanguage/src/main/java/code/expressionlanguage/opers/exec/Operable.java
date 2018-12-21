package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.util.CustList;

public interface Operable {

    Argument getArgument();
    CustList<Operable> getChildrenOperable();
    void setSimpleArgument(Argument _argument);
    void setSimpleArgumentAna(Argument _argument, Analyzable _conf);
}
