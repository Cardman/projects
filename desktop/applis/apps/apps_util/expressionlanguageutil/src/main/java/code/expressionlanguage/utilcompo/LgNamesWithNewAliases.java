package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LoggableLgNames;
import code.sml.util.Translations;

public interface LgNamesWithNewAliases extends BuildableLgNames, LoggableLgNames {
    FileInfos getInfos();
    AbstractInterceptor getInterceptor();
    CustAliases getCustAliases();
    ExecutingOptions getExecutingOptions();
    void setExecutingOptions(ExecutingOptions _executingOptions);
    void updateTranslations(Translations _trs, String _lg);
    ExecutingBlocks getExecutingBlocks();
    void forwardAndClear(Classes _classes);
}
