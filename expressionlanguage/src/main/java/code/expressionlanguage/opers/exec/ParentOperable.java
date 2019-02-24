package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.util.CustList;

public interface ParentOperable extends Operable {
    Operable getFirstChild();
    CustList<Operable> getChildrenOperable();

    void quickCalculate(Analyzable _conf);
}
