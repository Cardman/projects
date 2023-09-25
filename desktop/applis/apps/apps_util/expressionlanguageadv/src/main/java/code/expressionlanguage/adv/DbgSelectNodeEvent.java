package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final AbsDebuggerGui window;
    private final CustList<RenderPointPair> list;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;
    private final AbsCompoFactory compoFactory;
    private final AbstractThreadFactory threadFactory;

    public DbgSelectNodeEvent(AbsDebuggerGui _win, CustList<RenderPointPair> _renderList, AbsTreeGui _t, DbgRootStruct _r, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        this.window = _win;
        this.list = _renderList;
        this.tree = _t;
        this.root = _r;
        this.compoFactory = _compo;
        this.threadFactory = _th;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        AbstractMutableTreeNodeCore<DbgAbsNodeStruct> e_ = root.getNode().simular(_node);
        if (e_ == null) {
            return;
        }
        DbgAbsNodeStruct i_ = e_.info();
        process(window,i_, tree, compoFactory, threadFactory, list);
    }

    static AbstractThread process(AbsDebuggerGui _window, DbgAbsNodeStruct _i, AbsTreeGui _tree, AbsCompoFactory _compo, AbstractThreadFactory _thFact, CustList<RenderPointPair> _ls) {
        if (_i.select()){
            AbstractMutableTreeNodeCore<String> sel_ = _tree.selectEvt();
            MutableTreeNodeUtil.reload(_tree);
            return render(_window,_i, _tree, _compo, _thFact, _ls, sel_);
        }
        _window.currentThreadActions(null);
        return _window.getCurrentThreadActions();
    }

    static AbstractThread render(AbsDebuggerGui _window, DbgAbsNodeStruct _i, AbsTreeGui _tree, AbsCompoFactory _compo, AbstractThreadFactory _thFact, CustList<RenderPointPair> _ls, AbstractMutableTreeNodeCore<String> _sel) {
        _window.currentThreadActions(new DbgRenderStrNodeTask(RenderPointPair.stopExc(_ls, _i), _tree, _sel, _i, _compo, _thFact));
        return _window.getCurrentThreadActions();
    }

}
