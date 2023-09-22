package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThreadFactory;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final AbsTreeGui tree;
    private final DbgRootStruct root;
    private final AbsCompoFactory compoFactory;
    private final AbstractThreadFactory threadFactory;

    public DbgSelectNodeEvent(AbsTreeGui _t, DbgRootStruct _r, AbsCompoFactory _compo, AbstractThreadFactory _th) {
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
        if (i_.select()){
            AbstractMutableTreeNodeCore<String> sel_ = tree.selectEvt();
            for (DbgAbsNodeStruct m: i_.getChildren()) {
                sel_.add(compoFactory.newMutableTreeNode(TreeNodeRenderUtil.format(m)));
            }
            MutableTreeNodeUtil.reload(tree);
            threadFactory.newStartedThread(new DbgRenderStrNodeTask(tree,sel_, i_, compoFactory,threadFactory));
        }
    }

}
