package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.AbsPanel;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.StringList;

public final class AddCommentRow implements AbsActionListener {
    private final CustList<EditCommentRow> allComments;
    private final AbsPanel cont;
    private final WindowCdmEditor windowCdmEditor;

    public AddCommentRow(CustList<EditCommentRow> _all, AbsPanel _container, WindowCdmEditor _window) {
        this.allComments = _all;
        this.cont = _container;
        this.windowCdmEditor = _window;
    }

    @Override
    public void action() {
        EditCommentRow r_ = new EditCommentRow(windowCdmEditor.getCommonFrame().getFrames(), new CommentDelimiters("", new StringList("")), allComments.size());
        allComments.add(r_);
        cont.add(r_.getLine());
        GuiBaseUtil.recalculate(windowCdmEditor.getDialogComments().getPane());
    }
}
