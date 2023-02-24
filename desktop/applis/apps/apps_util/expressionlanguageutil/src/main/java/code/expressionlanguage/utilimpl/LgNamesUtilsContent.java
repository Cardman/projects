package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.LoggableLgNames;
import code.expressionlanguage.utilcompo.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsFile;
import code.util.EntryCust;
import code.util.StringMap;

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
    public static StringMap<String> extractMap(TranslationsFile _file) {
        StringMap<String> m_ = new StringMap<String>();
        for (String v:_file.getMapping().values()) {
            int sep_ = v.indexOf('=');
            m_.addEntry(v.substring(0,sep_),v.substring(sep_+1));
        }
        return m_;
    }
    public static StringMap<String> extractKeys(TranslationsFile _file) {
        StringMap<String> m_ = new StringMap<String>();
        for (EntryCust<String, String> v:_file.getMapping().entryList()) {
            int sep_ = v.getValue().indexOf('=');
            m_.addEntry(v.getKey(),v.getValue().substring(0,sep_));
        }
        return m_;
    }
}
