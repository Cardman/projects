package cards.gui;

import cards.consts.*;
import cards.facade.Games;
import cards.gui.containers.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class ContainerPlayTarotTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p2() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertFalse(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p3() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p4() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p5() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p6() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p7() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    private HandTarot hand(ContainerSingleTarot _csb, int _i) {
        return _csb.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTricksHands().getDistribution().hand((byte) _i);
    }

    private void play(GameTarot _gb, CardTarot _card) {
        _gb.ajouterUneCarteDansPliEnCours(_card);
    }


    private CustList<HandTarot> hand(HandTarot _h1, HandTarot _h2, HandTarot _h3, HandTarot _h4) {
        CustList<HandTarot> l_ = new CustList<HandTarot>();
        l_.add(_h1);
        l_.add(_h2);
        l_.add(_h3);
        l_.add(_h4);
        return l_;
    }
    private HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }

    private void tryClickNextPhase(ContainerSingleTarot _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
    }

    private static void tryClickBid(ContainerSingleTarot _csb, MockGameTarot _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(_csb.getBids().indexOfObj(_mock.currentBid())));
    }


    private static void tryClickCard(ContainerSingleTarot _compo, MockGameTarot _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }

    private static void tryClickCall(ContainerSingleTarot _compo, MockGameTarot _mock) {
        tryClickCard(componentCall(_compo,_mock.currentCall().premiereCarte()));
    }
    private static AbsCustComponent component(ContainerSingleTarot _compo, CardTarot _cb) {
        return _compo.getPanelHand().getComponent(_compo.userHand().getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentCall(ContainerSingleTarot _compo, CardTarot _cb) {
        int index_ = indexCall(_compo, _cb);
        return _compo.getPanelCallableCards().getComponent(index_);
    }

    private static int indexCall(ContainerSingleTarot _compo, CardTarot _cb) {
        GameTarot partie_ = _compo.partieTarot();
        HandTarot callableCards_ = partie_.callableCards();
        return callableCards_.getCards().indexOfObj(_cb);
    }

    private ContainerSingleTarot editTarot(RulesTarot _rules, DealTarot _deal, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarot(_mock);
        ContainerSingleTarot cst_ = new ContainerSingleTarot(wc_);
        wc_.getCore().setContainerGame(cst_);
        cst_.editerTarot(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }

    private ContainerSingleTarot editTarotOtherDisplay(RulesTarot _rules, DealTarot _deal, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSingleTarot cst_ = new ContainerSingleTarot(wc_);
        wc_.getCore().setContainerGame(cst_);
        cst_.editerTarot(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }

    private GameTarot edited(DealTarot _deal, RulesTarot _rules) {
        GameTarot g_ = new GameTarot(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }

    
    private ContainerSingleTarot loadTarot(GameTarot _game, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarot(_mock);
        Games games_ = new Games();
        games_.jouerTarot(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleTarot) wc_.getCore().getContainerGame();
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock) {
        return loadTarotOtherDisplay(_game,_mock,0);
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock, int _i) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock,_i);
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        Games games_ = new Games();
        games_.jouerTarot(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleTarot) wc_.getCore().getContainerGame();
    }

    /*private ContainerSingleTarot modifyTarot(RulesTarot _rules, MockGameTarot _mock) {
        return modifyTarot(_rules,_mock,0);
    }
    private ContainerSingleTarot modifyTarot(RulesTarot _rules, MockGameTarot _mock, int _i) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock,_i);
        wc_.getCore().getFacadeCards().setReglesTarot(_rules);
        wc_.getCore().setFirstDealTarot(new TarotSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.TAROT));
        ContainerSingleTarot cst_ = (ContainerSingleTarot) wc_.getCore().getContainerGame();
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }*/
    private void nextBid(MockGameTarot _m, BidTarot _bid) {
        _m.getBids().add(_bid);
    }

    private void nextCall(MockGameTarot _m, CardTarot _bid) {
        _m.getCalls().add(create(_bid));
    }

    private void nextSlam(MockGameTarot _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextCard(MockGameTarot _m, CardTarot _bid) {
        _m.getCards().add(_bid);
    }

    private RulesTarot rules() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private static DealTarot deal1(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

}
