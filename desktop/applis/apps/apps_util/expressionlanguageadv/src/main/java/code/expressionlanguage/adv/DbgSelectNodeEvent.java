package code.expressionlanguage.adv;

import code.gui.*;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final DbgRootStruct root;
    private final AbsTreeGui tree;

    public DbgSelectNodeEvent(DbgRootStruct _r, AbsTreeGui _t) {
        this.root = _r;
        this.tree = _t;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        AbstractMutableTreeNodeCore e_ = MutableTreeNodeUtil.simular(root, (AbstractMutableTreeNode) _node);
        if (!(e_ instanceof DbgAbsNodeStruct)) {
            return;
        }
        if (((DbgAbsNodeStruct)e_).select()){
            AbstractMutableTreeNode sel_ = tree.selectEvt();
            for (DbgAbsNodeStruct m: ((DbgAbsNodeStruct) e_).getChildren()) {
                sel_.add(TreeNodeRenderUtil.format(root,m));
            }
            MutableTreeNodeUtil.reload(tree);
        }
    }
}
