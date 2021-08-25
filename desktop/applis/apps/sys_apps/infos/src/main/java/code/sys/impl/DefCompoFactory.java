package code.sys.impl;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;

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
    public AbsPaintableLabel newAbsPaintableLabel(AbsMetaLabel _absMetaLabel) {
        return new PaintableLabel(_absMetaLabel);
    }
}
