package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;

public class GameBeloteWithTrumpSuitFourTest extends GameBeloteWithTrumpSuit {

    static DealBelote initializeHands() {
        EqList<HandBelote> mains_ = new EqList<HandBelote>();
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
        main_.ajouter(CardBelote.HEART_7);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.SPADE_7);
        main_.ajouter(CardBelote.SPADE_QUEEN);
        main_.ajouter(CardBelote.SPADE_8);
        main_.ajouter(CardBelote.DIAMOND_JACK);
        main_.ajouter(CardBelote.SPADE_10);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        main_.ajouter(CardBelote.DIAMOND_KING);
        main_.ajouter(CardBelote.CLUB_QUEEN);
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.CLUB_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.HEART_9);
        mains_.add(main_);
        return new DealBelote(mains_,(byte)3);
    }
    @Test
    public void playableCards_WhileOverTrumpingOnPartner1Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.OVERTRUMP_ONLY);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_KING));
        game_.getDistribution().jouer(player_,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileOverTrumpingOnPartner2Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_KING));
        game_.getDistribution().jouer(player_,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFreePlayingOnPartner1(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFullFreePlayingOnPartner1(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.OVERTRUMP_ONLY);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_JACK));
        game_.getDistribution().jouer(player_,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(hand_.contientCartes(playableCards_));
    }
    @Test
    public void playableCards_WhileFreeOverTrumpingOnPartner1Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.OVERTRUMP_ONLY);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_7));
        game_.getDistribution().jouer(player_,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
    @Test
    public void playableCards_WhileFreeOverTrumpingOnPartner2Test(){
        RulesBelote regles_=new RulesBelote();
        regles_.setGestionCoupePartenaire(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        GameBelote game_ = new GameBelote(GameType.RANDOM,initializeHands(),regles_);
        //game_.resetNbPlisTotal();

        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_1));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(trumps_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(trumps_));
        assertTrue(hand_.contient(CardBelote.HEART_7));
        game_.getDistribution().jouer(player_,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        assertTrue(hand_.contient(CardBelote.SPADE_7));
        game_.getDistribution().jouer(player_,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        player_ = game_.playerAfter(player_);
        hand_ = game_.getDistribution().main(player_);
        suits_ = hand_.couleurs(game_.getContrat());
        trumps_ = suits_.getVal(game_.couleurAtout());
        HandBelote trumpsHandOverCurrentGreatestTrump_ = new HandBelote();
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_9);
        trumpsHandOverCurrentGreatestTrump_.ajouter(CardBelote.HEART_QUEEN);
        playableCards_ = game_.playableCards(suits_);
        assertEq(trumpsHandOverCurrentGreatestTrump_,playableCards_);
    }
}