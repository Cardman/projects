package code.expressionlanguage.adv;

import code.gui.AbsScrollPane;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.events.AbsActionListener;

public final class DbgSelectNodeLogEvent implements AbsActionListener {
    private final DbgRootStruct root;
    private final AbsTreeGui tree;
    private final AbsScrollPane scrollPane;

    public DbgSelectNodeLogEvent(DbgRootStruct _r, AbsTreeGui _t, AbsScrollPane _scroll) {
        this.root = _r;
        this.tree = _t;
        this.scrollPane = _scroll;
    }

    @Override
    public void action() {
        AbstractMutableTreeNodeCore<String> sel_ = tree.selectEvt();
        AbstractMutableTreeNodeCore<DbgAbsNodeStruct> e_ = root.getNode().simular(sel_);
        if (e_ == null) {
            return;
        }
        DbgAbsNodeStruct i_ = e_.info();
        scrollPane.setViewportView(i_.panel());
    }

}
