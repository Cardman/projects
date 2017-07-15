package cards.belote;
import static junitparams.JUnitParamsRunner.$;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;

import code.util.EnumMap;
import code.util.EqList;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;

public class GameBeloteWithoutTrumpSuitOneTest extends GameBeloteWithoutTrumpSuit {

    static Object[] bidsWithoutTrumpSuit() {
        return $($(BidBelote.NO_TRUMP),
                $(BidBelote.ALL_TRUMP));
    }
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
        main_.ajouter(CardBelote.HEART_8);
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
        main_.ajouter(CardBelote.HEART_7);
        main_.ajouter(CardBelote.CLUB_9);
        main_.ajouter(CardBelote.HEART_QUEEN);
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

    @Parameters(method="bidsWithoutTrumpSuit")
    @Test
    public void playableCards_BeginningTrick1Test(BidBelote _bid) {
        bidding(_bid);
        game.setPliEnCours();
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingNoTrumpSuitTrick2Test() {
        bidding(BidBelote.NO_TRUMP);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.SPADE_9);
        game.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertTrue(playableCards_.toString(),playableCards_.contient(CardBelote.SPADE_JACK));
        assertEq(1, playableCards_.total());
        assertEq(1, suits_.getVal(game.getPliEnCours().couleurDemandee()).total());
    }
    @Test
    public void playableCards_WhileFollowingAnyUnderAllTrumpSuitTrick3Test() {
        bidding(BidBelote.ALL_TRUMP);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.CLUB_JACK));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.CLUB_JACK);
        game.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.CLUB_9);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_10);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_7);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingGreaterAllTrumpSuitTrick4Test() {
        bidding(BidBelote.ALL_TRUMP);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.CLUB_KING));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.CLUB_KING);
        game.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.CLUB_9);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_10);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingAnyOverAllTrumpSuitTrick5Test() {
        bidding(BidBelote.ALL_TRUMP);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.HEART_7));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.HEART_7);
        game.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.HEART_1);
        expectedAllowedCards_.ajouter(CardBelote.HEART_KING);
        expectedAllowedCards_.ajouter(CardBelote.HEART_QUEEN);
        expectedAllowedCards_.ajouter(CardBelote.HEART_8);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Parameters(method="bidsWithoutTrumpSuit")
    @Test
    public void playableCards_WhileDiscarding6Test(BidBelote _bid) {
        bidding(_bid);
        game.setPliEnCours();
        assertEq(0,game.getEntameur());
        HandBelote hand_ = game.getDistribution().main(game.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        byte player_ = game.playerAfter(game.getEntameur());
        hand_ = game.getDistribution().main(player_);
        EnumMap<Suit,HandBelote> suits_ = hand_.couleurs(game.getContrat());
        HandBelote playableCards_ = game.playableCards(suits_);
        assertTrue(suits_.getVal(game.getPliEnCours().couleurDemandee()).estVide());
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.toString(), playableCards_.contientCartes(hand_));
    }
    @Parameters(method="bidsWithoutTrumpSuit")
    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards1Test(BidBelote _bid) {
        bidding(_bid);
        game.setPliEnCours();
        assertTrue(game.keepPlayingCurrentTrick());
        game.getDistribution().jouer(game.getEntameur(),CardBelote.DIAMOND_8);
        game.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        assertTrue(game.keepPlayingCurrentTrick());
        byte player_ = game.playerAfter(game.getEntameur());
        game.getDistribution().jouer(player_,CardBelote.HEART_QUEEN);
        game.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
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
