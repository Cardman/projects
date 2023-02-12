package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;

public final class OutputDialogComments {
    private final WindowCdmEditor windowCdmEditor;
    private final CustList<CommentDelimiters> comments;
    private CustList<EditCommentRow> commentsRows = new CustList<EditCommentRow>();
    private final AbstractAtomicBoolean valid;
    private AbsPlainButton add;
    private AbsPlainButton rem;
    private AbsPlainButton val;
    private AbsPlainButton cancel;
    public OutputDialogComments(WindowCdmEditor _w) {
        windowCdmEditor = _w;
        AbstractProgramInfos factories_ = _w.getCommonFrame().getFrames();
        comments = new CustList<CommentDelimiters>(_w.getComments());
        valid = factories_.getThreadFactory().newAtomicBoolean();
    }
    public void update() {
        int len_ = comments.size();
        AbstractProgramInfos factories_ = windowCdmEditor.getCommonFrame().getFrames();
        commentsRows = new CustList<EditCommentRow>();
        AbsPanel dels_ = factories_.getCompoFactory().newPageBox();
        for (int i = 0; i < len_; i++) {
            EditCommentRow ed_ = new EditCommentRow(factories_,comments.get(i), i);
            commentsRows.add(ed_);
            dels_.add(ed_.getLine());
        }
        AbsScrollPane sc_ = factories_.getCompoFactory().newAbsScrollPane(dels_);
        AbsPanel all_ = factories_.getCompoFactory().newPageBox();
        all_.add(sc_);
        add = factories_.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddCommentRow(commentsRows,dels_,windowCdmEditor));
        all_.add(add);
        rem = factories_.getCompoFactory().newPlainButton("-");
        rem.addActionListener(new RemoveCommentRow(commentsRows,dels_,windowCdmEditor));
        all_.add(rem);
        val = factories_.getCompoFactory().newPlainButton("OK");
        val.addActionListener(new ValidateComments(this,commentsRows));
        all_.add(val);
        cancel = factories_.getCompoFactory().newPlainButton("KO");
        cancel.addActionListener(new CancelComments(windowCdmEditor));
        all_.add(cancel);
        windowCdmEditor.getDialogComments().setContentPane(all_);
        windowCdmEditor.getDialogComments().setVisible(true);
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public AbstractAtomicBoolean getValid() {
        return valid;
    }

    public WindowCdmEditor getWindowCdmEditor() {
        return windowCdmEditor;
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
