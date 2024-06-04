package code.expressionlanguage.adv;

import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;

public final class OutputDialogAliases implements WithFrame{
    private final OutputDialogMapMessagesEdit messages;
    private final OutputDialogMapMessagesEdit keyWords;
    private final OutputDialogMapMessagesEdit aliases;
    private final AbsButton check;
    private final AbsButton val;
    private final AbsTextArea errors;
    private final GuiAliases guiAliases;
    private final CustAliases custAliases;
    private final LgNamesContent lgNamesContent;

    private final AbsCommonFrame frame;
    private final EnabledMenu associated;

    public OutputDialogAliases(WindowWithTreeImpl _w,AbsCommonFrame _fr, EnabledMenu _c) {
        frame = _fr;
        associated = _c;
        messages = new OutputDialogMapMessagesEdit(_w,_w.getLgMessages(), keysMessages(_w));
        keyWords = new OutputDialogMapMessagesEdit(_w,_w.getLgKeyWords(), keyWords(_w));
        guiAliases = new GuiAliases();
        custAliases = new CustAliases();
        lgNamesContent = new LgNamesContent();
        aliases = new OutputDialogMapMessagesEdit(_w,_w.getLgAliases(), aliases(_w));
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsTabbedPane tab_ = factories_.getCompoFactory().newAbsTabbedPane();
        tab_.add("messages",messages.getScroll());
        tab_.add("key words",keyWords.getScroll());
        tab_.add("aliases",aliases.getScroll());
        all_.add(tab_);
        errors = factories_.getCompoFactory().newTextArea(32, 32);
        AbsScrollPane scErr_ = factories_.getCompoFactory().newAbsScrollPane(errors);
        scErr_.setPreferredSize(new MetaDimension(384,128));
        all_.add(scErr_);
        AbsPanel buttons_ = factories_.getCompoFactory().newLineBox();
        check = factories_.getCompoFactory().newPlainButton("X");
        check.addActionListener(new CheckAliases(this, _w,errors));
        buttons_.add(check);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateAliases(messages.getMessagesRows(),aliases.getMessagesRows(), keyWords.getMessagesRows(), _w));
        buttons_.add(val);
        all_.add(buttons_);
        frame.setContentPane(all_);
        frame.pack();
        frame.setVisible(true);
        associated.setEnabled(false);
    }
    public void reinit(WindowWithTreeImpl _w) {
        resetGui(_w);
        frame.setVisible(true);
        associated.setEnabled(false);
    }

    public void resetGui(WindowWithTreeImpl _w) {
        messages.reinit(_w);
        keyWords.reinit(_w);
        aliases.reinit(_w);
    }

    static CustList<String> keyWords(WindowWithTreeImpl _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getFrames().getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.KEYWORDS);
        return TranslationsFile.extractKeys(com_).values();
    }
    static StringList aliases(WindowWithTreeImpl _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getFrames().getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile types_ = app_.getMapping().getVal(FileInfos.TYPES);
        TranslationsFile typesGui_ = app_.getMapping().getVal(FileInfos.TYPES_GUI);
        StringList v_ = new StringList(TranslationsFile.extractKeys(types_).values());
        v_.addAllElts(TranslationsFile.extractKeys(typesGui_).values());
        return v_;
    }

    static CustList<String> keysMessages(WindowWithTreeImpl _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getFrames().getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.MESSAGES);
        return TranslationsFile.extractKeys(com_).values();
    }

    @Override
    public AbsCommonFrame getFrame() {
        return frame;
    }

    @Override
    public EnabledMenu getMenu() {
        return associated;
    }

    public CustAliases getCustAliases() {
        return custAliases;
    }

    public AbsTextArea getErrors() {
        return errors;
    }

    public AbsButton getCheck() {
        return check;
    }

    public GuiAliases getGuiAliases() {
        return guiAliases;
    }

    public LgNamesContent getLgNamesContent() {
        return lgNamesContent;
    }

    public OutputDialogMapMessagesEdit getMessages() {
        return messages;
    }

    public OutputDialogMapMessagesEdit getAliases() {
        return aliases;
    }

    public OutputDialogMapMessagesEdit getKeyWords() {
        return keyWords;
    }


    public AbsButton getVal() {
        return val;
    }

}
