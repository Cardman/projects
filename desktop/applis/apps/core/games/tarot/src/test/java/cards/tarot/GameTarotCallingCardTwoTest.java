package cards.tarot;

import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.EnumMap;

public class GameTarotCallingCardTwoTest extends CommonTarotGame {

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
    public void callableCards_WithCallOfKingWhileOwningFourKings1Test() {
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
        HandTarot callableCards_ = game_.callableCards();
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.HEART_QUEEN);
        expected_.ajouter(CardTarot.SPADE_QUEEN);
        expected_.ajouter(CardTarot.DIAMOND_QUEEN);
        expected_.ajouter(CardTarot.CLUB_QUEEN);
        assertEq(expected_,callableCards_);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        game_.setCarteAppelee(cartesAppeler_);
        game_.initConfianceAppele();
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)1));
        assertTrue(game_.confiance((byte)1,(byte)4));
        //game.setContrat(contrat_tmp);

    }
    @Test
    public void callableCards_WithCallOfCharacterWhileOwningFourKings2Test() {
        RulesTarot regles_=initializeRulesWithBids();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
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
        HandTarot callableCards_ = game_.callableCards();
        HandTarot expected_ = new HandTarot();
        expected_.ajouter(CardTarot.HEART_KING);
        expected_.ajouter(CardTarot.HEART_QUEEN);
        expected_.ajouter(CardTarot.HEART_KNIGHT);
        expected_.ajouter(CardTarot.HEART_JACK);
        expected_.ajouter(CardTarot.SPADE_KING);
        expected_.ajouter(CardTarot.SPADE_QUEEN);
        expected_.ajouter(CardTarot.SPADE_KNIGHT);
        expected_.ajouter(CardTarot.SPADE_JACK);
        expected_.ajouter(CardTarot.DIAMOND_KING);
        expected_.ajouter(CardTarot.DIAMOND_QUEEN);
        expected_.ajouter(CardTarot.DIAMOND_KNIGHT);
        expected_.ajouter(CardTarot.DIAMOND_JACK);
        expected_.ajouter(CardTarot.CLUB_KING);
        expected_.ajouter(CardTarot.CLUB_QUEEN);
        expected_.ajouter(CardTarot.CLUB_KNIGHT);
        expected_.ajouter(CardTarot.CLUB_JACK);
        expected_.ajouter(CardTarot.TRUMP_1);
        expected_.ajouter(CardTarot.TRUMP_21);
        expected_.ajouter(CardTarot.EXCUSE);
        assertEq(expected_,callableCards_);
        //game.setContrat(contrat_tmp);

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
        assertSame(CardTarot.SPADE_QUEEN,h_.premiereCarte());
    }
}
