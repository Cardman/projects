package cards.tarot;

import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;

public class GameTarotPlayingFiveTest extends CommonTarotGame {

    static DealTarot initializeHands(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
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
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    static RulesTarot initializeRulesWithBids() {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        return regles_;
    }

    @Test
    public void playableCards_beginningFreeTrickWithoutCall1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        biddingSix(BidTarot.GUARD_AGAINST, (byte) 4, game_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();

        firstTrick(game_);
        assertEq(3,game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(game_.getEntameur());
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }

    @Test
    public void playableCards_beginningFreeSecondTrickWithoutCall2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands((byte) 2), regles_);
        //game.resetNbPlisTotal();
        biddingSix(BidTarot.GUARD_AGAINST, (byte) 4, game_);
        game_.initEquipeDeterminee();
        game_.gererChienInconnu();
        firstTrick(game_);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        game_.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        game_.ajouterPetitAuBoutPliEnCours();
        assertEq(5,game_.getEntameur());
        HandTarot hand_ = game_.getDistribution().hand(game_.getEntameur());
        HandTarot playableCards_ = game_.playableCards(hand_.couleurs());

        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game_.getPliEnCours().couleurDemandee());
    }

    private void firstTrick(GameTarot _game) {
        _game.firstLead();
    }
}
