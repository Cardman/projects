package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class FormInputDebugLines {
    private final CustList<EditValueRow> commentsRows;
    private final AbsButton add;
    private final AbsButton rem;
//    private final AbsPlainButton val;
    private final AbsScrollPane scrollPaneGl;
//    private final CustList<StringStruct> output = new CustList<StringStruct>();
    public FormInputDebugLines(AbsCommonFrame _par, AbstractProgramInfos _api) {
        commentsRows = new CustList<EditValueRow>();
        AbsPanel dels_ = _api.getCompoFactory().newPageBox();
        AbsPanel all_ = _api.getCompoFactory().newPageBox();
        all_.add(_api.getCompoFactory().newAbsScrollPane(dels_));
        add = _api.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddValueRow(commentsRows,dels_, _api, _par));
        all_.add(add);
        rem = _api.getCompoFactory().newPlainButton("-");
        rem.addActionListener(new RemoveValueRow(commentsRows,dels_, _par));
        all_.add(rem);
//        val = factories_.getCompoFactory().newPlainButton("OK");
//        val.addActionListener(new ValidateValues(output,commentsRows));
//        all_.add(val);
        scrollPaneGl = _api.getCompoFactory().newAbsScrollPane(all_);
    }

    public AbsScrollPane getScrollPaneGl() {
        return scrollPaneGl;
    }

    public AbsButton getAdd() {
        return add;
    }

//    public CustList<StringStruct> getOutput() {
//        return output;
//    }

    public AbsButton getRem() {
        return rem;
    }

    public CustList<EditValueRow> getCommentsRows() {
        return commentsRows;
    }

//    public AbsPlainButton getVal() {
//        return val;
//    }
}
