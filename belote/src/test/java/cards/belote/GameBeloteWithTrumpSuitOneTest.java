package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;

public class GameBeloteWithTrumpSuitOneTest extends GameBeloteWithTrumpSuit {

    public GameBelote initialize() {
        EqList<HandBelote> mains_ = new EqList<HandBelote>();
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_QUEEN);
        main_.ajouter(CardBelote.HEART_10);
        main_.ajouter(CardBelote.SPADE_9);
        main_.ajouter(CardBelote.CLUB_JACK);
        main_.ajouter(CardBelote.DIAMOND_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.HEART_1);
        main_.ajouter(CardBelote.HEART_KING);
        main_.ajouter(CardBelote.SPADE_JACK);
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
        main_.ajouter(CardBelote.SPADE_1);
        main_.ajouter(CardBelote.CLUB_8);
        mains_.add(main_);
        main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_9);
        main_.ajouter(CardBelote.CLUB_KING);
        main_.ajouter(CardBelote.HEART_QUEEN);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_8);
        main_.ajouter(CardBelote.CLUB_10);
        main_.ajouter(CardBelote.DIAMOND_10);
        main_.ajouter(CardBelote.HEART_JACK);
        main_.ajouter(CardBelote.CLUB_1);
        main_.ajouter(CardBelote.HEART_9);
        main_.ajouter(CardBelote.SPADE_KING);
        main_.ajouter(CardBelote.DIAMOND_7);
        mains_.add(main_);
        DealBelote donne_ = new DealBelote(mains_,(byte)3);
        RulesBelote regles_=new RulesBelote();
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        return new GameBelote(GameType.RANDOM,donne_,regles_);
    }
    @Test
    public void playableCards_BeginningTrick1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingNoTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.contient(CardBelote.SPADE_JACK));
        assertEq(1, playableCards_.total());
        assertEq(1, suits_.getVal(game_.getPliEnCours().couleurDemandee()).total());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe1Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.SPADE).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe2Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.CLUB).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe3Test(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.couleur(game_.getContrat(),Suit.DIAMOND).estVide());
    }
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe4(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().main(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotSame(game_.couleurAtout(), game_.getPliEnCours().couleurDemandee());
        byte player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getContrat());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(suits_.getVal(Suit.HEART).total(), playableCards_.couleur(game_.getContrat(),Suit.HEART).total());
        assertTrue(!suits_.getVal(Suit.HEART).estVide());
    }
    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards1(){
        GameBelote game_ = initialize();
        biddingTrumpSuit(game_,BidBelote.OTHER_SUIT,Suit.HEART);
        game_.setPliEnCours();
        assertTrue(game_.keepPlayingCurrentTrick());
        game_.getDistribution().jouer(game_.getEntameur(),CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.getDistribution().jouer(player_,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.getDistribution().jouer(player_,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        assertTrue(!game_.keepPlayingCurrentTrick());
        assertTrue(game_.keepPlayingCurrentGame());
    }
}