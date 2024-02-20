package cards.tarot;

import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;

public class GameTarotCallingCardThreeTest extends CommonTarotGame {

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
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
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
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.CLUB_KING);
        hands_.add(hand_);
        return new DealTarot(hands_,(byte) 4);
    }
    static RulesTarot initializeRulesWithBids() {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowAllBids();
        return regles_;
    }
    @Test
    public void setCarteAppelee_callKingInHand1Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)4));
//        assertTrue(!game_.confiance((byte)0,(byte)1));
//        assertTrue(!game_.confiance((byte)0,(byte)2));
//        assertTrue(!game_.confiance((byte)0,(byte)3));
//        assertTrue(!game_.confiance((byte)0,(byte)4));
//        assertTrue(!game_.confiance((byte)1,(byte)0));
//        assertTrue(!game_.confiance((byte)1,(byte)2));
//        assertTrue(!game_.confiance((byte)1,(byte)3));
//        assertTrue(!game_.confiance((byte)1,(byte)4));
//        assertTrue(!game_.confiance((byte)2,(byte)0));
//        assertTrue(!game_.confiance((byte)2,(byte)1));
//        assertTrue(!game_.confiance((byte)2,(byte)3));
//        assertTrue(!game_.confiance((byte)2,(byte)4));
//        assertTrue(!game_.confiance((byte)3,(byte)0));
//        assertTrue(!game_.confiance((byte)3,(byte)1));
//        assertTrue(!game_.confiance((byte)3,(byte)2));
//        assertTrue(!game_.confiance((byte)3,(byte)4));
//        assertTrue(!game_.confiance((byte)4,(byte)0));
//        assertTrue(!game_.confiance((byte)4,(byte)1));
//        assertTrue(!game_.confiance((byte)4,(byte)2));
//        assertTrue(!game_.confiance((byte)4,(byte)3));
    }
    @Test
    public void setCarteAppelee_callKingFoundInDog2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        assertEq( 0, game_.getAppele().size());
//        assertTrue(game_.confiance((byte)0,(byte)1));
//        assertTrue(game_.confiance((byte)0,(byte)2));
//        assertTrue(game_.confiance((byte)0,(byte)3));
//        assertTrue(game_.confiance((byte)1,(byte)0));
//        assertTrue(game_.confiance((byte)1,(byte)2));
//        assertTrue(game_.confiance((byte)1,(byte)3));
//        assertTrue(game_.confiance((byte)2,(byte)0));
//        assertTrue(game_.confiance((byte)2,(byte)1));
//        assertTrue(game_.confiance((byte)2,(byte)3));
//        assertTrue(game_.confiance((byte)3,(byte)0));
//        assertTrue(game_.confiance((byte)3,(byte)1));
//        assertTrue(game_.confiance((byte)3,(byte)2));
    }
    @Test
    public void setCarteAppelee_callKingInDog3Test() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT,player_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.CLUB_KING);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        assertEq(0,game_.getAppele().size());
//        assertTrue(!game_.confiance((byte)0,(byte)1));
//        assertTrue(!game_.confiance((byte)0,(byte)2));
//        assertTrue(!game_.confiance((byte)0,(byte)3));
//        assertTrue(!game_.confiance((byte)1,(byte)0));
//        assertTrue(!game_.confiance((byte)1,(byte)2));
//        assertTrue(!game_.confiance((byte)1,(byte)3));
//        assertTrue(!game_.confiance((byte)2,(byte)0));
//        assertTrue(!game_.confiance((byte)2,(byte)1));
//        assertTrue(!game_.confiance((byte)2,(byte)3));
//        assertTrue(!game_.confiance((byte)3,(byte)0));
//        assertTrue(!game_.confiance((byte)3,(byte)1));
//        assertTrue(!game_.confiance((byte)3,(byte)2));
    }
    @Test
    public void strategieAppelTest() {
        RulesTarot regles_=initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM, initializeHands(), regles_);
        //game.resetNbPlisTotal();
        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        HandTarot h_ = game_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.CLUB_KING,h_.premiereCarte());
    }
}
