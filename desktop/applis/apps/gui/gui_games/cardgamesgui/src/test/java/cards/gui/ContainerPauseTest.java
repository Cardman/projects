package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.gui.containers.*;
import cards.gui.dialogs.EditorCardsNonModalEvent;
import cards.main.CardsNonModalEvent;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class ContainerPauseTest extends EquallableCardsGuiUtil {

    @Test
    public void p1() {
        RulesBelote rules_ = rulesBelote();
        DealBelote deal_ = dealBelote(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_, new BidBelotePausingCardsAnims());
        tryAnimate(csb_);
        assertEq(2, csb_.getPaused().get());
        assertEq(1, csb_.partieBelote().tousContrats().size());
        tryClick(csb_.window().getPause());
        tryAnimate(csb_);
        assertEq(3, csb_.partieBelote().tousContrats().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }

    @Test
    public void p2() {
        RulesBelote rules_ = rulesBelote();
        DealBelote deal_ = dealBelote(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_, new CardBelotePausingCardsAnims());
        tryAnimate(csb_);
        assertTrue(new CardsNonModalEvent(csb_).act());
        assertFalse(new EditorCardsNonModalEvent(csb_.window()).act());
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        assertEq(6, csb_.getPaused().get());
        assertEq(1, csb_.partieBelote().getProgressingTrick().total());
        tryClick(csb_.window().getPause());
        tryAnimate(csb_);
        assertEq(3, csb_.partieBelote().getProgressingTrick().total());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }

    @Test
    public void p3() {
        RulesPresident r_ = rulesPresident();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_,new CardPresidentPausingCardsAnims());
        display(csp_);
        tryAnimate(csp_);
        assertTrue(CardsNonModalEvent.enabledEvents(null));
        assertEq(2, csp_.getPaused().get());
        assertEq(1, csp_.partiePresident().getProgressingTrick().total());
        tryClick(csp_.window().getPause());
        tryAnimate(csp_);
        assertEq(3, csp_.partiePresident().getProgressingTrick().total());
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
    public void p4() {
        RulesTarot rules_ = rulesTarot();
        DealTarot deal_ = dealTarot(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_, new BidTarotPausingCardsAnims());
        tryAnimate(cst_);
        assertEq(2, cst_.getPaused().get());
        assertEq(1, cst_.partieTarot().contrats());
        tryClick(cst_.window().getPause());
        tryAnimate(cst_);
        assertEq(4, cst_.partieTarot().contrats());
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
    public void p5() {
        RulesTarot rules_ = rulesTarot();
        DealTarot deal_ = dealTarot(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextCard(mock_, CardTarot.TRUMP_3);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextSlam(mock_,BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_, new CardTarotPausingCardsAnims());
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        tryClick(cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(7, cst_.getPaused().get());
        assertEq(1, cst_.partieTarot().getPliEnCours().total());
        tryClick(cst_.window().getPause());
        tryAnimate(cst_);
        assertEq(4, cst_.partieTarot().getPliEnCours().total());
    }
    @Test
    public void p6() {
        RulesBelote rules_ = rulesBelote();
        DealBelote deal_ = dealBelote(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_, new BidBelotePausingCardsAnims());
        tryAnimate(csb_);
        assertEq(2, csb_.getPaused().get());
        assertEq(1, csb_.partieBelote().tousContrats().size());
        assertFalse(new CardsNonModalEvent(csb_).act());
    }

    @Test
    public void p7() {
        MockProgramInfos pr_ = updateDialogDisplay(build());
        WindowCards wc_ = new WindowCards(stream(pr_), pr_);
        wc_.pause();
        assertEq(0,factory(wc_).size());
    }
    private static void tryClickCall(ContainerSingleTarot _compo, MockGameTarot _mock) {
        tryClickCard(componentCall(_compo,_mock.currentCall().premiereCarte()));
    }
    private void clickUniqButton(ContainerSingleTarot _cst) {
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) _cst.window().getPane()).getTreeAccessible();
        assertEq(1, tr4_.size());
        tryClick((AbsButton) tr4_.get(0));
    }
    private static AbsCustComponent component(ContainerSingleBelote _compo, CardBelote _cb) {
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
    private void tryClickNextPhase(ContainerSingleBelote _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
    }

    private static void tryClickBid(ContainerSingleBelote _csb, MockGameBelote _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(_csb.getBids(), _mock.currentBid())));
    }

    private static void tryClickBid(ContainerSingleTarot _csb, MockGameTarot _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(_csb.getBids().indexOfObj(_mock.currentBid())));
    }
    private ContainerSingleBelote editBelote(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock, AbsPausingCardsAnims _pause) {
        WindowCards wc_ = frameSingleBelote(_mock);
        wc_.setPausingCardsAnims(_pause);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSinglePresident editPresident(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock, AbsPausingCardsAnims _pause) {
        WindowCards wc_ = frameSinglePresident(_mock);
        wc_.setPausingCardsAnims(_pause);
        ContainerSinglePresident csp_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csp_);
        csp_.editerPresident(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csp_;
    }

    private ContainerSingleTarot editTarot(RulesTarot _rules, DealTarot _deal, MockGameTarot _mock, AbsPausingCardsAnims _pause) {
        WindowCards wc_ = frameSingleTarot(_mock);
        wc_.setPausingCardsAnims(_pause);
        ContainerSingleTarot cst_ = new ContainerSingleTarot(wc_);
        wc_.getCore().setContainerGame(cst_);
        cst_.editerTarot(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return cst_;
    }

    private GameBelote edited(DealBelote _deal, RulesBelote _rules) {
        GameBelote g_ = new GameBelote(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }
    private GamePresident edited(DealPresident _deal, RulesPresident _rules) {
        return edited(_deal,_rules,new Ints());
    }

    private GamePresident edited(DealPresident _deal, RulesPresident _rules, Ints _rks) {
        GamePresident g_ = new GamePresident(GameType.EDIT, _deal, _rules, _rks);
        g_.setNombre();
        return g_;
    }

    private GameTarot edited(DealTarot _deal, RulesTarot _rules) {
        GameTarot g_ = new GameTarot(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }
    private HandPresident create(CardPresident... _cb) {
        HandPresident c_ = new HandPresident();
        c_.setCards(new IdList<CardPresident>(_cb));
        return c_;
    }
    private static AbsCustComponent component(ContainerSinglePresident _compo, HandPresident _cb) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        GamePresident game_ = _compo.partiePresident();
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying(), game_.isReversed());
        int f_ = _compo.userHand().getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelHand().getComponent(s_);
    }
    private void display(ContainerSinglePresident _csp) {
        _csp.getDisplayingPresident().getDisplaying().setSuits(new IdList<Suit>(Suit.HEART,Suit.SPADE,Suit.DIAMOND,Suit.CLUB));
    }
    private void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
        _m.getBids().add(_bid);
    }

    private void nextCard(MockGameBelote _m, CardBelote _bid) {
        _m.getCards().add(_bid);
    }

    private void nextCard(MockGamePresident _m, HandPresident _bid) {
        _m.getCards().add(_bid);
    }
    private void nextCard(MockGameTarot _m, CardTarot _bid) {
        _m.getCards().add(_bid);
    }
    private void nextBid(MockGameTarot _m, BidTarot _bid) {
        _m.getBids().add(_bid);
    }
    private void nextSlam(MockGameTarot _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextNoHandful(MockGameTarot _m) {
        _m.getHandfuls().add(new IdList<Handfuls>());
        _m.getHandfulsCard().add(new HandTarot());
    }

//    private void nextHandful(MockGameTarot _m, Handfuls _h, CardTarot... _ct) {
//        _m.getHandfuls().add(new IdList<Handfuls>(_h));
//        _m.getHandfulsCard().add(create(_ct));
//    }
    private HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }
private void nextCall(MockGameTarot _m, CardTarot _bid) {
    _m.getCalls().add(create(_bid));
}
    private void nextMisere(MockGameTarot _m, Miseres... _mis) {
        _m.getMiseres().add(new IdList<Miseres>(_mis));
    }
    private static DealBelote dealBelote(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        firstHands(hands_);
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.CLUB_7);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.HEART_7);
        hands_.add(hand_);
        return new DealBelote(hands_, _dealer);
    }

    private static void firstHands(CustList<HandBelote> _hands) {
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        _hands.add(hand_);
    }

    private DealPresident mix(int _d) {
        return new DealPresident(dealPresident(), _d);
    }

    static CustList<HandPresident> dealPresident() {
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

    private static DealTarot dealTarot(int _dealer) {
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
        return new DealTarot(hands_, _dealer);
    }
    private RulesBelote rulesBelote() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
    private BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }

    private RulesPresident rulesPresident() {
        RulesPresident r_ = new RulesPresident(4);
        r_.getCommon().setNbDeals(1);
        return r_;
    }
    private RulesTarot rulesTarot() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
}
