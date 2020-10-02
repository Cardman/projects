package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.coverage.Coverage;

public interface BuildableLgNames {
    void build();
    LgNamesContent getContent();
    ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage, Initializer _init);
}
