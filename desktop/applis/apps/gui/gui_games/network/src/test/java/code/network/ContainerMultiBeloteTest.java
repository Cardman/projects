package code.network;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
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
        intro(server_, client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size()-((MockCustComponent) ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size()-((MockCustComponent) ((ContainerMulti) client_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        ready(server_, client_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size()-((MockCustComponent) ((ContainerMulti) client_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        ready(server_, client_, socketClient_);
        netPlayers(server_, client_);
        IdList<AbsCustComponent> clientCompo3_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo3_.size()-((MockCustComponent) ((ContainerMulti) client_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
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

        ready(server_, server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSingleBelote(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        ready(server_, server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size()-((MockCustComponent) ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo_);
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(2, clientCompo_.size()-((MockCustComponent) ((ContainerMulti) client_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame().self()));

        choicePosition(server_, client_, server_, socketServ_, 2);

        choicePosition(server_, client_, client_, socketClient_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(21, serverCompo2_.size()-((MockCustComponent) ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        checkRules((ContainerMultiBelote) server_.getNetg().getContainerGame(),serverCompo2_);
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size()-((MockCustComponent) ((ContainerMulti) client_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
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
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(3, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(2, client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(clientCompo_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompo_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        tryClickBid(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);
        IdList<AbsCustComponent> serverCompoAf_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoAf_.size());
        assertTrue(serverCompoAf_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(2, server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(serverCompoAf_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompoAf_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        IdList<AbsCustComponent> clientCompoAf_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoAf_.size());
        assertTrue(clientCompoAf_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid(m_, server_, server_, socketServ_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo2_.size());
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(5, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(4, client_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(clientCompo2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompo2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompo2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompo2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)client_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT)), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        tryClickBid(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoAf2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoAf2_.size());
        assertTrue(serverCompoAf2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertEq(4, server_.getNetg().getContainerGame().getPanneauBoutonsJeu().getComponentCount());
        assertTrue(serverCompoAf2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompoAf2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompoAf2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompoAf2_.containsObj(compo(ContainerBelote.index(((ContainerBelote)server_.getNetg().getContainerGame()).getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT)), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        IdList<AbsCustComponent> clientCompoAf2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoAf2_.size());
        assertTrue(clientCompoAf2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid(m_, server_, server_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) client_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
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
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourAll());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(),1);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
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

        fold(server_, client_, socketClient_);
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

        fold(server_, server_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) client_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        assertFalse(Net.isProgressingGame(server_.getNet()));
    }

    @Test
    public void greatBid1() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        allow(server_, client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(9, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_JACK)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_9)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_1)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_10)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_KING)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_QUEEN)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_8)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_7)));
    }

    @Test
    public void greatBid2() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.ALL_TRUMP));
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getBids().getVal(BidBelote.ALL_TRUMP));
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        allow(server_, client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(9, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_JACK)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_9)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_1)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_10)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_KING)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_QUEEN)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_8)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.SPADE_7)));
    }

    @Test
    public void greatBid3() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextCard(m_,CardBelote.SPADE_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        choicePosition(server_,client_,server_,socketServ_,3);
        choicePosition(server_,client_,client_,socketClient_,2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(9, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_JACK)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_9)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_1)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_10)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_KING)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_QUEEN)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_8)));
        assertTrue(clientCompo_.containsObj(component((ContainerMultiBelote) client_.getNetg().getContainerGame(),CardBelote.DIAMOND_7)));
    }

    @Test
    public void greatBid4() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextCard(m_,CardBelote.SPADE_7);
        nextCard(m_,CardBelote.DIAMOND_7);
        nextCard(m_,CardBelote.CLUB_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        choicePosition(server_,client_,client_,socketClient_,2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(10, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteRebelote()));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_JACK)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_9)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_1)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_10)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_KING)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_8)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_7)));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }

    @Test
    public void greatBid5() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextCard(m_,CardBelote.HEART_7);
        nextCard(m_,CardBelote.DIAMOND_7);
        nextCard(m_,CardBelote.CLUB_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourAll());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        choicePosition(server_,client_,client_,socketClient_,2);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(),1);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        bidDealAll(m_, server_, socketServ_, client_);
        playIa(server_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        allow(server_, server_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(9, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteRebelote()));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_JACK)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_9)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_1)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_10)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_KING)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_8)));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }

    @Test
    public void greatBid6() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextCard(m_,CardBelote.SPADE_7);
        nextCard(m_,CardBelote.DIAMOND_7);
        nextCard(m_,CardBelote.CLUB_7);
        nextCard(m_,CardBelote.HEART_KING);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourClassic());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        choicePosition(server_,client_,client_,socketClient_,2);
        tryToggle(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getDeclares().getVal(DeclaresBelote.HUNDRED));
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);
        tryToggle(((ContainerMultiBelote) client_.getNetg().getContainerGame()).getBeloteDeclare());
        socketClient_.getOutput().clear();
        tryClickCard((ContainerMultiBelote) client_.getNetg().getContainerGame(),m_);
        writeToServer(server_, socketClient_);
        self(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(11, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo_.containsObj(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteRebelote()));
        assertTrue(serverCompo_.containsObj(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteDeclare()));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_JACK)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_9)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_1)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_10)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_KING)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_8)));
        assertTrue(serverCompo_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_7)));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        tryToggle(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteRebelote());
        tryToggle(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteDeclare());
        tryClickCard(m_, server_, server_, socketServ_);
        self(server_, server_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo2_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(9, serverCompo2_.size());
        assertTrue(serverCompo2_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompo2_.containsObj(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getBeloteRebelote()));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_JACK)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_9)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_1)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_10)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_8)));
        assertTrue(serverCompo2_.containsObj(component((ContainerMultiBelote) server_.getNetg().getContainerGame(),CardBelote.HEART_7)));
        IdList<AbsCustComponent> clientCompo2_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo2_.size());
        assertTrue(clientCompo2_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

    }

    @Test
    public void greatBidThreePlayers1() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard());

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(15, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_9)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.SPADE_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_7)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_8)));
    }

    @Test
    public void greatBidThreePlayers2() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscard(m_, CardBelote.DIAMOND_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard());

        CardBelote cb_ = m_.currentDiscard();
        socketServ_.getOutput().clear();
        tryClickCard(componentUnion((ContainerMultiBelote) server_.getNetg().getContainerGame(),cb_));
        writeToServer(server_, socketServ_);
        loopServer2(server_.getSockets());

        socketServ_.getOutput().clear();
        tryClickCard(componentDog((ContainerMultiBelote) server_.getNetg().getContainerGame(),cb_));
        writeToServer(server_, socketServ_);
        loopServer2(server_.getSockets());

        discard(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(15, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_9)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.SPADE_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_8)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_7)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_8)));
    }

    @Test
    public void greatBidThreePlayers3() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscard(m_, CardBelote.CLUB_8);
        nextDiscard(m_, CardBelote.CLUB_QUEEN);
        nextDiscard(m_, CardBelote.DIAMOND_7);
        nextDiscard(m_, CardBelote.DIAMOND_8);
        nextDiscard(m_, CardBelote.DIAMOND_QUEEN);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard());

        discards(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(17, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getValidateDiscard()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getSlamButton()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_9)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.SPADE_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_8)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_7)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_8)));
    }

    @Test
    public void greatBidThreePlayers4() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscard(m_, CardBelote.CLUB_8);
        nextDiscard(m_, CardBelote.CLUB_QUEEN);
        nextDiscard(m_, CardBelote.DIAMOND_7);
        nextDiscard(m_, CardBelote.DIAMOND_8);
        nextDiscard(m_, CardBelote.DIAMOND_QUEEN);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard());

        discards(m_, server_, socketServ_);

        slam(server_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(11, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getBeloteRebelote()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.HEART_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.SPADE_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiBelote) server_.getNetg().getContainerGame()),CardBelote.CLUB_1)));
    }

    @Test
    public void greatBidThreePlayers5() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscard(m_, CardBelote.CLUB_8);
        nextDiscard(m_, CardBelote.CLUB_QUEEN);
        nextDiscard(m_, CardBelote.DIAMOND_7);
        nextDiscard(m_, CardBelote.DIAMOND_8);
        nextDiscard(m_, CardBelote.DIAMOND_QUEEN);
        nextSlam(m_, BoolVal.FALSE);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerBelote)server_.getNetg().getContainerGame()).getTakeCardDiscard());

        discards(m_, server_, socketServ_);

        slamNo(server_, server_, socketServ_);
        allow(server_,client_);
        IdList<AbsCustComponent> clientCompoTwo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(10, clientCompoTwo_.size());
        assertTrue(clientCompoTwo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_JACK)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_9)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_10)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_KING)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_QUEEN)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.SPADE_8)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.HEART_8)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.HEART_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiBelote) client_.getNetg().getContainerGame()),CardBelote.CLUB_7)));
    }

    @Test
    public void greatBidThreePlayers6() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscardIa(m_, CardBelote.CLUB_8, CardBelote.CLUB_QUEEN, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.DIAMOND_QUEEN);
        nextSlam(m_, BoolVal.TRUE);
        nextCard(m_, CardBelote.HEART_JACK);
        nextCard(m_, CardBelote.HEART_7);
        nextCard(m_, CardBelote.SPADE_7);

        nextCard(m_, CardBelote.HEART_9);
        nextCard(m_, CardBelote.HEART_8);
        nextCard(m_, CardBelote.CLUB_9);

        nextCard(m_, CardBelote.HEART_1);
        nextCard(m_, CardBelote.CLUB_7);
        nextCard(m_, CardBelote.DIAMOND_9);

        nextCard(m_, CardBelote.HEART_10);
        nextCard(m_, CardBelote.SPADE_8);
        nextCard(m_, CardBelote.DIAMOND_JACK);

        nextCard(m_, CardBelote.HEART_KING);
        nextCard(m_, CardBelote.SPADE_9);
        nextCard(m_, CardBelote.CLUB_JACK);

        nextCard(m_, CardBelote.HEART_QUEEN);
        nextCard(m_, CardBelote.SPADE_JACK);
        nextCard(m_, CardBelote.DIAMOND_KING);

        nextCard(m_, CardBelote.DIAMOND_1);
        nextCard(m_, CardBelote.SPADE_QUEEN);
        nextCard(m_, CardBelote.DIAMOND_10);

        nextCard(m_, CardBelote.SPADE_1);
        nextCard(m_, CardBelote.SPADE_KING);
        nextCard(m_, CardBelote.CLUB_KING);

        nextCard(m_, CardBelote.CLUB_1);
        nextCard(m_, CardBelote.SPADE_10);
        nextCard(m_, CardBelote.CLUB_10);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        choicePosition(server_, client_, server_, socketServ_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        m_.getStacks().add(new DealBelote(Net.getGames(server_.getNet()).partieBelote().getDeal()));
        bidClient(m_, server_, client_, socketClient_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);

        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size()-((MockCustComponent)((ContainerMultiBelote) client_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        readyPlayers(server_,socketServ_,client_,socketClient_);
        socketServ_.getOutput().clear();

        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        writeToServer(server_, socketServ_);
        deal(server_,client_);
        assertTrue(Net.isProgressingGame(server_.getNet()));
    }
    @Test
    public void tricks1() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscardIa(m_, CardBelote.CLUB_8, CardBelote.CLUB_QUEEN, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.DIAMOND_QUEEN);
        nextSlam(m_, BoolVal.TRUE);
        nextCard(m_, CardBelote.HEART_JACK);
        nextCard(m_, CardBelote.HEART_7);
        nextCard(m_, CardBelote.SPADE_7);

        nextCard(m_, CardBelote.HEART_9);
        nextCard(m_, CardBelote.HEART_8);
        nextCard(m_, CardBelote.CLUB_9);

        nextCard(m_, CardBelote.HEART_1);
        nextCard(m_, CardBelote.CLUB_7);
        nextCard(m_, CardBelote.DIAMOND_9);

        nextCard(m_, CardBelote.HEART_10);
        nextCard(m_, CardBelote.SPADE_8);
        nextCard(m_, CardBelote.DIAMOND_JACK);

        nextCard(m_, CardBelote.HEART_KING);
        nextCard(m_, CardBelote.SPADE_9);
        nextCard(m_, CardBelote.CLUB_JACK);

        nextCard(m_, CardBelote.HEART_QUEEN);
        nextCard(m_, CardBelote.SPADE_JACK);
        nextCard(m_, CardBelote.DIAMOND_KING);

        nextCard(m_, CardBelote.DIAMOND_1);
        nextCard(m_, CardBelote.SPADE_QUEEN);
        nextCard(m_, CardBelote.DIAMOND_10);

        nextCard(m_, CardBelote.SPADE_1);
        nextCard(m_, CardBelote.SPADE_KING);
        nextCard(m_, CardBelote.CLUB_KING);

        nextCard(m_, CardBelote.CLUB_1);
        nextCard(m_, CardBelote.SPADE_10);
        nextCard(m_, CardBelote.CLUB_10);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        choicePosition(server_, client_, server_, socketServ_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        m_.getStacks().add(new DealBelote(Net.getGames(server_.getNet()).partieBelote().getDeal()));
        bidClient(m_, server_, client_, socketClient_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getTricksHands());
        writeToServer(server_,socketClient_);
        self(server_,client_);
        assertTrue(client_.getDialogTricksBelote().getCommonFrame().isVisible());
    }

    @Test
    public void tricks2() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextCard(m_,CardBelote.HEART_7);
        nextCard(m_,CardBelote.DIAMOND_7);
        nextCard(m_,CardBelote.CLUB_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourAll());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(),1);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        bidDealAll(m_, server_, socketServ_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        socketServ_.getOutput().clear();
        tryClick(server_.getTricksHands());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        assertFalse(server_.getDialogTricksBelote().getCommonFrame().isVisible());
    }

    @Test
    public void teams1() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,0,BidBelote.SUIT));
        nextDiscardIa(m_, CardBelote.CLUB_8, CardBelote.CLUB_QUEEN, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.DIAMOND_QUEEN);
        nextSlam(m_, BoolVal.TRUE);
        nextCard(m_, CardBelote.HEART_JACK);
        nextCard(m_, CardBelote.HEART_7);
        nextCard(m_, CardBelote.SPADE_7);

        nextCard(m_, CardBelote.HEART_9);
        nextCard(m_, CardBelote.HEART_8);
        nextCard(m_, CardBelote.CLUB_9);

        nextCard(m_, CardBelote.HEART_1);
        nextCard(m_, CardBelote.CLUB_7);
        nextCard(m_, CardBelote.DIAMOND_9);

        nextCard(m_, CardBelote.HEART_10);
        nextCard(m_, CardBelote.SPADE_8);
        nextCard(m_, CardBelote.DIAMOND_JACK);

        nextCard(m_, CardBelote.HEART_KING);
        nextCard(m_, CardBelote.SPADE_9);
        nextCard(m_, CardBelote.CLUB_JACK);

        nextCard(m_, CardBelote.HEART_QUEEN);
        nextCard(m_, CardBelote.SPADE_JACK);
        nextCard(m_, CardBelote.DIAMOND_KING);

        nextCard(m_, CardBelote.DIAMOND_1);
        nextCard(m_, CardBelote.SPADE_QUEEN);
        nextCard(m_, CardBelote.DIAMOND_10);

        nextCard(m_, CardBelote.SPADE_1);
        nextCard(m_, CardBelote.SPADE_KING);
        nextCard(m_, CardBelote.CLUB_KING);

        nextCard(m_, CardBelote.CLUB_1);
        nextCard(m_, CardBelote.SPADE_10);
        nextCard(m_, CardBelote.CLUB_10);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetThreeClassic());

        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(), 2);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        choicePosition(server_, client_, server_, socketServ_, 2);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        m_.getStacks().add(new DealBelote(Net.getGames(server_.getNet()).partieBelote().getDeal()));
        bidClient(m_, server_, client_, socketClient_);
        bidServer(m_, server_, client_, socketServ_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getTeams());
        writeToServer(server_,socketClient_);
        self(server_,client_);
        assertTrue(client_.getDialogTeamsPlayers().getCommonFrame().isVisible());
    }

    @Test
    public void teams2() {
        MockGameBelote m_ = new MockGameBelote();
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(m_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextCard(m_,CardBelote.HEART_7);
        nextCard(m_,CardBelote.DIAMOND_7);
        nextCard(m_,CardBelote.CLUB_7);
        WindowNetWork server_ = frameSingleBelote(m_);
        server_.getNetg().setFirstDealBelote(new BeloteSampleFirstDealNetFourAll());

        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleBelote(m_);
        client_.getNetg().setFirstDealBelote(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiBelote) server_.getNetg().getContainerGame()).getDialogBeloteContent().getListeChoixFour().getCombo(),1);
        rules(server_, socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        bidDealAll(m_, server_, socketServ_, client_);
        foldClient(server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        socketServ_.getOutput().clear();
        tryClick(server_.getTeams());
        writeToServer(server_,socketServ_);
        loopServer2(server_.getSockets());
        assertFalse(server_.getDialogTeamsPlayers().getCommonFrame().isVisible());
    }

    private void discards(MockGameBelote _m, WindowNetWork _server, MockSocket _socketServ) {
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
    }


    private void bidServer(MockGameBelote _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketServ) {
        allow(_server, _server);

        tryClickBid(_m, _server, _server, _socketServ);
        playIa(_server, _client);
    }

    private void bidClient(MockGameBelote _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        allow(_server, _client);

        tryClickBid(_m, _server, _client, _socketClient);
        playIa(_server, _client);
    }

    private void bidDealAll(MockGameBelote _m, WindowNetWork _server, MockSocket _socketServ, WindowNetWork _client) {
        allow(_server, _server);
        _socketServ.getOutput().clear();
        tryClickBidDealAll((ContainerBelote) _server.getNetg().getContainerGame(), _m);
        writeToServer(_server, _socketServ);
        playIa(_server, _client);
    }

    private void foldClient(WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        allow(_server, _client);

        fold(_server, _client, _socketClient);
        playIa(_server, _client);
    }

    private void playServer(MockGameBelote _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketServ) {
        allow(_server, _server);
        tryClickCard(_m, _server, _server, _socketServ);
        self(_server, _server);
        playIa(_server, _client);
    }

    private void playClient(MockGameBelote _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        allow(_server, _client);
        tryClickCard(_m, _server, _client, _socketClient);
        self(_server, _client);
        playIa(_server, _client);
    }

    private void intro(WindowNetWork _server, WindowNetWork _client) {
        sendClient(_server.getSockets(), _server);
        loopClient(_server.getSockets(), _server);
        loopServer2(_server.getSockets());
        sendClient(_server.getSockets(), _client);
        loopClient(_server.getSockets(), _client);
    }

    private void rules(WindowNetWork _server, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMultiBelote) _server.getNetg().getContainerGame()).getSelectRules());
        writeToServer(_server, _soc);
    }

    private void ready(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMulti) _target.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(_server, _soc);
    }

    private void fold(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerBelote) _target.getNetg().getContainerGame()).getFold());
        writeToServer(_server, _soc);
    }

    private void tryClickCard(MockGameBelote _m, WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClickCard(((ContainerMultiBelote) _target.getNetg().getContainerGame()), _m);
        writeToServer(_server, _soc);
    }

    private void play(WindowNetWork _server, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerMulti) _server.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());

        writeToServer(_server, _socket);
    }


    private void tryClickBid(MockGameBelote _m, WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClickBid((ContainerBelote) _target.getNetg().getContainerGame(), _m);
        writeToServer(_server, _soc);
    }

    private void discard(MockGameBelote _m, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClickCard(componentUnion((ContainerMultiBelote) _target.getNetg().getContainerGame(), _m.currentDiscard()));
        writeToServer(_target, _socket);
        loopServer2(_target.getSockets());
    }

    private void slam(WindowNetWork _server, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerBelote)_target.getNetg().getContainerGame()).getSlamButton());
        writeToServer(_server, _socket);
        allow(_server,_target);
    }

    private void slamNo(WindowNetWork _server, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerBelote)_target.getNetg().getContainerGame()).getValidateDiscard());
        writeToServer(_server, _socket);
    }
    private void choicePosition(WindowNetWork _server, WindowNetWork _client, WindowNetWork _dest, MockSocket _socket, int _i) {
        _socket.getOutput().clear();
        eventsCombo(((ContainerMulti) _dest.getNetg().getContainerGame()).getContainerMultiContent().getChoiceOfPlaceForPlayingGame(), _i);
        writeToServer(_server, _socket);
        netPlayers(_server, _client);
    }

    private void readyPlayers(WindowNetWork _server, MockSocket _socketServ, WindowNetWork _client, MockSocket _socketClient) {
        _socketServ.getOutput().clear();
        ready(_server, _server, _socketServ);
        netPlayers(_server, _client);

        _socketClient.getOutput().clear();
        ready(_server, _client, _socketClient);
        netPlayers(_server, _client);
    }

    private static void tryClickBid(ContainerBelote _csb, MockGameBelote _mock) {
        tryClick((AbsButton) compo(ContainerBelote.index(_csb.getBids(), _mock.currentBid()), _csb.getPanneauBoutonsJeu()));
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
//        HandBelote h_ = new HandBelote();
//        h_.ajouterCartes(_compo.getCardsInDog());
//        h_.trier(_compo.getDisplayingBelote().getDisplaying().getSuits(), _compo.getDisplayingBelote().getDisplaying().isDecreasing(), _compo.getBidMax());
//        return _compo.tapisBelote().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
        return compo(_compo.getCardsInDog().getCards().indexOfObj(_cb), _compo.tapisBelote().getCenterDeck());
    }
    private static void tryClickCard(ContainerMultiBelote _compo, MockGameBelote _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }
    private static AbsCustComponent component(ContainerMultiBelote _compo, CardBelote _cb) {
        return compo(_compo.getPlayerHand().getCards().indexOfObj(_cb), _compo.getPanelHand());
    }
    private static AbsCustComponent componentUnion(ContainerMultiBelote _compo, CardBelote _cb) {
        HandBelote h_ = new HandBelote();
        h_.ajouterCartes(_compo.getTakerCardsDiscard());
        h_.trier(_compo.getDisplayingBelote().getDisplaying().getSuits(), _compo.getDisplayingBelote().getDisplaying().isDecreasing(), _compo.getBidMax());
        return compo(h_.getCards().indexOfObj(_cb), _compo.getPanelHand());
    }

    private static AbsCustComponent compo(int _index, AbsPanel _pan) {
        return ((MockPanel)_pan).getComponent(_index);
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
