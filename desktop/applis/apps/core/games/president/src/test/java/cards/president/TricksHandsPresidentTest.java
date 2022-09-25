package cards.president;

import cards.consts.GameType;
import cards.consts.Suit;
import cards.president.enumerations.CardPresident;
import code.util.Bytes;
import code.util.CustList;
import code.util.IdList;
import org.junit.Test;

public final class TricksHandsPresidentTest extends EquallablePresidentUtil {
    @Test
    public void sortHands0Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        tricksHands_.setDistribution(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis());
        tricksHands_.setReversed(true);
        tricksHands_.setProgressingTrick(game_.getProgressingTrick());
        tricksHands_.setRanks(game_.getNewRanks());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.getDisplaying().setClockwise(true);
        assertTrue(displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setClockwise(false);
        assertTrue(!displaying_.getDisplaying().isClockwise());
        displaying_.getDisplaying().setDecreasing(true);
        displaying_.getDisplaying().setSuits(new IdList<Suit>());
        assertEq(18, tricksHands_.getTricks().size());
        assertEq(1,displaying_.getNbDeals());
        assertSame(game_.getProgressingTrick(),tricksHands_.getProgressingTrick());
        assertTrue(tricksHands_.isReversed());
        assertEq(2,tricksHands_.getNumberMaxSwitchedCards());
        assertEq(4,tricksHands_.getRanks().size());
        assertEq(0,tricksHands_.getSwitchedCards().size());
    }
    @Test
    public void sortHands1Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(15, tricksHands_.getFilledTricksCount());
        assertEq(0, tricksHands_.getFilledTricksIndex(0));
        assertEq(1, tricksHands_.getFilledTricksIndex(1));
        assertEq(2, tricksHands_.getFilledTricksIndex(2));
        assertEq(3, tricksHands_.getFilledTricksIndex(3));
        assertEq(4, tricksHands_.getFilledTricksIndex(4));
        assertEq(5, tricksHands_.getFilledTricksIndex(5));
        assertEq(6, tricksHands_.getFilledTricksIndex(6));
        assertEq(7, tricksHands_.getFilledTricksIndex(7));
        assertEq(8, tricksHands_.getFilledTricksIndex(8));
        assertEq(9, tricksHands_.getFilledTricksIndex(9));
        assertEq(10, tricksHands_.getFilledTricksIndex(10));
        assertEq(12, tricksHands_.getFilledTricksIndex(11));
        assertEq(13, tricksHands_.getFilledTricksIndex(12));
        assertEq(14, tricksHands_.getFilledTricksIndex(13));
        assertEq(17, tricksHands_.getFilledTricksIndex(14));
        assertEq(-1, tricksHands_.getFilledTricksIndex(15));
    }
    @Test
    public void sortHands2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(0).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(1).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(2).total());
        assertEq(13,tricksHands_.getCardsHandsAtInitialState().get(3).total());
        assertEq(0, tricksHands_.getFilledTricksCount());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs());
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs(), (byte) -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        game_.donnerMeilleuresCartes();
        game_.giveWorstCards();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs(), (byte) -1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick4Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs());
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick5Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs(),(byte)-1);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrick6Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrick(displaying_,game_.getNombreDeJoueurs(),(byte)0);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,game_.getNombreDeJoueurs(), (byte) 1);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard2Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,game_.getNombreDeJoueurs(),(byte)1);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(0, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident game_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        game_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        game_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,game_.getNombreDeJoueurs(), (byte) 0);
        assertEq(4, tricksHands_.getDistribution().nombreDeMains());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(12, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(13, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard4Test() {
        GamePresident game_ = getSimpleDeal();
        TricksHandsPresident tricksHands_ = new TricksHandsPresident();
        tricksHands_.setDistributionCopy(game_.getDeal());
        tricksHands_.setNumberMaxSwitchedCards(game_.nombresCartesEchangesMax());
        tricksHands_.setRanks(game_.getRanks());
        tricksHands_.setSwitchedCards(game_.getSwitchedCards());
        tricksHands_.setTricks(game_.unionPlis(), game_.getProgressingTrick(), game_.getNombreDeJoueurs());
        DisplayingPresident displaying_ = new DisplayingPresident();
        displaying_.setNbDeals(0);
        displaying_.getDisplaying().getSuits().clear();
        displaying_.validate();
        displaying_ = new DisplayingPresident(displaying_);
        displaying_.validate();
        tricksHands_.sortHands(displaying_, game_.getNombreDeJoueurs());
        tricksHands_.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displaying_,game_.getNombreDeJoueurs(),(byte)7,(byte)1);
        assertEq(4,tricksHands_.getCardsHandsAtInitialState().size());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 0).total());
        assertEq(5, tricksHands_.getDistribution().hand((byte) 1).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 2).total());
        assertEq(6, tricksHands_.getDistribution().hand((byte) 3).total());
    }
    GamePresident getSimpleDeal2() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        rk_.add((byte) 1);
        rk_.add((byte) 4);
        rk_.add((byte) 3);
        rk_.add((byte) 2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        played_.ajouter(CardPresident.DIAMOND_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        g_.noPlay((byte)2);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_5);
        played_.ajouter(CardPresident.SPADE_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_6);
        played_.ajouter(CardPresident.SPADE_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_QUEEN);
        played_.ajouter(CardPresident.SPADE_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        g_.noPlay((byte)3);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_1);
        played_.ajouter(CardPresident.CLUB_1);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        g_.noPlay((byte)0);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop((byte)3, played_);
        return g_;
    }

    GamePresident getSimpleDeal() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = new Bytes();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_4);
        played_.ajouter(CardPresident.DIAMOND_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_8);
        played_.ajouter(CardPresident.HEART_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_QUEEN);
        played_.ajouter(CardPresident.CLUB_QUEEN);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_1);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_1);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_JACK);
        played_.ajouter(CardPresident.SPADE_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 0, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_JACK);
        played_.ajouter(CardPresident.HEART_JACK);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 1, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_5);
        played_.ajouter(CardPresident.HEART_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 3, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_6);
        played_.ajouter(CardPresident.CLUB_6);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop((byte) 2, played_);
        return g_;
    }

    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }
}
