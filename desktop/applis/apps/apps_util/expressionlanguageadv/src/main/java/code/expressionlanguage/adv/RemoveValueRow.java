package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class RemoveValueRow implements AbsActionListener {
    private final CustList<EditValueRow> allComments;
    private final AbsPanel cont;
    private final AbsCommonFrame frame;

    public RemoveValueRow(CustList<EditValueRow> _all, AbsPanel _container, AbsCommonFrame _fr) {
        this.allComments = _all;
        this.cont = _container;
        frame = _fr;
    }

    @Override
    public void action() {
        int index_ = 0;
        while (index_ < allComments.size()) {
            EditValueRow e_ = allComments.get(index_);
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
        GuiBaseUtil.recalculate(frame.getPane());
    }
}
