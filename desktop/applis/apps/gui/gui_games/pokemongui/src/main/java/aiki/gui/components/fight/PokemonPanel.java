package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.EvolutionChoiceMap;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.PokemonSelection;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.*;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.EntryCust;
import code.util.Ints;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class PokemonPanel {

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

    private final AbsPlainLabel title;

    private final PokemonDataRenderer renderer;

    private final ScrollCustomGraphicList<String> liste;

    private final FacadeGame facade;

    private final AbsPanel container;
    private final AbsCompoFactory compoFactory;
    private final int maxVisible;

    public PokemonPanel(WindowAiki _parent, int _nb, String _titre, FacadeGame _facade, String _noEvo) {
        renderer = new PokemonDataRenderer(_parent.getFrames().getImageFactory(),_facade, _noEvo,_parent.getTileRender());
        compoFactory = _parent.getCompoFactory();
        liste = AikiFactory.str(compoFactory, _parent.getImageFactory(),renderer, new PkNonModalEvent(_parent.getModal()));
        facade = _facade;
        container = _parent.getFrames().getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _parent.getFrames().getCompoFactory().newPlainLabel(_titre);
        title.setToolTipText(_titre);
        container.add(title, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        liste.getElements().setFont(title.getMetaFont());
        maxVisible = _nb;
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
//        int s_ = _facade.getData().getMap().getSideLength();
//        liste.getScrollPane().setPreferredSize(new MetaDimension(100,s_*(_nb+1)));
        initEvos();
        container.add(liste.getScrollPane(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        container.setPreferredSize(new MetaDimension(100,s_*(_nb+1)+16));
    }

    public void setNoEvoMessage(String _message) {
        renderer.setNoEvo(_message);
    }
    public void setPanelTitle(String _title) {
        title.setText(_title);
        title.setToolTipText(_title);
    }

    public void initEvos() {
        int s_ = facade.getData().getMap().getSideLength();
        MetaFont metaFont_ = liste.getElements().getMetaFont();
        int width_ = NumberUtil.max(widthName(compoFactory, metaFont_, facade), compoFactory.stringWidth(metaFont_, renderer.getNoEvo()+SPACES));
        int w_ = NumberUtil.max(100,s_+width_);
        int h_ = NumberUtil.max(s_, metaFont_.getRealSize() + 2);
        liste.getScrollPane().setPreferredSize(new MetaDimension(w_,(h_ + 2)*(maxVisible+1)));
        renderer.initWidth(width_);
        liste.clear();
        EvolutionChoiceMap map_ = facade.getEvolutions();
        for (String b: map_.getKeys()) {
            liste.add(b);
        }
//        liste.setListData(map_.getKeys().toArray(new String[CustList.SIZE_EMPTY]));
        int index_ = IndexConstants.FIRST_INDEX;
        for (String b: map_.getKeys()) {
            if (map_.getVal(b) == BoolVal.TRUE) {
                liste.select(index_);
            }
            index_++;
        }
        liste.computeDimensions();
        //liste.validate();
    }
    public static int widthName(AbsCompoFactory _facto, MetaFont _f, FacadeGame _facade) {
        EvolutionChoiceMap map_ = _facade.getEvolutions();
        int maxWidthName_ = 0;
        for (EntryCust<String, BoolVal> b: map_.entryList()) {
            maxWidthName_ = NumberUtil.max(maxWidthName_, _facto.stringWidth(_f, _facade.translatePokemon(b.getKey())+SPACES));
        }
        return maxWidthName_;
    }
    public void addListener(Battle _battle) {
        liste.setListener(new PokemonSelection(_battle));
    }

    public String getSelectedEvo() {
        Ints ind_ = liste.getSelectedIndexes();
        if (ind_.isEmpty()) {
            return null;
        }
        return liste.get(ind_.first());
    }

    public ScrollCustomGraphicList<String> getListe() {
        return liste;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
