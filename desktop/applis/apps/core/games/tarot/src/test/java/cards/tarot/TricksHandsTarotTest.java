package cards.tarot;

import cards.consts.GameType;
import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.*;
import org.junit.Test;

public final class TricksHandsTarotTest extends CommonGameTarot {
    @Test
    public void sortHands0Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setCardsHandsAtInitialState(new CustList<HandTarot>());
        tricksHands_.setDistribution(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.getTricks());
        DisplayingTarot displaying_ = new DisplayingTarot();
        displaying_.setClockwise(true);
        assertTrue(displaying_.isClockwise());
        displaying_.setClockwise(false);
        assertTrue(!displaying_.isClockwise());
        displaying_.setDecreasing(true);
        displaying_.setSuits(new EnumList<Suit>());
        assertEq(25, tricksHands_.getTricks().size());
        assertEq(game_.getPreneur(), tricksHands_.getPreneur());
    }
    @Test
    public void sortHands1Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        displaying_.validate();
        displaying_.getSuits().clear();
        displaying_ = new DisplayingTarot(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        assertEq(4, tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 0).total());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 1).total());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 2).total());
        assertEq(6, tricksHands_.getCardsHandsAtInitialState().get((byte) 3).total());
        assertEq(25, tricksHands_.getTricks().size());
        assertEq(game_.getPreneur(), tricksHands_.getPreneur());
    }
    @Test
    public void sortHands2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD_AGAINST);
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = newFirstTrick(bids_, rules_, dealer_);
        dog_.ajouter(CardTarot.HEART_JACK);
        dog_.ajouter(CardTarot.SPADE_4);
        dog_.ajouter(CardTarot.CLUB_6);
        dog_.ajouter(CardTarot.DIAMOND_JACK);
        dog_.ajouter(CardTarot.SPADE_KNIGHT);
        dog_.ajouter(CardTarot.SPADE_JACK);
        trs_.add(dog_);
        BooleanList dSlam_ = new BooleanList();
        dSlam_.add(false);
        dSlam_.add(false);
        dSlam_.add(false);
        int taker_ = getTaker(rules_, dealer_, bids_);
        dSlam_.set(taker_,true);
        TrickTarot t_ = newSlamTrick(bids_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.CLUB_4);
        t_.ajouter(CardTarot.HEART_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.DIAMOND_7);
        t_.ajouter(CardTarot.HEART_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.DIAMOND_6);
        t_.ajouter(CardTarot.HEART_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_17);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.SPADE_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.SPADE_5);
        t_.ajouter(CardTarot.SPADE_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.CLUB_9);
        t_.ajouter(CardTarot.DIAMOND_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_18);
        t_.ajouter(CardTarot.CLUB_KNIGHT);
        t_.ajouter(CardTarot.CLUB_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.DIAMOND_KNIGHT);
        t_.ajouter(CardTarot.CLUB_2);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.DIAMOND_1);
        t_.ajouter(CardTarot.CLUB_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_13);
        t_.ajouter(CardTarot.DIAMOND_2);
        t_.ajouter(CardTarot.HEART_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.CLUB_3);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_16);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.CLUB_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.DIAMOND_8);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.SPADE_8);
        t_.ajouter(CardTarot.DIAMOND_4);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_12);
        t_.ajouter(CardTarot.SPADE_3);
        t_.ajouter(CardTarot.EXCUSE);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.CLUB_JACK);
        t_.ajouter(CardTarot.HEART_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.CLUB_8);
        t_.ajouter(CardTarot.SPADE_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.DIAMOND_5);
        t_.ajouter(CardTarot.CLUB_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.HEART_8);
        t_.ajouter(CardTarot.CLUB_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.SPADE_2);
        t_.ajouter(CardTarot.SPADE_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.SPADE_1);
        t_.ajouter(CardTarot.CLUB_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_KING);
        t_.ajouter(CardTarot.DIAMOND_9);
        t_.ajouter(CardTarot.DIAMOND_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_QUEEN);
        t_.ajouter(CardTarot.DIAMOND_3);
        t_.ajouter(CardTarot.DIAMOND_QUEEN);
        trs_.add(t_);
        BooleanList small_ = new BooleanList();
        small_.add(false);
        small_.add(false);
        small_.add(false);
        CustList<EnumList<Miseres>> m_ = new CustList<EnumList<Miseres>>();
        CustList<EnumList<Handfuls>> dh_ = new CustList<EnumList<Handfuls>>();
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        for (int i = 0; i < 3; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
            dh_.add(new EnumList<Handfuls>());
        }
        GameTarot game_ = newEndedGameTarot(rules_, trs_, m_, dh_, h_, dealer_, bids_, new HandTarot(), last_);
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        assertEq(4, tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 0).total());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 1).total());
        assertEq(24, tricksHands_.getCardsHandsAtInitialState().get((byte) 2).total());
        assertEq(6, tricksHands_.getCardsHandsAtInitialState().get((byte) 3).total());
    }
    @Test
    public void sortHands3Test() {
        RulesTarot regles_=GameTarotPlayingOneTest.initializeRulesWithBids();
        GameTarot game_ = new GameTarot(GameType.RANDOM,GameTarotPlayingOneTest.initializeHands((byte) 0),regles_);
        game_.ajouterContrat(BidTarot.FOLD,game_.playerHavingToBid());
        game_.ajouterContrat(BidTarot.FOLD,game_.playerHavingToBid());
        game_.ajouterContrat(BidTarot.FOLD,game_.playerHavingToBid());
        game_.ajouterContrat(BidTarot.FOLD,game_.playerHavingToBid());
        game_.ajouterContrat(BidTarot.FOLD,game_.playerHavingToBid());
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        assertEq(6, tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(15, tricksHands_.getCardsHandsAtInitialState().get((byte) 0).total());
        assertEq(15, tricksHands_.getCardsHandsAtInitialState().get((byte) 1).total());
        assertEq(15, tricksHands_.getCardsHandsAtInitialState().get((byte) 2).total());
        assertEq(15, tricksHands_.getCardsHandsAtInitialState().get((byte) 3).total());
        assertEq(15, tricksHands_.getCardsHandsAtInitialState().get((byte) 4).total());
        assertEq(3, tricksHands_.getCardsHandsAtInitialState().get((byte) 5).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick1Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,(byte)3, (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick2Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,(byte)3, (byte) 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick3Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,(byte)3, (byte) 2);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(22, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(22, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(22, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick4Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,(byte)3, (byte) -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick5Test() {
        GameTarot game_ = newSimpleDealOther();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,(byte)3, (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard1Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,(byte)3, (byte) 0, (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard2Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,(byte)3, (byte) 1, (byte) 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard3Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,(byte)3, (byte) 2, (byte) 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(22, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(22, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(23, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard4Test() {
        GameTarot game_ = newSimpleDeal();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,(byte)3, (byte) -1, (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard5Test() {
        GameTarot game_ = newSimpleDealOther();
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.setDistributionCopy(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.unionPlis(), game_.getNombreDeJoueurs());
        DisplayingTarot displaying_ = new DisplayingTarot();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,(byte)3, (byte) 0, (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(24, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    static GameTarot newSimpleDeal() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.TRUMP_19);
        last_.ajouter(CardTarot.TRUMP_7);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = newFirstTrick(bids_, rules_, dealer_);
        dog_.ajouter(CardTarot.HEART_JACK);
        dog_.ajouter(CardTarot.SPADE_4);
        dog_.ajouter(CardTarot.CLUB_6);
        dog_.ajouter(CardTarot.DIAMOND_JACK);
        dog_.ajouter(CardTarot.SPADE_KNIGHT);
        dog_.ajouter(CardTarot.SPADE_JACK);
        trs_.add(dog_);
        BooleanList dSlam_ = new BooleanList();
        dSlam_.add(false);
        dSlam_.add(false);
        dSlam_.add(false);
        int taker_ = getTaker(rules_, dealer_, bids_);
        dSlam_.set(taker_,true);
        TrickTarot t_ = newSlamTrick(bids_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.CLUB_4);
        t_.ajouter(CardTarot.HEART_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.DIAMOND_7);
        t_.ajouter(CardTarot.HEART_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.DIAMOND_6);
        t_.ajouter(CardTarot.HEART_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_17);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.SPADE_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.SPADE_5);
        t_.ajouter(CardTarot.SPADE_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.CLUB_9);
        t_.ajouter(CardTarot.DIAMOND_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_18);
        t_.ajouter(CardTarot.CLUB_KNIGHT);
        t_.ajouter(CardTarot.CLUB_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.DIAMOND_KNIGHT);
        t_.ajouter(CardTarot.CLUB_2);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.DIAMOND_1);
        t_.ajouter(CardTarot.CLUB_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_13);
        t_.ajouter(CardTarot.DIAMOND_2);
        t_.ajouter(CardTarot.HEART_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.CLUB_3);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_16);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.CLUB_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.DIAMOND_8);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.SPADE_8);
        t_.ajouter(CardTarot.DIAMOND_4);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_12);
        t_.ajouter(CardTarot.SPADE_3);
        t_.ajouter(CardTarot.EXCUSE);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.CLUB_JACK);
        t_.ajouter(CardTarot.HEART_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.CLUB_8);
        t_.ajouter(CardTarot.SPADE_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.DIAMOND_5);
        t_.ajouter(CardTarot.CLUB_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.HEART_8);
        t_.ajouter(CardTarot.CLUB_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.SPADE_2);
        t_.ajouter(CardTarot.SPADE_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.SPADE_1);
        t_.ajouter(CardTarot.CLUB_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_KING);
        t_.ajouter(CardTarot.DIAMOND_9);
        t_.ajouter(CardTarot.DIAMOND_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_QUEEN);
        t_.ajouter(CardTarot.DIAMOND_3);
        t_.ajouter(CardTarot.DIAMOND_QUEEN);
        trs_.add(t_);
        BooleanList small_ = new BooleanList();
        small_.add(false);
        small_.add(false);
        small_.add(false);
        CustList<EnumList<Miseres>> m_ = new CustList<EnumList<Miseres>>();
        CustList<EnumList<Handfuls>> dh_ = new CustList<EnumList<Handfuls>>();
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        for (int i = 0; i < 3; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
            dh_.add(new EnumList<Handfuls>());
        }
        return newEndedGameTarot(rules_, trs_, m_, dh_, h_, dealer_, bids_, new HandTarot(), last_);
    }
    static GameTarot newSimpleDealOther() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        rules_.setDiscardAfterCall(false);
        byte dealer_ = (byte) 2;
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.SPADE_KNIGHT);
        last_.ajouter(CardTarot.HEART_JACK);
        last_.ajouter(CardTarot.SPADE_JACK);
        last_.ajouter(CardTarot.SPADE_4);
        last_.ajouter(CardTarot.CLUB_6);
        last_.ajouter(CardTarot.DIAMOND_JACK);
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        TrickTarot dog_ = new TrickTarot((byte)3,false);
        dog_.ajouter(CardTarot.HEART_JACK);
        dog_.ajouter(CardTarot.SPADE_4);
        dog_.ajouter(CardTarot.CLUB_6);
        dog_.ajouter(CardTarot.DIAMOND_JACK);
        dog_.ajouter(CardTarot.SPADE_KNIGHT);
        dog_.ajouter(CardTarot.SPADE_JACK);
        trs_.add(dog_);
        BooleanList dSlam_ = new BooleanList();
        dSlam_.add(false);
        dSlam_.add(false);
        dSlam_.add(false);
        TrickTarot t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.CLUB_4);
        t_.ajouter(CardTarot.HEART_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.DIAMOND_7);
        t_.ajouter(CardTarot.HEART_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.DIAMOND_6);
        t_.ajouter(CardTarot.HEART_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_17);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.SPADE_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.SPADE_5);
        t_.ajouter(CardTarot.SPADE_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.CLUB_9);
        t_.ajouter(CardTarot.DIAMOND_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_18);
        t_.ajouter(CardTarot.CLUB_KNIGHT);
        t_.ajouter(CardTarot.CLUB_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.DIAMOND_KNIGHT);
        t_.ajouter(CardTarot.CLUB_2);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.DIAMOND_1);
        t_.ajouter(CardTarot.CLUB_1);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_13);
        t_.ajouter(CardTarot.DIAMOND_2);
        t_.ajouter(CardTarot.HEART_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.CLUB_3);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_16);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.CLUB_QUEEN);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.DIAMOND_8);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.SPADE_8);
        t_.ajouter(CardTarot.DIAMOND_4);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_12);
        t_.ajouter(CardTarot.SPADE_3);
        t_.ajouter(CardTarot.EXCUSE);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.CLUB_JACK);
        t_.ajouter(CardTarot.HEART_9);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.CLUB_8);
        t_.ajouter(CardTarot.SPADE_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.DIAMOND_5);
        t_.ajouter(CardTarot.CLUB_7);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.HEART_8);
        t_.ajouter(CardTarot.CLUB_5);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.SPADE_2);
        t_.ajouter(CardTarot.SPADE_6);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.SPADE_1);
        t_.ajouter(CardTarot.CLUB_10);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_KING);
        t_.ajouter(CardTarot.DIAMOND_9);
        t_.ajouter(CardTarot.DIAMOND_KING);
        trs_.add(t_);
        t_ = newClassicTrick(trs_,rules_,dealer_);
        t_.ajouter(CardTarot.SPADE_QUEEN);
        t_.ajouter(CardTarot.DIAMOND_3);
        t_.ajouter(CardTarot.DIAMOND_QUEEN);
        trs_.add(t_);
        BooleanList small_ = new BooleanList();
        small_.add(false);
        small_.add(false);
        small_.add(false);
        CustList<EnumList<Miseres>> m_ = new CustList<EnumList<Miseres>>();
        CustList<EnumList<Handfuls>> dh_ = new CustList<EnumList<Handfuls>>();
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        for (int i = 0; i < 3; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
            dh_.add(new EnumList<Handfuls>());
        }
        return newEndedGameTarot(rules_, trs_, m_, dh_, h_, dealer_, bids_, new HandTarot(), last_);
    }

    private static GameTarot newEndedGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,
                                               CustList<EnumList<Miseres>> _m, CustList<EnumList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                               EnumList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand) {
        CustList<HandTarot> deal_ = new CustList<HandTarot>();
        byte nbPl_ = (byte) _r.getRepartition().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            deal_.add(new HandTarot());
        }
        return newEndedGameTarot(_r,_trs,deal_,_m,_dh,_h,_dealer,_bids,_calledCards,_lastHand);
    }
    private static GameTarot newEndedGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,CustList<HandTarot> _deal,
                                               CustList<EnumList<Miseres>> _m, CustList<EnumList<Handfuls>> _dh, CustList<HandTarot> _h, int _dealer,
                                               EnumList<BidTarot> _bids, HandTarot _calledCards, HandTarot _lastHand) {
        CustList<HandTarot> deal_ = new CustList<HandTarot>(_deal);
        byte nbPl_ = (byte) _r.getRepartition().getNombreJoueurs();
        deal_.add(_lastHand);
        TrickTarot last_ = _trs.last();
        GameTarot g_ = new GameTarot(GameType.RANDOM,new DealTarot(deal_, (byte) _dealer),_r);
        g_.setProgressingTrick(new TrickTarot(new HandTarot(), last_.getStarter(), true));
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setDeclaresHandfuls(_dh);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);
        byte player_ = g_.playerAfter((byte) _dealer);
        int taker_ = getTaker(_r,_dealer,_bids);
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                bid_ = b;
            }
            player_ = g_.playerAfter(player_);
        }
        g_.setPreneur((byte) taker_);
        g_.setContrat(bid_);
        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
            g_.initEquipeDetermineeSansPreneur();
        } else if (_r.getRepartition().getAppel() == CallingCard.DEFINED) {
            g_.initEquipeDeterminee();
        } else if (_r.getRepartition().getAppel() == CallingCard.WITHOUT) {
            g_.initDefense();
        }
        for (TrickTarot t: g_.getTricks()) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            g_.retrieveCalledPlayers(t);
        }
        byte starter_ = last_.getEntameur();
        byte trickWinner_ = last_.getEntameur();
        if (g_.getPreneur() > -1) {
            for (byte i = 0; i < nbPl_; i++) {
                g_.getTeamsRelation().determinerConfiance(i, nbPl_);
            }
        }
        g_.setStarter(starter_);
        g_.setTrickWinner(trickWinner_);
        CheckerGameTarotWithRules.check(g_);
        assertTrue("Error",g_.getError().isEmpty());
        return g_;
    }
}
