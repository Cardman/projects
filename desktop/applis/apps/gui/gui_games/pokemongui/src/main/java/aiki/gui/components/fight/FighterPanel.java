package aiki.gui.components.fight;


import aiki.facade.FacadeGame;
import aiki.game.fight.Fighter;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.BackFighterSelection;
import aiki.gui.listeners.FighterSelection;
import aiki.gui.listeners.FrontFighterSelection;
import aiki.main.AikiFactory;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.CustList;

public final class FighterPanel {

    private final AbsPlainLabel title;

    private final ScrollCustomGraphicList<Fighter> liste;

    private final FacadeGame facade;

    private final AbsPanel container;

    public FighterPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade, CustList<Fighter> _fighters) {
        liste = AikiFactory.fighter(_window.getCompoFactory(), _window.getImageFactory(),new FighterRenderer(_window.getFrames().getImageFactory(),_facade));
        facade = _facade;
        container = _window.getFrames().getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _window.getFrames().getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int s_ = _facade.getData().getMap().getSideLength();
        liste.getScrollPane().setPreferredSize(new MetaDimension(150,s_*_nb));
        initFighters(_fighters);
        container.add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new MetaDimension(150,s_*_nb+16));
    }

    public void initFighters(CustList<Fighter> _fighters) {
        liste.clear();
        for (Fighter f: _fighters) {
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

//    public void initFighters() {
//        liste.clear();
//        CustList<Fighter> fronts_ = facade.getPlayerTeam();
//        for (Fighter f: fronts_) {
//            liste.add(f);
//        }
//    }

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
        liste.deselectAll();
    }

    public AbsPanel getContainer() {
        return container;
    }

    public ScrollCustomGraphicList<Fighter> getListe() {
        return liste;
    }
}
