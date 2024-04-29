package aiki.gui.components.walk;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.MetaDimension;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class TmPanel {

    private static final String SPACE = " ";

    private final ScrollCustomGraphicList<String> liste;

    private final StringList items = new StringList();

    private final AbsPlainLabel amount;

    private final FacadeGame facade;

    private final AbsPanel container;

    public TmPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        liste = AikiFactory.str(_window.getCompoFactory(), _window.getImageFactory(),new TmRenderer(_window.getFrames().getImageFactory(),_facade), new PkNonModalEvent(_window.getModal()));
        facade = _facade;
        amount = _window.getFrames().getCompoFactory().newPlainLabel("");
        container = _window.getFrames().getCompoFactory().newBorder();
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = _window.getFrames().getCompoFactory().newPlainLabel(_titre);
        titrePanneau_.setToolTipText(_titre);
        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        liste.getElements().setFont(titrePanneau_.getMetaFont());
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int side_ = facade.getMap().getSideLength();
        liste.getScrollPane().setPreferredSize(new MetaDimension(150,2*side_*_nb));
        initItems();
        container.add(liste.getScrollPane(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.add(amount, GuiConstants.BORDER_LAYOUT_SOUTH);
        container.setPreferredSize(new MetaDimension(150,2*side_*_nb+32));
    }

    public void initItems() {
        liste.clear();
        items.clear();
        for (String i: facade.getChosenTmForBuy()) {
            liste.add(i);
            items.add(i);
        }
        amount.setText(StringUtil.concat(facade.amountTm().toNumberString(),SPACE,facade.getPlayer().getMoney().toNumberString()));
        liste.computeDimensions();
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
        liste.deselectAll();
    }

    public ScrollCustomGraphicList<String> getListe() {
        return liste;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
