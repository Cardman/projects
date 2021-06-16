package code.sys.impl;

import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import code.gui.DefMutableTreeNode;
import code.gui.TreeGui;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.LoadLanguageUtil;

import javax.swing.tree.TreePath;

public final class DefCompoFactory implements AbsCompoFactory {
    @Override
    public AbsTreeGui newTreeGui(AbstractMutableTreeNode _node) {
        return new TreeGui(_node);
    }

    @Override
    public AbstractMutableTreeNode newMutableTreeNode(String _name) {
        return new DefMutableTreeNode(_name);
    }

    @Override
    public AbstractMutableTreeNode newMutableTreeNode(TreePath _cp) {
        return new DefMutableTreeNode(LoadLanguageUtil.selected(_cp));
    }
}
