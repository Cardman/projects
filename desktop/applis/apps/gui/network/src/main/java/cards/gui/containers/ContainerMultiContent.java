package cards.gui.containers;

import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.network.common.PlayerActionGame;
import cards.network.common.PlayerActionGameType;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.threads.Net;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.NumComboBox;
import code.gui.document.RenderedPage;
import code.network.WindowNetWork;
import code.util.CustList;
import code.util.IntMap;
import code.util.IntTreeMap;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class ContainerMultiContent {
    private StringMap<String> messages = new StringMap<String>();
    private int noClient;
    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private AbsCustCheckBox ready;
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
        if (ComparatorBoolean.eq(readyToPlay, ready.isSelected())) {
            return;
        }
        readyToPlay = !readyToPlay;
        Ready choice_ = new Ready();
        choice_.setIndex(noClient);
        choice_.setReady(readyToPlay);
        window().sendObject(choice_);
    }
    public AbsPanel resultUsers(ContainerMulti _cont,PlayersNamePresent _players) {
        nbChoosenPlayers = _players.getNbPlayers();
        AbsPanel container_ = win.getFrames().getCompoFactory().newPageBox();
        AbsPanel panel_ = win.getFrames().getCompoFactory().newGrid(0, 2);
        panel_.add(win.getFrames().getCompoFactory().newPlainLabel(_cont.getContainerMultiContent().getMessages().getVal(WindowNetWork.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(win.getFrames());
        choiceOfPlaceForPlayingGame.setItems(nbChoosenPlayers);
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos().size() - 1);
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(_cont));
        panel_.add(choiceOfPlaceForPlayingGame.self());
        ready = win.getFrames().getCompoFactory().newCustCheckBox(_cont.getContainerMultiContent().getMessages().getVal(WindowNetWork.READY));
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
    public void updateAfter(PlayersNamePresent _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
        for (int i: _players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i: _players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i: _players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
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
        ready = win.getFrames().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowNetWork.READY));
        ready.addActionListener(new ReadyEvent(_cont));
        _panel.add(ready);
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
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
        dealt_.setPlace(getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }
    public void sendPause() {
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

    public AbsCustCheckBox getReady() {
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
