package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    public static ResultContext buildDefKw(String _lang, StringList _mainArgs,
                                               Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, GuiInterpreterElements _currentElements) {
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
        return build(_mainArgs, _options, _exec,mess_,kwl_, _undefinedLgNames, _files, _currentElements);
    }
    public static ResultContext build(StringList _mainArgs,
                                          Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files, GuiInterpreterElements _currentElements) {
        _definedLgNames.setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(_mainArgs, _currentElements);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames,fileBuilder_, _options);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ContextEl reportedMessages_ = ContextFactory.addResourcesAndValidate(_files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultContext(reportedMessages_,page_.getMessages());
    }
}
