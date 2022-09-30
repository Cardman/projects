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
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    private GuiContextFactory() {
    }
    public static ResultContext buildDefKw(String _lang, StringList _mainArgs,
                                               Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, GuiInterpreterElements _currentElements) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        CustContextFactory.preinitAliases(_lang,_exec,mess_,kwl_,_undefinedLgNames);
        if (!_lang.isEmpty()) {
            _undefinedLgNames.getGuiAliases().otherAliasGui(_undefinedLgNames.addon(_lang),_exec.getAliases());
        } else {
            _undefinedLgNames.getGuiAliases().otherAliasGui(_exec.getAliases(),new StringMap<String>());
        }
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns()));
        _undefinedLgNames.setExecutingOptions(_exec);
        _undefinedLgNames.getGuiExecutingBlocks().initApplicationParts(_mainArgs, _currentElements);
        return build(_options, _exec, mess_, kwl_, _undefinedLgNames, _files);
    }

    public static ResultContext build(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames, _definedLgNames,fileBuilder_, _options);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.validateStds(forwards_, _mess, _definedKw, _definedLgNames.getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ContextEl reportedMessages_ = ContextFactory.addResourcesAndValidate(_files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultContext(reportedMessages_, page_.getMessages());
    }
}
