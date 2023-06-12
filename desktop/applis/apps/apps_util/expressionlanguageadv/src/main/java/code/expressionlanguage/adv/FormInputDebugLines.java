package code.expressionlanguage.adv;

import code.expressionlanguage.structs.StringStruct;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class FormInputDebugLines {
    private final CustList<EditValueRow> commentsRows;
    private final AbsPlainButton add;
    private final AbsPlainButton rem;
    private final AbsPlainButton val;
    private final AbsScrollPane scrollPaneGl;
    private final CustList<StringStruct> output = new CustList<StringStruct>();
    public FormInputDebugLines(AbsCommonFrame _par) {
        AbstractProgramInfos factories_ = _par.getFrames();
        commentsRows = new CustList<EditValueRow>();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(factories_.getCompoFactory().newAbsScrollPane(dels_));
        add = factories_.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddValueRow(commentsRows,dels_,factories_, _par));
        all_.add(add);
        rem = factories_.getCompoFactory().newPlainButton("-");
        rem.addActionListener(new RemoveValueRow(commentsRows,dels_, _par));
        all_.add(rem);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateValues(output,commentsRows));
        all_.add(val);
        scrollPaneGl = factories_.getCompoFactory().newAbsScrollPane(all_);
    }

    public AbsScrollPane getScrollPaneGl() {
        return scrollPaneGl;
    }

    public AbsPlainButton getAdd() {
        return add;
    }

    public CustList<StringStruct> getOutput() {
        return output;
    }

    public AbsPlainButton getRem() {
        return rem;
    }

    public CustList<EditValueRow> getCommentsRows() {
        return commentsRows;
    }

    public AbsPlainButton getVal() {
        return val;
    }
}
