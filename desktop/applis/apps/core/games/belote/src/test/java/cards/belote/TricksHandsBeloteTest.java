package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import org.junit.Test;

public final class TricksHandsBeloteTest extends CommonGameBelote {
    @Test
    public void sortHands0Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setCardsHandsAtInitialState(new CustList<HandBelote>());
        tricksHands_.setDistribution(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.getTricks());
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setBid(game_.getBid());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.getDisplaying().setClockwise(true);
        assertTrue(displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setClockwise(false);
        assertTrue(!displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setDecreasing(true);
        displaying_.getDisplaying().setSuits(new IdList<Suit>());
        assertEq(8, tricksHands_.getTricks().size());
        assertEq(game_.getPreneur(), tricksHands_.getPreneur());
        assertSame(game_.getRules(),tricksHands_.getRules());
        assertSame(game_.getBid(),tricksHands_.getBid());
    }
    @Test
    public void sortHands1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs(),b_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs(),b_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs(),b_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs(),b_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 2);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) -1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 0, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 1, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 2, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(7, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) -1, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte)4).total());
    }

    private void setTricks(TricksHandsBelote _t, GameBelote _g) {
        _t.tricks(_g);
    }

    GameBelote getSimpleSlamDeal() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
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
}
