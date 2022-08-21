package cards.tarot;

import code.util.CustList;
import org.junit.Test;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.EnumList;

public class GameTarotBiddingTest extends CommonTarotGame {

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
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
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
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_8);
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
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        return new DealTarot(hands_,(byte) 2);
    }
    static DealTarot initializeHandsForSixPlayers() {
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
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.HEART_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.DIAMOND_KING);
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
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.HEART_10);
        hands_.add(hand_);
        return new DealTarot(hands_,(byte) 5);
    }
    static RulesTarot initializeRulesWithBids(EnumList<BidTarot> _bids) {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_1_VS_2);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowSome(_bids);
        return regles_;
    }

    static RulesTarot initializeRulesWithBidsForSixPlayers(EnumList<BidTarot> _bids) {
        RulesTarot regles_=new RulesTarot();
        regles_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        regles_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        regles_.allowSome(_bids);
        return regles_;
    }    @Test
    public void allowedBids_BidsInitialize1(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.TAKE);
        expected_.add(BidTarot.GUARD);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
    }
    @Test
    public void allowedBids_BidsInitialize2(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.GUARD);
        expected_.add(BidTarot.GUARD_WITHOUT);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
    }
    @Test
    public void allowedBids_BidsInitialize3(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.GUARD);
        assertEq(2,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.last(),bids_.last());
    }
    @Test
    public void allowedBids_BidsInitialize4(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE, BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.TAKE);
        expected_.add(BidTarot.GUARD);
        expected_.add(BidTarot.GUARD_WITHOUT);
        assertEq(4,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.get(2),bids_.get(2));
        assertEq(expected_.last(),bids_.last());
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid1Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD,BidTarot.GUARD),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid2Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE,BidTarot.GUARD),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid3Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD,BidTarot.GUARD_WITHOUT),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid4Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD,BidTarot.GUARD_AGAINST),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid5Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD,BidTarot.GUARD_WITHOUT),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid6Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD,BidTarot.GUARD_AGAINST),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid7Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD, BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST),bids_);
    }
    @Test
    public void allowedBids_InitializeWithoutTakingBid8Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        EnumList<BidTarot> bids_ = game_.allowedBids();
        assertEqBids(new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD, BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST),bids_);
    }


    private static void assertEqBids(EnumList<BidTarot> _expected, EnumList<BidTarot> _result) {
        int expectedLen_ = _expected.size();
        assertEq(expectedLen_,_result.size());
        for (int i = 0; i < expectedLen_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }    @Test
    public void allowedBids_InitializeByTakingBid1(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        assertEq(player_,game_.getPreneur());
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());

        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.GUARD);
        expected_.add(BidTarot.GUARD_WITHOUT);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
        assertTrue(BidTarot.FOLD.estDemandable(game_.getContrat()));
        assertTrue(!BidTarot.GUARD.estDemandable(game_.getContrat()));
        assertTrue(BidTarot.GUARD_WITHOUT.estDemandable(game_.getContrat()));
    }
    @Test
    public void allowedBids_InitializeByTakingBid2(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        assertEq(player_,game_.getPreneur());
        player_ = game_.playerAfter(player_);
        assertTrue(!game_.keepBidding());

        EnumList<BidTarot> bids_ = game_.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.TAKE);
        expected_.add(BidTarot.GUARD);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
        assertTrue(BidTarot.FOLD.estDemandable(game_.getContrat()));
        assertTrue(!BidTarot.TAKE.estDemandable(game_.getContrat()));
        assertTrue(!BidTarot.GUARD.estDemandable(game_.getContrat()));
    }
    @Test
    public void allowedBids_InitializeByPassing1Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing2Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing3Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing4Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing5Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing6Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing7Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void allowedBids_InitializeByPassing8Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game_.keepBidding());

    }
    @Test
    public void maximumBid_AtSecondRound1(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game_.maximumBid(BidTarot.GUARD));
    }
    @Test
    public void maximumBid_AtSecondRound2(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game_.maximumBid(BidTarot.GUARD));
    }
    @Test
    public void maximumBid_AtSecondRound3(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game_.maximumBid(BidTarot.GUARD_AGAINST));
    }
    @Test
    public void maximumBid_AtSecondRound4(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(!game_.maximumBid(BidTarot.GUARD_WITHOUT));
    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking1Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking2Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking3Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking4Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking5Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking6Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking7Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking8Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        assertTrue(game_.keepBidding());
        game_.ajouterContrat(BidTarot.GUARD,player_);
        game_.initDefense();
        assertTrue(!game_.keepBidding());
        assertEq(2,game_.getPreneur());
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking1Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking2Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.TAKE));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking3Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking4Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking5Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking6Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking7Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking8Test(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>(BidTarot.TAKE,BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);

        byte player_ = game_.playerAfter(game_.getDistribution().getDealer());
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.GUARD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game_.playerAfter(player_);
        game_.ajouterContrat(BidTarot.FOLD,player_);
        game_.initEquipeDeterminee();
        assertEq(2,game_.getPreneur());
        assertEq(1,game_.getAppele().size());
        assertTrue(game_.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 1));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)0 ,(byte) 4));
        assertTrue(game_.confiance((byte)1 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 3));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)3 ,(byte) 1));
        assertTrue(game_.confiance((byte)3 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 0));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        assertTrue(game_.confiance((byte)4 ,(byte) 3));

    }
    @Test
    public void initEquipeDetermineeSansPreneur_revealedTeamWithoutCallingSixPlyersByTaking1(){
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);
        game_.initEquipeDetermineeSansPreneur();
        assertEq(BidTarot.FOLD,game_.getContrat());
        assertTrue(game_.confiance((byte)2 ,(byte) 5));
        assertTrue(game_.confiance((byte)5 ,(byte) 2));
        assertTrue(game_.confiance((byte)0 ,(byte) 3));
        assertTrue(game_.confiance((byte)3 ,(byte) 0));
        assertTrue(game_.confiance((byte)1 ,(byte) 4));
        assertTrue(game_.confiance((byte)4 ,(byte) 1));
        //game.setContrat(contrat_tmp);
    }
    @Test
    public void strategieContrat1Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game_.keepBidding());
        assertEq(0,game_.playerHavingToBid());
        assertSame(BidTarot.FOLD,game_.strategieContrat());

    }
    @Test
    public void strategieContrat2Test(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        game_.ajouterContrat(game_.strategieContrat(), (byte) 0);
        assertTrue(game_.keepBidding());
        assertEq(1,game_.playerHavingToBid());
        assertSame(BidTarot.FOLD,game_.strategieContrat());

    }
    @Test
    public void playerHasAlreadyBiddedTest(){
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        GameTarot game_ = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game_.keepBidding());
        assertTrue(!game_.playerHasAlreadyBidded((byte) 0));
        assertTrue(game_.playerHasAlreadyBidded((byte) 0));
    }
}