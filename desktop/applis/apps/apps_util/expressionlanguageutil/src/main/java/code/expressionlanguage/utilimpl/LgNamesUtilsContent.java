package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.LoggableLgNames;
import code.expressionlanguage.utilcompo.*;
import code.sml.util.Translations;

public final class LgNamesUtilsContent implements LoggableLgNames {
    private final CustAliases custAliases = new CustAliases();

    private final FileInfos infos;

    private ExecutingOptions executingOptions;

    private final ExecutingBlocks executingBlocks = new ExecutingBlocks();
    public LgNamesUtilsContent(FileInfos _infos, AbstractInterceptor _inter) {
        custAliases.setInfos(_infos);
        custAliases.setInterceptor(_inter);
        infos = _infos;
    }
    @Override
    public String logIssue(String _info, ReportedMessages _rep) {
        getInfos().tryLogIssue(_info);
        return _info;
    }
    public void forwardAndClear(LgNamesContent _content, Classes _classes) {
        getExecutingBlocks().forwardAndClear(_content, getCustAliases(),_classes);
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

    public void updateTranslations(Translations _trs, String _lg, String _userLg) {
        custAliases.setTranslations(_trs);
        custAliases.setLanguage(_lg);
        custAliases.setUserLg(_userLg);
    }
}
