package aiki.gui.components.fight;

import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.gui.listeners.BackFighterSelection;
import aiki.gui.listeners.FighterSelection;
import aiki.gui.listeners.FrontFighterSelection;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;

public class FighterPanel {

    private final AbsPlainLabel title;

    private final AbsGraphicList<Fighter> liste;

    private final FacadeGame facade;

    private final AbsPanel container;

    public FighterPanel(AbstractProgramInfos _fact, int _nb, String _titre, FacadeGame _facade, ByteTreeMap<Fighter> _fighters, AbsGraphicList<Fighter> _liste) {
        liste = _liste;
        facade = _facade;
        container = _fact.getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _fact.getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setRender(new FighterRenderer(_fact.getImageFactory(),facade));
        initFighters(_fighters);
        container.add(liste.self(), GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new Dimension(150,64*_nb));
    }

    public void initFighters(ByteTreeMap<Fighter> _fighters) {
        liste.clear();
        for (Fighter f: _fighters.values()) {
            liste.add(f);
        }
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }
//    public void initFighters(boolean _front) {
//        modeleListe.clear();
//        if (_front) {
//            TreeMap<Byte,Fighter> fronts_ = facade.getPlayerFrontTeam();
//            for (Fighter f: fronts_.values()) {
//                modeleListe.addElement(f);
//            }
//        } else {
//            TreeMap<Byte,Fighter> fronts_ = facade.getPlayerBackTeam();
//            for (Fighter f: fronts_.values()) {
//                modeleListe.addElement(f);
//            }
//        }
//    }

    public void initFighters() {
        liste.clear();
        ByteTreeMap<Fighter> fronts_ = facade.getPlayerTeam();
        for (Fighter f: fronts_.values()) {
            liste.add(f);
        }
    }

    public void addListener(Battle _battle, boolean _front) {
        if (_front) {
            liste.setListener(new FrontFighterSelection(_battle));
        } else {
            liste.setListener(new BackFighterSelection(_battle));
        }
    }

    public void addListener(Battle _battle) {
        liste.setListener(new FighterSelection(_battle));
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public void deselect() {
        liste.clearSelection();
    }

    public AbsPanel getContainer() {
        return container;
    }
}
