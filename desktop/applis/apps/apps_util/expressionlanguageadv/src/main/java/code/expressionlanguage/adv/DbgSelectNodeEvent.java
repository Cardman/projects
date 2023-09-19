package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;

public final class DbgSelectNodeEvent implements AbsShortListTree {
    private final DbgRootStruct root;
    private final AbsTreeGui tree;
    private final AbsCompoFactory compoFactory;

    public DbgSelectNodeEvent(DbgRootStruct _r, AbsTreeGui _t, AbsCompoFactory _compo) {
        this.root = _r;
        this.tree = _t;
        this.compoFactory = _compo;
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
                sel_.add(compoFactory.newMutableTreeNode(TreeNodeRenderUtil.format(m, root.getResult())));
            }
            MutableTreeNodeUtil.reload(tree);
        }
    }

}
