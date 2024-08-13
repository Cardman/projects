package code.expressionlanguage.adv;

import code.gui.AbsShortListTree;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeUtil;
import code.util.CustList;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final AbsDebuggerGui window;
    private final CustList<RenderPointPair> list;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;

    public DbgSelectNodeEvent(AbsDebuggerGui _win, CustList<RenderPointPair> _renderList, AbsTreeGui _t, DbgRootStruct _r) {
        this.window = _win;
        this.list = _renderList;
        this.tree = _t;
        this.root = _r;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        AbstractMutableTreeNodeCore<DbgAbsNodeStruct> e_ = root.getNode().simular(_node);
        if (e_ == null) {
            return;
        }
        DbgAbsNodeStruct i_ = e_.info();
        process(window,i_, tree, list);
    }

    static void process(AbsDebuggerGui _window, DbgAbsNodeStruct _i, AbsTreeGui _tree, CustList<RenderPointPair> _ls) {
        if (_i.select()){
            AbstractMutableTreeNodeCore<String> sel_ = _tree.selectEvt();
            MutableTreeNodeUtil.reload(_tree);
            render(_window, _i, _tree, _ls, sel_);
            return;
        }
        _window.currentThreadActions(null);
    }

    static void render(AbsDebuggerGui _window, DbgAbsNodeStruct _i, AbsTreeGui _tree, CustList<RenderPointPair> _ls, AbstractMutableTreeNodeCore<String> _sel) {
        _window.currentThreadActions(new DbgRenderStrNodeTask(RenderPointPair.stopExc(_ls, _i), _tree, _sel, _i, _window.getFrames()));
    }

}
