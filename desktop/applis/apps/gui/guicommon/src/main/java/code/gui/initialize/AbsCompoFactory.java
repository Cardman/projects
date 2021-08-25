package code.gui.initialize;

import code.gui.AbsMetaLabel;
import code.gui.AbsPaintableLabel;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;

public interface AbsCompoFactory {
    AbsTreeGui newTreeGui(AbstractMutableTreeNode _node);

    AbstractMutableTreeNode newMutableTreeNode(String _name);

    AbsPaintableLabel newAbsPaintableLabel(AbsMetaLabel _absMetaLabel);
}
