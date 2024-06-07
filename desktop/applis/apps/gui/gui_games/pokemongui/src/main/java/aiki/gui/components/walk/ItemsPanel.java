package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.maths.LgInt;
import code.util.EntryCust;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ItemsPanel extends AbsItemsPanel {

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

//    private final ScrollCustomGraphicList<String> liste;

//    private final StringList items = new StringList();

//    private final AbsPlainLabel amount;

//    private final FacadeGame facade;

//    private final AbsPanel container;
//    private final ItemRenderer renderer;
//    private final AbsCompoFactory compoFactory;
    private final int maxVisible;

    public ItemsPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        super(_window,_facade,_nb,_titre,AikiFactory.str(_window.getCompoFactory(), _window.getImageFactory(),  new ItemRenderer(_window.getFrames().getImageFactory(), _window.getFrames().getCompoFactory(), _facade, _window.getTileRender()), new PkNonModalEvent(_window.getModal())));
//        compoFactory = _window.getFrames().getCompoFactory();
        maxVisible = _nb;
//        renderer = new ItemRenderer(_window.getFrames().getImageFactory(), compoFactory, _facade, _window.getTileRender());
//        liste = AikiFactory.str(_window.getCompoFactory(), _window.getImageFactory(), renderer, new PkNonModalEvent(_window.getModal()));
//        facade = _facade;
//        container = compoFactory.newBorder();
//        amount = compoFactory.newPlainLabel("");
//        container.setLoweredBorder();
//        AbsPlainLabel titrePanneau_ = compoFactory.newPlainLabel(_titre);
//        titrePanneau_.setToolTipText(_titre);
//        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
//        liste.getElements().setFont(titrePanneau_.getMetaFont());
//        maxVisible = _nb;
//        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
//        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
////        int side_ = facade.getMap().getSideLength();
////        liste.getScrollPane().setPreferredSize(new MetaDimension(100,2*side_*_nb));
//        initItems();
//        container.add(liste.getScrollPane(),GuiConstants.BORDER_LAYOUT_CENTER);
//        container.add(amount, GuiConstants.BORDER_LAYOUT_SOUTH);
//        container.setPreferredSize(new MetaDimension(100,2*side_*_nb+32));
    }

    public void initItems() {
        MetaFont metaFont_ = getListe().getElements().getMetaFont();
        int widthName_ = widthName(getFacade(), getListe().getCompoFactory(), metaFont_);
        int widthFreq_ = widthFreq(getFacade(), getListe().getCompoFactory(), metaFont_);
        int side_ = getFacade().getMap().getSideLength();
        int width_ = side_+widthName_+widthFreq_+ DefTileRender.widthLgMax(getListe().getCompoFactory(),metaFont_);
        int h_ = NumberUtil.max(side_, metaFont_.getRealSize() + 2);
        getListe().getScrollPane().setPreferredSize(new MetaDimension(width_,h_*maxVisible));
//        renderer.setMaxWordWidth(widthName_);
//        renderer.setMaxNbWidth(widthFreq_);
        int index_ = getSelectedIndex();
        getListe().clear();
        getItems().clear();
        for (String i: getFacade().getChosenItemsForBuyOrSell().getKeys()) {
            getListe().add(i);
            getItems().add(i);
        }
        getAmount().setText(StringUtil.concat(getFacade().amount().toNumberString(),SPACE, getFacade().getPlayer().getMoney().toNumberString()));
        if (index_ != IndexConstants.INDEX_NOT_FOUND_ELT) {
            getListe().select(index_);
        }
        getListe().computeDimensions();
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
//    public boolean isSelected() {
//        return liste.getSelectedIndex() != IndexConstants.INDEX_NOT_FOUND_ELT;
//    }
//
//    public String getSelectedItem() {
//        return items.get(getSelectedIndex());
//    }
//
//    public int getSelectedIndex() {
//        return liste.getSelectedIndex();
//    }

//    public void deselect() {
//        liste.deselectAll();
//    }

//    public ScrollCustomGraphicList<String> getListe() {
//        return liste;
//    }
//
//    public AbsPanel getContainer() {
//        return container;
//    }
}
