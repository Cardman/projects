package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class RemoveCommentRow implements AbsActionListener {
    private final CustList<EditCommentRow> allComments;
    private final AbsPanel cont;
    private final WindowCdmEditor windowCdmEditor;

    public RemoveCommentRow(CustList<EditCommentRow> _all, AbsPanel _container, WindowCdmEditor _window) {
        this.allComments = _all;
        this.cont = _container;
        this.windowCdmEditor = _window;
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
        windowCdmEditor.getDialogComments().pack();
    }
}
