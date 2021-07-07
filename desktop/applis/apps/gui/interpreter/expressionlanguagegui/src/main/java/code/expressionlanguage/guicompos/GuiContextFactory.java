package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    public static ResultsGuiContext buildDefKw(String _lang, StringList _mainArgs, MainWindow _window,
                                               Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        if (!_lang.isEmpty()) {
            _undefinedLgNames.getCustAliases().messages(mess_, _lang, _exec.getMessages());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _lang, _exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.getCustAliases().messages(mess_, _exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.getCustAliases().keyWord(kwl_, _exec.getKeyWords(), new StringMap<String>());
            _undefinedLgNames.allAlias(_exec.getAliases(), new StringMap<String>());
        }
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns()));
        return build(_mainArgs,_window, _options, _exec,mess_,kwl_, _undefinedLgNames, _files);
    }
    public static ResultsGuiContext build(StringList _mainArgs, MainWindow _window,
                                          Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        GuiInitializer ci_ = new GuiInitializer(_window.getThreadFactory().newAtomicLong());
        _definedLgNames.setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(ci_,_mainArgs,_window);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames,fileBuilder_, _options);
        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ReportedMessages reportedMessages_ = ContextFactory.addResourcesAndValidate(_options, _files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultsGuiContext((GuiContextEl) forwards_.getContext(),reportedMessages_);
    }
}
