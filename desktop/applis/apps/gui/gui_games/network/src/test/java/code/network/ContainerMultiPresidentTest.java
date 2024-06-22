package code.network;

import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.network.threads.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ContainerMultiPresidentTest extends EquallableNetworkUtil {
    @Test
    public void intro1() {
        MockGamePresident m_ = new MockGamePresident();
        WindowNetWork server_ = frameSinglePresident(m_);
        serverVersionOld(server_,6);
        retrievedSocket(server_, server_, 0);
        WindowNetWork client_ = frameSinglePresident(m_);
        clientVersionOld(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(12, serverCompo_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        ready(server_, client_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        ready(server_, client_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo3_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo3_.size());
        assertTrue(clientCompo3_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo3_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }

    @Test
    public void intro2() {
        MockGamePresident m_ = new MockGamePresident();
        WindowNetWork server_ = frameSinglePresident(m_);
        serverVersionNew(server_,6);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        ready(server_, server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSinglePresident(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        ready(server_, server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(12, serverCompo_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        choicePosition(server_, client_, server_, socketServ_, 2);

        choicePosition(server_, client_, client_, socketClient_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(11, serverCompo2_.size());
        checkRules((ContainerMultiPresident) server_.getNetg().getContainerGame(),serverCompo2_);
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }
    @Test
    public void playFirst1() {
        MockGamePresident m_ = new MockGamePresident();
        WindowNetWork server_ = frameSinglePresident(m_);
        server_.getNetg().setFirstDealPresident(new PresidentSampleFirstDealNetSix());
        serverVersionNew(server_,6);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSinglePresident(m_);
        client_.getNetg().setFirstDealPresident(null);
        clientVersionNew(server_,client_);

        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(10, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3,CardPresident.SPADE_3))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3,CardPresident.SPADE_3,CardPresident.CLUB_3))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_7))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_8))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_8,CardPresident.SPADE_8))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_8,CardPresident.SPADE_8,CardPresident.DIAMOND_8))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_8,CardPresident.SPADE_8,CardPresident.DIAMOND_8,CardPresident.CLUB_8))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_10))));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }
    @Test
    public void playFirst2() {
        MockGamePresident m_ = new MockGamePresident();
        nextCard(m_,create(CardPresident.SPADE_7));
        WindowNetWork server_ = frameSinglePresident(m_);
        server_.getNetg().setFirstDealPresident(new PresidentSampleFirstDealNetSix());
        serverVersionNew(server_,6);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSinglePresident(m_);
        client_.getNetg().setFirstDealPresident(null);
        clientVersionNew(server_,client_);

        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        choicePosition(server_, client_, client_, socketClient_, 2);

        choicePosition(server_, client_, server_, socketServ_, 1);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_,client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(8, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.CLUB_7))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_9))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_9,CardPresident.CLUB_9))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.CLUB_10))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK))));
        assertTrue(serverCompo_.containsObj(((ContainerMultiPresident) server_.getNetg().getContainerGame()).getNoPlay()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }
    private static void tryClickCard(ContainerMultiPresident _compo, MockGamePresident _mock) {
        tryClickCard(component(_compo,_mock.currentCards()));
    }
    private static AbsCustComponent component(ContainerMultiPresident _compo, HandPresident _cb) {
        HandPresident cp_ = new HandPresident();
        cp_.ajouterCartes(_compo.getPlayerHandPresident());
        cp_.sortCards(_compo.getDisplayingPresident().getDisplaying(), _compo.isReversedGame());
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying(), _compo.isReversedGame());
        int f_ = cp_.getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelHand().getComponent(s_);
    }
    private void play(WindowNetWork _server, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerMulti) _server.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());

        writeToServer(_server, _socket);
    }
    private static HandPresident create(CardPresident... _cb) {
        HandPresident c_ = new HandPresident();
        c_.setCards(new IdList<CardPresident>(_cb));
        return c_;
    }

    private static void nextCard(MockGamePresident _m, HandPresident _bid) {
        _m.getCards().add(_bid);
    }
    private void choicePosition(WindowNetWork _server, WindowNetWork _client, WindowNetWork _dest, MockSocket _socket, int _i) {
        _socket.getOutput().clear();
        eventsCombo(((ContainerMulti) _dest.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(), _i);
        writeToServer(_server, _socket);
        netPlayers(_server, _client);
    }
    private void rules(WindowNetWork _server, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMultiPresident) _server.getNetg().getContainerGame()).getSelectRules());
        writeToServer(_server, _soc);
    }

    private void ready(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMulti) _target.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(_server, _soc);
    }
    private void readyPlayers(WindowNetWork _server, MockSocket _socketServ, WindowNetWork _client, MockSocket _socketClient) {
        _socketServ.getOutput().clear();
        ready(_server, _server, _socketServ);
        netPlayers(_server, _client);

        _socketClient.getOutput().clear();
        ready(_server, _client, _socketClient);
        netPlayers(_server, _client);
    }
    private static void checkRules(ContainerMultiPresident _cont, IdList<AbsCustComponent> _tr) {
        DialogPresidentContent d_ = _cont.getDialogPresidentContent();
        assertTrue(_tr.containsObj(d_.getNbStacks()));
        assertTrue(_tr.containsObj(d_.getCanPass()));
        assertTrue(_tr.containsObj(d_.getLooseFinishBestCards()));
        assertTrue(_tr.containsObj(d_.getLooserStartsFirst()));
        assertTrue(_tr.containsObj(d_.getPossibleReversing()));
        assertTrue(_tr.containsObj(d_.getSwitchCards()));
        assertTrue(_tr.containsObj(d_.getListeChoix().self()));
        assertTrue(_tr.containsObj(d_.getEquality().self()));
        assertTrue(_tr.containsObj(_cont.getSelectRules()));
    }
}
