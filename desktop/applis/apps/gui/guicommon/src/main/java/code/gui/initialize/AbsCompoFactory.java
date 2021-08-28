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
    AbsScrollPane newAbsScrollPane();
    AbsScrollPane newAbsScrollPane(AbsCustComponent _center);
    AbsScrollPane newAbsScrollPane(AbsMetaLabel _center);
    AbsTabbedPane newAbsTabbedPane();

    AbsSplitPane newAbsSplitPane(int _split, AbsCustComponent _left, AbsCustComponent _right);

    AbsPopupMenu newAbsPopupMenu();
    AbsSlider newAbsSlider();
    AbsSlider newAbsSlider(int _o);
    AbsSlider newAbsSlider(int _min,int _max);
    AbsSlider newAbsSlider(int _min,int _max,int _v);
    AbsSlider newAbsSlider(int _o,int _min,int _max,int _v);

    AbsProgressBar newAbsProgressBar();

    AbsSpinner newSpinner(int _a, int _b, int _c, int _d);
    AbsTextArea newTextArea();
    AbsTextArea newTextArea(int _r,int _c);
    AbsTextArea newTextArea(String _txt);
    AbsTextArea newTextArea(String _txt,int _r,int _c);
}
