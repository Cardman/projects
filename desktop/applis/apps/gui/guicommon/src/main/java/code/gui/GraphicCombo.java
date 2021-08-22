package code.gui;

import java.awt.*;

import javax.swing.JComponent;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.*;
import code.util.Ints;
import code.util.core.NumberUtil;

public final class GraphicCombo extends CustComponent implements GraphicComboGrIntBase  {

    private final GraphicStringList grList;

    private ListSelection listener;

    private final Panel panel = Panel.newLineBox();

    private final PreparedLabel currentSelected;

    private final PreparedLabel pseudoButton;

    private final PopupMenu menu = new PopupMenu();

    private int selectedIndex = -1;

    private boolean enabled = true;
    private AbstractImageFactory fact;

    public GraphicCombo(AbstractImageFactory _fact, GraphicStringList _grList, int _selectedIndex) {
        fact = _fact;
        grList = _grList;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        Font font_ = panel.getFont();
        int s_ = panel.heightFont() + 2;
        AbstractImage img_ = _fact.newImageRgb(s_, s_);
        img_.setColor(Color.WHITE);
        img_.fillRect(0, 0, s_, s_);
        img_.setColor(Color.BLACK);
        img_.fillPolygon(NumberUtil.wrapIntArray(s_/4,s_*3/4,s_/2), NumberUtil.wrapIntArray(s_/4,s_/4,s_*3/4), 3);
        pseudoButton = new PreparedLabel(fact,img_);
        pseudoButton.setIcon(fact,img_);
        pseudoButton.addMouseListener(new Popup(this));
        int w_ = 5;
        AbstractImage img2_ = _fact.newImageRgb(w_, s_);
        img2_.setFont(font_);
        img2_.setColor(Color.WHITE);
        img2_.fillRect(0, 0, w_, s_);
        currentSelected = new PreparedLabel(fact,img2_);
        currentSelected.setLineBorder(Color.BLACK);
        img_.dispose();
        panel.add(currentSelected);
        panel.add(pseudoButton);
        panel.add(menu);
        if (!_grList.getList().isEmpty()) {
            selectItem(_selectedIndex);
        } else {
            setNoSelected();
        }
    }

    public GraphicStringList getGrList() {
        return grList;
    }

    public String getSelectedItem() {
        CustList<String> list_ = grList.getList();
        if (!list_.isValidIndex(selectedIndex)) {
            return null;
        }
        return list_.get(selectedIndex);
    }

    @Override
    public void addItem(String _object) {
        grList.add(_object);
        if (grList.getList().size() == 1) {
            selectedIndex = 0;
            int w_ = grList.getMaxWidth();
            int s_ = panel.heightFont() + 2;
            AbstractImage img_ = fact.newImageRgb(w_, s_);
//            CustGraphics gr_ = new CustGraphics(img_.createGraphics());
            img_.setFont(currentSelected.getFont());
            img_.setColor(Color.WHITE);
            img_.fillRect(0, 0, w_, s_);
            img_.setColor(Color.BLACK);
            img_.drawString(_object, 0, s_ - 1);
            currentSelected.setIcon(fact,img_);
        }
    }

    @Override
    public int getItemCount() {
        return grList.getList().size();
    }

    @Override
    public void simpleRemoveAllItems() {
        getGrList().clearRevalidate();
        setSelectedIndex(-1);
        setNoSelected();
    }

    @Override
    public void removeAllItems() {
        int len_ = grList.getList().size();
        for (int i = len_ - 1; i > -1; i--) {
            removeItem(i);
        }
    }

    public ListSelection getListener() {
        return listener;
    }

    @Override
    public void setListener(ListSelection _listener) {
        listener = _listener;
    }

    @Override
    public ListSelection[] getListeners() {
        if (listener == null) {
            return new ListSelection[0];
        }
        return new ListSelection[]{listener};
    }

    @Override
    public void addListener(ListSelection _listener) {
        if (_listener == null) {
            return;
        }
        listener = _listener;
    }

    @Override
    public void removeListener(ListSelection _listener) {
        if (listener != _listener) {
            return;
        }
        listener = null;
    }

    public Panel getPanel() {
        return panel;
    }

    @Override
    public void popup() {
        if (!enabled) {
            return;
        }
        int len_ = grList.getListComponents().size();
        for (int i = 0; i < len_; i++) {
            grList.repaintSelect(i,false);
        }
        menu.show(panel, 0, pseudoButton.getHeight());
    }
    @Override
    public void simpleRemoveItem(int _index) {
        removeItem(_index);
    }
    @Override
    public void removeItem(int _index) {
        if (!getGrList().getList().isValidIndex(_index)) {
            return;
        }
        int oldIndex_ = getSelectedIndex();
        grList.remove(_index);
        if (oldIndex_ == _index) {
            if (!grList.getList().isEmpty()) {
                updateLoc();
            } else {
                selectedIndex = -1;
                setNoSelected();
            }
        }
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
        int old_ = selectedIndex;
        simpleSelectItem(_index);
        CustComponent.invokeLater(new SelectionComboEvent(_index, _index, this, getListener(), old_));
    }
    public void simpleSelectItem(int _index) {
        if (_index < 0) {
            return;
        }
        selectedIndex = _index;
        grList.setFirstIndex(_index);
        grList.setLastIndex(_index);
        grList.addRange();
    }

    public void update() {
        updateLoc();
    }

    private void updateLoc() {
        String selected_ = getSelectedItem();
        if (selected_ == null) {
            return;
        }
        int w_ = grList.getMaxWidth();
        int s_ = panel.heightFont() + 2;
        AbstractImage img_ = fact.newImageRgb(w_, s_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(currentSelected.getFont());
        img_.setColor(Color.WHITE);
        img_.fillRect(0, 0, w_, s_);
        img_.setColor(Color.BLACK);
        img_.drawString(selected_, 0, s_ - 1);
        currentSelected.setIcon(fact,img_);
    }

    public void setNoSelected() {
        int s_ = panel.heightFont() + 2;
        int w_ = 5;
        AbstractImage img_ = fact.newImageRgb(w_, s_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(currentSelected.getFont());
        img_.setColor(Color.WHITE);
        img_.fillRect(0, 0, w_, s_);
        currentSelected.setIcon(fact,img_);
    }

    public CustComponent getCurrentSelected() {
        return currentSelected;
    }

    @Override
    public CustComponent getGlobal() {
        return getPanel();
    }

    @Override
    public Ints getSelectedIndexes() {
        if (selectedIndex == -1) {
            return new Ints();
        }
        return new Ints(selectedIndex);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    @Override
    protected JComponent getComponent() {
        return getPanel().getComponent();
    }

    @Override
    public CustComponent self() {
        return this;
    }
}
