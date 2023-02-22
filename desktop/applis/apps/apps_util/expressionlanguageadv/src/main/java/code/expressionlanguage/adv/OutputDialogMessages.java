package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;

public final class OutputDialogMessages {
    private final StringMap<String> messagesRows;
    private final AbsTextField key;
    private final AbsTextArea value;
    private final AbsPlainButton val;
    private final AbsPlainButton valPart;
    private final AbsPlainButton cancel;
    private final AutoCompleteDocument auto;

    public OutputDialogMessages(WindowCdmEditor _w) {
        messagesRows = new StringMap<String>(_w.getLgMessages());
        for (String k:new AnalysisMessages().allMessages().getKeys()){
            if (!messagesRows.contains(k)) {
                messagesRows.put(k,"");
            }
        }
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        AbsPanel line_ = factories_.getCompoFactory().newLineBox();
        AbsTextField keyAuto_ = factories_.getCompoFactory().newTextField(32);
        value = factories_.getCompoFactory().newTextArea("",1,32);
        auto = new AutoCompleteDocument(keyAuto_, new StringList(new AnalysisMessages().allMessages().getKeys()), factories_, new FeedMessageValue(value, messagesRows));
        value.setLineBorder(GuiConstants.BLACK);
        line_.add(keyAuto_);
        key = keyAuto_;
        line_.add(value);
        dels_.add(line_);
        valPart = factories_.getCompoFactory().newPlainButton("MATCH");
        valPart.addActionListener(new ValidateMessage(keyAuto_,value, messagesRows));
        dels_.add(valPart);
        AbsScrollPane sc_ = factories_.getCompoFactory().newAbsScrollPane(dels_);
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(sc_);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateMessages(messagesRows, _w));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogAliases()));
        all_.add(cancel);
        _w.getDialogAliases().setContentPane(all_);
        _w.getDialogAliases().pack();
        _w.getDialogAliases().setVisible(true);
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }

    public StringMap<String> getMessagesRows() {
        return messagesRows;
    }

    public AbsPlainButton getVal() {
        return val;
    }

    public AbsTextField getKey() {
        return key;
    }

    public AbsTextArea getValue() {
        return value;
    }

    public AbsPlainButton getValPart() {
        return valPart;
    }

    public AutoCompleteDocument getAuto() {
        return auto;
    }
}
