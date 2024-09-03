package aiki.gui.components.walk;

import aiki.facade.*;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;
import code.util.core.IndexConstants;

public abstract class AbsItemsPanel {
    private final ScrollCustomGraphicList<String> liste;
    private final StringList items = new StringList();
    private final AbsPlainLabel amount;
    private final FacadeGame facade;
    private final AbsPanel container;
    protected AbsItemsPanel(WindowAiki _window, FacadeGame _facade, int _nb, String _titre, ScrollCustomGraphicList<String> _l) {
        AbsCompoFactory compoFactory_ = _window.getFrames().getCompoFactory();
        container = compoFactory_.newBorder();
        amount = compoFactory_.newPlainLabel("");
        facade = _facade;
        liste = _l;
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = _window.getFrames().getCompoFactory().newPlainLabel(_titre);
        container.add(titrePanneau_, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int side_ = facade.getMap().getSideLength();
        liste.getScrollPane().setPreferredSize(new MetaDimension(100,2*side_*_nb));
        initItems();
        container.add(liste.getScrollPane(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
        container.add(amount, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        container.setPreferredSize(new MetaDimension(100,2*side_*_nb+32));
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public AbsPlainLabel getAmount() {
        return amount;
    }

    public StringList getItems() {
        return items;
    }

    protected abstract void initItems();

    public boolean isSelected() {
        return liste.getSelectedIndex() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public String getSelectedItem() {
        return items.get(getSelectedIndex());
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

//    public void deselect() {
//        liste.deselectAll();
//    }

    public ScrollCustomGraphicList<String> getListe() {
        return liste;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
