package code.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public class GraphicCombo implements GraphicComboInt, Input {

    private GraphicStringList grList;

    private ListSelection listener;

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    private JLabel currentSelected = new JLabel();

    private JLabel pseudoButton = new JLabel();

    private JPopupMenu menu = new JPopupMenu();

    public GraphicCombo() {
        this(new StringList());
    }

    public GraphicCombo(StringList _list) {
        this(new GraphicStringList(true, true, _list));
    }

    public GraphicCombo(GraphicStringList _grList) {
        grList = _grList;
        grList.setListener(new ComboSelection(menu, this));
        menu.add(grList.getGlobal());
        menu.addPopupMenuListener(new PopupListener(_grList));
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
        currentSelected.setPreferredSize(new Dimension(128, s_));
        currentSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gr_.dispose();
        panel.add(currentSelected);
        panel.add(pseudoButton);
        panel.add(menu);
        if (!_grList.getList().isEmpty()) {
            selectItem(0);
            update();
        }
    }

    public GraphicStringList getGrList() {
        return grList;
    }

    @Override
    public String getSelectedItem() {
        if (grList.getSelectedIndexes().isEmpty()) {
            return null;
        }
        int index_ = grList.getSelectedIndexes().first();
        return grList.getList().get(index_);
    }

    @Override
    public void addItem(String _object) {
        if (grList.getList().isEmpty()) {
            int w_ = currentSelected.getPreferredSize().width;
            Font font_ = panel.getFont();
            FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
            int s_ = fontMetrics_.getHeight() + 2;
            w_ = Math.max(w_, fontMetrics_.stringWidth(_object));
            BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
            Graphics2D gr_ = img_.createGraphics();
            gr_.setColor(Color.WHITE);
            gr_.fillRect(0, 0, w_, s_);
            gr_.setColor(Color.BLACK);
            gr_.drawString(_object, 0, s_ - 1);
            currentSelected.setIcon(new ImageIcon(img_));
        }
        grList.add(_object);
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
        // TODO Auto-generated method stub

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
            Font font_ = panel.getFont();
            FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
            int s_ = fontMetrics_.getHeight() + 2;
            if (!grList.getList().isEmpty()) {
                String object_ = grList.getList().first();
                int w_ = currentSelected.getPreferredSize().width;
                BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
                Graphics2D gr_ = img_.createGraphics();
                gr_.setColor(Color.WHITE);
                gr_.fillRect(0, 0, w_, s_);
                gr_.setColor(Color.BLACK);
                gr_.drawString(object_, 0, s_ - 1);
                currentSelected.setIcon(new ImageIcon(img_));
            } else {
                currentSelected.setPreferredSize(new Dimension(128, s_));
            }
        }
    }

    @Override
    public int getSelectedIndex() {
        if (grList.getSelectedIndexes().isEmpty()) {
            return -1;
        }
        return grList.getSelectedIndexes().first();
    }

    @Override
    public void selectItem(int _index) {
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
        int w_ = currentSelected.getPreferredSize().width;
        Font font_ = panel.getFont();
        FontMetrics fontMetrics_ = panel.getFontMetrics(font_);
        int s_ = fontMetrics_.getHeight() + 2;
        w_ = Math.max(w_, fontMetrics_.stringWidth(selected_));
        BufferedImage img_ = new BufferedImage(w_, s_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setColor(Color.WHITE);
        gr_.fillRect(0, 0, w_, s_);
        gr_.setColor(Color.BLACK);
        gr_.drawString(selected_, 0, s_ - 1);
        currentSelected.setIcon(new ImageIcon(img_));
    }

    @Override
    public JPanel getGlobal() {
        return getPanel();
    }

    @Override
    public Numbers<Integer> getSelectedIndexes() {
        return grList.getSelectedIndexes();
    }

    @Override
    public StringList getSelectedValues() {
        if (grList.getSelectedIndexes().isEmpty()) {
            return new StringList();
        }
        return new StringList(getSelectedItem());
    }

}
