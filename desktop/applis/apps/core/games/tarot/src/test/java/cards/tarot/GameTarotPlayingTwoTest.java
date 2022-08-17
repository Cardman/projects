package cards.tarot;

import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.EnumMap;

public class GameTarotPlayingTwoTest extends CommonTarotGame {

    static DealTarot initializeHands(byte _dealer) {
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
        hand_.ajouter(CardTarot.CLUB_KING);
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
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.DIAMOND_KING);
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
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
    static RulesTarot initializeRulesWithBids() {
        RulesTarot regles_=new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
    @Test
    public void playableCards_constrainedOverTrumpingTrick1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 4), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.CLUB_QUEEN);
        discardedCards_.ajouter(CardTarot.DIAMOND_QUEEN);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game_.supprimerCartes(game_.getPreneur(),discardedCards_);

        game_.setPliEnCours(false);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.addCurTrick();
        game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
        game_.setPliEnCours(true);
        game_.jouer(game_.getEntameur(),CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.jouer(player_,CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.TRUMP_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.EXCUSE);
        expected_.ajouter(CardTarot.TRUMP_21);
        expected_.ajouter(CardTarot.TRUMP_20);
        expected_.ajouter(CardTarot.TRUMP_19);
        expected_.ajouter(CardTarot.TRUMP_15);
        expected_.ajouter(CardTarot.TRUMP_14);
        expected_.ajouter(CardTarot.TRUMP_13);
        expected_.ajouter(CardTarot.TRUMP_12);
        expected_.ajouter(CardTarot.TRUMP_11);
        expected_.ajouter(CardTarot.TRUMP_10);
        expected_.ajouter(CardTarot.TRUMP_6);

        player_ = game_.playerAfter(player_);
        HandTarot hand_ = game_.getDistribution().hand(player_);
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
        //
    }

    @Test
    public void playableCards_freeOverTrumpingTrick2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.CLUB_QUEEN);
        discardedCards_.ajouter(CardTarot.DIAMOND_QUEEN);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game_.supprimerCartes(game_.getPreneur(),discardedCards_);

        game_.setPliEnCours(false);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.addCurTrick();
        game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
        game_.setPliEnCours(true);
        game_.jouer(game_.getEntameur(),CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.jouer(player_,CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.TRUMP_5);
        expected_.ajouter(CardTarot.TRUMP_4);
        expected_.ajouter(CardTarot.TRUMP_3);
        player_ = game_.playerAfter(player_);
        HandTarot hand_ = game_.getDistribution().hand(player_);
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
        //
    }
    @Test
    public void playableCards_underTrumpingTrick3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.CLUB_QUEEN);
        discardedCards_.ajouter(CardTarot.DIAMOND_QUEEN);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game_.supprimerCartes(game_.getPreneur(),discardedCards_);

        game_.setPliEnCours(false);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.addCurTrick();
        game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
        game_.setPliEnCours(true);
        game_.jouer(game_.getEntameur(),CardTarot.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.jouer(player_,CardTarot.TRUMP_21);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.TRUMP_5);
        expected_.ajouter(CardTarot.TRUMP_4);
        expected_.ajouter(CardTarot.TRUMP_3);
        player_ = game_.playerAfter(player_);
        HandTarot hand_ = game_.getDistribution().hand(player_);
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(expected_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(expected_));
        //
    }
    @Test
    public void playableCards_keepPlayingTrick4Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD, (byte) 4, game_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        game_.ajouterCartes(game_.getPreneur(), game_.derniereMain());
        HandTarot discardedCards_ = new HandTarot();
        discardedCards_.ajouter(CardTarot.CLUB_QUEEN);
        discardedCards_.ajouter(CardTarot.DIAMOND_QUEEN);
        discardedCards_.ajouter(CardTarot.HEART_10);
        game_.supprimerCartes(game_.getPreneur(),discardedCards_);

        game_.setPliEnCours(false);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        game_.addCurTrick();
        assertTrue(game_.keepPlayingCurrentGame());
        game_.setEntameur(game_.playerAfter(game_.getDistribution().getDealer()));
        game_.setPliEnCours(true);
        assertTrue(game_.keepPlayingCurrentTrick());
        game_.jouer(game_.getEntameur(),CardTarot.DIAMOND_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_3);
        assertTrue(game_.keepPlayingCurrentTrick());
        byte player_ = game_.playerAfter(game_.getEntameur());
        game_.jouer(player_,CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        assertTrue(game_.keepPlayingCurrentTrick());
        player_ = game_.playerAfter(player_);
        game_.jouer(player_,CardTarot.TRUMP_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        assertTrue(!game_.keepPlayingCurrentTrick());
        assertTrue(game_.keepPlayingCurrentGame());
        //
    }
}
