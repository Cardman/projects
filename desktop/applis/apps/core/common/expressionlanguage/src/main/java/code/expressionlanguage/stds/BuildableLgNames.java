package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.coverage.Coverage;

public interface BuildableLgNames {
    void build();
    void logIssue(String _info);
    LgNamesContent getContent();
    ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage);
}
