package cards.tarot;
import static code.util.opers.EquallableUtil.assertEq;
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

public class GameTarotDiscardingCardsOneTest extends CommonTarotGame {

    static DealTarot initializeHands() {
        EqList<HandTarot> hands_ = new EqList<HandTarot>();
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
        return new DealTarot(hands_,(byte) 4);
    }
    static RulesTarot initializeRulesWithBids() {
        RulesTarot regles_=new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
    @Test
    public void discardableCards_WithTrumpsForGame1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.GUARD,player_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot takerHand_ = game.getDistribution().main(game.getPreneur());
        EnumMap<Suit,HandTarot> suits_ = takerHand_.couleurs();
        HandTarot discardableCards_ =
            game.getCartesEcartables(game.getDistribution()
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
        assertTrue(discardableCards_.toString(),expected_.contientCartes(discardableCards_));
    }
    @Test
    public void discardableCards_WithoutTrumpsForGame2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game.setCarteAppelee(cartesAppeler_);
        game.initConfianceAppele();
        game.ajouterCartes(game.getPreneur(), game.derniereMain());
        HandTarot takerHand_ = game.getDistribution().main(game.getPreneur());
        EnumMap<Suit,HandTarot> suits_ = takerHand_.couleurs();
        HandTarot discardableCards_ =
            game.getCartesEcartables(game.getDistribution()
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
        assertTrue(discardableCards_.toString(),expected_.contientCartes(discardableCards_));
    }
}
