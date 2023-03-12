package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class OutputDialogComments implements WithFrame {
    private final CustList<CommentDelimiters> comments;
    private final CustList<EditCommentRow> commentsRows;
    private final AbsPlainButton add;
    private final AbsPlainButton rem;
    private final AbsPlainButton val;
    private final AbsPanel dels;
    private final AbsScrollPane scrollPane;
    private final AbsCommonFrame frame;
    private final AbsMenuItem associated;
    public OutputDialogComments(WindowWithTreeImpl _w,AbsCommonFrame _fr, AbsMenuItem _c) {
        frame = _fr;
        associated = _c;
        comments = new CustList<CommentDelimiters>(_w.getComments());
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        commentsRows = new CustList<EditCommentRow>();
        dels = factories_.getCompoFactory().newPageBox();
        lines(factories_);
        scrollPane = factories_.getCompoFactory().newAbsScrollPane(dels);
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(scrollPane);
        add = factories_.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddCommentRow(commentsRows,dels,_w,frame));
        all_.add(add);
        rem = factories_.getCompoFactory().newPlainButton("-");
        rem.addActionListener(new RemoveCommentRow(commentsRows,dels, frame));
        all_.add(rem);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateComments(this,commentsRows, _w));
        all_.add(val);
        frame.setContentPane(all_);
        frame.pack();
        frame.setVisible(true);
        associated.setEnabled(false);
    }
    public void reinit(WindowWithTreeImpl _w) {
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        comments.clear();
        comments.addAllElts(_w.getComments());
        dels.removeAll();
        commentsRows.clear();
        lines(factories_);
        GuiBaseUtil.recalculate(scrollPane);
        frame.setVisible(true);
        associated.setEnabled(false);
    }

    private void lines(AbstractProgramInfos _facts) {
        int len_ = comments.size();
        for (int i = 0; i < len_; i++) {
            EditCommentRow ed_ = new EditCommentRow(_facts, comments.get(i), i);
            commentsRows.add(ed_);
            dels.add(ed_.getLine());
        }
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    @Override
    public AbsMenuItem getMenu() {
        return associated;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public AbsPlainButton getAdd() {
        return add;
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
