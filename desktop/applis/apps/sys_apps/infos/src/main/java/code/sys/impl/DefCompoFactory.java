package code.sys.impl;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;

import javax.swing.*;

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

    @Override
    public AbsPreparedLabel newPreparedLabel(AbstractImage _icon) {
        return new PreparedLabel(_icon);
    }

    @Override
    public AbsPreparedLabel newPreparedLabel(String _key) {
        return new PreparedLabel(UIManager.getIcon(_key));
    }

    @Override
    public AbsImgButton newImgButton(AbstractImage _imageIcon) {
        return new ImgButton(_imageIcon);
    }

    @Override
    public void invokeLater(Runnable _r) {
        SwingUtilities.invokeLater(_r);
    }
}
