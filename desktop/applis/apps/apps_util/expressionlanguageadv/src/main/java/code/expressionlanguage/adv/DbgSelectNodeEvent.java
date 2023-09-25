package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final CustList<RenderPointPair> list;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;
    private final AbsCompoFactory compoFactory;
    private final AbstractThreadFactory threadFactory;

    public DbgSelectNodeEvent(CustList<RenderPointPair> _renderList, AbsTreeGui _t, DbgRootStruct _r, AbsCompoFactory _compo, AbstractThreadFactory _th) {
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
        process(i_, tree, compoFactory, threadFactory, list);
    }

    static void process(DbgAbsNodeStruct _i, AbsTreeGui _tree, AbsCompoFactory _compo, AbstractThreadFactory _thFact, CustList<RenderPointPair> _ls) {
        if (_i.select()){
            AbstractMutableTreeNodeCore<String> sel_ = _tree.selectEvt();
            for (DbgAbsNodeStruct m: _i.getChildren()) {
                sel_.add(_compo.newMutableTreeNode(TreeNodeRenderUtil.format(m)));
            }
            MutableTreeNodeUtil.reload(_tree);
            render(_i, _tree, _compo, _thFact, _ls, sel_);
        }
    }

    static AbstractThread render(DbgAbsNodeStruct _i, AbsTreeGui _tree, AbsCompoFactory _compo, AbstractThreadFactory _thFact, CustList<RenderPointPair> _ls, AbstractMutableTreeNodeCore<String> _sel) {
        return _thFact.newStartedThread(new DbgRenderStrNodeTask(RenderPointPair.stopExc(_ls, _i), _tree, _sel, _i, _compo, _thFact));
    }

}
