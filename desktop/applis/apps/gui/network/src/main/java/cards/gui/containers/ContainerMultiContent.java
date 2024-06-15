package cards.gui.containers;

import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.network.common.PlayerActionGame;
import cards.network.common.PlayerActionGameType;
import cards.network.common.before.*;
import cards.network.threads.Net;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesGuiCards;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class ContainerMultiContent {
    private StringMap<String> messages = new StringMap<String>();
    private int noClient;
    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private AbsButton ready;
    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
    private final boolean hasCreatedServer;
    private boolean readyToPlay;
    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
    private RenderedPage editor;
    private IntTreeMap< Byte> playersPlacesForGame = new IntTreeMap< Byte>();
    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private final AbsPlainLabel canPlayLabel;
    private final WindowNetWork win;
//    private final AbstractAtomicInteger win;
    public ContainerMultiContent(boolean _hasCreatedServer, WindowNetWork _window) {
        hasCreatedServer = _hasCreatedServer;
        win = _window;
        canPlayLabel = _window.getFrames().getCompoFactory().newPlainLabel("");
    }
    public void changePlace() {
        if (readyToPlay) {
            return;
        }
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
        ChoosenPlace choice_ = new ChoosenPlace();
        choice_.setIndex(noClient);
        choice_.setPlace(indexInGame);
        choice_.setPlacesPlayers(new IntTreeMap< Byte>());
        window().sendObject(choice_);
    }
    public void changeReady() {
//        if (ComparatorBoolean.eq(readyToPlay, ready.isSelected())) {
//            return;
//        }
        readyToPlay = !readyToPlay;
        if (readyToPlay) {
            ready.setLineBorder(GuiConstants.RED);
        } else {
            ready.setLineBorder(GuiConstants.BLACK);
        }
        Ready choice_ = new Ready();
        choice_.setIndex(noClient);
        choice_.setReady(readyToPlay);
        window().sendObject(choice_);
    }
    public AbsPanel resultUsers(ContainerMulti _cont, IndexOfArrivingCards _players) {
        nbChoosenPlayers = _players.getNbPlayers();
        AbsPanel container_ = win.getFrames().getCompoFactory().newPageBox();
        AbsPanel panel_ = win.getFrames().getCompoFactory().newGrid(0, 2);
        panel_.add(win.getFrames().getCompoFactory().newPlainLabel(_cont.getContainerMultiContent().getMessages().getVal(MessagesGuiCards.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(win.getFrames());
        choiceOfPlaceForPlayingGame.setItems(nbChoosenPlayers);
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getIndex());
        choiceOfPlaceForPlayingGame.getCombo().repaint();
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(_cont));
        panel_.add(choiceOfPlaceForPlayingGame.self());
        ready = win.getFrames().getCompoFactory().newPlainButton(_cont.getContainerMultiContent().getMessages().getVal(MessagesGuiCards.READY));
        ready.setLineBorder(GuiConstants.BLACK);
        ready.addActionListener(new ReadyEvent(_cont));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = win.getFrames().getCompoFactory().newGrid(0, 3);
        playersPseudos.clear();
        for (int i = IndexConstants.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            AbsPlainLabel pseudo_ = win.getFrames().getCompoFactory().newPlainLabel("");
            playersPseudos.add(pseudo_);
            panel_.add(pseudo_);
            AbsPlainLabel place_ = win.getFrames().getCompoFactory().newPlainLabel("");
            playersPlaces.add(place_);
            panel_.add(place_);
            AbsCustCheckBox ready_ = win.getFrames().getCompoFactory().newCustCheckBox();
            ready_.setEnabled(false);
            playersReady.add(ready_);
            panel_.add(ready_);
        }
        container_.add(panel_);
        return container_;
    }
//    public void updateAfter(PlayersNamePresent _players) {
//        playersPlacesForGame = _players.getPlacesPlayers();
//        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
//        for (int i: _players.getPseudos().getKeys()) {
//            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
//        }
//        for (int i: _players.getPlacesPlayers().getKeys()) {
//            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
//        }
//        for (int i: _players.getReadyPlayers().getKeys()) {
//            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
//        }
//    }
    public void updateAfter(NewPlayerCards _players) {
//        playersPlacesForGame = _players.getPlacesPlayers();
        playersPlacesForGame.put(_players.getIndex(), (byte) _players.getIndex());
        playersPlaces.get(_players.getIndex()).setText(Integer.toString(_players.getIndex()));
        playersPseudosForGame.put(_players.getIndex(),_players.getPseudo());
//        update(_players.getPlacesPlayers(), _players.getReadyPlayers());
        playersPseudos.get(_players.getIndex()).setText(_players.getPseudo());
    }
    public void updateAfter(OldPlayerCards _players) {
        playersPseudos.get(_players.getIndex()).setText(_players.getPseudo());
        playersPseudosForGame.put(_players.getIndex(),_players.getPseudo());
    }
    public void updateAfter(IndexOfArrivingCards _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>();
        update(_players.getPlacesPlayers(), _players.getReadyPlayers());
    }

    private void update(IntTreeMap<Byte> _places, IntMap<BoolVal> _ready) {
        for (int i: _places.getKeys()) {
            playersPlaces.get(i).setText(_places.getVal(i).toString());
        }
        for (int i: _ready.getKeys()) {
            playersReady.get(i).setSelected(_ready.getVal(i) == BoolVal.TRUE);
        }
    }
    public void updatePlaces(ChoosenPlace _choosePlace) {
        playersPlacesForGame = _choosePlace.getPlacesPlayers();
        playersPlaces.get(_choosePlace.getIndex()).setText(Long.toString(_choosePlace.getPlace()));
    }
    public void updateReady(Ready _readyPlayer) {
        playersReady.get(_readyPlayer.getIndex()).setSelected(_readyPlayer.isReady());
    }

    public AbsPanel endNickname() {
        AbsPanel panel_ = win.getFrames().getCompoFactory().newGrid(0,3);
        for (int i = IndexConstants.FIRST_INDEX; i< nbChoosenPlayers; i++) {
            panel_.add(playersPseudos.get(i));
            panel_.add(playersPlaces.get(i));
            panel_.add(playersReady.get(i));
        }
        return panel_;
    }
    public void endReady(ContainerMulti _cont,AbsPanel _panel) {
        readyToPlay = false;
        ready = win.getFrames().getCompoFactory().newPlainButton(getMessages().getVal(MessagesGuiCards.READY));
        ready.setLineBorder(GuiConstants.BLACK);
        ready.addActionListener(new ReadyEvent(_cont));
        _panel.add(ready);
    }

    public ByteTreeMap<String> nicknames(CustList<String> _bots) {
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : _bots) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p: getPlayersPlacesForGame().values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        return pseudos_;
    }
    public byte relative(int _otherPlayerIndex) {
        byte iter_ = 0;
        for (byte p = indexInGame; p < nbChoosenPlayers; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        for (byte p = IndexConstants.FIRST_INDEX; p < indexInGame; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        return iter_;
    }
    public String getPseudoByPlace(byte _place) {
        for (int i : playersPlacesForGame.getKeys()) {
            if (playersPlacesForGame.getVal(i) == _place) {
                return playersPseudosForGame.getVal(i);
            }
        }
        return ContainerGame.EMPTY_STRING;
    }
    public void sendDealt() {
        if (Net.QUICK) {
            NetGroupFrame.trySendString(Net.exportDealt(getIndexInGame()),window().getSocket());
            return;
        }
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
        dealt_.setPlace(getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }
    public void sendPause() {
        if (Net.QUICK) {
            NetGroupFrame.trySendString(Net.exportDonePause(getIndexInGame()),window().getSocket());
            return;
        }
        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
        d_.setPlace(getIndexInGame());
//        d_.setLocale(lg_.getKey());
        window().sendObject(d_);
    }
    public void sendOk() {
        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.OK);
        d_.setPlace(getIndexInGame());
//        d_.setLocale(lg_.getKey());
        window().sendObject(d_);
    }

    public void showTeams() {
//        if (!isCanPlay()) {
//            return;
//        }
//        String lg_ = getOwner().getLanguageKey();
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
        select_.setPlace(getIndexInGame());
//        select_.setLocale(lg_);
        window().sendObject(select_);
    }
    public void showTricksHands() {
//        if (!isCanPlay()) {
//            return;
//        }
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
        select_.setPlace(getIndexInGame());
//        String lg_ = getOwner().getLanguageKey();
//        select_.setLocale(lg_);
        window().sendObject(select_);
    }
    public boolean notAllReadyDistinct() {
        return !window().getSockets().allReady() || !Net.distinctPlaces(window().getNet(), window().getSockets());
    }
    public IntTreeMap<Byte> getPlayersPlacesForGame() {
        return playersPlacesForGame;
    }

    public AbsPlainLabel getCanPlayLabel() {
        return canPlayLabel;
    }

    public RenderedPage getEditor() {
        return editor;
    }

    public void setEditor(RenderedPage _e) {
        this.editor = _e;
    }

    public NumComboBox getChoiceOfPlaceForPlayingGame() {
        return choiceOfPlaceForPlayingGame;
    }

    public AbsButton getReady() {
        return ready;
    }
    public boolean isHasCreatedServer() {
        return hasCreatedServer;
    }

    public int getNbChoosenPlayers() {
        return nbChoosenPlayers;
    }

    public byte getIndexInGame() {
        return indexInGame;
    }

    public void setNoClient(int _noClient) {
        noClient = _noClient;
    }

    public int getNoClient() {
        return noClient;
    }
    public WindowNetWork window() {
        return win;
    }
    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _m) {
        this.messages = _m;
    }
}
