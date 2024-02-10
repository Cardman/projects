package cards.gui;

import cards.consts.*;
import cards.facade.*;
import cards.gui.containers.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ContainerPlayPresidentTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p2() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.HEART_4,CardPresident.SPADE_4));
        nextCard(mock_, create(CardPresident.HEART_5,CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p3() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(12, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4,CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p4() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        nextCard(mock_, create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(10, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p5() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        nextCard(mock_, create(CardPresident.DIAMOND_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p6() {
        RulesPresident r_ = rules();
        r_.setHasToPlay(true);
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(10, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
    }
    @Test
    public void p7() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.HEART_7));
        nextCard(mock_, create(CardPresident.DIAMOND_7));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    private void display(ContainerSinglePresident _csp) {
        _csp.getDisplayingPresident().getDisplaying().setSuits(new IdList<Suit>(Suit.HEART,Suit.SPADE,Suit.DIAMOND,Suit.CLUB));
    }
//    private HandPresident hand(ContainerSinglePresident _csb, int _i) {
//        return _csb.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands().getDistribution().hand((byte) _i);
//    }
//
//    private void play(GamePresident _gb, CardPresident _card) {
//        byte nbPlayers_ = _gb.getNombreDeJoueurs();
//        _gb.ajouterUneCarteDansPliEnCours(_gb.getProgressingTrick().getNextPlayer(nbPlayers_), _card);
//    }

//    private CustList<HandPresident> hand(HandPresident _h1, HandPresident _h2, HandPresident _h3,HandPresident _h4) {
//        CustList<HandPresident> l_ = new CustList<HandPresident>();
//        l_.add(_h1);
//        l_.add(_h2);
//        l_.add(_h3);
//        l_.add(_h4);
//        return l_;
//    }
    private HandPresident create(CardPresident... _cb) {
        HandPresident c_ = new HandPresident();
        c_.setCards(new IdList<CardPresident>(_cb));
        return c_;
    }

    private static void tryClickCard(ContainerSinglePresident _compo, MockGamePresident _mock) {
        tryClickCard(component(_compo,_mock.currentCards()));
    }
    private static AbsCustComponent component(ContainerSinglePresident _compo, HandPresident _cb) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        GamePresident game_ = _compo.partiePresident();
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying().isDecreasing(), game_.isReversed());
        int f_ = _compo.userHand().getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelHand().getComponent(s_);
    }
    private ContainerSinglePresident editPresident(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresident(_mock);
        ContainerSinglePresident csb_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerPresident(new GamePresident(GameType.EDIT, _deal, _rules, new Bytes()));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

//    private ContainerSinglePresident editPresidentOtherDisplay(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock) {
//        WindowCards wc_ = frameSinglePresidentWithEnd(_mock);
//        wc_.baseWindow().getFacadeCards().getDisplayingPresident().getDisplaying().setClockwise(true);
//        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
//        ContainerSinglePresident csb_ = new ContainerSinglePresident(wc_);
//        wc_.getCore().setContainerGame(csb_);
//        csb_.editerPresident(new GamePresident(GameType.EDIT, _deal, _rules));
//        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
//        return csb_;
//    }

    private ContainerSinglePresident loadPresident(GamePresident _game, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresident(_mock);
        Games games_ = new Games();
        games_.jouerPresident(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSinglePresident) wc_.getCore().getContainerGame();
    }
    private void tryClickNextPhase(ContainerSinglePresident _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
    }
    private DealPresident mix(int _d) {
        return new DealPresident(deal1(), (byte) _d);
    }

    private void nextCard(MockGamePresident _m, HandPresident _bid) {
        _m.getCards().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }
    private RulesPresident rules() {
        return new RulesPresident(4);
    }

    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }
}
