package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.GameType;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import org.junit.Test;

public final class TricksHandsBeloteTest extends CommonGameBelote {
    @Test
    public void sortHands0Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.setCardsHandsAtInitialState(new CustList<HandBelote>());
        tricksHands_.setDistribution(game_.getDistribution());
        tricksHands_.setPreneur(game_.getPreneur());
        tricksHands_.setTricks(game_.getTricks());
//        tricksHands_.setRules(game_.getRules());
        tricksHands_.setBid(game_.getBid());
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.getDisplaying().setClockwise(true);
        assertTrue(displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setClockwise(false);
        assertTrue(!displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setDecreasing(true);
        displaying_.getDisplaying().setSuits(new IdList<Suit>());
        assertEq(8, tricksHands_.getTricks().size());
        assertEq(game_.getPreneur(), tricksHands_.getPreneur());
        assertSame(game_.getBid(),tricksHands_.getBid());
    }
    @Test
    public void sortHands1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(5,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(12,tricksHands_.getCardsHandsAtInitialState().get(4).total());
    }
    @Test
    public void sortHands5Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(3).total());
    }
    @Test
    public void sortHands6Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(3).total());
    }
    @Test
    public void sortHands7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        setTricks(tricksHands_, game_);
        DisplayingBelote displaying_ = new DisplayingBelote();
        displaying_.setOrderBeforeBids(Order.NOTHING);
        displaying_.validate();
        displaying_.getDisplaying().getSuits().clear();
        displaying_ = new DisplayingBelote(displaying_);
        displaying_.validate();
        BidBeloteSuit b_ = new BidBeloteSuit();
        sortHandsSimple(tricksHands_, displaying_, b_, game_);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(8,tricksHands_.getCardsHandsAtInitialState().get(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(7, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 2);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(6, tricksHands_.getDistribution().hand(2).total());
        assertEq(6, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMains(game_, -1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand(0).total());
        assertEq(5, tricksHands_.getDistribution().hand(1).total());
        assertEq(5, tricksHands_.getDistribution().hand(2).total());
        assertEq(5, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick5Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick6Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick7Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 2);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(6, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick8Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMains(game_, -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick9Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick10Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick11Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 2);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(6, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick12Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMains(game_, -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick13Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick14Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick15Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 2);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(6, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick16Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand(0).total());
        assertEq(5, tricksHands_.getDistribution().hand(1).total());
        assertEq(5, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick17Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick18Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }

    @Test
    public void restoreHandsAtSelectedNumberedTrick19Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, 2);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(6, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick20Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMains(game_, -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard1Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 0, 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard2Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 1, 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard3Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 2, 1);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(7, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard4Test() {
        GameBelote game_ = getSimpleSlamDeal();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, -1, 0);
        assertEq(5, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand(0).total());
        assertEq(5, tricksHands_.getDistribution().hand(1).total());
        assertEq(5, tricksHands_.getDistribution().hand(2).total());
        assertEq(5, tricksHands_.getDistribution().hand(3).total());
        assertEq(12, tricksHands_.getDistribution().hand(4).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard5Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 0, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard6Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 1, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard7Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 2, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard8Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, -1, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard9Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 0, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard10Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 1, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard11Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 2, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard12Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, -1, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard13Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 0, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard14Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 1, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard15Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 2, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard16Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, -1, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(5, tricksHands_.getDistribution().hand(0).total());
        assertEq(5, tricksHands_.getDistribution().hand(1).total());
        assertEq(5, tricksHands_.getDistribution().hand(2).total());
        assertEq(17, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard17Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 0, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard18Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 1, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(7, tricksHands_.getDistribution().hand(0).total());
        assertEq(7, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard19Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, 2, 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(6, tricksHands_.getDistribution().hand(0).total());
        assertEq(6, tricksHands_.getDistribution().hand(1).total());
        assertEq(7, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard20Test() {
        GameBelote game_ = getSimpleSlamDealThreePlayers2_24();
        TricksHandsBelote tricksHands_ = restituerMainsCards(game_, -1, 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(8, tricksHands_.getDistribution().hand(0).total());
        assertEq(8, tricksHands_.getDistribution().hand(1).total());
        assertEq(8, tricksHands_.getDistribution().hand(2).total());
        assertEq(8, tricksHands_.getDistribution().hand(3).total());
    }
    private TricksHandsBelote restituerMainsCards(GameBelote _g, int _i, int _j) {
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, _g);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_,_g.getRules());
        tricksHands_.restituerMains(displaying_, _i, _j,_g.getRules());
        return tricksHands_;
    }

    private void setTricks(TricksHandsBelote _t, GameBelote _g) {
        _t.tricks(_g);
    }

    GameBelote getSimpleSlamDeal() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam(3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
    }

    private static DealBelote deal1Slam(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }
    GameBelote getSimpleSlamDealThreePlayers() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        game_.ajouterChelemUtilisateur();
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
    }
    GameBelote getSimpleSlamDealThreePlayers2() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        game_.ajouterChelemUtilisateur();
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
    }

    GameBelote getSimpleSlamDealThreePlayers_24() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Classic();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.SUIT));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
    }
    GameBelote getSimpleSlamDealThreePlayers2_24() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Coinche();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,162,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterDixDeDerPliEnCours();
        return game_;
    }
    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
    private DealBelote dealThreePlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer(1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private DealBelote dealThreePlayers24Classic() {
        DealBelote db_ = new DealBelote();
        db_.setDealer(2);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK));
        db_.getDeal().add(create(CardBelote.HEART_1,
                CardBelote.CLUB_1,CardBelote.DIAMOND_10,CardBelote.CLUB_10,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote dealThreePlayers24Coinche() {
        DealBelote db_ = new DealBelote();
        db_.setDealer(1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,
                CardBelote.HEART_1,CardBelote.CLUB_1,CardBelote.DIAMOND_10));
        db_.getDeal().add(create(CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_10));
        db_.getDeal().add(create(
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private TricksHandsBelote restituerMains(GameBelote _g, int _i) {
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
//        tricksHands_.setRules(game_.getRules());
//        tricksHands_.setDistributionCopy(game_.getDistribution());
//        tricksHands_.setPreneur(game_.getPreneur());
//        tricksHands_.setBid(game_.getBid());
        setTricks(tricksHands_, _g);
        DisplayingBelote displaying_ = new DisplayingBelote();
        tricksHands_.sortHands(displaying_,_g.getRules());
        tricksHands_.restituerMains(displaying_, _i,_g.getRules());
        return tricksHands_;
    }

    private void sortHandsSimple(TricksHandsBelote _tricksHands, DisplayingBelote _displaying, BidBeloteSuit _b, GameBelote _g) {
        _tricksHands.sortHands(_displaying, _b, _g.getRules());
    }

}
