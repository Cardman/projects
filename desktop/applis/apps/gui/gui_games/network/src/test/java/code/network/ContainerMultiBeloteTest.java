package code.network;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.facade.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.network.threads.*;
import code.gui.*;
import code.gui.events.*;
import code.mock.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class ContainerMultiBeloteTest extends EquallableNetworkUtil {
    @Test
    public void intro1() {
        MockGameBelote m_ = new MockGameBelote();
        WindowNetWork server_ = frameSingleBelote(m_);
        serverVersionOld(server_,4);
        retrievedSocket(server_, server_, 0);
        WindowNetWork client_ = frameSingleBelote(m_);
        clientVersionOld(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
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
        MockGameBelote m_ = new MockGameBelote();
        WindowNetWork server_ = frameSingleBelote(m_);
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSingleBelote(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        eventsCombo(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(),2);
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        eventsCombo(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(),2);
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(21, serverCompo2_.size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo2_);
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }

    @Test
    public void noGreatBid1() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());

        writeToServer(server_, socketServ_);
        deal(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(3, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(2, client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(clientCompo_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(clientCompo_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
        tryClickBid((ContainerBelote)client_.getNetg().getContainerGame(), m_);
        writeToServer(server_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        IdList<AbsCustComponent> serverCompoAf_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoAf_.size());
        assertTrue(serverCompoAf_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(2, server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(serverCompoAf_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(serverCompoAf_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
        IdList<AbsCustComponent> clientCompoAf_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoAf_.size());
        assertTrue(clientCompoAf_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid((ContainerBelote)server_.getNetg().getContainerGame(), m_);
        writeToServer(server_, socketServ_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo2_.size());
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(5, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(4, client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(clientCompo2_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(clientCompo2_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(clientCompo2_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(clientCompo2_.containsObj(client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT)))));
        tryClickBid((ContainerBelote)client_.getNetg().getContainerGame(), m_);

        writeToServer(server_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoAf2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoAf2_.size());
        assertTrue(serverCompoAf2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(4, server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(serverCompoAf2_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(serverCompoAf2_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(serverCompoAf2_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(serverCompoAf2_.containsObj(server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponent(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT)))));
        IdList<AbsCustComponent> clientCompoAf2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoAf2_.size());
        assertTrue(clientCompoAf2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid((ContainerBelote)server_.getNetg().getContainerGame(), m_);
        writeToServer(server_, socketServ_);
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
    }

    @Test
    public void noGreatBid2() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        sendClient(server_.getSockets(),server_);
        loopClient(server_.getSockets(),server_);
        loopServer2(server_.getSockets());
        sendClient(server_.getSockets(), client_);
        loopClient(server_.getSockets(),client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(),1);
        tryClick(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getSelectRules());
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketServ_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(server_, socketClient_);
        netPlayers(server_, client_);

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());

        writeToServer(server_, socketServ_);
        deal(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(16, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(80)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(clientCompo_.containsObj(((ContainerBelote)client_.getNetg().getContainerGame()).getFold()));

        tryClick(((ContainerBelote)client_.getNetg().getContainerGame()).getFold());
        writeToServer(server_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        IdList<AbsCustComponent> serverCompoAf_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(16, serverCompoAf_.size());
        assertTrue(serverCompoAf_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(80)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getBidsButtons().get(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(serverCompoAf_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getFold()));

        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getFold());
        writeToServer(server_, socketServ_);
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
        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }

    private static void tryClickBid(ContainerBelote _csb, MockGameBelote _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(_csb.getBids(), _mock.currentBid())));
    }

    private static void tryClickBidDealAll(ContainerBelote _csb, MockGameBelote _mock) {
        BidBeloteSuit bid_ = _mock.currentBid();
        tryClick(_csb.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(bid_.getPoints())).getButton());
        BidBeloteSuit cp_ = new BidBeloteSuit();
        cp_.setBid(bid_.getBid());
        cp_.setSuit(bid_.getSuit());
        CustList<AbsMouseListenerIntRel> rel_ = _csb.getBidsButtons().get(ContainerBelote.index(_csb.getBids(), cp_)).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        tryClick(_csb.getBidOk());
    }

    private static AbsCustComponent componentDog(ContainerMultiBelote _compo, CardBelote _cb) {
        HandBelote h_ = new HandBelote();
        h_.ajouterCartes(_compo.getCardsInDog());
        h_.trier(_compo.getDisplayingBelote().getDisplaying().getSuits(), _compo.getDisplayingBelote().getDisplaying().isDecreasing(), _compo.getBidMax());
        return _compo.tapisBelote().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
    }
    private static void tryClickCard(ContainerMultiBelote _compo, MockGameBelote _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }
    private static AbsCustComponent component(ContainerMultiBelote _compo, CardBelote _cb) {
        return _compo.getPanelHand().getComponent(_compo.getPlayerHand().getCards().indexOfObj(_cb));
    }
    private void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
        _m.getBids().add(_bid);
    }

    private void nextSlam(MockGameBelote _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextDiscard(MockGameBelote _m, CardBelote _bid) {
        _m.getDiscard().add(_bid);
    }
    private void nextDiscardIa(MockGameBelote _m, CardBelote... _bid) {
        _m.getDiscardIa().add(HandBelote.create(_bid));
    }

    private void nextCard(MockGameBelote _m, CardBelote _bid) {
        _m.getCards().add(_bid);
    }
    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
    private static void checkRules(ContainerMultiBelote _cont, IdList<AbsCustComponent> _tr) {
        DialogBeloteContent d_ = _cont.getDialogBeloteContent();
        assertTrue(_tr.containsObj(d_.getListeChoixFour().self()));
        assertTrue(_tr.containsObj(d_.getClassic()));
        assertTrue(_tr.containsObj(d_.getUnderTrumpingFoe()));
        assertTrue(_tr.containsObj(d_.getListeChoix().self()));
        assertTrue(_tr.containsObj(d_.getListChoiceTwo().self()));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidBelote.NO_TRUMP)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidBelote.ALL_TRUMP)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.THIRTY)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FIFTY)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.HUNDRED)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_1)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_7)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_8)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_9)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_10)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_JACK)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_QUEEN)));
        assertTrue(_tr.containsObj(d_.getDeclares().getVal(DeclaresBelote.FOUR_KING)));
        assertTrue(_tr.containsObj(_cont.getSelectRules()));
    }
}
