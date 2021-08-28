package code.gui;

import javax.swing.JComponent;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.Ints;
import code.util.core.NumberUtil;

import java.awt.Color;

public final class GraphicCombo extends CustComponent implements AbsGraphicCombo {

    private final AbsGraphicStringList grList;

    private ListSelection listener;

    private final AbsPanel panel = Panel.newLineBox();

    private final AbsPreparedLabel currentSelected;

    private final AbsPreparedLabel pseudoButton;

    private final PopupMenu menu = new PopupMenu();

    private int selectedIndex = -1;

    private boolean enabled = true;
    private final AbstractImageFactory fact;
    private final AbsCompoFactory compoFactory;

    public GraphicCombo(AbstractImageFactory _fact,AbsCompoFactory _compoFactory, AbsGraphicStringList _grList, int _selectedIndex) {
        fact = _fact;
        grList = _grList;
        compoFactory = _compoFactory;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        int s_ = panel.heightFont() + 2;
        AbstractImage img_ = _fact.newImageRgb(s_, s_);
        img_.setColor(Color.WHITE);
        img_.fillRect(0, 0, s_, s_);
        img_.setColor(Color.BLACK);
        img_.fillPolygon(NumberUtil.wrapIntArray(s_/4,s_*3/4,s_/2), NumberUtil.wrapIntArray(s_/4,s_/4,s_*3/4), 3);
        pseudoButton = _compoFactory.newPreparedLabel(img_);
        pseudoButton.setIcon(getFact(),img_);
        pseudoButton.addMouseListener(new Popup(this));
        int w_ = 5;
        AbstractImage img2_ = _fact.newImageRgb(w_, s_);
        img2_.setFont(panel);
        img2_.setColor(Color.WHITE);
        img2_.fillRect(0, 0, w_, s_);
        currentSelected = _compoFactory.newPreparedLabel(img2_);
        currentSelected.setLineBorder(Color.BLACK);
        img_.dispose();
        panel.add(currentSelected);
        panel.add(pseudoButton);
        panel.add(menu);
        prSelect(_grList, _selectedIndex);
    }

    private void prSelect(AbsGraphicStringList _grList, int _selectedIndex) {
        FrameUtil.prSelect(_grList, _selectedIndex, this);
    }

    public String getSelectedItem() {
        return FrameUtil.selIt(this);
    }

    @Override
    public void addItem(String _object) {
        FrameUtil.addIt(_object, this);
    }

    @Override
    public int getItemCount() {
        return grList.getList().size();
    }

    @Override
    public void simpleRemoveAllItems() {
        grList.clearRevalidate();
        setSelectedIndex(-1);
        FrameUtil.setNoSelected(this);
    }

    @Override
    public void removeAllItems() {
        FrameUtil.remAllIts(this);
    }

    public ListSelection getListener() {
        return listener;
    }

    @Override
    public void simpleSetListener(ListSelection _listener) {
        setListener(_listener);
    }

    @Override
    public void setListener(ListSelection _listener) {
        listener = _listener;
    }

    @Override
    public ListSelection[] getListeners() {
        return FrameUtil.listeners(listener);
    }

    @Override
    public void addListener(ListSelection _listener) {
        FrameUtil.addList(_listener, this);
    }

    @Override
    public void removeListener(ListSelection _listener) {
        FrameUtil.removeList(_listener, this);
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public void popup() {
        FrameUtil.pop(this);
    }

    public void showMenu() {
        menu.show(panel, 0, pseudoButton.getHeight());
    }

    @Override
    public void simpleRemoveItem(int _index) {
        removeItem(_index);
    }
    @Override
    public void removeItem(int _index) {
        FrameUtil.removeIt(_index, this);
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int _selectedIndex) {
        selectedIndex = _selectedIndex;
    }

    @Override
    public void selectItem(int _index) {
        int old_ = getSelectedIndex();
        simpleSelectItem(_index);
        FrameUtil.invokeLater(new SelectionComboEvent(_index, _index, this, getListener(), old_), compoFactory);
    }
    public void simpleSelectItem(int _index) {
        FrameUtil.simpleSelect(_index, this);
    }

    public void update() {
        FrameUtil.updateLoc(this);
    }

    public AbsGraphicStringList getGrList() {
        return grList;
    }

    public AbstractImageFactory getFact() {
        return fact;
    }


    public AbsCustComponent getCurrentSelected() {
        return getLabel();
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    @Override
    public AbsPreparedLabel getLabel() {
        return currentSelected;
    }

    @Override
    public AbsCustComponent getGlobal() {
        return getPanel();
    }

    @Override
    public Ints getSelectedIndexes() {
        return FrameUtil.selectedIndexes(selectedIndex);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    @Override
    protected JComponent getNatComponent() {
        return ((CustComponent) getPanel()).getNatComponent();
    }

    @Override
    public AbsCustComponent self() {
        return this;
    }
}
