package code.gui.initialize;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;

import javax.swing.tree.TreePath;

public interface AbsCompoFactory {
    AbsTreeGui newTreeGui(AbstractMutableTreeNode _node);
    AbstractMutableTreeNode newMutableTreeNode(TreePath _cp);
    AbstractMutableTreeNode newMutableTreeNode(String _name);
}
