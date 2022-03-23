package aiki.gui.components.walk;



import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.PokemonHostEvent;
import aiki.gui.listeners.PokemonSelectionItems;
import aiki.gui.listeners.PokemonSelectionMoveTutor;
import aiki.gui.listeners.PokemonSelectionStorage;
import aiki.gui.listeners.PokemonSelectionTeam;
import aiki.gui.listeners.PokemonSelectionTm;
import aiki.gui.listeners.PokemonSelectionTrading;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import code.gui.AbsGraphicList;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
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

    private final AbsGraphicList<UsablePokemon> liste;

    private final Bytes indexes = new Bytes();

    private final FacadeGame facade;

    private final AbsPlainLabel nbRemainPlaces;

    private final AbsPanel container;
    public TeamPanel(WindowAiki _parent, int _nb, String _titre, FacadeGame _facade, ByteTreeMap<UsablePokemon> _team, StringMap<String> _mess, boolean _single) {
        facade = _facade;
        PokemonRenderer render_ = new PokemonRenderer(_parent.getFrames(), facade, _single);
        liste = _parent.getAikiFactory().getGeneUsPkPanel().createSimple(_parent.getImageFactory(), render_);
        container = render_.getFact().getCompoFactory().newBorder();
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = render_.getFact().getCompoFactory().newPlainLabel(_titre);
        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        renderer = render_;
        initFighters(_team,_mess);
        int side_ = facade.getMap().getSideLength();
        container.add(liste.self(), GuiConstants.BORDER_LAYOUT_CENTER);
        nbRemainPlaces = render_.getFact().getCompoFactory().newPlainLabel("");
        translate(_mess);
        container.add(nbRemainPlaces,GuiConstants.BORDER_LAYOUT_SOUTH);
        container.setPreferredSize(new MetaDimension(getDeltaName(_team) * 2 + side_ * 2,side_*2*_nb));
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
                int value_ = nbRemainPlaces.stringWidth(StringUtil.concat(facade.translatePokemon(pk_.getName()),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = nbRemainPlaces.stringWidth(StringUtil.concat(facade.translateAbility(pk_.getAbility()),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = nbRemainPlaces.stringWidth(StringUtil.concat(pk_.getRemainingHp().toNumberString(),SPACES));
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
            }
            if (l instanceof PokemonPlayer) {
                PokemonPlayer egg_ = (PokemonPlayer) l;
                int value_ = nbRemainPlaces.stringWidth(StringUtil.concat(egg_.getName(),SPACES));
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

    public void addListenerTrading(ScenePanel _window) {
        liste.setListener(new PokemonSelectionTrading(_window));
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
        liste.clearSelection();
    }

    public void addListenerTm(ScenePanel _mainWindow) {
        liste.setListener(new PokemonSelectionTm(_mainWindow));
    }

    public AbsPanel getContainer() {
        return container;
    }
}
