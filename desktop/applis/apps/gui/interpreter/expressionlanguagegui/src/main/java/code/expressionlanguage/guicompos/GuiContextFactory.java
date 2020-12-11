package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class GuiContextFactory {
    public static ResultsGuiContext buildDefKw(String _lang, StringList _mainArgs, MainWindow _window,
                                          Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
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
        return build(_mainArgs,_window,IndexConstants.INDEX_NOT_FOUND_ELT, _options, _exec,mess_,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static ResultsGuiContext build(StringList _mainArgs, MainWindow _window, int _stack,
                                               Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files, int _tabWidth) {
        GuiInitializer ci_ = new GuiInitializer();
        _definedLgNames.setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(ci_,_mainArgs,_window);
        GuiContextEl r_ = (GuiContextEl)_definedLgNames.newContext(_tabWidth,_stack, new Coverage(_options.isCovering()));
        ReportedMessages reportedMessages_ = ContextFactory.validate(_mess, _definedKw, _definedLgNames, _files, r_, _exec.getSrcFolder(), _definedLgNames.getCustAliases().defComments(), _options, r_.getClasses().getCommon(), new AdvancedConstantsCalculator(_definedLgNames), new GuiFileBuilder(_definedLgNames.getContent(),_definedLgNames.getGuiAliases(),_definedLgNames.getCustAliases()), _definedLgNames.getContent());
        return new ResultsGuiContext(r_,reportedMessages_);
    }
}
