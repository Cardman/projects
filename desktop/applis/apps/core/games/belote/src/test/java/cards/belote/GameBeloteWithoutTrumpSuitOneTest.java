package cards.belote;

import code.util.CustList;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.util.IdMap;

public class GameBeloteWithoutTrumpSuitOneTest extends GameBeloteWithoutTrumpSuit {

    public GameBelote initialize() {
        CustList<HandBelote> mains_ = new CustList<HandBelote>();
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
        DealBelote donne_ = new DealBelote(mains_,3);
        RulesBelote regles_=new RulesBelote();
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return new GameBelote(GameType.RANDOM,donne_,regles_);
    }
    @Test
    public void playableCards_BeginningTrick1Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.NO_TRUMP);
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_BeginningTrick2Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileFollowingNoTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.NO_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.SPADE_9));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(playableCards_.contient(CardBelote.SPADE_JACK));
        assertEq(1, playableCards_.total());
        assertEq(1, suits_.getVal(game_.couleurDemandee()).total());
    }

    @Test
    public void playableCards_WhileFollowingAnyUnderAllTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.CLUB_JACK));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.CLUB_9);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_10);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_7);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingGreaterAllTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.CLUB_KING));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.CLUB_9);
        expectedAllowedCards_.ajouter(CardBelote.CLUB_10);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Test
    public void playableCards_WhileFollowingAnyOverAllTrumpSuitTrick1(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.HEART_7));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        HandBelote expectedAllowedCards_ = new HandBelote();
        expectedAllowedCards_.ajouter(CardBelote.HEART_1);
        expectedAllowedCards_.ajouter(CardBelote.HEART_KING);
        expectedAllowedCards_.ajouter(CardBelote.HEART_QUEEN);
        expectedAllowedCards_.ajouter(CardBelote.HEART_8);
        assertEq(expectedAllowedCards_,playableCards_);
    }
    @Test
    public void playableCards_WhileDiscarding1Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.NO_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(suits_.getVal(game_.couleurDemandee()).estVide());
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void playableCards_WhileDiscarding2Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        assertEq(0, game_.getEntameur());
        HandBelote hand_ = game_.getDistribution().hand(game_.getEntameur());
        assertTrue(hand_.contient(CardBelote.DIAMOND_8));
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        int player_ = game_.playerAfter(game_.getEntameur());
        hand_ = game_.getDistribution().hand(player_);
        IdMap<Suit,HandBelote> suits_ = hand_.couleurs(game_.getBid());
        HandBelote playableCards_ = game_.playableCards(suits_);
        assertTrue(suits_.getVal(game_.couleurDemandee()).estVide());
        assertEq(hand_.total(), playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
    }
    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards1Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.NO_TRUMP);
        assertTrue(game_.keepPlayingCurrentTrick());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        int player_ = game_.playerAfter(game_.getEntameur());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        assertTrue(!game_.keepPlayingCurrentTrick());
        assertTrue(game_.keepPlayingCurrentGame());
    }
    @Test
    public void keepPlayingCurrentTrick_keepPlayingCards2Test(){
        GameBelote game_ = initialize();
        bidding(game_, BidBelote.ALL_TRUMP);
        assertTrue(game_.keepPlayingCurrentTrick());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        assertTrue(game_.keepPlayingCurrentTrick());
        int player_ = game_.playerAfter(game_.getEntameur());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        assertTrue(!game_.keepPlayingCurrentTrick());
        assertTrue(game_.keepPlayingCurrentGame());
    }

}