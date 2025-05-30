package code.network;

import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.network.threads.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class ContainerMultiTarotTest extends EquallableNetworkUtil {
    @Test
    public void intro1() {
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        serverVersionOld(server_,4);
        retrievedSocket(server_, server_, 0);
        WindowNetWork client_ = frameSingleTarot(m_);
        clientVersionOld(server_,client_);
        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);
        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size()-((MockCustComponent) ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        checkRules((ContainerMultiTarot) server_.getNetg().getContainerGame(),serverCompo_);
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
        MockGameTarot m_ = new MockGameTarot();
        WindowNetWork server_ = frameSingleTarot(m_);
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        ready(server_, server_, socketServ_);
        netPlayers(server_);

        WindowNetWork client_ = frameSingleTarot(m_);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        ready(server_, server_, socketServ_);
        netPlayers(server_);


        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompo_.size()-((MockCustComponent) ((ContainerMulti) server_.getNetg().getContainerGame()).getContainerMultiContent().getEditor()).getTreeAccessible().size());
        checkRules((ContainerMultiTarot) server_.getNetg().getContainerGame(),serverCompo_);
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
        checkRules((ContainerMultiTarot) server_.getNetg().getContainerGame(),serverCompo2_);
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
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(6, serverCompo_.size());
        assertTrue(serverCompo_.containsObj(compo(((ContainerTarot)server_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.FOLD), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompo_.containsObj(compo(((ContainerTarot)server_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.TAKE), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompo_.containsObj(compo(((ContainerTarot)server_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompo_.containsObj(compo(((ContainerTarot)server_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD_WITHOUT), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompo_.containsObj(compo(((ContainerTarot)server_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD_AGAINST), server_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(serverCompo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> clientCompo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompo_.size());
        assertTrue(clientCompo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid(server_,(ContainerMultiTarot)server_.getNetg().getContainerGame(), socketServ_, m_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> clientCompoSec_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(6, clientCompoSec_.size());
        assertTrue(clientCompoSec_.containsObj(compo(((ContainerTarot)client_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.FOLD), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompoSec_.containsObj(compo(((ContainerTarot)client_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.TAKE), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompoSec_.containsObj(compo(((ContainerTarot)client_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompoSec_.containsObj(compo(((ContainerTarot)client_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD_WITHOUT), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompoSec_.containsObj(compo(((ContainerTarot)client_.getNetg().getContainerGame()).getBids().indexOfObj(BidTarot.GUARD_AGAINST), client_.getNetg().getContainerGame().getPanneauBoutonsJeu())));
        assertTrue(clientCompoSec_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        IdList<AbsCustComponent> serverCompoSec_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(1, serverCompoSec_.size());
        assertTrue(serverCompoSec_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClickBid(server_,(ContainerMultiTarot)client_.getNetg().getContainerGame(), socketClient_, m_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size()-((MockCustComponent)((ContainerMultiTarot) server_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size()-((MockCustComponent)((ContainerMultiTarot) client_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
    }

    @Test
    public void noGreatBid2() {
        MockGameTarot m_ = new MockGameTarot();
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_7);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixThree().getCombo(),4);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> clientCompoTwo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(18, clientCompoTwo_.size());
        assertTrue(clientCompoTwo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_QUEEN)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_KNIGHT)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_4)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_5)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_JACK)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_6)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_2)));
    }

    @Test
    public void noGreatBid3() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_7);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixThree().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> clientCompoTwo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(18, clientCompoTwo_.size());
        assertTrue(clientCompoTwo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_QUEEN)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_KNIGHT)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_4)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_5)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_JACK)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_6)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_2)));
    }
    @Test
    public void greatBid1() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(16, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
    }
    @Test
    public void greatBid2() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());
        
        CardTarot cb_ = m_.currentDiscard();
        socketServ_.getOutput().clear();
        tryClickCard(componentUnion((ContainerMultiTarot) server_.getNetg().getContainerGame(),cb_));
        writeToServer(server_, socketServ_);
        loopServer2(server_.getSockets());

        socketServ_.getOutput().clear();
        tryClickCard(componentDog((ContainerMultiTarot) server_.getNetg().getContainerGame(),cb_));
        writeToServer(server_, socketServ_);
        loopServer2(server_.getSockets());

        discard(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(16, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
    }
    @Test
    public void greatBid3() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(18, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
    }
    @Test
    public void greatBid4() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid5() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);


        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid6() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid7() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid8() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid9() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscardIa(m_, CardTarot.HEART_1, CardTarot.DIAMOND_1, CardTarot.DIAMOND_4, CardTarot.DIAMOND_6, CardTarot.DIAMOND_QUEEN, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        nextCard(m_, CardTarot.HEART_KING);
        nextNoHandful(m_);
        nextMisere(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        choicePosition(server_,client_,client_,socketClient_,2);
        choicePosition(server_,client_,server_,socketServ_,1);
        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(8, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_2)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid10() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServerWithoutAllow(m_, server_, socketServ_);
        playIaWithout(server_, client_);
        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(16, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
    }
    @Test
    public void greatBid11() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServerWithoutAllow(m_, server_, socketServ_);
        playIaWithout(server_, client_);
        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid12() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.FALSE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServerWithoutAllow(m_, server_, socketServ_);
        playIaWithout(server_, client_);
        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid13() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid14() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid15() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid16() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(20, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
    }
    @Test
    public void greatBid16_1() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        callServerWithoutAllow(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(20, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
    }
    @Test
    public void greatBid16_2() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(20, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));

    }

    @Test
    public void greatBid16_3() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        discards(m_, server_, socketServ_);

        callServerWithoutAllow(m_, server_, socketServ_);
        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
    }
    @Test
    public void greatBid16_4() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextCall(m_, CardTarot.DIAMOND_KING);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        callServerWithoutAllow(m_, server_, socketServ_);

        discards(m_, server_, socketServ_);
        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(22, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(componentDog(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(componentUnion(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
    }
    @Test
    public void greatBid17() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        callServerWithoutAllow(m_, server_, socketServ_);

        discards(m_, server_, socketServ_);

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid18() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.TAKE);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextDiscard(m_, CardTarot.HEART_1);
        nextDiscard(m_, CardTarot.DIAMOND_1);
        nextDiscard(m_, CardTarot.DIAMOND_4);
        nextDiscard(m_, CardTarot.DIAMOND_6);
        nextDiscard(m_, CardTarot.DIAMOND_QUEEN);
        nextDiscard(m_, CardTarot.CLUB_KNIGHT);
        nextSlam(m_, BoolVal.FALSE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIaWithout(server_, client_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog()));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        tryClick(((ContainerTarot)server_.getNetg().getContainerGame()).getTakeCardDog());

        callServerWithoutAllow(m_, server_, socketServ_);

        discards(m_, server_, socketServ_);

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(28, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid19() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        slam(server_,server_,socketServ_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));

    }
    @Test
    public void greatBid20() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.GUARD_WITHOUT);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextBid(m_,BidTarot.FOLD);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        bidClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);

        IdList<AbsCustComponent> serverCompoDog_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(3, serverCompoDog_.size());
        assertTrue(serverCompoDog_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getValidateDog()));
        assertTrue(serverCompoDog_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSlamButton()));
        IdList<AbsCustComponent> clientCompoDog_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoDog_.size());
        assertTrue(clientCompoDog_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        slamNo(server_,server_,socketServ_);
        allow(server_,server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }

    @Test
    public void greatBid21() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCall(m_, CardTarot.DIAMOND_KING);
        nextSlam(m_, BoolVal.TRUE);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getDiscardAfterCall());
        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoOne_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(5, serverCompoOne_.size());
        assertTrue(serverCompoOne_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.HEART_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.SPADE_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoOne_.containsObj(componentCall((ContainerMultiTarot) server_.getNetg().getContainerGame(),CardTarot.CLUB_KING)));
        IdList<AbsCustComponent> clientCompoOne_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoOne_.size());
        assertTrue(clientCompoOne_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        callServer(m_, server_, socketServ_);


        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(24, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()), CardTarot.HEART_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_JACK)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_QUEEN)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KNIGHT)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_1)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertFalse(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
    }
    @Test
    public void greatBid22() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCall(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_7);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        callServerWithoutAllow(m_, server_, socketServ_);
        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        IdList<AbsCustComponent> clientCompoTwo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(18, clientCompoTwo_.size());
        assertTrue(clientCompoTwo_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()), CardTarot.HEART_QUEEN)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_KNIGHT)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_7)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_4)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.SPADE_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_5)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.DIAMOND_2)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_JACK)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_6)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_3)));
        assertTrue(clientCompoTwo_.containsObj(component(((ContainerMultiTarot) client_.getNetg().getContainerGame()),CardTarot.CLUB_2)));
    }

    @Test
    public void greatBid23() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCard(m_,CardTarot.TRUMP_19);
        nextCard(m_,CardTarot.HEART_1);
        nextCard(m_,CardTarot.HEART_2);
        nextCard(m_,CardTarot.TRUMP_18);
        nextCard(m_,CardTarot.SPADE_1);
        nextCard(m_,CardTarot.SPADE_2);
        nextCard(m_,CardTarot.TRUMP_17);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_2);
        nextCard(m_,CardTarot.TRUMP_16);
        nextCard(m_,CardTarot.CLUB_1);
        nextCard(m_,CardTarot.CLUB_2);
        nextCard(m_,CardTarot.TRUMP_15);
        nextCard(m_,CardTarot.HEART_5);
        nextCard(m_,CardTarot.HEART_3);
        nextCard(m_,CardTarot.TRUMP_14);
        nextCard(m_,CardTarot.SPADE_5);
        nextCard(m_,CardTarot.SPADE_3);
        nextCard(m_,CardTarot.TRUMP_13);
        nextCard(m_,CardTarot.DIAMOND_5);
        nextCard(m_,CardTarot.DIAMOND_3);
        nextCard(m_,CardTarot.TRUMP_12);
        nextCard(m_,CardTarot.CLUB_5);
        nextCard(m_,CardTarot.CLUB_3);
        nextCard(m_,CardTarot.TRUMP_11);
        nextCard(m_,CardTarot.HEART_6);
        nextCard(m_,CardTarot.HEART_4);
        nextCard(m_,CardTarot.TRUMP_10);
        nextCard(m_,CardTarot.SPADE_6);
        nextCard(m_,CardTarot.SPADE_4);
        nextCard(m_,CardTarot.TRUMP_9);
        nextCard(m_,CardTarot.DIAMOND_6);
        nextCard(m_,CardTarot.DIAMOND_4);
        nextCard(m_,CardTarot.TRUMP_8);
        nextCard(m_,CardTarot.CLUB_6);
        nextCard(m_,CardTarot.CLUB_4);
        nextCard(m_,CardTarot.TRUMP_7);
        nextCard(m_,CardTarot.HEART_8);
        nextCard(m_,CardTarot.HEART_9);
        nextCard(m_,CardTarot.TRUMP_6);
        nextCard(m_,CardTarot.SPADE_8);
        nextCard(m_,CardTarot.SPADE_9);
        nextCard(m_,CardTarot.TRUMP_5);
        nextCard(m_,CardTarot.DIAMOND_8);
        nextCard(m_,CardTarot.DIAMOND_9);
        nextCard(m_,CardTarot.TRUMP_4);
        nextCard(m_,CardTarot.CLUB_8);
        nextCard(m_,CardTarot.CLUB_9);
        nextCard(m_,CardTarot.TRUMP_3);
        nextCard(m_,CardTarot.HEART_10);
        nextCard(m_,CardTarot.HEART_JACK);
        nextCard(m_,CardTarot.TRUMP_2);
        nextCard(m_,CardTarot.SPADE_10);
        nextCard(m_,CardTarot.SPADE_JACK);
        nextCard(m_,CardTarot.HEART_KING);
        nextCard(m_,CardTarot.HEART_QUEEN);
        nextCard(m_,CardTarot.HEART_KNIGHT);
        nextCard(m_,CardTarot.SPADE_KING);
        nextCard(m_,CardTarot.SPADE_QUEEN);
        nextCard(m_,CardTarot.SPADE_KNIGHT);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextCard(m_,CardTarot.DIAMOND_JACK);
        nextCard(m_,CardTarot.CLUB_KING);
        nextCard(m_,CardTarot.CLUB_10);
        nextCard(m_,CardTarot.CLUB_JACK);
        nextCard(m_,CardTarot.TRUMP_1);
        nextCard(m_,CardTarot.DIAMOND_QUEEN);
        nextCard(m_,CardTarot.DIAMOND_KNIGHT);
        nextCard(m_,CardTarot.EXCUSE);
        nextCard(m_,CardTarot.CLUB_QUEEN);
        nextCard(m_,CardTarot.CLUB_KNIGHT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextHandful(m_,Handfuls.THREE,CardTarot.TRUMP_19,CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_5,CardTarot.TRUMP_4,CardTarot.TRUMP_3,CardTarot.TRUMP_2);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetThree());
        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getMiseres().getVal(Miseres.LOW_CARDS));
        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getMiseres().getVal(Miseres.POINT));
        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFive().getCombo(),2);
        ((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getNbAtoutsPoignee().setValue(13);
        tryClick(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBoutonPoignees());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        m_.getStacks().add(new DealTarot(Net.getGames(server_.getNet()).partieTarot().getDeal()));
        bidServer(m_, server_, client_, socketServ_);

        allow(server_, server_);

        IdList<AbsCustComponent> serverCompoTwo_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(52, serverCompoTwo_.size());
        assertTrue(serverCompoTwo_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.EXCUSE)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_17)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_16)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_11)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_10)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_6)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_5)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_1)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.HEART_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.SPADE_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.DIAMOND_KING)));
        assertTrue(serverCompoTwo_.containsObj(component(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.CLUB_KING)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.EXCUSE)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_19)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_18)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_17)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_16)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_15)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_14)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_13)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_12)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_11)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_10)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_9)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_8)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_7)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_6)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_5)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_4)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_3)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_2)));
        assertTrue(serverCompoTwo_.containsObj(componentHandful(((ContainerMultiTarot) server_.getNetg().getContainerGame()),CardTarot.TRUMP_1)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.FOUR).getButton()));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSelectedMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(serverCompoTwo_.containsObj(((ContainerTarot)server_.getNetg().getContainerGame()).getSelectedMiseres().getVal(Miseres.POINT)));
        tryClickHandful(((ContainerTarot)server_.getNetg().getContainerGame()).getHandfulsRadio().getVal(Handfuls.THREE));
        tryToggle(((ContainerTarot)server_.getNetg().getContainerGame()).getSelectedMiseres().getVal(Miseres.LOW_CARDS));
        tryToggle(((ContainerTarot)server_.getNetg().getContainerGame()).getSelectedMiseres().getVal(Miseres.POINT));

        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_1));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_2));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_3));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_4));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_5));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.TRUMP_6));
        tryClickCard(componentHandful((ContainerMultiTarot)server_.getNetg().getContainerGame(),CardTarot.EXCUSE));
        tryClickCard(m_,server_,server_,socketServ_);
        self(server_, server_);
        playIa(server_, client_);

        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);

        loopServer2(server_.getSockets());
        IdList<AbsCustComponent> serverCompoNext_ = ((MockCustComponent) server_.getPane()).getTreeAccessible();
        assertEq(2, serverCompoNext_.size()-((MockCustComponent)((ContainerMultiTarot) server_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));
        assertTrue(serverCompoNext_.containsObj(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton()));
        IdList<AbsCustComponent> clientCompoNext_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(1, clientCompoNext_.size()-((MockCustComponent)((ContainerMultiTarot) client_.getNetg().getContainerGame()).getDetail()).getTreeAccessible().size());
        assertTrue(clientCompoNext_.containsObj(((ContainerMulti)client_.getNetg().getContainerGame()).getContainerMultiContent().getReady()));

        readyPlayers(server_,socketServ_,client_,socketClient_);
        socketServ_.getOutput().clear();
        tryClick(((ContainerMulti)server_.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());
        writeToServer(server_, socketServ_);
        deal(server_,client_);
        assertTrue(Net.isProgressingGame(server_.getNet()));
    }
    @Test
    public void greatBid24() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCard(m_,CardTarot.TRUMP_19);
        nextCard(m_,CardTarot.HEART_1);
        nextCard(m_,CardTarot.HEART_2);
        nextCard(m_,CardTarot.TRUMP_18);
        nextCard(m_,CardTarot.SPADE_1);
        nextCard(m_,CardTarot.SPADE_2);
        nextCard(m_,CardTarot.TRUMP_17);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_2);
        nextCard(m_,CardTarot.TRUMP_16);
        nextCard(m_,CardTarot.CLUB_1);
        nextCard(m_,CardTarot.CLUB_2);
        nextCard(m_,CardTarot.TRUMP_15);
        nextCard(m_,CardTarot.HEART_5);
        nextCard(m_,CardTarot.HEART_3);
        nextCard(m_,CardTarot.TRUMP_14);
        nextCard(m_,CardTarot.SPADE_5);
        nextCard(m_,CardTarot.SPADE_3);
        nextCard(m_,CardTarot.TRUMP_13);
        nextCard(m_,CardTarot.DIAMOND_5);
        nextCard(m_,CardTarot.DIAMOND_3);
        nextCard(m_,CardTarot.TRUMP_12);
        nextCard(m_,CardTarot.CLUB_5);
        nextCard(m_,CardTarot.CLUB_3);
        nextCard(m_,CardTarot.TRUMP_11);
        nextCard(m_,CardTarot.HEART_6);
        nextCard(m_,CardTarot.HEART_4);
        nextCard(m_,CardTarot.TRUMP_10);
        nextCard(m_,CardTarot.SPADE_6);
        nextCard(m_,CardTarot.SPADE_4);
        nextCard(m_,CardTarot.TRUMP_9);
        nextCard(m_,CardTarot.DIAMOND_6);
        nextCard(m_,CardTarot.DIAMOND_4);
        nextCard(m_,CardTarot.TRUMP_8);
        nextCard(m_,CardTarot.CLUB_6);
        nextCard(m_,CardTarot.CLUB_4);
        nextCard(m_,CardTarot.TRUMP_7);
        nextCard(m_,CardTarot.HEART_8);
        nextCard(m_,CardTarot.HEART_9);
        nextCard(m_,CardTarot.TRUMP_6);
        nextCard(m_,CardTarot.SPADE_8);
        nextCard(m_,CardTarot.SPADE_9);
        nextCard(m_,CardTarot.TRUMP_5);
        nextCard(m_,CardTarot.DIAMOND_8);
        nextCard(m_,CardTarot.DIAMOND_9);
        nextCard(m_,CardTarot.TRUMP_4);
        nextCard(m_,CardTarot.CLUB_8);
        nextCard(m_,CardTarot.CLUB_9);
        nextCard(m_,CardTarot.TRUMP_3);
        nextCard(m_,CardTarot.HEART_10);
        nextCard(m_,CardTarot.HEART_JACK);
        nextCard(m_,CardTarot.TRUMP_2);
        nextCard(m_,CardTarot.SPADE_10);
        nextCard(m_,CardTarot.SPADE_JACK);
        nextCard(m_,CardTarot.HEART_KING);
        nextCard(m_,CardTarot.HEART_QUEEN);
        nextCard(m_,CardTarot.HEART_KNIGHT);
        nextCard(m_,CardTarot.SPADE_KING);
        nextCard(m_,CardTarot.SPADE_QUEEN);
        nextCard(m_,CardTarot.SPADE_KNIGHT);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextCard(m_,CardTarot.DIAMOND_JACK);
        nextCard(m_,CardTarot.CLUB_KING);
        nextCard(m_,CardTarot.CLUB_10);
        nextCard(m_,CardTarot.CLUB_JACK);
        nextCard(m_,CardTarot.TRUMP_1);
        nextCard(m_,CardTarot.DIAMOND_QUEEN);
        nextCard(m_,CardTarot.DIAMOND_KNIGHT);
        nextCard(m_,CardTarot.EXCUSE);
        nextCard(m_,CardTarot.CLUB_QUEEN);
        nextCard(m_,CardTarot.CLUB_KNIGHT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextMisere(m_,Miseres.LOW_CARDS,Miseres.POINT);
        nextHandful(m_,Handfuls.THREE,CardTarot.TRUMP_19,CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_12,CardTarot.TRUMP_11,CardTarot.TRUMP_10,CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_5,CardTarot.TRUMP_4,CardTarot.TRUMP_3,CardTarot.TRUMP_2);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetThree());
        serverVersionNew(server_,3);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getMiseres().getVal(Miseres.LOW_CARDS));
        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getMiseres().getVal(Miseres.POINT));
        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFive().getCombo(),2);
        ((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getNbAtoutsPoignee().setValue(13);
        tryClick(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBoutonPoignees());
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        choicePosition(server_,client_,server_,socketServ_,2);
        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);

        allow(server_, client_);
        IdList<AbsCustComponent> clientCompoTwo_ = ((MockCustComponent) client_.getPane()).getTreeAccessible();
        assertEq(28, clientCompoTwo_.size());
    }
    @Test
    public void tricks() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCall(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_7);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        callServerWithoutAllow(m_, server_, socketServ_);
        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getTricksHands());
        writeToServer(server_,socketClient_);
        self(server_,client_);
        assertTrue(client_.getDialogTricksTarot().getCommonFrame().isVisible());
    }
    @Test
    public void teams() {
        MockGameTarot m_ = new MockGameTarot();
        nextBid(m_,BidTarot.SLAM);
        nextCall(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_1);
        nextCard(m_,CardTarot.DIAMOND_KING);
        nextCard(m_,CardTarot.DIAMOND_7);
        nextCard(m_,CardTarot.DIAMOND_10);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextMisere(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        nextNoHandful(m_);
        WindowNetWork server_ = frameSingleTarot(m_);
        server_.getNetg().setFirstDealTarot(new TarotSampleFirstDealNetFour());
        serverVersionNew(server_,4);
        MockSocket socketServ_ = retrievedSocket(server_, server_, 0);

        WindowNetWork client_ = frameSingleTarot(m_);
        client_.getNetg().setFirstDealTarot(null);
        clientVersionNew(server_,client_);


        MockSocket socketClient_ = retrievedSocket(server_, client_, 1);
        intro(server_, client_);

        tryToggle(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getBids().getVal(BidTarot.SLAM));
        eventsCombo(((ContainerMultiTarot)server_.getNetg().getContainerGame()).getDialogTarotContent().getListeChoixFour().getCombo(),2);
        rules(server_,socketServ_);
        netPlayers(server_, client_);

        readyPlayers(server_, socketServ_, client_, socketClient_);

        play(server_, socketServ_);
        deal(server_, client_);
        bidServer(m_, server_, client_, socketServ_);
        allow(server_, server_);

        callServerWithoutAllow(m_, server_, socketServ_);
        playServer(m_, server_, client_, socketServ_);
        playClient(m_, server_, client_, socketClient_);
        playIa(server_, client_);
        playIa(server_, client_);
        playIa(server_, client_);
        allow(server_, client_);

        socketClient_.getOutput().clear();
        tryClick(client_.getTeams());
        writeToServer(server_,socketClient_);
        self(server_,client_);
        assertTrue(client_.getDialogTeamsPlayers().getCommonFrame().isVisible());
    }

    private void callServer(MockGameTarot _m, WindowNetWork _server, MockSocket _socketServ) {
        callServerWithoutAllow(_m, _server, _socketServ);
        allow(_server, _server);
    }

    private void callServerWithoutAllow(MockGameTarot _m, WindowNetWork _server, MockSocket _socketServ) {
        _socketServ.getOutput().clear();
        tryClickCall((ContainerMultiTarot) _server.getNetg().getContainerGame(), _m);
        writeToServer(_server, _socketServ);
    }


    private void discards(MockGameTarot _m, WindowNetWork _server, MockSocket _socketServ) {
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
        discard(_m, _server, _socketServ);
    }

    private void playServer(MockGameTarot _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketServ) {
        allow(_server, _server);
        tryClickCard(_m, _server, _server, _socketServ);
        self(_server, _server);
        playIa(_server, _client);
    }


    private void playClient(MockGameTarot _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        allow(_server, _client);
        tryClickCard(_m, _server, _client, _socketClient);
        self(_server, _client);
        playIa(_server, _client);
    }

    private void bidServer(MockGameTarot _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketServ) {
        allow(_server, _server);
        tryClickBid(_server,(ContainerMultiTarot) _server.getNetg().getContainerGame(), _socketServ, _m);
        playIa(_server, _client);
    }

    private void bidClient(MockGameTarot _m, WindowNetWork _server, WindowNetWork _client, MockSocket _socketClient) {
        allow(_server, _client);
        tryClickBid(_server,(ContainerMultiTarot) _client.getNetg().getContainerGame(), _socketClient, _m);
        playIa(_server, _client);
    }
    private void nextBid(MockGameTarot _m, BidTarot _bid) {
        _m.getBids().add(_bid);
    }

    private void nextCall(MockGameTarot _m, CardTarot _bid) {
        _m.getCalls().add(create(_bid));
    }

    private void nextSlam(MockGameTarot _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextDiscard(MockGameTarot _m, CardTarot _bid) {
        _m.getDiscard().add(_bid);
    }
    private void nextDiscardIa(MockGameTarot _m, CardTarot... _bid) {
        _m.getDiscardIa().add(create(_bid));
    }
    private void nextDiscardVarIa(MockGameTarot _m, boolean _slam, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private void nextDiscardVarIaCall(MockGameTarot _m, boolean _slam, CardTarot _card, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setCarteAppelee(create(_card));
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private void nextCard(MockGameTarot _m, CardTarot _bid) {
        _m.getCards().add(_bid);
    }

    private void nextNoHandful(MockGameTarot _m) {
        _m.getHandfuls().add(new IdList<Handfuls>());
        _m.getHandfulsCard().add(new HandTarot());
    }

    private void nextHandful(MockGameTarot _m, Handfuls _h, CardTarot... _ct) {
        _m.getHandfuls().add(new IdList<Handfuls>(_h));
        _m.getHandfulsCard().add(create(_ct));
    }

    private void nextMisere(MockGameTarot _m, Miseres... _mis) {
        _m.getMiseres().add(new IdList<Miseres>(_mis));
    }

    private HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }

    private void discard(MockGameTarot _m, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClickCard(componentUnion((ContainerMultiTarot) _target.getNetg().getContainerGame(), _m.currentDiscard()));
        writeToServer(_target, _socket);
        loopServer2(_target.getSockets());
    }

    private void slam(WindowNetWork _server, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerTarot)_target.getNetg().getContainerGame()).getSlamButton());
        writeToServer(_server, _socket);
        allow(_server,_target);
    }

    private void slamNo(WindowNetWork _server, WindowNetWork _target, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerTarot)_target.getNetg().getContainerGame()).getValidateDog());
        writeToServer(_server, _socket);
    }
    private static AbsCustComponent componentDog(ContainerMultiTarot _compo, CardTarot _cb) {
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.getCardsInDog());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.tapisTarot().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
        return compo(_compo.getCardsInDog().getCards().indexOfObj(_cb), _compo.tapisTarot().getCenterDeck());
    }
    private static AbsCustComponent componentUnion(ContainerMultiTarot _compo, CardTarot _cb) {
        HandTarot h_ = new HandTarot();
        h_.ajouterCartes(_compo.getTakerCardsDog());
        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
        return compo(h_.getCards().indexOfObj(_cb), _compo.getPanelHand());
    }
    private static AbsCustComponent componentHandful(ContainerMultiTarot _compo, CardTarot _cb) {
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.getCurrentIncludedTrumps());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.getIncludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
        return compo(_compo.getCurrentIncludedTrumps().getCards().indexOfObj(_cb), _compo.getIncludedTrumpsForHandful());
    }
    private static AbsCustComponent componentHandfulExc(ContainerMultiTarot _compo, CardTarot _cb) {
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.getCurrentExcludedTrumps());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.getExcludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
        return compo(_compo.getCurrentExcludedTrumps().getCards().indexOfObj(_cb), _compo.getExcludedTrumpsForHandful());
    }

    private static void tryClickCall(ContainerMultiTarot _compo, MockGameTarot _mock) {
        tryClickCard(componentCall(_compo,_mock.currentCall().premiereCarte()));
    }
    private static AbsCustComponent componentCall(ContainerMultiTarot _compo, CardTarot _cb) {
        int index_ = indexCall(_compo, _cb);
        return compo(index_, _compo.getPanelCallableCards());
    }

    private static int indexCall(ContainerMultiTarot _compo, CardTarot _cb) {
        HandTarot callableCards_ = _compo.getCallableCards();
        return callableCards_.getCards().indexOfObj(_cb);
    }

    private void tryClickCard(MockGameTarot _m, WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClickCard(((ContainerMultiTarot) _target.getNetg().getContainerGame()), _m);
        writeToServer(_server, _soc);
    }
    private static void tryClickCard(ContainerMultiTarot _compo, MockGameTarot _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }
    private static AbsCustComponent component(ContainerMultiTarot _compo, CardTarot _cb) {
        return compo(_compo.getPlayerHand().getCards().indexOfObj(_cb), _compo.getPanelHand());
    }
    private static void tryClickBid(WindowNetWork _server, ContainerMultiTarot _csb, MockSocket _soc, MockGameTarot _mock) {
        _soc.getOutput().clear();
        tryClickBid(_csb,_mock);
        writeToServer(_server,_soc);
    }
    private static void tryClickBid(ContainerMultiTarot _csb, MockGameTarot _mock) {
        tryClick((AbsButton) compo(_csb.getBids().indexOfObj(_mock.currentBid()), _csb.getPanneauBoutonsJeu()));
    }

    private static AbsCustComponent compo(int _ind, AbsPanel _pan) {
        return ((MockPanel)_pan).getComponent(_ind);
    }

    private void play(WindowNetWork _server, MockSocket _socket) {
        _socket.getOutput().clear();
        tryClick(((ContainerMulti) _server.getNetg().getContainerGame()).getContainerMultiContent().getPlayGameButton());

        writeToServer(_server, _socket);
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
        tryClick(((ContainerMultiTarot) _server.getNetg().getContainerGame()).getSelectRules());
        writeToServer(_server, _soc);
    }

    private void ready(WindowNetWork _server, WindowNetWork _target, MockSocket _soc) {
        _soc.getOutput().clear();
        tryClick(((ContainerMulti) _target.getNetg().getContainerGame()).getContainerMultiContent().getReady());
        writeToServer(_server, _soc);
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
    private static void checkRules(ContainerMultiTarot _cont, IdList<AbsCustComponent> _tr) {
        DialogTarotContent d_ = _cont.getDialogTarotContent();
        assertTrue(_tr.containsObj(d_.getMiseres().getVal(Miseres.TRUMP)));
        assertTrue(_tr.containsObj(d_.getMiseres().getVal(Miseres.SUIT)));
        assertTrue(_tr.containsObj(d_.getMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(_tr.containsObj(d_.getMiseres().getVal(Miseres.POINT)));
        assertTrue(_tr.containsObj(d_.getMiseres().getVal(Miseres.CHARACTER)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidTarot.TAKE)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidTarot.GUARD_WITHOUT)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidTarot.GUARD_AGAINST)));
        assertTrue(_tr.containsObj(d_.getBids().getVal(BidTarot.SLAM)));
        assertTrue(_tr.containsObj(d_.getAllowPlayCalledSuit()));
        assertTrue(_tr.containsObj(d_.getDiscardAfterCall()));
        assertTrue(_tr.containsObj(d_.getNbAtoutsPoignee()));
        assertTrue(_tr.containsObj(d_.getListeChoix().self()));
        assertTrue(_tr.containsObj(d_.getListeChoixTwo().self()));
        assertTrue(_tr.containsObj(d_.getListeChoixThree().self()));
        assertTrue(_tr.containsObj(d_.getListeChoixFour().self()));
        assertTrue(_tr.containsObj(d_.getListeChoixFive().self()));
        assertTrue(_tr.containsObj(d_.getBoutonPoignees()));
        assertTrue(_tr.containsObj(_cont.getSelectRules()));
    }
}
