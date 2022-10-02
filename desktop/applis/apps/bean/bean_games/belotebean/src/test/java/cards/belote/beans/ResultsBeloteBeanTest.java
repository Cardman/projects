package cards.belote.beans;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.CoreResourcesAccess;
import cards.consts.GameType;
import cards.consts.Suit;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import org.junit.Test;

public final class ResultsBeloteBeanTest extends BeanBeloteCommonTs {

    private static final String SPADE = "spade";

    @Test
    public void win1() {
        assertTrue(callResultsBeloteBeanWin(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void win2() {
        assertFalse(callResultsBeloteBeanWin(displaying(beanResultsBelote(EN,results(game1(),1)))));
    }
    @Test
    public void eq1() {
        assertTrue(callResultsBeloteBeanEquality(displaying(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void eq2() {
        assertFalse(callResultsBeloteBeanEquality(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose1() {
        assertFalse(callResultsBeloteBeanLoose(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void loose2() {
        assertTrue(callResultsBeloteBeanLoose(displaying(beanResultsBelote(EN,results(game1(),1)))));
    }

    @Test
    public void successfulBid1() {
        assertTrue(callResultsBeloteBeanSuccessfulBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void successfulBid2() {
        assertFalse(callResultsBeloteBeanSuccessfulBid(displaying(beanResultsBelote(EN,results(game4(),0)))));
    }
    @Test
    public void midBid1() {
        assertTrue(callResultsBeloteBeanMidBid(displaying(beanResultsBelote(EN,results(game3(),1)))));
    }
    @Test
    public void midBid2() {
        assertFalse(callResultsBeloteBeanMidBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void looseBid1() {
        assertTrue(callResultsBeloteBeanFailedBid(displaying(beanResultsBelote(EN,results(game4(),0)))));
    }

    @Test
    public void looseBid2() {
        assertFalse(callResultsBeloteBeanFailedBid(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam1() {
        assertTrue(callResultsBeloteBeanSlam(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    @Test
    public void slam2() {
        assertFalse(callResultsBeloteBeanSlam(displaying(beanResultsBelote(EN,results(game2(),0)))));
    }

    @Test
    public void bidString1() {
        assertEq("",callResultsBeloteBeanBidString(displaying(beanResultsBelote(EN,results(game5(),0)))));
    }

    @Test
    public void bidString2() {
        assertEq(SPADE,callResultsBeloteBeanBidString(displaying(beanResultsBelote(EN,results(game1(),0)))));
    }

    private static ResultsBelote results(GameBelote _g, int _user) {
        ResultsBelote res_ = new ResultsBelote();
        res_.setGame(_g);
        res_.getRes().setUser((byte) _user);
        res_.initialize(fourPseudos("0","1","2","3"), new CustList<Longs>());
        res_.getRes().setGeneral(CoreResourcesAccess.key(Suit.SPADE)+SEP+ SPADE);
        res_.getRes().setSpecific("");
//        res_.getRes().setGeneral(readCoreResource());
//        res_.getRes().setSpecific(readResource());
        return res_;
    }

    private static StringList fourPseudos(String _one, String _two, String _three, String _four) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        return ps_;
    }

    private static GameBelote game5() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Loose((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.finEncherePremierTour();
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(), game_.playerHavingToBid());
        return game_;
    }

    private static GameBelote game4() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Loose((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        play(game_, 0, CardBelote.SPADE_JACK);
        play(game_, 1, CardBelote.SPADE_KING);
        play(game_, 2, CardBelote.SPADE_QUEEN);
        play(game_, 3, CardBelote.CLUB_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_9);
        play(game_, 1, CardBelote.CLUB_9);
        play(game_, 2, CardBelote.SPADE_8);
        play(game_, 3, CardBelote.HEART_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_1);
        play(game_, 1, CardBelote.DIAMOND_9);
        play(game_, 2, CardBelote.SPADE_7);
        play(game_, 3, CardBelote.HEART_9);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_KING);
        play(game_, 1, CardBelote.DIAMOND_8);
        play(game_, 2, CardBelote.DIAMOND_QUEEN);
        play(game_, 3, CardBelote.DIAMOND_1);
        tr(game_);
        play(game_, 3, CardBelote.DIAMOND_7);
        play(game_, 0, CardBelote.DIAMOND_10);
        play(game_, 1, CardBelote.HEART_8);
        play(game_, 2, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_10);
        play(game_, 1, CardBelote.CLUB_8);
        play(game_, 2, CardBelote.HEART_KING);
        play(game_, 3, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, 0, CardBelote.HEART_10);
        play(game_, 1, CardBelote.HEART_1);
        play(game_, 2, CardBelote.CLUB_1);
        play(game_, 3, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, 1, CardBelote.CLUB_JACK);
        play(game_, 2, CardBelote.CLUB_QUEEN);
        play(game_, 3, CardBelote.CLUB_10);
        play(game_, 0, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }
    private static GameBelote game3() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Eq((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        play(game_, 0, CardBelote.SPADE_JACK);
        play(game_, 1, CardBelote.SPADE_KING);
        play(game_, 2, CardBelote.SPADE_QUEEN);
        play(game_, 3, CardBelote.CLUB_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_9);
        play(game_, 1, CardBelote.CLUB_9);
        play(game_, 2, CardBelote.SPADE_8);
        play(game_, 3, CardBelote.HEART_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_1);
        play(game_, 1, CardBelote.DIAMOND_9);
        play(game_, 2, CardBelote.SPADE_7);
        play(game_, 3, CardBelote.HEART_9);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_KING);
        play(game_, 1, CardBelote.DIAMOND_8);
        play(game_, 2, CardBelote.DIAMOND_JACK);
        play(game_, 3, CardBelote.DIAMOND_1);
        tr(game_);
        play(game_, 3, CardBelote.DIAMOND_7);
        play(game_, 0, CardBelote.DIAMOND_10);
        play(game_, 1, CardBelote.HEART_8);
        play(game_, 2, CardBelote.DIAMOND_QUEEN);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_10);
        play(game_, 1, CardBelote.CLUB_8);
        play(game_, 2, CardBelote.HEART_KING);
        play(game_, 3, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, 0, CardBelote.HEART_10);
        play(game_, 1, CardBelote.HEART_1);
        play(game_, 2, CardBelote.CLUB_1);
        play(game_, 3, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, 1, CardBelote.CLUB_JACK);
        play(game_, 2, CardBelote.CLUB_QUEEN);
        play(game_, 3, CardBelote.CLUB_10);
        play(game_, 0, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }

    private static GameBelote game2() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Win((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        play(game_, 0, CardBelote.SPADE_JACK);
        play(game_, 1, CardBelote.SPADE_KING);
        play(game_, 2, CardBelote.SPADE_QUEEN);
        play(game_, 3, CardBelote.CLUB_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_9);
        play(game_, 1, CardBelote.CLUB_9);
        play(game_, 2, CardBelote.SPADE_8);
        play(game_, 3, CardBelote.HEART_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_1);
        play(game_, 1, CardBelote.DIAMOND_9);
        play(game_, 2, CardBelote.SPADE_7);
        play(game_, 3, CardBelote.HEART_9);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_10);
        play(game_, 1, CardBelote.HEART_8);
        play(game_, 2, CardBelote.DIAMOND_QUEEN);
        play(game_, 3, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_1);
        play(game_, 1, CardBelote.DIAMOND_8);
        play(game_, 2, CardBelote.DIAMOND_7);
        play(game_, 3, CardBelote.DIAMOND_KING);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_10);
        play(game_, 1, CardBelote.CLUB_8);
        play(game_, 2, CardBelote.HEART_KING);
        play(game_, 3, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, 0, CardBelote.HEART_10);
        play(game_, 1, CardBelote.HEART_1);
        play(game_, 2, CardBelote.CLUB_1);
        play(game_, 3, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, 1, CardBelote.CLUB_JACK);
        play(game_, 2, CardBelote.CLUB_10);
        play(game_, 3, CardBelote.CLUB_QUEEN);
        play(game_, 0, CardBelote.CLUB_KING);
        tr(game_);
        return game_;
    }
    private static GameBelote game1() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        play(game_, 0, CardBelote.SPADE_JACK);
        play(game_, 1, CardBelote.SPADE_KING);
        play(game_, 2, CardBelote.SPADE_QUEEN);
        play(game_, 3, CardBelote.CLUB_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_9);
        play(game_, 1, CardBelote.CLUB_9);
        play(game_, 2, CardBelote.SPADE_8);
        play(game_, 3, CardBelote.HEART_7);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_1);
        play(game_, 1, CardBelote.DIAMOND_9);
        play(game_, 2, CardBelote.SPADE_7);
        play(game_, 3, CardBelote.HEART_9);
        tr(game_);
        play(game_, 0, CardBelote.SPADE_10);
        play(game_, 1, CardBelote.HEART_8);
        play(game_, 2, CardBelote.DIAMOND_QUEEN);
        play(game_, 3, CardBelote.DIAMOND_JACK);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_1);
        play(game_, 1, CardBelote.DIAMOND_8);
        play(game_, 2, CardBelote.DIAMOND_7);
        play(game_, 3, CardBelote.DIAMOND_KING);
        tr(game_);
        play(game_, 0, CardBelote.DIAMOND_10);
        play(game_, 1, CardBelote.CLUB_8);
        play(game_, 2, CardBelote.HEART_KING);
        play(game_, 3, CardBelote.HEART_JACK);
        tr(game_);
        play(game_, 0, CardBelote.HEART_1);
        play(game_, 1, CardBelote.HEART_10);
        play(game_, 2, CardBelote.CLUB_1);
        play(game_, 3, CardBelote.HEART_QUEEN);
        tr(game_);
        play(game_, 0, CardBelote.CLUB_KING);
        play(game_, 1, CardBelote.CLUB_JACK);
        play(game_, 2, CardBelote.CLUB_10);
        play(game_, 3, CardBelote.CLUB_QUEEN);
        tr(game_);
        return game_;
    }

    private static void tr(GameBelote _game) {
        _game.ajouterDixDeDerPliEnCours();
        _game.setPliEnCours();
    }

    private static void play(GameBelote _game, int _nb, CardBelote _card) {
        _game.getDistribution().jouer((byte) _nb, _card);
        _game.ajouterUneCarteDansPliEnCours(_card);
    }

    private static DealBelote deal1Slam(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Eq(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Loose(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal1Win(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_1);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
}
