package cards.tarot;
import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.EnumMap;
import code.util.EqList;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;

public class GameTarotPlayingFiveTest extends CommonTarotGame {

    static DealTarot initializeHands(byte _dealer) {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
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
        regles_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }

    @Test
    public void playableCards_beginningFreeTrickWithoutCall1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 2),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD_AGAINST, (byte) 4);
        game.initEquipeDeterminee();
        game.gererChienInconnu();

        game.setEntameur(game.playerAfter(game.getDistribution().getDonneur()));
        game.setPliEnCours(true);
        assertEq(3,game.getEntameur());
        HandTarot hand_ = game.getDistribution().main(game.getEntameur());
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());
        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }

    @Test
    public void playableCards_beginningFreeSecondTrickWithoutCall2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands((byte) 2),regles_);
        //game.resetNbPlisTotal();
        bidding(BidTarot.GUARD_AGAINST, (byte) 4);
        game.initEquipeDeterminee();
        game.gererChienInconnu();
        game.setEntameur(game.playerAfter(game.getDistribution().getDonneur()));
        game.setPliEnCours(true);
        game.ajouterUneCarteDansPliEnCours((byte) 3, CardTarot.SPADE_2);
        game.ajouterUneCarteDansPliEnCours((byte) 4, CardTarot.TRUMP_2);
        game.ajouterUneCarteDansPliEnCours((byte) 5, CardTarot.TRUMP_5);
        game.ajouterUneCarteDansPliEnCours((byte) 0, CardTarot.DIAMOND_1);
        game.ajouterUneCarteDansPliEnCours((byte) 1, CardTarot.SPADE_3);
        game.ajouterUneCarteDansPliEnCours((byte) 2, CardTarot.SPADE_KING);
        game.ajouterPetitAuBoutPliEnCours();
        game.setPliEnCours(true);
        assertEq(5,game.getEntameur());
        HandTarot hand_ = game.getDistribution().main(game.getEntameur());
        HandTarot playableCards_ = game.playableCards(hand_.couleurs());

        assertEq(hand_.total(),playableCards_.total());
        assertTrue(playableCards_.display(),playableCards_.contientCartes(hand_));
        assertEq(Suit.UNDEFINED,game.getPliEnCours().couleurDemandee());
    }
}
