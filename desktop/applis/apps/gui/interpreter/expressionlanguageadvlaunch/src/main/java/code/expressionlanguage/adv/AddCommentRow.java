package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.util.CustList;
import code.util.StringList;

public final class AddCommentRow implements AbsActionListener {
    private final CustList<EditCommentRow> allComments;
    private final AbsPanel cont;
    private final WindowWithTreeImpl windowCdmEditor;
    private final AbsCommonFrame frame;

    public AddCommentRow(CustList<EditCommentRow> _all, AbsPanel _container, WindowWithTreeImpl _window, AbsCommonFrame _fr) {
        this.allComments = _all;
        this.cont = _container;
        this.windowCdmEditor = _window;
        frame = _fr;
    }

    @Override
    public void action() {
        EditCommentRow r_ = new EditCommentRow(windowCdmEditor.getCommonFrame().getFrames(), new CommentDelimiters(AbsEditorTabList.EMPTY_STRING, new StringList(AbsEditorTabList.EMPTY_STRING)), allComments.size());
        allComments.add(r_);
        cont.add(r_.getLine());
        GuiBaseUtil.recalculateWindow(frame);
    }
}
