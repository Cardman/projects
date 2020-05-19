package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.util.CustList;

public interface ParentOperable extends Operable {
    Operable getFirstChildOperable();
    CustList<Operable> getChildrenOperable();

    void quickCalculate(ContextEl _conf);
}
