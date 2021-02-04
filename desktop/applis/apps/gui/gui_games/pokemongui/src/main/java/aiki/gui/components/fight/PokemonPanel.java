package aiki.gui.components.fight;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import aiki.facade.FacadeGame;
import aiki.gui.listeners.PokemonSelection;
import code.gui.AbsGraphicList;
import code.gui.GraphicList;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.TreeMap;
import code.util.core.IndexConstants;

public class PokemonPanel {

    private final TextLabel title;

    private final PokemonDataRenderer renderer;

    private final AbsGraphicList<String> liste;

    private final FacadeGame facade;

    private final String noEvo;

    private final Panel container;
    public PokemonPanel(int _nb, String _titre, FacadeGame _facade, String _noEvo, AbsGraphicList<String> _liste) {
        liste = _liste;
        facade = _facade;
        container = Panel.newBorder();
        container.setLoweredBorder();
        title = new TextLabel(_titre, SwingConstants.CENTER);
        container.add(title, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        noEvo = _noEvo;
        renderer = new PokemonDataRenderer(facade, noEvo);
        liste.setRender(renderer);
        initEvos();
        container.add(liste.self(),BorderLayout.CENTER);
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
        return liste.getSelectedValue();
    }

    public Panel getContainer() {
        return container;
    }
}
