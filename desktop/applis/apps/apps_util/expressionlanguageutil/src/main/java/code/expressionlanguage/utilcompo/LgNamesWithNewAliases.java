package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.stds.BuildableLgNames;
import code.expressionlanguage.utilimpl.LgNamesUtilsContent;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public interface LgNamesWithNewAliases extends BuildableLgNames {
    LgNamesUtilsContent getExecContent();
    StringList args();
    ContextEl newContext(AbstractAtomicBoolean _at, CommonExecutionInfos _common, StringList _args);
}
