package cards.belote;

import code.util.CustList;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;

public class GameBeloteWithTrumpSuitEightTest extends GameBeloteWithTrumpSuit {

    static DealBelote initializeHands() {
        CustList<HandBelote> mains_ = new CustList<HandBelote>();
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.SPADE_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.CLUB_7);
        main_.ajouter(CardBelote.HEART_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.CLUB_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_7);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        mains_.add(main_);
        return new DealBelote(mains_,(byte)3);
    }

    @Test
    public void playableCards_WhileOverTrumpingOnFoe1Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_KING));
        game_.getDistribution().jouer(player_,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFreeOverTrumpingOnFoe2Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_8));
        game_.getDistribution().jouer(player_,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileUnderTrumpingOnFoe3Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setSousCoupeAdv(true);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileDiscardInsteadOfUnderTrumpingOnFoe4Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setSousCoupeAdv(false);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        assertTrue(!suits_.getVal(game_.couleurAtout()).estVide());
        playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingTrumpSuitGreaterOnFoe5Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setSousCoupeAdv(false);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.HEART_10));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        assertEq(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_JACK);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_1);
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingTrumpSuitAnyOverOnFoe6Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setSousCoupeAdv(false);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.HEART_7));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        assertEq(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_JACK);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_1);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_KING);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_8);
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingTrumpSuitAnyUnderOnFoe7Test() {
        RulesBelote regles_=new RulesBelote();
        regles_.setSousCoupeAdv(false);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.HEART_10));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        assertEq(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().hand(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
}
