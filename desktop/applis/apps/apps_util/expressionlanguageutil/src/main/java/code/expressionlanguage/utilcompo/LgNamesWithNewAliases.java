package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.BuildableLgNames;

public interface LgNamesWithNewAliases extends BuildableLgNames {
    CustAliases getCustAliases();
    ExecutingOptions getExecutingOptions();
    void setExecutingOptions(ExecutingOptions executingOptions);
    ExecutingBlocks getExecutingBlocks();
    void forwardAndClear(Classes _classes);
}
