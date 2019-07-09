package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import org.junit.Test;

import static cards.belote.EquallableBeloteUtil.assertEq;

public final class TricksHandsBeloteTest extends CommonGameBelote {
    @Test
    public void sortHands1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrdreAvantEncheres(Order.NOTHING);
        displaying_.validate();
        displaying_.getCouleurs().clear();
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
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrdreAvantEncheres(Order.NOTHING);
        displaying_.validate();
        displaying_.getCouleurs().clear();
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
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrdreAvantEncheres(Order.NOTHING);
        displaying_.validate();
        displaying_.getCouleurs().clear();
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
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrdreAvantEncheres(Order.NOTHING);
        displaying_.validate();
        displaying_.getCouleurs().clear();
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
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 2);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(6, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(6, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) -1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 0, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 1, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(8, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) 2, (byte) 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(6, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(7, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setRules(game_.getRules());
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setBid(game_.getContrat());
        tricksHands_.setTricks(game_.getTricks(), game_.getNombreDeJoueurs());
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restituerMains(displaying_,(byte)4, (byte) -1, (byte) 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().main((byte) 0).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 1).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 2).total());
        assertEq(5, tricksHands_.getDistribution().main((byte) 3).total());
        assertEq(12, tricksHands_.getDistribution().main((byte)4).total());
    }
    GameBelote getSimpleSlamDeal() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDonneur());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setEnchere(BidBelote.SUIT);
        bid_.setCouleur(Suit.SPADE);
        game_.ajouterContrat(bid_, (byte) first_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
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
