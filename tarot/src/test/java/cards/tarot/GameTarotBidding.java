package cards.tarot;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.AllowedBiddingTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;

@RunWith(JUnitParamsRunner.class)
public class GameTarotBidding extends CommonTarotGame {

    static Object[] bids(){
        Object[] bids_ = new Object[8];
        EnumList<BidTarot> allowedBids_ = new EnumList<BidTarot>();
        bids_[0] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        bids_[1] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        bids_[2] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[3] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        bids_[4] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[5] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[6] = $(allowedBids_);
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[7] = $(allowedBids_);
        return bids_;
    }

    static Object[] bidsExpect(){
        Object[] bids_ = new Object[8];
        EnumList<BidTarot> allowedBids_ = new EnumList<BidTarot>();
        bids_[0] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD,BidTarot.GUARD));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        bids_[1] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE,BidTarot.GUARD));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        bids_[2] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD,BidTarot.GUARD_WITHOUT));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[3] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD,BidTarot.GUARD_AGAINST));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        bids_[4] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD,BidTarot.GUARD_WITHOUT));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[5] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD,BidTarot.GUARD_AGAINST));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[6] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.GUARD, BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        allowedBids_ = new EnumList<BidTarot>();
        allowedBids_.add(BidTarot.TAKE);
        allowedBids_.add(BidTarot.GUARD_WITHOUT);
        allowedBids_.add(BidTarot.GUARD_AGAINST);
        bids_[7] = $(allowedBids_, new EnumList<BidTarot>(BidTarot.FOLD, BidTarot.TAKE, BidTarot.GUARD, BidTarot.GUARD_WITHOUT,BidTarot.GUARD_AGAINST));
        return bids_;
    }

    static Object[] bidsSingle(){
        Object[] bids_ = new Object[3];
        bids_[0] = $(BidTarot.TAKE);
        bids_[1] = $(BidTarot.GUARD_WITHOUT);
        bids_[2] = $(BidTarot.GUARD_AGAINST);
        return bids_;
    }

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
        regles_.setRepartition(DealingTarot.DEAL_1_VS_2);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            if (b.getPossibiliteAnnonce() != AllowedBiddingTarot.ALWAYS) {
                contrats_.put(b,false);
            } else {
                contrats_.put(b,true);
            }
        }
        for (BidTarot b: _bids) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }
    static RulesTarot initializeRulesWithBidsForSixPlayers(EnumList<BidTarot> _bids) {
        RulesTarot regles_=new RulesTarot();
        regles_.setRepartition(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        regles_.setCartesBattues(MixCardsChoice.NEVER);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot b: regles_.getContrats().getKeys()) {
            if (b.getPossibiliteAnnonce() != AllowedBiddingTarot.ALWAYS) {
                contrats_.put(b,false);
            } else {
                contrats_.put(b,true);
            }
        }
        for (BidTarot b: _bids) {
            contrats_.put(b,true);
        }
        regles_.setContrats(contrats_);
        return regles_;
    }

    @Test
    public void allowedBids_BidsInitialize1Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game.allowedBids();
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
    public void allowedBids_BidsInitialize2Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game.allowedBids();
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
    public void allowedBids_BidsInitialize3Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.GUARD);
        assertEq(2,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.last(),bids_.last());
    }

    @Test
    public void allowedBids_BidsInitialize4Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE, BidTarot.GUARD_WITHOUT));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        EnumList<BidTarot> bids_ = game.allowedBids();
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

    @Parameters(method = "bidsExpect",source =GameTarotBidding.class)
    @Test
    public void allowedBids_InitializeWithoutTakingBid5Test(EnumList<BidTarot> _bidsWithoutTrumpSuit, EnumList<BidTarot> _expected) {
        RulesTarot regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        EnumList<BidTarot> bids_ = game.allowedBids();
        assertEqBids(_expected,bids_);
    }

    private static void assertEqBids(EnumList<BidTarot> _expected, EnumList<BidTarot> _result) {
        int expectedLen_ = _expected.size();
        assertEq(expectedLen_,_result.size());
        for (int i = 0; i < expectedLen_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }
    @Test
    public void allowedBids_InitializeByTakingBid6Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_WITHOUT));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.GUARD,player_);
        assertEq(player_,game.getPreneur());
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        //game.setContrat(contrat_tmp);
        EnumList<BidTarot> bids_ = game.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.GUARD);
        expected_.add(BidTarot.GUARD_WITHOUT);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
        assertTrue(BidTarot.FOLD.estDemandable(game.getContrat()));
        assertTrue(!BidTarot.GUARD.estDemandable(game.getContrat()));
        assertTrue(BidTarot.GUARD_WITHOUT.estDemandable(game.getContrat()));
    }

    @Test
    public void allowedBids_InitializeByTakingBid7Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.GUARD,player_);
        assertEq(player_,game.getPreneur());
        player_ = game.playerAfter(player_);
        assertTrue(!game.keepBidding());
        //game.setContrat(contrat_tmp);
        EnumList<BidTarot> bids_ = game.allowedBids();
        EnumList<BidTarot> expected_ = new EnumList<BidTarot>();
        expected_.add(BidTarot.FOLD);
        expected_.add(BidTarot.TAKE);
        expected_.add(BidTarot.GUARD);
        assertEq(3,bids_.size());
        assertEq(expected_.first(),bids_.first());
        assertEq(expected_.get(1),bids_.get(1));
        assertEq(expected_.last(),bids_.last());
        assertTrue(BidTarot.FOLD.estDemandable(game.getContrat()));
        assertTrue(!BidTarot.TAKE.estDemandable(game.getContrat()));
        assertTrue(!BidTarot.GUARD.estDemandable(game.getContrat()));
    }

    @Parameters(method = "bids",source =GameTarotBidding.class)
    @Test
    public void allowedBids_InitializeByPassing8Test(EnumList<BidTarot> _bidsWithoutTrumpSuit) {
        RulesTarot regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        assertTrue(!game.keepBidding());
        //game.setContrat(contrat_tmp);
    }

    @Test
    public void maximumBid_AtSecondRound1Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>());
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game.maximumBid(BidTarot.GUARD));
    }

    @Test
    public void maximumBid_AtSecondRound2Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.TAKE));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game.maximumBid(BidTarot.GUARD));
    }

    @Test
    public void maximumBid_AtSecondRound3Test() {
        RulesTarot regles_=initializeRulesWithBids(new EnumList<BidTarot>(BidTarot.GUARD_AGAINST));
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        assertTrue(game.maximumBid(BidTarot.GUARD_AGAINST));
    }

    @Parameters(method = "bids",source =GameTarotBidding.class)
    @Test
    public void initDefense_revealedTeamWithoutCallingByTaking1Test(EnumList<BidTarot> _bidsWithoutTrumpSuit) {
        RulesTarot regles_=initializeRulesWithBids(_bidsWithoutTrumpSuit);
        game = new GameTarot(GameType.RANDOM,initializeHands(),regles_);
        //game.resetNbPlisTotal();
        byte player_ = game.playerAfter(game.getDistribution().getDonneur());
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.FOLD,player_);
        player_ = game.playerAfter(player_);
        assertTrue(game.keepBidding());
        game.ajouterContrat(BidTarot.GUARD,player_);
        game.initDefense();
        assertTrue(!game.keepBidding());
        assertEq(2,game.getPreneur());
        assertEq(BidTarot.GUARD,game.getContrat());
        assertTrue(game.confiance((byte)0 ,(byte) 1));
        assertTrue(game.confiance((byte)1 ,(byte) 0));
        //game.setContrat(contrat_tmp);
    }
    @Parameters(method = "bids",source =GameTarotBidding.class)
    @Test
    public void initEquipeDeterminee_revealedTeamWithoutCallingSixPlyersByTaking1Test(EnumList<BidTarot> _bidsWithoutTrumpSuit) {
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(_bidsWithoutTrumpSuit);
        game = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);
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
        player_ = game.playerAfter(player_);
        game.ajouterContrat(BidTarot.FOLD,player_);
        game.initEquipeDeterminee();
        assertEq(2,game.getPreneur());
        assertEq(1,game.getAppele().size());
        assertTrue(game.getAppele().toString(),game.getAppele().containsObj((byte)5));
        assertEq(BidTarot.GUARD,game.getContrat());
        assertTrue(game.confiance((byte)2 ,(byte) 5));
        assertTrue(game.confiance((byte)5 ,(byte) 2));
        assertTrue(game.confiance((byte)0 ,(byte) 1));
        assertTrue(game.confiance((byte)0 ,(byte) 3));
        assertTrue(game.confiance((byte)0 ,(byte) 4));
        assertTrue(game.confiance((byte)1 ,(byte) 0));
        assertTrue(game.confiance((byte)1 ,(byte) 3));
        assertTrue(game.confiance((byte)1 ,(byte) 4));
        assertTrue(game.confiance((byte)3 ,(byte) 0));
        assertTrue(game.confiance((byte)3 ,(byte) 1));
        assertTrue(game.confiance((byte)3 ,(byte) 4));
        assertTrue(game.confiance((byte)4 ,(byte) 0));
        assertTrue(game.confiance((byte)4 ,(byte) 1));
        assertTrue(game.confiance((byte)4 ,(byte) 3));
        //game.setContrat(contrat_tmp);
    }

    @Test
    public void initEquipeDetermineeSansPreneur_revealedTeamWithoutCallingSixPlyersByTaking2Test() {
        RulesTarot regles_=initializeRulesWithBidsForSixPlayers(new EnumList<BidTarot>());
        game = new GameTarot(GameType.RANDOM,initializeHandsForSixPlayers(),regles_);
        game.initEquipeDetermineeSansPreneur();
        assertEq(BidTarot.FOLD,game.getContrat());
        assertTrue(game.confiance((byte)2 ,(byte) 5));
        assertTrue(game.confiance((byte)5 ,(byte) 2));
        assertTrue(game.confiance((byte)0 ,(byte) 3));
        assertTrue(game.confiance((byte)3 ,(byte) 0));
        assertTrue(game.confiance((byte)1 ,(byte) 4));
        assertTrue(game.confiance((byte)4 ,(byte) 1));
        //game.setContrat(contrat_tmp);
    }
}
