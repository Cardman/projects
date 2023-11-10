package code.expressionlanguage.guicompos;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.*;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.util.StringList;
import code.util.StringMap;

public final class GuiContextFactory {
    private GuiContextFactory() {
    }
    public static ResultContext buildDefKw(StringList _mainArgs,
                                           Options _options, ExecutingOptions _exec, LgNamesGui _undefinedLgNames, StringMap<String> _files, AbstractLightProgramInfos _currentElements) {
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        _undefinedLgNames.getExecContent().updateTranslations(_currentElements.getTranslations(),_currentElements.getLanguage(),_exec.getLg());
        CustContextFactory.preinitAliases(_exec,mess_,kwl_, _undefinedLgNames.getContent(), _undefinedLgNames.getExecContent().getCustAliases(), _undefinedLgNames.getGuiAliases());
//        if (!_lang.isEmpty()) {
//            _undefinedLgNames.getGuiAliases().otherAliasGui(_undefinedLgNames.addon(_lang),_exec.getAliases());
//        } else {
//            _undefinedLgNames.getGuiAliases().otherAliasGui(_exec.getAliases(),new StringMap<String>());
//        }
        _options.setWarningShow(AnalysisMessages.build(_exec.getWarns(), _undefinedLgNames.getExecContent().getCustAliases().extractMessagesKeys()));
        return CustContextFactory.customer(_exec, _files, CustContextFactory.predefined(_options, _exec, _undefinedLgNames, _files, _mainArgs, new DefBuildLightResultContextNext(), null), new AdvContextGenerator(_currentElements.getThreadFactory().newAtomicBoolean()));
    }

}
