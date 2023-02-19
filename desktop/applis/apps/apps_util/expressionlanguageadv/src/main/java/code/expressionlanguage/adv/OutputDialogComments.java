package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class OutputDialogComments {
    private final CustList<CommentDelimiters> comments;
    private final CustList<EditCommentRow> commentsRows;
    private final AbsPlainButton add;
    private final AbsPlainButton rem;
    private final AbsPlainButton val;
    private final AbsPlainButton cancel;
    public OutputDialogComments(WindowCdmEditor _w) {
        comments = new CustList<CommentDelimiters>(_w.getComments());
        int len_ = comments.size();
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        commentsRows = new CustList<EditCommentRow>();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        for (int i = 0; i < len_; i++) {
            EditCommentRow ed_ = new EditCommentRow(factories_, comments.get(i), i);
            commentsRows.add(ed_);
            dels_.add(ed_.getLine());
        }
        AbsScrollPane sc_ = factories_.getCompoFactory().newAbsScrollPane(dels_);
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(sc_);
        add = factories_.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddCommentRow(commentsRows,dels_,_w));
        all_.add(add);
        rem = factories_.getCompoFactory().newPlainButton("-");
        rem.addActionListener(new RemoveCommentRow(commentsRows,dels_,_w));
        all_.add(rem);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateComments(this,commentsRows, _w));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelBasic(_w.getDialogComments()));
        all_.add(cancel);
        _w.getDialogComments().setContentPane(all_);
        _w.getDialogComments().pack();
        _w.getDialogComments().setVisible(true);
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public AbsPlainButton getAdd() {
        return add;
    }

    public AbsPlainButton getCancel() {
        return cancel;
    }

    public AbsPlainButton getRem() {
        return rem;
    }

    public CustList<EditCommentRow> getCommentsRows() {
        return commentsRows;
    }

    public AbsPlainButton getVal() {
        return val;
    }
}
