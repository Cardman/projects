package cards.tarot;

import cards.tarot.enumerations.ReasonDiscard;
import code.util.CustList;
import code.util.core.*;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.IdMap;

public final class GameTarotDiscardingCardsOneTest extends CommonTarotGame {
    @Test
    public void discardableCards_WithTrumpsForGame1Test() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot takerHand_ = game_.getDistribution().hand(game_.getPreneur());
        IdMap<Suit,HandTarot> suits_ = takerHand_.couleurs();
        HandTarot discardableCards_ =
                GameTarotCallDiscard.getCartesEcartables(game_.getDistribution()
                    .derniereMain().total(),suits_);
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.TRUMP_20);
        expected_.ajouter(CardTarot.TRUMP_19);
        expected_.ajouter(CardTarot.TRUMP_15);
        expected_.ajouter(CardTarot.TRUMP_14);
        expected_.ajouter(CardTarot.TRUMP_13);
        expected_.ajouter(CardTarot.TRUMP_12);
        expected_.ajouter(CardTarot.TRUMP_11);
        expected_.ajouter(CardTarot.TRUMP_10);
        expected_.ajouter(CardTarot.TRUMP_6);
        expected_.ajouter(CardTarot.TRUMP_2);
        expected_.ajouter(CardTarot.HEART_10);
        assertEq(expected_.total(),discardableCards_.total());
        HandTarot ecartables_ = game_.ecartables();
        assertEq(expected_.total(), ecartables_.total());
        assertTrue(expected_.contientCartes(discardableCards_));
        assertTrue(expected_.contientCartes(ecartables_));
    }
    @Test
    public void discardableCards_WithoutTrumpsForGame2Test() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot takerHand_ = game_.getDistribution().hand(game_.getPreneur());
        IdMap<Suit,HandTarot> suits_ = takerHand_.couleurs();
        HandTarot discardableCards_ =
            GameTarotCallDiscard.getCartesEcartables(game_.getDistribution()
                    .derniereMain().total(),suits_);
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.CLUB_QUEEN);
        expected_.ajouter(CardTarot.CLUB_KNIGHT);
        expected_.ajouter(CardTarot.CLUB_JACK);
        expected_.ajouter(CardTarot.CLUB_3);
        expected_.ajouter(CardTarot.CLUB_2);
        expected_.ajouter(CardTarot.CLUB_1);
        expected_.ajouter(CardTarot.HEART_6);
        expected_.ajouter(CardTarot.HEART_5);
        expected_.ajouter(CardTarot.HEART_4);
        expected_.ajouter(CardTarot.HEART_9);
        expected_.ajouter(CardTarot.HEART_8);
        expected_.ajouter(CardTarot.HEART_7);
        expected_.ajouter(CardTarot.HEART_10);
        assertEq(expected_.total(),discardableCards_.total());
        HandTarot ecartables_ = game_.ecartables();
        assertEq(expected_.total(), ecartables_.total());
        assertTrue(expected_.contientCartes(discardableCards_));
        assertTrue(expected_.contientCartes(ecartables_));
    }
    @Test
    public void strategieEcartTest() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot h_ = game_.strategieEcart();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_QUEEN));
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.CLUB_JACK));
    }
    @Test
    public void strategieAppelApresEcart1Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        CallDiscard callDiscard_ = game_.strategieAppelApresEcart(false);
        assertTrue(callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void strategieAppelApresEcart2Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart2(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        CallDiscard callDiscard_ = game_.strategieAppelApresEcart(false);
        assertTrue(!callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void strategieAppelApresEcart3Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        CallDiscard callDiscard_ = callDiscard(game_);
        assertTrue(callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void strategieAppelApresEcart4Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart2(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        CallDiscard callDiscard_ = callDiscard(game_);
        assertTrue(!callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void strategieAppelApresEcart5Test() {
        RulesTarot regles_=initializeRulesWithBids2(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart3(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        CallDiscard callDiscard_ = game_.strategieAppelApresEcart(false);
        assertTrue(!callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.TRUMP_1));
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(6, h_.total());
    }
    @Test
    public void strategieAppelApresEcart6Test() {
        RulesTarot regles_=initializeRulesWithBids3(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart3(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        CallDiscard callDiscard_ = game_.strategieAppelApresEcart(false);
        assertTrue(!callDiscard_.isChelem());
        HandTarot c_ = callDiscard_.getCarteAppelee();
        assertEq(0, c_.total());
        HandTarot h_ = callDiscard_.getEcartAFaire();
        assertEq(6, h_.total());
    }
    @Test
    public void appelApresEcart1Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.appelApresEcart(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
        HandTarot c_ = game_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void appelApresEcart2Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart2(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.appelApresEcart(new DefGameTarot());
        assertTrue(!game_.chelemAnnonce());
        HandTarot c_ = game_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void appelApresEcart3Test() {
        RulesTarot regles_=initializeRulesWithBids3(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart3(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.appelApresEcart(new DefGameTarot());
        assertTrue(!game_.chelemAnnonce());
        HandTarot c_ = game_.getCarteAppelee();
        assertEq(0, c_.total());
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(6, h_.total());
    }
    @Test
    public void ecarter1Test() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ecarter(new DefGameTarot());
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_QUEEN));
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.CLUB_JACK));
    }
//    @Test
//    public void ecarter2Test() {
//        RulesTarot regles_=initializeRulesWithBids(true);
//        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
//        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
//        game_.ajouterContrat(BidTarot.FOLD,player_);
//        player_ = game_.playerAfter(player_);
//        game_.ajouterContrat(BidTarot.FOLD,player_);
//        player_ = game_.playerAfter(player_);
//        game_.ajouterContrat(BidTarot.GUARD,player_);
//        player_ = game_.playerAfter(player_);
//        game_.ajouterContrat(BidTarot.FOLD,player_);
//        player_ = game_.playerAfter(player_);
//        game_.ajouterContrat(BidTarot.FOLD,player_);
//        HandTarot cartesAppeler_ = new HandTarot();
//        cartesAppeler_.ajouter(CardTarot.HEART_KING);
//        game_.setCarteAppelee(cartesAppeler_);
//        game_.initConfianceAppele();
//        game_.setEntameur(game_.getPreneur());
//        game_.setPliEnCours(false);
//        game_.ajouterCartes(game_.getPreneur(),game_.derniereMain());
//        game_.ajouterUneCarteDansPliEnCours(game_.getPreneur(),CardTarot.CLUB_QUEEN);
//        game_.ecarter(false);
//        HandTarot h_ = game_.getTricks().first().getCartes();
//        assertEq(3, h_.total());
//        assertTrue(h_.contient(CardTarot.CLUB_QUEEN));
//        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
//        assertTrue(h_.contient(CardTarot.CLUB_JACK));
//    }
    @Test
    public void ecarter3Test() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ecarter(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
        HandTarot c_ = game_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void slamTest() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, slam(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.gererChienInconnu();
        game_.slam(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
    }
    @Test
    public void slamAlreadyTest() {
        RulesTarot regles_=initializeRulesWithBids(false);
        regles_.getAllowedBids().put(BidTarot.SLAM, BoolVal.TRUE);
        GameTarot game_ = new GameTarot(GameType.RANDOM, slam(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.SLAM);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.gererChienInconnu();
        game_.slam(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
    }
    @Test
    public void ecart1() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, strategieEcart(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        game_.ecart(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
        HandTarot c_ = game_.getCarteAppelee();
        assertEq(1, c_.total());
        assertTrue(c_.contient(CardTarot.SPADE_KING));
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(h_.contient(CardTarot.HEART_KNIGHT));
    }
    @Test
    public void ecart2() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ecart(new DefGameTarot());
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_QUEEN));
        assertTrue(h_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(h_.contient(CardTarot.CLUB_JACK));
    }
    @Test
    public void ecart3() {
        RulesTarot regles_=initializeRulesWithBids(false);
        GameTarot game_ = new GameTarot(GameType.RANDOM, slam(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.SPADE_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.gererChienInconnu();
        game_.ecart(new DefGameTarot());
        assertTrue(game_.chelemAnnonce());
    }
    @Test
    public void ecart4() {
        RulesTarot regles_=initializeRulesWithBids(true);
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        int player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD);
        game_.ecart(new DefGameTarot());
        HandTarot h_ = game_.getTricks().first().getCartes();
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardTarot.CLUB_1));
        assertTrue(h_.contient(CardTarot.CLUB_2));
        assertTrue(h_.contient(CardTarot.CLUB_3));
    }
    @Test
    public void reasonDiscard1() {
        assertSame(ReasonDiscard.KING,GameTarot.reasonDiscard(CardTarot.HEART_KING));
    }
    @Test
    public void reasonDiscard2() {
        assertSame(ReasonDiscard.TRUMP_CARD_OULDER,GameTarot.reasonDiscard(CardTarot.TRUMP_1));
    }
    @Test
    public void reasonDiscard3() {
        assertSame(ReasonDiscard.TRUMP_CARD,GameTarot.reasonDiscard(CardTarot.TRUMP_2));
    }
    @Test
    public void reasonDiscard4() {
        assertSame(ReasonDiscard.OULDER,GameTarot.reasonDiscard(CardTarot.EXCUSE));
    }

    static DealTarot initializeHands() {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        return new DealTarot(hands_,4);
    }
    static RulesTarot initializeRulesWithBids(boolean _discardAfterCall) {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        regles_.setDiscardAfterCall(_discardAfterCall);
        return regles_;
    }
    static RulesTarot initializeRulesWithBids2(boolean _discardAfterCall) {
        RulesTarot regles_=new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        regles_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        regles_.setDiscardAfterCall(_discardAfterCall);
        return regles_;
    }
    static RulesTarot initializeRulesWithBids3(boolean _discardAfterCall) {
        RulesTarot regles_=new RulesTarot(DealingTarot.DEAL_1_VS_3);
        regles_.setDealing(DealingTarot.DEAL_1_VS_3);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        regles_.setDiscardAfterCall(_discardAfterCall);
        return regles_;
    }

    static DealTarot strategieEcart() {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.EXCUSE);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hands_.add(hand_);
        return new DealTarot(hands_,4);
    }

    static DealTarot strategieEcart2() {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.TRUMP_6);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hands_.add(hand_);
        return new DealTarot(hands_,4);
    }

    static DealTarot strategieEcart3() {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hands_.add(hand_);
        return new DealTarot(hands_,3);
    }

    static DealTarot slam() {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.EXCUSE);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hands_.add(hand_);
        return new DealTarot(hands_,4);
    }

    private CallDiscard callDiscard(GameTarot _game) {
        _game.ajouterCartesUtilisateur();
//        _game.ajouterCartes(_game.getPreneur(), _game.derniereMain());
//        _game.setPliEnCours(false);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_KNIGHT);
        return _game.strategieAppelApresEcart(true);
    }
}
