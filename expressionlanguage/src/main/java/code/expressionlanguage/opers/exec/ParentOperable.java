package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;

public interface ParentOperable extends Operable {
    Operable getFirstChild();
    void quickCalculate(Analyzable _conf);
}
