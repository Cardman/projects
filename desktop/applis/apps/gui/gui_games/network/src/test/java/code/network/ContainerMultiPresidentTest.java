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
    @Test
    public void playFirst3() {
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

        ((ContainerMultiPresident) server_.getNetg().getContainerGame()).getDialogPresidentContent().getCanPass().setSelected(false);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_,client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(7, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.CLUB_7))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_9))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.SPADE_9,CardPresident.CLUB_9))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.CLUB_10))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK))));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }
    @Test
    public void playFirst4() {
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

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(8, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.CLUB_7))));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.SPADE_9))));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.SPADE_9,CardPresident.CLUB_9))));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.CLUB_10))));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK))));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiPresident) client_.getNetg().getContainerGame(),create(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK))));
        assertTrue(clientCompo_.containsObj(((ContainerMultiPresident) client_.getNetg().getContainerGame()).getNoPlay()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

    }
    @Test
    public void playFirst5() {
        MockGamePresident m_ = new MockGamePresident();
        nextCard(m_,create(CardPresident.SPADE_7));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
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
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);

        noPlay(server_, client_, socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(9, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3,CardPresident.SPADE_3))));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiPresident) server_.getNetg().getContainerGame(),create(CardPresident.HEART_3,CardPresident.SPADE_3,CardPresident.CLUB_3))));
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
    public void playFirst6() {
        MockGamePresident m_ = new MockGamePresident();
        nextCard(m_,create(CardPresident.CLUB_3,CardPresident.HEART_3,CardPresident.SPADE_3));
        nextCard(m_,create(CardPresident.CLUB_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_9,CardPresident.CLUB_9));
        nextCard(m_,create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_2,CardPresident.CLUB_2));
        nextCard(m_,create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(m_,create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_KING,CardPresident.CLUB_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_1,CardPresident.CLUB_1));
        nextCard(m_,create(CardPresident.DIAMOND_1,CardPresident.HEART_1));
        nextCard(m_,create(CardPresident.DIAMOND_2,CardPresident.HEART_2));
        nextCard(m_,create(CardPresident.DIAMOND_4));
        nextCard(m_,create(CardPresident.HEART_9));
        nextCard(m_,create(CardPresident.CLUB_QUEEN));
        nextCard(m_,create(CardPresident.DIAMOND_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.HEART_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.DIAMOND_3));
        nextCard(m_,create(CardPresident.HEART_7));
        nextCard(m_,create(CardPresident.SPADE_7));
        nextCard(m_,create(CardPresident.CLUB_7));
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.DIAMOND_7));
        nextCard(m_,create(CardPresident.DIAMOND_9));
        nextCard(m_,create(CardPresident.SPADE_10));
        nextCard(m_,create(CardPresident.CLUB_10));
        nextCard(m_,create(CardPresident.HEART_10));
        nextCard(m_,create(CardPresident.DIAMOND_10));
        nextCard(m_,create(CardPresident.SPADE_8,CardPresident.DIAMOND_8,CardPresident.HEART_8,CardPresident.CLUB_8));
        nextCard(m_,create(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));

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

        m_.getStacks().add(new DealPresident(Net.getGames(server_.getNet()).partiePresident().getDeal()));

        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);
        noPlay(server_,client_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);
        noPlay(server_,client_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);

        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        readyPlayers(server_,socketServ_,client_,socketClient_);
        socketServ_.getOutput().clear();

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        writeToServer(server_, socketServ_);
        deal(server_,client_);
        assertTrue(Net.isProgressingGame(server_.getNet()));
    }
    @Test
    public void playNext1() {
        MockGamePresident m_ = new MockGamePresident();
        nextCard(m_,create(CardPresident.CLUB_3,CardPresident.HEART_3,CardPresident.SPADE_3));
        nextCard(m_,create(CardPresident.CLUB_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_9,CardPresident.CLUB_9));
        nextCard(m_,create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_2,CardPresident.CLUB_2));
        nextCard(m_,create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(m_,create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_KING,CardPresident.CLUB_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.SPADE_1,CardPresident.CLUB_1));
        nextCard(m_,create(CardPresident.DIAMOND_1,CardPresident.HEART_1));
        nextCard(m_,create(CardPresident.DIAMOND_2,CardPresident.HEART_2));
        nextCard(m_,create(CardPresident.DIAMOND_4));
        nextCard(m_,create(CardPresident.HEART_9));
        nextCard(m_,create(CardPresident.CLUB_QUEEN));
        nextCard(m_,create(CardPresident.DIAMOND_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.HEART_KING));
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.DIAMOND_3));
        nextCard(m_,create(CardPresident.HEART_7));
        nextCard(m_,create(CardPresident.SPADE_7));
        nextCard(m_,create(CardPresident.CLUB_7));
        nextCard(m_,create());
        nextCard(m_,create(CardPresident.DIAMOND_7));
        nextCard(m_,create(CardPresident.DIAMOND_9));
        nextCard(m_,create(CardPresident.SPADE_10));
        nextCard(m_,create(CardPresident.CLUB_10));
        nextCard(m_,create(CardPresident.HEART_10));
        nextCard(m_,create(CardPresident.DIAMOND_10));
        nextCard(m_,create(CardPresident.SPADE_8,CardPresident.DIAMOND_8,CardPresident.HEART_8,CardPresident.CLUB_8));
        nextCard(m_,create(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        m_.getSw().add(create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        m_.getSw().add(create(CardPresident.DIAMOND_3));
        nextCard(m_,create(CardPresident.SPADE_6,CardPresident.DIAMOND_6,CardPresident.HEART_6,CardPresident.CLUB_6));

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

        choicePosition(server_, client_, client_, socketClient_, 5);
        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);

        m_.getStacks().add(new DealPresident(Net.getGames(server_.getNet()).partiePresident().getDeal()));

        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        noPlay(server_,client_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        noPlay(server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        noPlay(server_,client_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        noPlay(server_,client_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);

        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);

        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryClickCard(server_,(ContainerMultiPresident) client_.getNetg().getContainerGame(),m_,socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        tryClickCard(server_,(ContainerMultiPresident) server_.getNetg().getContainerGame(),m_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        readyPlayers(server_,socketServ_,client_,socketClient_);
        socketServ_.getOutput().clear();

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        writeToServer(server_, socketServ_);
        deal(server_,client_);
        playIa(server_, client_);
        GamePresident next_ = Net.getGames(server_.getNet()).partiePresident();
        assertEq(1, next_.getTricks().size());
        assertEq(0,next_.getProgressingTrick().getCards().size());
        HandPresident played_ = next_.empiler();
        assertEq(4, played_.total());
        assertEq(4, played_.getCardsByStrength(CardPresident.HEART_6.strength(next_.isReversed()),next_.isReversed()).total());
    }

    private void noPlay(WindowNetWork _server, WindowNetWork _dest, MockSocket _so) {
        _so.getOutput().clear();
        tryClick(((ContainerMultiPresident) _dest.getNetg().getContainerGame()).getNoPlay());
        writeToServer(_server, _so);
    }

    private static void tryClickCard(WindowNetWork _server,ContainerMultiPresident _compo, MockGamePresident _mock, MockSocket _so) {
        _so.getOutput().clear();
        tryClickCard(component(_compo,_mock.currentCards()));
        writeToServer(_server, _so);
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
