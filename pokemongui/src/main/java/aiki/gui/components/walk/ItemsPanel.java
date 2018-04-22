package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import aiki.facade.FacadeGame;
import code.gui.GraphicList;
import code.gui.Panel;
import code.util.CustList;
import code.util.StringList;

public class ItemsPanel extends Panel {

    private static final String SPACE = " ";

    private GraphicList<String> liste;

    private StringList items = new StringList();

    private JLabel amount = new JLabel();

    private FacadeGame facade;

    public ItemsPanel(int _nb, String _titre, FacadeGame _facade) {
        liste = new GraphicList<String>(false, true);
        facade = _facade;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel titrePanneau_ = new JLabel(_titre, SwingConstants.CENTER);
        add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setRender(new ItemRenderer(facade));
        initItems();
        int side_ = facade.getMap().getSideLength();
        add(liste.getComponent(),BorderLayout.CENTER);
        add(amount, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(100,2*side_*_nb));
    }

    public void initItems() {
        int index_ = getSelectedIndex();
        liste.clear();
        items.clear();
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            liste.add(i);
            items.add(i);
        }
        amount.setText(StringList.concat(facade.amount().toNumberString(),SPACE,facade.getPlayer().getMoney().toNumberString()));
        if (index_ != CustList.INDEX_NOT_FOUND_ELT) {
            liste.setSelectedIndice(index_);
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
