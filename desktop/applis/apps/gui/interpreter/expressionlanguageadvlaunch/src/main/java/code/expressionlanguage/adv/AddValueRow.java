package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class AddValueRow implements AbsActionListener {
    private final CustList<EditValueRow> allComments;
    private final AbsPanel cont;
    private final AbstractProgramInfos windowCdmEditor;
    private final AbsCommonFrame frame;

    public AddValueRow(CustList<EditValueRow> _all, AbsPanel _container, AbstractProgramInfos _window, AbsCommonFrame _fr) {
        this.allComments = _all;
        this.cont = _container;
        this.windowCdmEditor = _window;
        frame = _fr;
    }

    @Override
    public void action() {
        EditValueRow r_ = new EditValueRow(windowCdmEditor, AbsEditorTabList.EMPTY_STRING, allComments.size());
        allComments.add(r_);
        cont.add(r_.getLine());
        frame.pack();
//        GuiBaseUtil.recalculateWindow(frame);
    }
}
