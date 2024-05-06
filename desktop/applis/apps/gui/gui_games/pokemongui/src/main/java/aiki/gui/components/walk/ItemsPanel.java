package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.maths.LgInt;
import code.util.EntryCust;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ItemsPanel {

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

    private final ScrollCustomGraphicList<String> liste;

    private final StringList items = new StringList();

    private final AbsPlainLabel amount;

    private final FacadeGame facade;

    private final AbsPanel container;
    private final ItemRenderer renderer;
    private final AbsCompoFactory compoFactory;
    private final int maxVisible;

    public ItemsPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        compoFactory = _window.getFrames().getCompoFactory();
        renderer = new ItemRenderer(_window.getFrames().getImageFactory(), compoFactory, _facade, _window.getTileRender());
        liste = AikiFactory.str(_window.getCompoFactory(), _window.getImageFactory(), renderer, new PkNonModalEvent(_window.getModal()));
        facade = _facade;
        container = compoFactory.newBorder();
        amount = compoFactory.newPlainLabel("");
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = compoFactory.newPlainLabel(_titre);
        titrePanneau_.setToolTipText(_titre);
        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        liste.getElements().setFont(titrePanneau_.getMetaFont());
        maxVisible = _nb;
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
//        int side_ = facade.getMap().getSideLength();
//        liste.getScrollPane().setPreferredSize(new MetaDimension(100,2*side_*_nb));
        initItems();
        container.add(liste.getScrollPane(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.add(amount, GuiConstants.BORDER_LAYOUT_SOUTH);
//        container.setPreferredSize(new MetaDimension(100,2*side_*_nb+32));
    }

    public void initItems() {
        MetaFont metaFont_ = liste.getElements().getMetaFont();
        int widthName_ = widthName(facade, compoFactory, metaFont_);
        int widthFreq_ = widthFreq(facade, compoFactory, metaFont_);
        int side_ = facade.getMap().getSideLength();
        int width_ = side_+widthName_+widthFreq_+ DefTileRender.widthLgMax(compoFactory,metaFont_);
        int h_ = NumberUtil.max(side_, metaFont_.getRealSize() + 2);
        liste.getScrollPane().setPreferredSize(new MetaDimension(width_,h_*maxVisible));
        renderer.setMaxWordWidth(widthName_);
        renderer.setMaxNbWidth(widthFreq_);
        int index_ = getSelectedIndex();
        liste.clear();
        items.clear();
        for (String i: facade.getChosenItemsForBuyOrSell().getKeys()) {
            liste.add(i);
            items.add(i);
        }
        amount.setText(StringUtil.concat(facade.amount().toNumberString(),SPACE,facade.getPlayer().getMoney().toNumberString()));
        if (index_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
            liste.select(index_);
        }
        liste.computeDimensions();
    }

    public static int widthName(FacadeGame _facade, AbsCompoFactory _compo, MetaFont _lab) {
        int maxWordWidth_ = 0;
        for (EntryCust<String, LgInt> i: _facade.getChosenItemsForBuyOrSell().entryList()) {
            String disp_ = _facade.translateItem(i.getKey());
            int w_ = _compo.stringWidth(_lab,disp_+SPACES);
            maxWordWidth_ = NumberUtil.max(maxWordWidth_, w_);
        }
        return maxWordWidth_;
    }

    public static int widthFreq(FacadeGame _facade, AbsCompoFactory _compo, MetaFont _lab) {
        int maxWordWidth_ = 0;
        for (EntryCust<String, LgInt> i: _facade.getChosenItemsForBuyOrSell().entryList()) {
            String disp_ = i.getValue().toNumberString();
            int w_ = _compo.stringWidth(_lab,disp_+SPACES);
            maxWordWidth_ = NumberUtil.max(maxWordWidth_, w_);
        }
        return maxWordWidth_;
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
