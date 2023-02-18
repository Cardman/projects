package code.expressionlanguage.utilimpl;

import code.expressionlanguage.utilcompo.*;
import code.sml.util.Translations;

public final class LgNamesUtilsContent {
    private final CustAliases custAliases = new CustAliases();

    private final FileInfos infos;

    private ExecutingOptions executingOptions;

    private final ExecutingBlocks executingBlocks = new ExecutingBlocks();
    public LgNamesUtilsContent(FileInfos _infos, AbstractInterceptor _inter) {
        custAliases.setInfos(_infos);
        custAliases.setInterceptor(_inter);
        infos = _infos;
    }
    public AbstractInterceptor getInterceptor() {
        return custAliases.getInterceptor();
    }
    public FileInfos getInfos() {
        return infos;
    }
    public CustAliases getCustAliases() {
        return custAliases;
    }

    public ExecutingBlocks getExecutingBlocks() {
        return executingBlocks;
    }

    public ExecutingOptions getExecutingOptions() {
        return executingOptions;
    }

    public void setExecutingOptions(ExecutingOptions _executingOptions) {
        this.executingOptions = _executingOptions;
    }

    public void updateTranslations(Translations _trs, String _lg) {
        custAliases.setTranslations(_trs);
        custAliases.setLanguage(_lg);
    }
}
