package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.utilcompo.CustLockingClass;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    public static ResultsGuiContext buildDefKw(String _lang, StringList _mainArgs, MainWindow _window,
                                          Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, int _tabWidth) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        if (StringList.quickEq(_lang, "en")) {
            _undefinedLgNames.messages(mess_,_lang,_exec.getMessages());
            _undefinedLgNames.keyWord(kwl_,_lang,_exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else if (StringList.quickEq(_lang, "fr")) {
            _undefinedLgNames.messages(mess_,_lang,_exec.getMessages());
            _undefinedLgNames.keyWord(kwl_,_lang,_exec.getKeyWords());
            _undefinedLgNames.otherAlias(_lang,_exec.getAliases());
        } else {
            _undefinedLgNames.messages(mess_,_exec.getMessages(), new StringMap<String>());
            _undefinedLgNames.keyWord(kwl_,_exec.getKeyWords(), new StringMap<String>());
            _undefinedLgNames.allAlias(_exec.getAliases(), new StringMap<String>());
        }
        return build(_mainArgs,_window,CustList.INDEX_NOT_FOUND_ELT, _options, _exec,mess_,kwl_, _undefinedLgNames, _files, _tabWidth);
    }
    public static ResultsGuiContext build(StringList _mainArgs, MainWindow _window, int _stack,
                                               Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files, int _tabWidth) {
        CustLockingClass cl_ = new CustLockingClass();
        GuiInitializer ci_ = new GuiInitializer();
        ClassesCommon com_ = new ClassesCommon();
        GuiContextEl r_ = new GuiContextEl(_stack, cl_, ci_, _options, _exec, _definedLgNames,_tabWidth, com_);
        r_.initApplicationParts(_mainArgs,_window);
        ReportedMessages reportedMessages_ = ContextFactory.validate(_mess, _definedKw, _definedLgNames, _files, r_, _exec.getSrcFolder(), _definedLgNames.defComments(), _options, com_);
        return new ResultsGuiContext(r_,reportedMessages_);
    }
}
