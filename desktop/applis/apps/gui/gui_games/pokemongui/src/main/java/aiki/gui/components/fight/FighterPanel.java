package aiki.gui.components.fight;


import aiki.facade.FacadeGame;
import aiki.game.fight.FighterPosition;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.DefTileRender;
import aiki.gui.listeners.*;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class FighterPanel {

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

    private final AbsPlainLabel title;

    private final ScrollCustomGraphicList<FighterPosition> liste;

    private final AbsPanel container;
    private final FighterRenderer renderer;
    private final AbsCompoFactory compoFactory;
    private final FacadeGame facade;
    private final int maxVisible;

    public FighterPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade, CustList<FighterPosition> _fighters) {
        renderer = new FighterRenderer(_window.getFrames().getImageFactory(), _facade, _window.getTileRender());
        liste = AikiFactory.fighter(_window.getCompoFactory(), _window.getImageFactory(), renderer, new PkNonModalEvent(_window.getModal()));
        compoFactory = _window.getFrames().getCompoFactory();
        facade = _window.getFacade();
        container = compoFactory.newBorder();
        container.setLoweredBorder();
        title = compoFactory.newPlainLabel(_titre);
        title.setToolTipText(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        liste.getElements().setFont(title.getMetaFont());
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        maxVisible = _nb;
//        int s_ = _facade.getData().getMap().getSideLength();
//        liste.getScrollPane().setPreferredSize(new MetaDimension(150,s_*_nb));
        initFighters(_fighters);
        container.add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
//        container.setPreferredSize(new MetaDimension(150,s_*_nb+16));
    }

    public void initFighters(CustList<FighterPosition> _fighters) {
        MetaFont metaFont_ = liste.getElements().getMetaFont();
        int wName_ = widthName(_fighters, compoFactory, metaFont_, facade);
        int wPercent_ = DefTileRender.width(compoFactory, metaFont_);
        int s_ = facade.getData().getMap().getSideLength();
        int h_ = NumberUtil.max(s_, 2 * metaFont_.getRealSize() + 2);
        liste.getScrollPane().setPreferredSize(new MetaDimension(NumberUtil.max(150,s_+wName_+wPercent_),(h_ + 2)*maxVisible));
        renderer.initWidth(wName_, wPercent_);
        liste.clear();
        for (FighterPosition f: _fighters) {
            liste.add(f);
        }
        liste.computeDimensions();
    }
    public static int widthName(CustList<FighterPosition> _fighters, AbsCompoFactory _facto, MetaFont _f, FacadeGame _facade) {
        int maxWidthName_ = 0;
        for (FighterPosition b: _fighters) {
            maxWidthName_ = NumberUtil.max(maxWidthName_, _facto.stringWidth(_f, _facade.translatePokemon(b.getFighter().getName())+SPACES));
        }
        return maxWidthName_;
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
        title.setToolTipText(_title);
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

    public void addListener(Battle _battle, boolean _front, boolean _sub) {
        if (_front) {
            liste.setListener(new FrontFighterSelection(_battle));
        } else if (_sub){
            liste.setListener(new BackFighterSubSelection(_battle));
        } else {
            liste.setListener(new BackFighterSelection(_battle));
        }
    }

    public void addListener(Battle _battle) {
        liste.setListener(new FighterSelection(_battle));
    }


    public void addFleeListener(Battle _battle) {
        liste.setListener(new FighterFleeSelection(_battle));
    }

    public void addFighterCaughtListener(Battle _battle) {
        liste.setListener(new FighterCaughtSelection(_battle));
    }

    public void addFighterCaughtNicknameListener(Battle _battle) {
        liste.setListener(new FighterCaughtNicknameSelection(_battle));
    }

    public void addFighterCatchingListener(Battle _battle) {
        liste.setListener(new FighterCatchingSelection(_battle));
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

    public ScrollCustomGraphicList<FighterPosition> getListe() {
        return liste;
    }
}
