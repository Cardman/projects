package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.TreeGui;

import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public final class TreeStruct extends CustComponentStruct {
    private final TreeGui tree;
    protected TreeStruct(String _className, TreeNodeStruct _node) {
        super(_className);
        tree = new TreeGui(_node.getTreeNode());
    }

    public BooleanStruct isRootVisible() {
        return BooleanStruct.of(tree.isRootVisible());
    }

    public void setRootVisible(Struct _rootVisible) {
        tree.setRootVisible(BooleanStruct.isTrue(_rootVisible));
    }

    public Struct getLastSelectedPathComponent() {
        Object sel_ = tree.getLastSelectedPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            return new TreeNodeStruct((DefaultMutableTreeNode) sel_);
        }
        return NullStruct.NULL_VALUE;
    }
    public void select(Struct _node) {
        if (_node instanceof TreeNodeStruct) {
            tree.select(((TreeNodeStruct) _node).getTreeNode());
        }
    }

    public void addTreeSelectionListener(Struct _tsl) {
        if (_tsl instanceof TreeSelectionListener) {
            tree.addTreeSelectionListener((TreeSelectionListener) _tsl);
        }
    }

    public void reload() {
        tree.reload();
    }

    @Override
    protected CustComponent getComponent() {
        return tree;
    }
}
