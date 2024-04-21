package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import aiki.gui.WindowAikiInt;
import aiki.gui.listeners.PokemonHostEvent;
import aiki.gui.listeners.PokemonSelectionItems;
import aiki.gui.listeners.PokemonSelectionMoveTutor;
import aiki.gui.listeners.PokemonSelectionStorage;
import aiki.gui.listeners.PokemonSelectionTeam;
import aiki.gui.listeners.PokemonSelectionTm;
import aiki.main.AikiFactory;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.gui.*;
import code.gui.events.AbsActionListenerAct;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class TeamPanel {
    public static final String TEAM_PANEL = "aiki.gui.components.walk.teampanel";

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

    private static final String ROOMS = "rooms";

    private final PokemonRenderer renderer;

    private final ScrollCustomGraphicList<UsablePokemon> liste;

    private final Bytes indexes = new Bytes();

    private final FacadeGame facade;

    private final AbsPlainLabel nbRemainPlaces;

    private final AbsPanel container;
    private final AbsCompoFactory compoFactory;

    public TeamPanel(WindowAikiInt _parent, String _titre, FacadeGame _facade, ByteTreeMap<UsablePokemon> _team, StringMap<String> _mess, boolean _single, AbsActionListenerAct _act) {
        facade = _facade;
        PokemonRenderer render_ = new PokemonRenderer(_parent.getFrames(), facade, _single);
        liste = AikiFactory.usable(_parent.getFrames().getCompoFactory(), _parent.getImageFactory(), render_, _act);
        compoFactory = render_.getFact().getCompoFactory();
        container = compoFactory.newBorder();
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = compoFactory.newPlainLabel(_titre);
        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int side_ = facade.getMap().getSideLength();
        nbRemainPlaces = compoFactory.newPlainLabel("");
        liste.getScrollPane().setPreferredSize(new MetaDimension(getDeltaName(_team) * 2 + side_ * 2,side_*2*2));
        renderer = render_;
        initFighters(_team,_mess);
        container.add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
        translate(_mess);
        container.add(nbRemainPlaces,GuiConstants.BORDER_LAYOUT_SOUTH);
        container.setPreferredSize(new MetaDimension(getDeltaName(_team) * 2 + side_ * 2,side_*2*2+32));
    }

    public void initFighters(ByteTreeMap<UsablePokemon> _fighters, StringMap<String> _mess) {
        liste.clear();
        int maxPixName_ = getDeltaName(_fighters);
        renderer.setCoords(maxPixName_);
        indexes.clear();
        for (byte f: _fighters.getKeys()) {
            indexes.add(f);
            liste.add(_fighters.getVal(f));
        }
        translate(_mess);
        liste.computeDimensions();
    }

    public void translate(StringMap<String> _mess) {
        if (nbRemainPlaces == null) {
            return;
        }
        int rem_ = facade.getRemainingRooms();
        String message_ = _mess.getVal(ROOMS);
        nbRemainPlaces.setText(StringUtil.simpleNumberFormat(message_, rem_));
    }

    int getDeltaName(ByteTreeMap<UsablePokemon> _team) {
        int maxPixName_ = 0;
        for (UsablePokemon l: _team.values()) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = compoFactory.stringWidth(nbRemainPlaces.getMetaFont(),StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = compoFactory.stringWidth(nbRemainPlaces.getMetaFont(),StringUtil.concat(facade.translateAbility(pk_.getAbility()),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = compoFactory.stringWidth(nbRemainPlaces.getMetaFont(),StringUtil.concat(pk_.getRemainingHp().toNumberString(),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
            }
            if (l instanceof PokemonPlayer) {
                PokemonPlayer egg_ = (PokemonPlayer) l;
                int value_ = compoFactory.stringWidth(nbRemainPlaces.getMetaFont(),StringUtil.concat(egg_.getName(),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
            }
        }
        return maxPixName_;
    }

    public void addListenerHost(ScenePanel _battle) {
        liste.setListener(new PokemonHostEvent(_battle));
    }

    public void addListener(ScenePanel _battle) {
        liste.setListener(new PokemonSelectionItems(_battle));
    }

    public void addListenerTeam(ScenePanel _battle) {
        liste.setListener(new PokemonSelectionTeam(_battle));
    }

    public void addListenerMoveTutor(ScenePanel _battle) {
        liste.setListener(new PokemonSelectionMoveTutor(_battle));
    }

    public void addListenerStorage(ScenePanel _window) {
        liste.setListener(new PokemonSelectionStorage(_window));
    }

    public ScrollCustomGraphicList<UsablePokemon> getListe() {
        return liste;
    }

//    public boolean isSingleSelected() {
//        if (liste.getSelectedIndices().length == 0) {
//            return false;
//        }
//        return liste.getSelectedIndices().length <= 2;
//    }

    public boolean isSelected() {
        return liste.getSelectedIndex() != IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public int getSelectedIndexSingle() {
        int index_ = liste.getSelectedIndex();
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return index_;
        }
        return indexes.get(index_);
    }

    public void deselect() {
        liste.deselectAll();
    }

    public void addListenerTm(ScenePanel _mainWindow) {
        liste.setListener(new PokemonSelectionTm(_mainWindow));
    }

    public AbsPanel getContainer() {
        return container;
    }
}
