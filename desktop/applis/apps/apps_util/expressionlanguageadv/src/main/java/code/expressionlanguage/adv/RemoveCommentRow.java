package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class RemoveCommentRow implements AbsActionListener {
    private final CustList<EditCommentRow> allComments;
    private final AbsPanel cont;
    private final AbsCommonFrame frame;

    public RemoveCommentRow(CustList<EditCommentRow> _all, AbsPanel _container, AbsCommonFrame _fr) {
        this.allComments = _all;
        this.cont = _container;
        frame = _fr;
    }

    @Override
    public void action() {
        int index_ = 0;
        while (index_ < allComments.size()) {
            EditCommentRow e_ = allComments.get(index_);
            if (e_.getSelectForDelete().isSelected()) {
                allComments.remove(index_);
                cont.remove(index_);
            } else {
                index_++;
            }
        }
        int len_ = allComments.size();
        for (int i = 0; i < len_; i++) {
            allComments.get(i).setIndex(i);
        }
        GuiBaseUtil.recalculateWindow(frame);
    }
}
