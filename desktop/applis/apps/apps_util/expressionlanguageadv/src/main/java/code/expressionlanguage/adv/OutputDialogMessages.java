package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class OutputDialogMessages {
    private final CustList<EditMessageRow> messagesRows;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    public OutputDialogMessages(WindowCdmEditor _w) {
        messagesRows = new CustList<EditMessageRow>();
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        for (String k:new AnalysisMessages().allMessages().getKeys()){
            EditMessageRow ed_;
            int index_ = _w.getLgMessages().indexOfEntry(k);
            if (index_ > -1){
                ed_ = new EditMessageRow(factories_, k, _w.getLgMessages().getValue(index_));
            } else {
                ed_ = new EditMessageRow(factories_, k, "");
            }
            messagesRows.add(ed_);
            dels_.add(ed_.getLine());
        }
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

    public CustList<EditMessageRow> getMessagesRows() {
        return messagesRows;
    }

    public AbsPlainButton getVal() {
        return val;
    }
}
