package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilimpl.LgNamesUtilsContent;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringMap;

public final class OutputDialogMessages {
    private final OutputDialogMapMessagesEdit messages;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;

    public OutputDialogMessages(WindowCdmEditor _w) {
        messages = new OutputDialogMapMessagesEdit(_w,_w.getLgMessages(), keys(_w));
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(messages.getScroll());
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateMessages(messages.getMessagesRows(), _w));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogAliases()));
        all_.add(cancel);
        _w.getDialogAliases().setContentPane(all_);
        _w.getDialogAliases().pack();
        _w.getDialogAliases().setVisible(true);
    }

    static CustList<String> keys(WindowCdmEditor _w) {
        TranslationsLg lg_ = CustAliases.lg(_w.getCommonFrame().getFrames().getTranslations(), _w.getUsedLg(), _w.getCommonFrame().getLanguageKey());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.MESSAGES);
        return LgNamesUtilsContent.extractKeys(com_).values();
    }

    static StringMap<String> initRows(StringMap<String> _infos, CustList<String> _keys) {
        StringMap<String> messagesRows_ = new StringMap<String>();
        for (String k:_keys){
            int index_ = _infos.indexOfEntry(k);
            if (index_ < 0) {
                messagesRows_.addEntry(k,"");
            } else {
                messagesRows_.addEntry(k,_infos.getValue(index_));
            }
        }
        return messagesRows_;
    }
    public AbsPlainButton getCancel() {
        return cancel;
    }

    public StringMap<String> getMessagesRows() {
        return messages.getMessagesRows();
    }

    public AbsPlainButton getVal() {
        return val;
    }

    public AbsTextField getKey() {
        return messages.getKey();
    }

    public AbsTextArea getValue() {
        return messages.getValue();
    }

    public AbsPlainButton getValPart() {
        return messages.getValPart();
    }

    public AutoCompleteDocument getAuto() {
        return messages.getAuto();
    }
}
