package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.threads.AbstractThreadFactory;

public interface TestableFrame {
    boolean ok(String _file);
    AbstractInterceptor getIntercept();
    String getTxtConf();
    void showProgress(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved);
    void finish(Struct _infos, LgNamesWithNewAliases _evolved);
    void setResults(ContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved);
    FileInfos getInfos();

    AbstractThreadFactory getThreadFactory();
}
