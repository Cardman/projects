package code.gui;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import code.util.*;
import code.util.Ints;
import code.util.core.NumberUtil;

public final class GraphicCombo extends CustComponent implements GraphicComboGrInt  {

    private final GraphicStringList grList;

    private ListSelection listener;

    private final Panel panel = Panel.newLineBox();

    private final PreparedLabel currentSelected = new PreparedLabel();

    private final PreparedLabel pseudoButton = new PreparedLabel();

    private final PopupMenu menu = new PopupMenu();

    private int selectedIndex = -1;

    private boolean enabled = true;

    public GraphicCombo(GraphicStringList _grList, int _selectedIndex) {
        grList = _grList;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        BufferedImage img_ = new BufferedImage(s_, s_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, s_, s_);
        gr_.setColor(Color.BLACK);
        gr_.fillPolygon(NumberUtil.wrapIntArray(s_/4,s_*3/4,s_/2), NumberUtil.wrapIntArray(s_/4,s_/4,s_*3/4), 3);
        pseudoButton.setIcon(img_);
        pseudoButton.addMouseListener(new Popup(this));
        currentSelected.setLineBorder(Color.BLACK);
        gr_.dispose();
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
            Font font_ = panel.getFont();
            FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
            int s_ = fontMetrics_.getHeight() + 2;
            BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
            CustGraphics gr_ = new CustGraphics(img_.createGraphics());
            gr_.setFont(currentSelected.getFont());
            gr_.setColor(Color.WHITE);
            gr_.fillRect(0, 0, w_, s_);
            gr_.setColor(Color.BLACK);
            gr_.drawString(_object, 0, s_ - 1);
            currentSelected.setIcon(img_);
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

    @Override
    public ListSelection getListener() {
        return listener;
    }

    @Override
    public void setListener(ListSelection _listener) {
        listener = _listener;
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
                update();
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
        String selected_ = getSelectedItem();
        if (selected_ == null) {
            return;
        }
        int w_ = grList.getMaxWidth();
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(currentSelected.getFont());
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        gr_.setColor(Color.BLACK);
        gr_.drawString(selected_, 0, s_ - 1);
        currentSelected.setIcon(img_);
    }

    public void setNoSelected() {
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        int w_ = 5;
        BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(currentSelected.getFont());
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        currentSelected.setIcon(img_);
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
