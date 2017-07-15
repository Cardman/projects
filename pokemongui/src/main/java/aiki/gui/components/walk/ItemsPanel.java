package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import code.gui.StringJl;
import code.gui.StringListModel;
import code.util.CustList;
import code.util.StringList;
import aiki.facade.FacadeGame;

public class ItemsPanel extends JPanel {

    private static final String SPACE = " ";

    private StringListModel modeleListe = new StringListModel();

    private StringJl liste = new StringJl(modeleListe);

    private StringList items = new StringList();

    private JLabel amount = new JLabel();

    private FacadeGame facade;

    public ItemsPanel(int _nb, String _titre, FacadeGame _facade) {
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel titrePanneau_ = new JLabel(_titre, SwingConstants.CENTER);
        add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.setCellRenderer(new ItemRenderer(facade));
        initItems();
        int side_ = facade.getMap().getSideLength();
        add(new JScrollPane(liste),BorderLayout.CENTER);
        add(amount, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(100,2*side_*_nb));
    }

    public void initItems() {
        int index_ = getSelectedIndex();
        modeleListe.clear();
        items.clear();
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            modeleListe.addElement(i);
            items.add(i);
        }
        amount.setText(facade.amount().toString()+SPACE+facade.getPlayer().getMoney().toString());
        if (index_ != CustList.INDEX_NOT_FOUND_ELT) {
            liste.setSelectedIndex(index_);
        }
    }

    public boolean isSelected() {
        return liste.getSelectedIndex() != CustList.INDEX_NOT_FOUND_ELT;
    }

    public String getSelectedItem() {
        return items.get(getSelectedIndex());
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public void deselect() {
        liste.clearSelection();
    }
}
