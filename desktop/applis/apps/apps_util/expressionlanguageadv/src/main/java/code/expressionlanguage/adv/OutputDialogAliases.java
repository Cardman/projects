package code.expressionlanguage.adv;

import code.expressionlanguage.guicompos.GuiAliases;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.LgNamesUtilsContent;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;

public final class OutputDialogAliases {
    private final OutputDialogMapMessagesEdit keyWords;
    private final OutputDialogMapMessagesEdit aliases;
    private final AbsPlainButton check;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    private final AbsTextArea errors;
    private final GuiAliases guiAliases;
    private final CustAliases custAliases;
    private final LgNamesContent lgNamesContent;

    public OutputDialogAliases(WindowCdmEditor _w) {
        keyWords = new OutputDialogMapMessagesEdit(_w,_w.getLgKeyWords(), keyWords(_w));
        guiAliases = new GuiAliases();
        custAliases = new CustAliases();
        lgNamesContent = new LgNamesContent();
        aliases = new OutputDialogMapMessagesEdit(_w,_w.getLgAliases(), aliases(_w));
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        AbsTabbedPane tab_ = factories_.getCompoFactory().newAbsTabbedPane();
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
        val.addActionListener(new ValidateAliases(aliases.getMessagesRows(), keyWords.getMessagesRows(), _w));
        buttons_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogAliases()));
        buttons_.add(cancel);
        all_.add(buttons_);
        _w.getDialogAliases().setContentPane(all_);
        _w.getDialogAliases().pack();
        _w.getDialogAliases().setVisible(true);
    }
    static CustList<String> keyWords(WindowCdmEditor _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getLanguageKey());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.KEYWORDS);
        return LgNamesUtilsContent.extractKeys(com_).values();
    }
    static StringList aliases(WindowCdmEditor _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getLanguageKey());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile types_ = app_.getMapping().getVal(FileInfos.TYPES);
        TranslationsFile typesGui_ = app_.getMapping().getVal(FileInfos.TYPES_GUI);
        StringList v_ = new StringList(LgNamesUtilsContent.extractKeys(types_).values());
        v_.addAllElts(LgNamesUtilsContent.extractKeys(typesGui_).values());
        return v_;
    }

    public CustAliases getCustAliases() {
        return custAliases;
    }

    public AbsTextArea getErrors() {
        return errors;
    }

    public AbsPlainButton getCheck() {
        return check;
    }

    public GuiAliases getGuiAliases() {
        return guiAliases;
    }

    public LgNamesContent getLgNamesContent() {
        return lgNamesContent;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }

    public OutputDialogMapMessagesEdit getAliases() {
        return aliases;
    }

    public OutputDialogMapMessagesEdit getKeyWords() {
        return keyWords;
    }


    public AbsPlainButton getVal() {
        return val;
    }

}
