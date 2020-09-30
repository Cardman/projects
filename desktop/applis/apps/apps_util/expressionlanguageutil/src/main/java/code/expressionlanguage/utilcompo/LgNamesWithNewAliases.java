package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;

public interface LgNamesWithNewAliases {
    CustAliases getCustAliases();
    ExecutingOptions getExecutingOptions();
    ExecutingBlocks getExecutingBlocks();
    void forwardAndClear(Classes _classes);
}
