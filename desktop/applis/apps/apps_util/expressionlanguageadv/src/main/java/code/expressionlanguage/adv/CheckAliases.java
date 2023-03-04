package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.gui.AbsTextArea;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class CheckAliases implements AbsActionListener {
    private final OutputDialogAliases dialog;
    private final WindowCdmEditor windowCdmEditor;
    private final AbsTextArea errors;

    public CheckAliases(OutputDialogAliases _dial, WindowCdmEditor _ed, AbsTextArea _errs) {
        dialog = _dial;
        windowCdmEditor = _ed;
        errors = _errs;
    }

    @Override
    public void action() {
        StringMap<String> messages_ = windowCdmEditor.getLgMessages();
        AnalysisMessages mess_ = new AnalysisMessages();
        KeyWords kwl_ = new KeyWords();
        ExecutingOptions ex_ = new ExecutingOptions(windowCdmEditor.getCommonFrame().getFrames().getThreadFactory().newAtomicBoolean());
        ex_.setMessages(messages_);
        ex_.setAliases(ValidateMessages.filterFields(dialog.getAliases().getMessagesRows()));
        ex_.setKeyWords(ValidateMessages.filterFields(dialog.getKeyWords().getMessagesRows()));
        ex_.setLg(windowCdmEditor.getUsedLg());
        dialog.getCustAliases().setTranslations(windowCdmEditor.getCommonFrame().getFrames().getTranslations());
        dialog.getCustAliases().setLanguage(windowCdmEditor.getCommonFrame().getLanguageKey());
        dialog.getCustAliases().setUserLg(windowCdmEditor.getUsedLg());
        CustContextFactory.preinitAliases(ex_,mess_,kwl_,dialog.getLgNamesContent() , dialog.getCustAliases(), dialog.getGuiAliases());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setMappingKeyWords(dialog.getCustAliases().extractKeywordsKeys());
        StringMap<String> m_ = dialog.getCustAliases().extractAliasesKeys();
        m_.addAllEntries(LgNamesGui.extractAliasesKeys(dialog.getCustAliases()));
        page_.setMappingAliases(m_);
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kwl_);
        page_.setStandards(dialog.getLgNamesContent());
        ListLoggableLgNames ls_ = new ListLoggableLgNames();
        page_.setLogErr(ls_);
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(dialog.getLgNamesContent(), dialog.getGuiAliases(), dialog.getCustAliases());
        page_.setFileBuilder(fileBuilder_);
        AnalysisMessages.validateMessageContents(mess_.allMessages(dialog.getCustAliases().extractMessagesKeys()), page_);
        ContextFactory.validate(kwl_,page_,fileBuilder_);
        errors.setText("");
        for (String e: ls_.getList()) {
            errors.append(e);
            errors.append("\n");
        }
    }

}
