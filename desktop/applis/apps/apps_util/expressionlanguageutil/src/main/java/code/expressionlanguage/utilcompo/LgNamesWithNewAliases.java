package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.stds.LoggableLgNames;
import code.expressionlanguage.utilimpl.LgNamesUtilsContent;

public interface LgNamesWithNewAliases extends BuildableLgNames, LoggableLgNames {
    LgNamesUtilsContent getExecContent();
    void forwardAndClear(Classes _classes);
}
