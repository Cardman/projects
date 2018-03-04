package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;

public class GameBeloteWithTrumpSuitOneTest extends GameBeloteTest{

    @Before
    public void initialize() {
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
        game = new GameBelote(GameType.RANDOM,donne_,regles_);
        //game.resetNbPlisTotal();
    }

    @Test
    public void playableCards_BeginningTrick1Test() {
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingNoTrumpSuitTrick2Test() {
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.SPADE_9);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        assertNotEquals(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertTrue(playableCards_.display(),playableCards_.contient(CardBelote.SPADE_JACK));
        assertEq(1, playableCards_.total());
        assertEq(1, suits_.getVal(game.getPliEnCours().couleurDemandee()).total());
    }

    public static Object[] suits(){
        return $($(Suit.SPADE),$(Suit.CLUB),$(Suit.DIAMOND));
    }

    @Parameters(method = "suits")
    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe3Test(Suit _suit) {
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotEquals(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        assertTrue(!game.memeEquipe(game.getEntameur(),player_));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertTrue(playableCards_.display(),playableCards_.couleur(game.getContrat(),_suit).estVide());
    }

    @Test
    public void playableCards_WhileTrumpingTrickOverAFoe4Test() {
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertNotEquals(game.couleurAtout(), game.getPliEnCours().couleurDemandee());
        byte player_ = game.playerAfter(game.getEntameur());
        assertTrue(!game.memeEquipe(game.getEntameur(),player_));
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(suits_.getVal(Suit.HEART).total(), playableCards_.couleur(game.getContrat(),Suit.HEART).total());
        assertTrue(!suits_.getVal(Suit.HEART).estVide());
    }

    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards1Test() {
        biddingTrumpSuit(BidBelote.OTHER_SUIT,Suit.HEART);
        game.setPliEnCours();
        assertTrue(game.keepPlayingCurrentTrick());
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertTrue(game.keepPlayingCurrentTrick());
        byte player_ = game.playerAfter(game.getEntameur());
        game.getDistribution().jouer(player_,CardBelote.HEART_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        assertTrue(game.keepPlayingCurrentTrick());
        player_ = game.playerAfter(player_);
        game.getDistribution().jouer(player_,CardBelote.DIAMOND_JACK);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        assertTrue(game.keepPlayingCurrentTrick());
        player_ = game.playerAfter(player_);
        game.getDistribution().jouer(player_,CardBelote.DIAMOND_KING);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        assertTrue(!game.keepPlayingCurrentTrick());
        assertTrue(game.keepPlayingCurrentGame());
    }
}
