package code.gui;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import code.util.*;
import code.util.Ints;
import code.util.StringList;

public class GraphicCombo extends CustComponent implements WithPopup,GraphicComboInt, Input  {

    private GraphicStringList grList;

    private ListSelection listener;

    private Panel panel = Panel.newLineBox();

    private PreparedLabel currentSelected = new PreparedLabel();

    private PreparedLabel pseudoButton = new PreparedLabel();

    private PopupMenu menu = new PopupMenu();

    private int selectedIndex = -1;

    private boolean enabled = true;

    public GraphicCombo() {
        this(new StringList());
    }

    public GraphicCombo(StringList _list) {
        this(_list,0);
    }

    public GraphicCombo(StringList _list, int _selectedIndex) {
        this(new GraphicStringList(true, true, _list),_selectedIndex);
    }

    public GraphicCombo(GraphicStringList _grList, int _selectedIndex) {
        grList = _grList;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        BufferedImage img_ = new BufferedImage(s_, s_, BufferedImage.TYPE_INT_RGB);
        Graphics gr_ = img_.createGraphics();
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, s_, s_);
        gr_.setColor(Color.BLACK);
        gr_.fillPolygon(Numbers.wrapIntArray(s_/4,s_*3/4,s_/2), Numbers.wrapIntArray(s_/4,s_/4,s_*3/4), 3);
        pseudoButton.setIcon(new ImageIcon(img_));
        pseudoButton.addMouseListener(new Popup(this));
        currentSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
            Graphics gr_ = img_.createGraphics();
            gr_.setFont(currentSelected.getFont());
            gr_.setColor(Color.WHITE);
            gr_.fillRect(0, 0, w_, s_);
            gr_.setColor(Color.BLACK);
            gr_.drawString(_object, 0, s_ - 1);
            currentSelected.setIcon(new ImageIcon(img_));
        }
    }

    @Override
    public int getItemCount() {
        return grList.getList().size();
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

    @Override
    public int getMaxWidth() {
        return grList.getMaxWidth();
    }

    @Override
    public Panel getPanel() {
        return panel;
    }

    @Override
    public void popup() {
        if (!enabled) {
            return;
        }
        menu.show(panel, 0, pseudoButton.getHeight());
    }

    @Override
    public void removeItem(int _index) {
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
        selectedIndex = _index;
        grList.setFirstIndex(_index);
        grList.setLastIndex(_index);
        grList.addRange();
        CustComponent.invokeLater(new SelectionComboEvent(_index, _index, this));
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
        Graphics gr_ = img_.createGraphics();
        gr_.setFont(currentSelected.getFont());
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        gr_.setColor(Color.BLACK);
        gr_.drawString(selected_, 0, s_ - 1);
        currentSelected.setIcon(new ImageIcon(img_));
    }

    public void setNoSelected() {
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        int w_ = 5;
        BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
        Graphics gr_ = img_.createGraphics();
        gr_.setFont(currentSelected.getFont());
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        currentSelected.setIcon(new ImageIcon(img_));
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
    public JComponent getComponent() {
        return getPanel().getComponent();
    }

}
