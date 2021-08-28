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

    @Override
    public AbsPanel newAbsolute() {
        return Panel.newAbsolute();
    }

    @Override
    public AbsPanel newBorder() {
        return Panel.newBorder();
    }

    @Override
    public AbsPanel newGrid(int _row, int _col) {
        return Panel.newGrid(_row, _col);
    }

    @Override
    public AbsPanel newGrid(int _row, int _col, int _h, int _v) {
        return Panel.newGrid(_row, _col, _h, _v);
    }

    @Override
    public AbsPanel newLineBox() {
        return Panel.newLineBox();
    }

    @Override
    public AbsPanel newPageBox() {
        return Panel.newPageBox();
    }

    @Override
    public AbsScrollPane newAbsScrollPane() {
        return new ScrollPane();
    }

    @Override
    public AbsScrollPane newAbsScrollPane(AbsMetaLabel _center) {
        return new ScrollPane(_center);
    }

    @Override
    public AbsScrollPane newAbsScrollPane(AbsCustComponent _center) {
        return new ScrollPane(_center);
    }

    @Override
    public AbsTabbedPane newAbsTabbedPane() {
        return new TabbedPane();
    }

    @Override
    public AbsSplitPane newAbsSplitPane(int _split, AbsCustComponent _left, AbsCustComponent _right) {
        return new SplitPane(_split,_left,_right);
    }

    @Override
    public AbsPopupMenu newAbsPopupMenu() {
        return new PopupMenu();
    }

    @Override
    public AbsSlider newAbsSlider() {
        return new Slider();
    }

    @Override
    public AbsSlider newAbsSlider(int _o) {
        return new Slider(_o);
    }

    @Override
    public AbsSlider newAbsSlider(int _min, int _max) {
        return new Slider(_min, _max);
    }

    @Override
    public AbsSlider newAbsSlider(int _min, int _max, int _v) {
        return new Slider(_min, _max, _v);
    }

    @Override
    public AbsSlider newAbsSlider(int _o, int _min, int _max, int _v) {
        return new Slider(_o, _min, _max, _v);
    }

    @Override
    public AbsProgressBar newAbsProgressBar() {
        return new ProgressBar();
    }
}
