package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import aiki.facade.FacadeGame;
import code.gui.AbsGraphicList;
import code.gui.GraphicList;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class TmPanel {

    private static final String SPACE = " ";

    private final AbsGraphicList<String> liste;

    private final StringList items = new StringList();

    private final TextLabel amount = new TextLabel("");

    private final FacadeGame facade;

    private final Panel container;
    public TmPanel(int _nb, String _titre, FacadeGame _facade, AbsGraphicList<String> _liste) {
        liste = _liste;
        facade = _facade;
        container = Panel.newBorder();
        container.setLoweredBorder();
        TextLabel titrePanneau_ = new TextLabel(_titre, SwingConstants.CENTER);
        container.add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setRender(new TmRenderer(facade));
        initItems();
        int side_ = facade.getMap().getSideLength();
        container.add(liste.self(),BorderLayout.CENTER);
        container.add(amount, BorderLayout.SOUTH);
        container.setPreferredSize(new Dimension(150,2*side_*_nb));
    }

    public void initItems() {
        liste.clear();
        items.clear();
        for (String i: facade.getChosenTmForBuy()) {
            liste.add(i);
            items.add(i);
        }
        amount.setText(StringUtil.concat(facade.amountTm().toNumberString(),SPACE,facade.getPlayer().getMoney().toNumberString()));
    }

    public boolean isSelected() {
        return liste.getSelectedIndex() != IndexConstants.INDEX_NOT_FOUND_ELT;
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

    public Panel getContainer() {
        return container;
    }
}
