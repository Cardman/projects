package code.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public class GraphicCombo extends CustComponent implements GraphicComboInt, Input  {

    private GraphicStringList grList;

    private ListSelection listener;

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    private JLabel currentSelected = new JLabel();

    private JLabel pseudoButton = new JLabel();

    private JPopupMenu menu = new JPopupMenu();

    private int selectedIndex = -1;

    public GraphicCombo() {
        this(new StringList());
    }

    public GraphicCombo(StringList _list) {
        this(_list,0);
    }

    public GraphicCombo(StringList _list, int _selectedIndex) {
        this(new GraphicStringList(true, true, _list),_selectedIndex);
    }

    public GraphicCombo(GraphicStringList _grList) {
        this(_grList,0);
    }

    public GraphicCombo(GraphicStringList _grList, int _selectedIndex) {
        grList = _grList;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        BufferedImage img_ = new BufferedImage(s_, s_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
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

    @Override
    public String getSelectedItem() {
        if (selectedIndex == -1) {
            return null;
        }
        return grList.getList().get(selectedIndex);
    }

    @Override
    public void addItem(String _object) {
        grList.add(_object);
        if (grList.getList().size() == 1) {
            int w_ = grList.getMaxWidth();
            Font font_ = panel.getFont();
            FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
            int s_ = fontMetrics_.getHeight() + 2;
            BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
            Graphics2D gr_ = img_.createGraphics();
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
    public CustList<String> getList() {
        return grList.getList();
    }

    @Override
    public String getItem(int _index) {
        return grList.getList().get(_index);
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
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void popup() {
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

    @Override
    public void selectItem(int _index) {
        selectedIndex = _index;
        grList.setFirstIndex(_index);
        grList.setLastIndex(_index);
        grList.addRange();
        SwingUtilities.invokeLater(new SelectionComboEvent(_index, _index, this));
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
        Graphics2D gr_ = img_.createGraphics();
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        gr_.setColor(Color.BLACK);
        gr_.drawString(selected_, 0, s_ - 1);
        currentSelected.setIcon(new ImageIcon(img_));
    }

    void setNoSelected() {
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        int w_ = 5;
        BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        currentSelected.setIcon(new ImageIcon(img_));
    }

    @Override
    public JPanel getGlobal() {
        return getPanel();
    }

    @Override
    public Numbers<Integer> getSelectedIndexes() {
        if (selectedIndex == -1) {
            return new Numbers<Integer>();
        }
        return new Numbers<Integer>(selectedIndex);
    }

    @Override
    public StringList getSelectedValues() {
        if (selectedIndex == -1) {
            return new StringList();
        }
        return new StringList(getSelectedItem());
    }

    @Override
    public JComponent getComponent() {
        return getPanel();
    }

}
