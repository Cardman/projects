package aiki.gui.components.walk;


import aiki.facade.FacadeGame;
import aiki.gui.WindowAikiInt;
import aiki.gui.listeners.PokemonHostEvent;
import aiki.gui.listeners.PokemonSelectionMoveTutor;
import aiki.gui.listeners.PokemonSelectionStorage;
import aiki.gui.listeners.PokemonSelectionTeam;
import aiki.main.AikiFactory;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.sml.MessagesRenderScenePanel;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.events.AbsActionListenerAct;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.ByteTreeMap;
import code.util.Bytes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TeamPanel {
//    public static final String TEAM_PANEL = "aiki.gui.components.walk.teampanel";

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

//    private static final String ROOMS = "rooms";

    private final PokemonRenderer renderer;

    private final ScrollCustomGraphicList<UsablePokemon> liste;

    private final Bytes indexes = new Bytes();

    private final FacadeGame facade;

    private final AbsPlainLabel nbRemainPlaces;

    private final AbsPanel container;
    private final AbsCompoFactory compoFactory;

    public TeamPanel(WindowAikiInt _parent, String _titre, FacadeGame _facade, ByteTreeMap<UsablePokemon> _team, StringMap<String> _mess, boolean _single, AbsActionListenerAct _act) {
        facade = _facade;
        renderer = new PokemonRenderer(_parent.getFrames(), facade, _single,_parent.getTileRender());
        liste = AikiFactory.usable(_parent.getFrames().getCompoFactory(), _parent.getImageFactory(), renderer, _act);
        compoFactory = renderer.getFact().getCompoFactory();
        container = compoFactory.newBorder();
        container.setLoweredBorder();
        AbsPlainLabel titrePanneau_ = compoFactory.newPlainLabel(_titre);
        titrePanneau_.setToolTipText(_titre);
        container.add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        liste.getElements().setFont(titrePanneau_.getMetaFont());
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        nbRemainPlaces = compoFactory.newPlainLabel("");
        initFighters(_team,_mess);
        container.add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
        translate(_mess);
        container.add(nbRemainPlaces,GuiConstants.BORDER_LAYOUT_SOUTH);
    }

    public void initFighters(ByteTreeMap<UsablePokemon> _fighters, StringMap<String> _mess) {
        liste.clear();
        int side_ = facade.getMap().getSideLength();
        MetaFont metaFont_ = liste.getElements().getMetaFont();
        int prefWidth_ = getDeltaName(_fighters) + secCol(_fighters.values(), compoFactory, metaFont_, facade) + 2;
        int h_ = NumberUtil.max(side_, 3* metaFont_.getRealSize());
        liste.getScrollPane().setPreferredSize(new MetaDimension(prefWidth_ + side_ * 2,h_*2*2));
        int maxPixName_ = getDeltaName(_fighters);
        renderer.setCoords(maxPixName_, secCol(_fighters.values(), compoFactory, metaFont_, facade));
        indexes.clear();
        for (byte f: _fighters.getKeys()) {
            indexes.add(f);
            liste.add(_fighters.getVal(f));
        }
        translate(_mess);
        liste.computeDimensions();
    }

    public void translate(StringMap<String> _mess) {
//        if (nbRemainPlaces == null) {
//            return;
//        }
        int rem_ = facade.getRemainingRooms();
        String message_ = _mess.getVal(MessagesRenderScenePanel.ROOMS);
        nbRemainPlaces.setText(StringUtil.simpleNumberFormat(message_, rem_));
    }

    int getDeltaName(ByteTreeMap<UsablePokemon> _team) {
        CustList<UsablePokemon> values_ = _team.values();
        MetaFont metaFont_ = liste.getElements().getMetaFont();
        return NumberUtil.max(name(values_,compoFactory, metaFont_, facade),NumberUtil.max(ability(values_, compoFactory, metaFont_, facade),remainingHp(values_,compoFactory, metaFont_)));
    }

    public static int ability(CustList<UsablePokemon> _values, AbsCompoFactory _cp, MetaFont _meta, FacadeGame _facade) {
        int maxPixAbName_ = 0;
        for (UsablePokemon l: _values) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = _cp.stringWidth(_meta,StringUtil.concat(_facade.translateAbility(pk_.getAbility()),SPACES));
                maxPixAbName_ = NumberUtil.max(maxPixAbName_,value_);
            }
        }
        return maxPixAbName_;
    }

    public static int gender(CustList<UsablePokemon> _values, AbsCompoFactory _cp, MetaFont _meta, FacadeGame _facade) {
        int maxPixGenderName_ = 0;
        for (UsablePokemon l: _values) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = _cp.stringWidth(_meta,StringUtil.concat(_facade.translateGenders(pk_.getGender()),SPACES));
                maxPixGenderName_ = NumberUtil.max(maxPixGenderName_,value_);
            }
        }
        return maxPixGenderName_;
    }

    public static int name(CustList<UsablePokemon> _values, AbsCompoFactory _cp, MetaFont _meta, FacadeGame _facade) {
        int maxPixIdName_ = 0;
        for (UsablePokemon l: _values) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = _cp.stringWidth(_meta,StringUtil.concat(_facade.translatePokemon(pk_.getName()),SPACES));
                maxPixIdName_ = NumberUtil.max(maxPixIdName_,value_);
            }
            if (l instanceof Egg) {
                Egg egg_ = (Egg) l;
                int value_ = _cp.stringWidth(_meta,StringUtil.concat(_facade.translatePokemon(egg_.getName()),SPACES));
                maxPixIdName_ = NumberUtil.max(maxPixIdName_,value_);
            }
        }
        return maxPixIdName_;
    }

    public static int remainingHp(CustList<UsablePokemon> _values, AbsCompoFactory _cp, MetaFont _meta) {
        int maxPixGenderName_ = 0;
        for (UsablePokemon l: _values) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = _cp.stringWidth(_meta,StringUtil.concat(pk_.getRemainingHp().toNumberString(),SPACES));
                maxPixGenderName_ = NumberUtil.max(maxPixGenderName_,value_);
            }
        }
        return maxPixGenderName_;
    }

    public static int secCol(CustList<UsablePokemon> _values, AbsCompoFactory _cp, MetaFont _meta, FacadeGame _facade) {
        return NumberUtil.max(DefTileRender.widthLgMax(_cp, _meta),NumberUtil.max(TeamPanel.gender(_values, _cp, _meta, _facade),DefTileRender.width(_cp, _meta)));
    }
    public void addListenerHost(ScenePanel _battle) {
        liste.setListener(new PokemonHostEvent(_battle));
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

//    public boolean isSelected() {
//        return liste.getSelectedIndex() != IndexConstants.INDEX_NOT_FOUND_ELT;
//    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public int getSelectedIndexSingle() {
        return adjustIndex(liste, indexes);
    }

    public static int adjustIndex(ScrollCustomGraphicList<UsablePokemon> _ls, Bytes _indexes) {
        int index_ = _ls.getSelectedIndex();
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return index_;
        }
        return _indexes.get(index_);
    }

    public void deselect() {
        liste.deselectAll();
    }

    public AbsPanel getContainer() {
        return container;
    }
}
