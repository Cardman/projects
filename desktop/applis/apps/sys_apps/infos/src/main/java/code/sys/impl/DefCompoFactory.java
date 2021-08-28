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

    @Override
    public AbsSpinner newSpinner(int _a, int _b, int _c, int _d) {
        return new Spinner(_a,_b,_c,_d);
    }

    @Override
    public AbsTextArea newTextArea() {
        return new TextArea();
    }

    @Override
    public AbsTextArea newTextArea(int _r, int _c) {
        return new TextArea(_r, _c);
    }

    @Override
    public AbsTextArea newTextArea(String _txt) {
        return new TextArea(_txt);
    }

    @Override
    public AbsTextArea newTextArea(String _txt, int _r, int _c) {
        return new TextArea(_txt, _r, _c);
    }

    @Override
    public AbsTextField newTextField() {
        return new TextField();
    }

    @Override
    public AbsTextField newTextField(int _c) {
        return new TextField(_c);
    }

    @Override
    public AbsTextField newTextField(String _txt) {
        return new TextField(_txt);
    }

    @Override
    public AbsTextField newTextField(String _txt, int _c) {
        return new TextField(_txt,_c);
    }

    @Override
    public AbsCustCheckBox newCustCheckBox() {
        return new CustCheckBox();
    }

    @Override
    public AbsCustCheckBox newCustCheckBox(String _t) {
        return new CustCheckBox(_t);
    }

    @Override
    public AbsCustCheckBox newCustCheckBox(String _t, boolean _s) {
        return new CustCheckBox(_t, _s);
    }

    @Override
    public AbsPlainButton newPlainButton() {
        return new PlainButton();
    }

    @Override
    public AbsPlainButton newPlainButton(String _t) {
        return new PlainButton(_t);
    }

    @Override
    public AbsRadioButton newRadioButton() {
        return new RadioButton();
    }

    @Override
    public AbsRadioButton newRadioButton(String _value) {
        return new RadioButton(_value);
    }

    @Override
    public AbsRadioButton newRadioButton(String _value, boolean _sel) {
        return new RadioButton(_value,_sel);
    }

    @Override
    public AbsPlainLabel newPlainLabel(String _value) {
        return new PlainLabel(_value);
    }

    @Override
    public AbsTableGui newTableGui(String... _cols) {
        return new TableGui(_cols);
    }

    @Override
    public AbsWrappedTextArea newWrappedTextArea(int _rows, int _columns) {
        return new WrappedTextArea(_rows, _columns);
    }
}
