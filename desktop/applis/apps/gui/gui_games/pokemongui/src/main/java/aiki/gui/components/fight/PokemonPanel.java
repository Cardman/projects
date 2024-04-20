package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.EvolutionChoiceMap;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.PokemonSelection;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.Ints;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class PokemonPanel {

    private final AbsPlainLabel title;

    private final PokemonDataRenderer renderer;

    private final ScrollCustomGraphicList<String> liste;

    private final FacadeGame facade;

    private final AbsPanel container;
    public PokemonPanel(WindowAiki _parent, int _nb, String _titre, FacadeGame _facade, String _noEvo) {
        renderer = new PokemonDataRenderer(_parent.getFrames().getImageFactory(),_facade, _noEvo);
        liste = AikiFactory.str(_parent.getCompoFactory(), _parent.getImageFactory(),renderer, new PkNonModalEvent(_parent.getModal()));
        facade = _facade;
        container = _parent.getFrames().getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _parent.getFrames().getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int s_ = _facade.getData().getMap().getSideLength();
        liste.getScrollPane().setPreferredSize(new MetaDimension(100,s_*(_nb+1)));
        initEvos();
        container.add(liste.getScrollPane(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new MetaDimension(100,s_*(_nb+1)+16));
    }

    public void setNoEvoMessage(String _message) {
        renderer.setNoEvo(_message);
    }
    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initEvos() {
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
        //liste.validate();
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

    public AbsPanel getContainer() {
        return container;
    }
}
