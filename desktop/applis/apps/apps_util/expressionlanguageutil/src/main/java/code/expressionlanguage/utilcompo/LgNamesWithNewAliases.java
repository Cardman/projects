package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LoggableLgNames;

public interface LgNamesWithNewAliases extends BuildableLgNames, LoggableLgNames {
    FileInfos getInfos();
    AbstractInterceptor getInterceptor();
    CustAliases getCustAliases();
    ExecutingOptions getExecutingOptions();
    void setExecutingOptions(ExecutingOptions _executingOptions);
    ExecutingBlocks getExecutingBlocks();
    void forwardAndClear(Classes _classes);
}
