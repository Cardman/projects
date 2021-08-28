package aiki.gui.components.fight;

import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.gui.listeners.PokemonSelection;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.TreeMap;
import code.util.core.IndexConstants;

public class PokemonPanel {

    private final AbsPlainLabel title;

    private final PokemonDataRenderer renderer;

    private final AbsGraphicList<String> liste;

    private final FacadeGame facade;

    private final String noEvo;

    private final AbsPanel container;
    public PokemonPanel(AbstractProgramInfos _fact, int _nb, String _titre, FacadeGame _facade, String _noEvo, AbsGraphicList<String> _liste) {
        liste = _liste;
        facade = _facade;
        container = _fact.getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _fact.getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        noEvo = _noEvo;
        renderer = new PokemonDataRenderer(_fact.getImageFactory(),facade, noEvo);
        liste.setRender(renderer);
        initEvos();
        container.add(liste.self(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new Dimension(100,32*(_nb+1)));
    }

    public void setNoEvoMessage(String _message) {
        renderer.setNoEvo(_message);
    }
    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initEvos() {
        liste.clear();
        TreeMap<String,Boolean> map_ = facade.getEvolutions();
        for (String b: map_.getKeys()) {
            liste.add(b);
        }
//        liste.setListData(map_.getKeys().toArray(new String[CustList.SIZE_EMPTY]));
        int index_ = IndexConstants.FIRST_INDEX;
        for (String b: map_.getKeys()) {
            if (map_.getVal(b)) {
                liste.setSelectedIndice(index_);
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
