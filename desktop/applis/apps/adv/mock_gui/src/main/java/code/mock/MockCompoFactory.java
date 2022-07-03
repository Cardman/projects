package code.mock;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class MockCompoFactory implements AbsCompoFactory {
    private final CustList<Runnable> later = new CustList<Runnable>();
    @Override
    public AbsTreeGui newTreeGui(AbstractMutableTreeNode _node) {
        return new MockTreeGui(_node);
    }

    @Override
    public AbstractMutableTreeNode newMutableTreeNode(String _name) {
        return new MockMutableTreeNode(_name);
    }

    @Override
    public AbsPaintableLabel newAbsPaintableLabel(AbsMetaLabelInt _absMetaLabel) {
        return new MockPaintableLabel(_absMetaLabel);
    }

    @Override
    public AbsPaintableLabel newAbsPaintableLabel() {
        return new MockPaintableLabelNo();
    }

    @Override
    public AbsPreparedLabel newPreparedLabel(AbstractImage _icon) {
        return new MockPreparedLabel(_icon);
    }

    @Override
    public AbsPreparedLabel newPreparedLabel(String _key) {
        return new MockPreparedLabel();
    }

    @Override
    public AbsImgButton newImgButton(AbstractImage _imageIcon) {
        _imageIcon.getWidth();
        return new MockImgButton();
    }

    @Override
    public void invokeLater(Runnable _runnable) {
        later.add(_runnable);
    }

    public void invoke() {
        for (Runnable r: later) {
            r.run();
        }
    }


    @Override
    public AbsPanel newAbsolute() {
        return new MockPanel(MockLayout.ABSOLUTE);
    }

    @Override
    public AbsPanel newBorder() {
        return new MockPanel(MockLayout.BORDER);
    }

    @Override
    public AbsPanel newGrid(int _row, int _col) {
        return new MockPanel(MockLayout.GRID);
    }

    @Override
    public AbsPanel newGrid(int _row, int _col, int _h, int _v) {
        return new MockPanel(MockLayout.GRID);
    }

    @Override
    public AbsPanel newPageBox() {
        return new MockPanel(MockLayout.PAGE);
    }

    @Override
    public AbsPanel newLineBox() {
        return new MockPanel(MockLayout.LINE);
    }

    @Override
    public AbsScrollPane newAbsScrollPane() {
        return new MockScrollPane();
    }

    @Override
    public AbsScrollPane newAbsScrollPane(AbsCustComponent _center) {
        return new MockScrollPane(_center);
    }

    @Override
    public AbsScrollPane newAbsScrollPane(AbsMetaLabelComInt _center) {
        return new MockScrollPane(_center.getPaintableLabel());
    }

    @Override
    public AbsTabbedPane newAbsTabbedPane() {
        return new MockTabbedPane();
    }

    @Override
    public AbsSplitPane newVerticalSplitPane(AbsCustComponent _left, AbsCustComponent _right) {
        return new MockSplitPane(false,_left,_right);
    }

    @Override
    public AbsSplitPane newHorizontalSplitPane(AbsCustComponent _left, AbsCustComponent _right) {
        return new MockSplitPane(true,_left,_right);
    }

    @Override
    public AbsPopupMenu newAbsPopupMenu() {
        return new MockPopupMenu();
    }

    @Override
    public AbsSlider newAbsSlider() {
        return new MockSlider();
    }

    @Override
    public AbsSlider newAbsSlider(int _o) {
        return new MockSlider(_o);
    }

    @Override
    public AbsSlider newAbsSlider(int _min, int _max) {
        return new MockSlider(_min, _max);
    }

    @Override
    public AbsSlider newAbsSlider(int _min, int _max, int _v) {
        return new MockSlider(_min, _max, _v);
    }

    @Override
    public AbsSlider newAbsSlider(int _o, int _min, int _max, int _v) {
        return new MockSlider(_o, _min, _max, _v);
    }

    @Override
    public AbsProgressBar newAbsProgressBar() {
        return new MockProgressBar();
    }

    @Override
    public AbsSpinner newSpinner(int _a, int _b, int _c, int _d) {
        return new MockSpinner(_a,_b,_c,_d);
    }

    @Override
    public AbsTextArea newTextArea() {
        return new MockTextArea();
    }

    @Override
    public AbsTextArea newTextArea(int _r, int _c) {
        return new MockTextArea(_r,_c);
    }

    @Override
    public AbsTextArea newTextArea(String _txt) {
        return new MockTextArea(_txt);
    }

    @Override
    public AbsTextArea newTextArea(String _txt, int _r, int _c) {
        return new MockTextArea(_txt,_r,_c);
    }

    @Override
    public AbsTextField newTextField() {
        return new MockTextField();
    }

    @Override
    public AbsTextField newTextField(int _c) {
        return new MockTextField(_c);
    }

    @Override
    public AbsTextField newTextField(String _txt) {
        return new MockTextField(_txt);
    }

    @Override
    public AbsTextField newTextField(String _txt, int _c) {
        return new MockTextField(_txt,_c);
    }

    @Override
    public AbsCustCheckBox newCustCheckBox() {
        return new MockCustCheckBox();
    }

    @Override
    public AbsCustCheckBox newCustCheckBox(String _t) {
        return new MockCustCheckBox(_t);
    }

    @Override
    public AbsCustCheckBox newCustCheckBox(String _t, boolean _s) {
        return new MockCustCheckBox(_t,_s);
    }

    @Override
    public AbsPlainButton newPlainButton() {
        return new MockPlainButton();
    }

    @Override
    public AbsPlainButton newPlainButton(String _t) {
        return new MockPlainButton(_t);
    }

    @Override
    public AbsRadioButton newRadioButton() {
        return new MockRadioButton();
    }

    @Override
    public AbsRadioButton newRadioButton(String _value) {
        return new MockRadioButton(_value);
    }

    @Override
    public AbsRadioButton newRadioButton(String _value, boolean _sel) {
        return new MockRadioButton(_value,_sel);
    }

    @Override
    public AbsPlainLabel newPlainLabel(String _value) {
        return new MockPlainLabel(_value);
    }

    @Override
    public AbsTableGui newTableGui(String... _fr) {
        return new MockTableGui(_fr);
    }

    @Override
    public AbsWrappedTextArea newWrappedTextArea(int _rows, int _columns) {
        return new MockWrappedTextArea(_rows,_columns);
    }

    @Override
    public AbsMenuBar newMenuBar() {
        return new MockMenuBar();
    }

    @Override
    public AbsMenu newMenu() {
        return new MockMenu();
    }

    @Override
    public AbsMenu newMenu(String _value) {
        return new MockMenu(_value);
    }

    @Override
    public AbsCheckBoxMenuItem newCheckBoxMenuItem() {
        return new MockCheckBoxMenuItem();
    }

    @Override
    public AbsCheckBoxMenuItem newCheckBoxMenuItem(String _value) {
        return new MockCheckBoxMenuItem(_value);
    }

    @Override
    public AbsMenuItem newMenuItem() {
        return new MockMenuItem();
    }

    @Override
    public AbsMenuItem newMenuItem(String _value) {
        return new MockMenuItem(_value);
    }
}
