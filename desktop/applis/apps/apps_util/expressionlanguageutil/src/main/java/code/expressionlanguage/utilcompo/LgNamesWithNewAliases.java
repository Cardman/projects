package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.BuildableLgNames;

public interface LgNamesWithNewAliases extends BuildableLgNames {
    FileInfos getInfos();
    AbstractInterceptor getInterceptor();
    CustAliases getCustAliases();
    ExecutingOptions getExecutingOptions();
    void setExecutingOptions(ExecutingOptions _executingOptions);
    ExecutingBlocks getExecutingBlocks();
    void forwardAndClear(Classes _classes);
}
