package code.gui.initialize;

import code.gui.*;
import code.gui.images.AbstractImage;

public interface AbsCompoFactory {
    AbsTreeGui newTreeGui(AbstractMutableTreeNode _node);

    AbstractMutableTreeNode newMutableTreeNode(String _name);

    AbsPaintableLabel newAbsPaintableLabel(AbsMetaLabel _absMetaLabel);
    AbsPreparedLabel newPreparedLabel(AbstractImage _icon);
    AbsPreparedLabel newPreparedLabel(String _key);
    AbsImgButton newImgButton(AbstractImage _imageIcon);
    void invokeLater(Runnable _r);
    AbsPanel newAbsolute();
    AbsPanel newBorder();
    AbsPanel newGrid(int _row,int _col);
    AbsPanel newGrid(int _row,int _col, int _h, int _v);
    AbsPanel newPageBox();
    AbsPanel newLineBox();
}
