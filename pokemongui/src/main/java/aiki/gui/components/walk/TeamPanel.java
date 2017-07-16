package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import code.gui.CustListModel;
import code.gui.Jl;
import code.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.listeners.PokemonHostEvent;
import aiki.gui.listeners.PokemonSelectionItems;
import aiki.gui.listeners.PokemonSelectionMoveTutor;
import aiki.gui.listeners.PokemonSelectionStorage;
import aiki.gui.listeners.PokemonSelectionTeam;
import aiki.gui.listeners.PokemonSelectionTm;
import aiki.gui.listeners.PokemonSelectionTrading;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;

public class TeamPanel extends JPanel {
    private static final String TEAM_PANEL = "aiki.gui.components.walk.TeamPanel";

    private static final String SPACE = " ";

    private static final String SPACES = SPACE + SPACE;

    private static final String ROOMS = "rooms";

    private static StringMap<String> _messages_ = new StringMap<String>();

    private CustListModel<UsablePokemon> modeleListe = new CustListModel<UsablePokemon>();

    private PokemonRenderer renderer;

    private Jl<UsablePokemon> liste;

    private Numbers<Byte> indexes = new Numbers<Byte>();

    private FacadeGame facade;

    private JLabel nbRemainPlaces;

    public TeamPanel(int _nb, String _titre, FacadeGame _facade, NatTreeMap<Byte,UsablePokemon> _team, boolean _single) {
        facade = _facade;
        liste = new Jl<UsablePokemon>(modeleListe);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel titrePanneau_ = new JLabel(_titre, SwingConstants.CENTER);
        add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb+1);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        renderer = new PokemonRenderer(facade, _single);
        liste.setCellRenderer(renderer);
        initFighters(_team);
        int side_ = facade.getMap().getSideLength();
        add(new JScrollPane(liste),BorderLayout.CENTER);
        nbRemainPlaces = new JLabel();
        translate();
        add(nbRemainPlaces,BorderLayout.SOUTH);
        setPreferredSize(new Dimension(getDeltaName(_team) * 2 + side_ * 2,side_*2*_nb));
    }

    public static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), TEAM_PANEL);
    }

    public void initFighters(NatTreeMap<Byte,UsablePokemon> _fighters) {
        modeleListe.clear();
        int maxPixName_ = getDeltaName(_fighters);
        renderer.setCoords(maxPixName_);
        indexes.clear();
        for (byte f: _fighters.getKeys()) {
            indexes.add(f);
            modeleListe.addElement(_fighters.getVal(f));
        }
        translate();
    }

    public void translate() {
        if (nbRemainPlaces == null) {
            return;
        }
        int rem_ = facade.getRemainingRooms();
        String message_ = _messages_.getVal(ROOMS);
        nbRemainPlaces.setText(StringList.simpleFormat(message_, rem_));
    }

    int getDeltaName(NatTreeMap<Byte,UsablePokemon> _team) {
        int maxPixName_ = 0;
        JLabel ex_ = new JLabel();
        for (UsablePokemon l: _team.values()) {
            if (l instanceof PokemonPlayer) {
                PokemonPlayer pk_ = (PokemonPlayer) l;
                int value_ = ex_.getFontMetrics(ex_.getFont()).stringWidth(facade.translatePokemon(pk_.getName())+SPACES);
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = ex_.getFontMetrics(ex_.getFont()).stringWidth(facade.translateAbility(pk_.getAbility())+SPACES);
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
                value_ = ex_.getFontMetrics(ex_.getFont()).stringWidth(pk_.getRemainingHp().toString()+SPACES);
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
            }
            if (l instanceof PokemonPlayer) {
                PokemonPlayer egg_ = (PokemonPlayer) l;
                int value_ = ex_.getFontMetrics(ex_.getFont()).stringWidth(egg_.getName()+SPACES);
                if (value_ > maxPixName_) {
                    maxPixName_ = value_;
                }
            }
        }
        return maxPixName_;
    }

    public void addListenerHost(ScenePanel _battle) {
        liste.addListSelectionListener(new PokemonHostEvent(_battle));
    }

    public void addListener(ScenePanel _battle) {
        liste.addListSelectionListener(new PokemonSelectionItems(_battle));
    }

    public void addListenerTeam(ScenePanel _battle) {
        liste.addListSelectionListener(new PokemonSelectionTeam(_battle));
    }

    public void addListenerMoveTutor(ScenePanel _battle) {
        liste.addListSelectionListener(new PokemonSelectionMoveTutor(_battle));
    }

    public void addListenerStorage(ScenePanel _window) {
        liste.addListSelectionListener(new PokemonSelectionStorage(_window));
    }

    public void addListenerTrading(ScenePanel _window) {
        liste.addListSelectionListener(new PokemonSelectionTrading(_window));
    }

//    public boolean isSingleSelected() {
//        if (liste.getSelectedIndices().length == 0) {
//            return false;
//        }
//        return liste.getSelectedIndices().length <= 2;
//    }

    public boolean isSelected() {
        return liste.getSelectedIndex() != CustList.INDEX_NOT_FOUND_ELT;
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public int getSelectedIndexSingle() {
        int index_ = liste.getSelectedIndex();
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return index_;
        }
        return indexes.get(index_);
    }

    public void deselect() {
        liste.clearSelection();
    }

    public void addListenerTm(ScenePanel _mainWindow) {
        liste.addListSelectionListener(new PokemonSelectionTm(_mainWindow));
    }
}
