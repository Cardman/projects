package code.gui.initialize;

import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

public interface AbsCompoFactory {
    AbsTreePaths emptyList();
    AbsTreeGui newTreeGui(AbstractMutableTreeNodeCore<String> _node);
    AbsTreeGui newTreeGui(AbstractMutableTreeNodeCore<String> _node, int _select);

    AbstractMutableTreeNodeCore<String> newMutableTreeNode(String _name);

    AbsPaintableLabel newAbsPaintableLabel();
    AbsPreparedLabel newPreparedLabel(AbstractImage _icon);
    AbsPreparedLabel newPreparedLabel(String _key);
    AbsButton newImgButton(AbstractImage _imageIcon);
    void invokeLater(Runnable _r);
    void invokeNow(Runnable _r);
    AbsPanel newAbsolute();
    AbsPanel newBorder();
    AbsGridConstraints newGridCts();
    AbsPanel newGrid();
    AbsPanel newGrid(int _row,int _col);
    AbsPanel newGrid(int _row,int _col, int _h, int _v);
    AbsPanel newPageBox();
    AbsPanel newLineBox();
    AbsPanel newLineBoxLeaf();
    AbsScrollPane newAbsScrollPane();
    AbsScrollPane newAbsScrollPane(AbsCustComponent _center);
    AbsScrollPane newAbsScrollPaneLeaf(AbsCustComponent _center);

    AbsTabbedPane newAbsTabbedPane();

    AbsSplitPane newVerticalSplitPane(AbsCustComponent _left, AbsCustComponent _right);
    AbsSplitPane newHorizontalSplitPane(AbsCustComponent _left, AbsCustComponent _right);

    AbsPopupMenu newAbsPopupMenu();
    AbsSlider newAbsSlider();
    AbsSlider newAbsSlider(int _o);
    AbsSlider newAbsSlider(int _min,int _max);
    AbsSlider newAbsSlider(int _min,int _max,int _v);
    AbsSlider newAbsSlider(int _o,int _min,int _max,int _v);

    AbsProgressBar newAbsProgressBar();

    AbsSpinner newSpinner(long _a, long _b, long _c, long _d);
    AbsTextArea newTextArea();
    AbsTextArea newTextArea(int _r,int _c);
    AbsTextArea newTextArea(String _txt);
    AbsTextArea newTextArea(String _txt,int _r,int _c);
    AbsTextField newTextField();
    AbsTextField newTextField(int _c);
    AbsTextField newTextField(String _txt);
    AbsTextField newTextField(String _txt,int _c);
    AbsCustCheckBox newCustCheckBox();
    AbsCustCheckBox newCustCheckBox(String _t);
    AbsCustCheckBox newCustCheckBox(String _t,boolean _s);
    AbsButton newPlainButton();
    AbsButton newPlainButton(String _t);

    AbsRadioButton newRadioButton();

    AbsRadioButton newRadioButton(String _value);

    AbsRadioButton newRadioButton(String _value, boolean _sel);
    AbsPlainLabel newPlainLabel(String _value);
    AbsTableGui newTableGui(String... _cols);
    AbsWrappedTextArea newWrappedTextArea(int _rows, int _columns);
    AbsMenuBar newMenuBar();

    EnabledMenu newMenu();

    EnabledMenu newMenu(String _value);

    EnabledMenu newCheckBoxMenuItem();

    EnabledMenu newCheckBoxMenuItem(String _value);

    EnabledMenu newMenuItem();

    EnabledMenu newMenuItem(String _value);
    AbsSeparator newSep();
    int stringWidth(MetaFont _font, String _string);
    int heightFont(MetaFont _font);
    AbsEnabledAction wrap(AbsAdvActionListener _actionListener);
    AbsEnabledAction wrap(AbsActionListenerAct _act, AbsActionListener _actionListener);
    AbsEnabledAction wrap(AbsActionListener _actionListener);
    AbsTextPane newTextPane();
    AbsAttrSet newAttrSet();
    AbsTabStop newAbsTabStop(int _v);
    AbsTabStops newAbsTabStops(int _v);
}
