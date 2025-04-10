package code.mock;

import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsEnabledAction;
import code.gui.images.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class MockCompoFactory implements AbsCompoFactory {
    private final CustList<Runnable> later = new CustList<Runnable>();

    @Override
    public AbsTreePaths emptyList() {
        return new MockTreePaths(new CustList<AbsTreePath>());
    }

    @Override
    public AbsTreeGui newTreeGui(AbstractMutableTreeNodeCore<String> _node) {
        return newTreeGui(_node,1);
    }

    @Override
    public AbsTreeGui newTreeGui(AbstractMutableTreeNodeCore<String> _node, int _select) {
        return new MockTreeGui(_node);
    }

    @Override
    public AbstractMutableTreeNodeCore<String> newMutableTreeNode(String _name) {
        return new MockMutableTreeNode(_name);
    }

    @Override
    public AbsPaintableLabel newAbsPaintableLabel() {
        return new MockPaintableLabelAbs();
    }

    @Override
    public AbsPreparedLabel newPreparedLabel(AbstractImage _icon) {
        return new MockPreparedLabel(_icon);
    }

    @Override
    public AbsButton newImgButton(AbstractImage _imageIcon) {
        _imageIcon.getWidth();
        return new MockPlainButton();
    }

    @Override
    public void invokeLater(Runnable _runnable) {
        getLater().add(_runnable);
    }

    @Override
    public void invokeNow(Runnable _r) {
        _r.run();
    }

    public void invoke() {
        for (Runnable r: new CustList<Runnable>(getLater())) {
            r.run();
        }
    }

    public CustList<Runnable> getLater() {
        return later;
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
    public AbsGridConstraints newGridCts() {
        return new MockGridConstraints();
    }

    @Override
    public AbsPanel newGrid() {
        return new MockPanel(MockLayout.GRID_BAG);
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
    public AbsPanel newLineBoxLeaf() {
        return new MockPanel(MockLayout.LEAF);
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
    public AbsScrollPane newAbsScrollPaneLeaf(AbsCustComponent _center) {
        return new MockScrollPane(true, _center);
    }

    @Override
    public AbsScrollPane newAbsScrollPane(AbsCustComponent _center) {
        return new MockScrollPane(_center);
    }

    @Override
    public AbsTabbedPane newAbsTabbedPane() {
        return new MockTabbedPane();
    }

    @Override
    public AbsSplitPane newVerticalSplitPane() {
        return new MockSplitPane(false);
    }

    @Override
    public AbsSplitPane newVerticalSplitPaneLeft(AbsCustComponent _left) {
        MockSplitPane sp_ = new MockSplitPane(false);
        sp_.setLeftComponent(_left);
        return sp_;
    }

    @Override
    public AbsSplitPane newVerticalSplitPaneRight(AbsCustComponent _right) {
        MockSplitPane sp_ = new MockSplitPane(false);
        sp_.setRightComponent(_right);
        return sp_;
    }

    @Override
    public AbsSplitPane newVerticalSplitPane(AbsCustComponent _left, AbsCustComponent _right) {
        return new MockSplitPane(false,_left,_right);
    }

    @Override
    public AbsSplitPane newHorizontalSplitPane() {
        return new MockSplitPane(true);
    }

    @Override
    public AbsSplitPane newHorizontalSplitPaneLeft(AbsCustComponent _left) {
        MockSplitPane sp_ = new MockSplitPane(true);
        sp_.setLeftComponent(_left);
        return sp_;
    }

    @Override
    public AbsSplitPane newHorizontalSplitPaneRight(AbsCustComponent _right) {
        MockSplitPane sp_ = new MockSplitPane(true);
        sp_.setRightComponent(_right);
        return sp_;
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
    public AbsSpinner newSpinner(long _a, long _b, long _c, long _d) {
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
    public AbsButton newPlainButton() {
        return new MockPlainButton();
    }

    @Override
    public AbsButton newPlainButton(String _t) {
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
    public EnabledMenu newMenu() {
        return new MockMenu();
    }

    @Override
    public EnabledMenu newMenu(String _value) {
        return new MockMenu(_value);
    }

    @Override
    public EnabledMenu newCheckBoxMenuItem() {
        return new MockCheckBoxMenuItem();
    }

    @Override
    public EnabledMenu newCheckBoxMenuItem(String _value) {
        return new MockCheckBoxMenuItem(_value);
    }

    @Override
    public EnabledMenu newMenuItem() {
        return new MockMenuItem();
    }

    @Override
    public EnabledMenu newMenuItem(String _value) {
        return new MockMenuItem(_value);
    }

    @Override
    public AbsSeparator newSep() {
        return new MockSeparator();
    }

    @Override
    public int stringWidth(MetaFont _font, String _string) {
        return MockCustComponent.strWidth(_font, _string);
    }

    @Override
    public int heightFont(MetaFont _font) {
        return MockCustComponent.heightFontStr(_font);
    }

    @Override
    public AbsEnabledAction wrap(AbsAdvActionListener _actionListener) {
        return new MockAdvAbstractAction(_actionListener);
    }

    @Override
    public AbsEnabledAction wrap(AbsActionListener _actionListener) {
        return new MockAbstractAction(_actionListener);
    }

    @Override
    public AbsEnabledAction wrap(AbsActionListenerAct _act, AbsActionListener _actionListener) {
        return wrap(_actionListener);
    }

    @Override
    public AbsTextPane newTextPane() {
        return new MockTextPane();
    }

    @Override
    public AbsAttrSet newAttrSet() {
        return new MockAttrSet();
    }

    @Override
    public AbsTabStop newAbsTabStop(int _v) {
        return new MockTabStop(_v);
    }

    @Override
    public AbsTabStops newAbsTabStops(int _v) {
        return new MockTabStops(_v);
    }
}
