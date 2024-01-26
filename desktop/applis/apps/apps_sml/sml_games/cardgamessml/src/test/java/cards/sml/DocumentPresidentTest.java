package cards.sml;

import cards.consts.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.maths.*;
import code.util.*;

import org.junit.Test;

public final class DocumentPresidentTest extends EquallableCardsSerialUtil {
    @Test
    public void t1() {
        CustList<GamePresident> ls_ = new CustList<GamePresident>();
        GamePresident g_ = new GamePresident();
        g_.setRanks(Bytes.newList((byte) 2));
        DealPresident d_ = new DealPresident();
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.HEART_2);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickPresident pt_ = new TrickPresident();
        HandPresident pl_ = new HandPresident();
        pl_.ajouter(CardPresident.HEART_3);
        pl_.ajouter(CardPresident.HEART_4);
        pt_.getCards().add(pl_);
        HandPresident pl2_ = new HandPresident();
        pl2_.ajouter(CardPresident.HEART_5);
        pl2_.ajouter(CardPresident.HEART_6);
        pt_.getCards().add(pl2_);
        g_.setProgressingTrick(pt_);
        CustList<TrickPresident> done_ = new CustList<TrickPresident>();
        done_.add(new TrickPresident());
        g_.setTricks(done_);
        CustList<HandPresident> sw_ = new CustList<HandPresident>();
        sw_.add(new HandPresident());
        g_.setSwitchedCards(sw_);
        g_.setType(GameType.RANDOM);
        ls_.add(g_);
        CustList<GamePresident> o_ = saveResultPresident(ls_);
        assertEq(1,o_.size());
        assertEq(1,o_.get(0).getRanks().size());
        assertEq(2,o_.get(0).getRanks().get(0));
        assertEq(1,o_.get(0).getDeal().getDeal().size());
        assertEq(2,o_.get(0).getDeal().getDeal().get(0).getCards().size());
        assertEq(CardPresident.HEART_1,o_.get(0).getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardPresident.HEART_2,o_.get(0).getDeal().getDeal().get(0).getCards().get(1));
        assertEq(2,o_.get(0).getProgressingTrick().getCards().size());
        assertEq(2,o_.get(0).getProgressingTrick().getCards().get(0).getCards().size());
        assertEq(CardPresident.HEART_3,o_.get(0).getProgressingTrick().getCards().get(0).getCards().get(0));
        assertEq(CardPresident.HEART_4,o_.get(0).getProgressingTrick().getCards().get(0).getCards().get(1));
        assertEq(2,o_.get(0).getProgressingTrick().getCards().get(1).getCards().size());
        assertEq(CardPresident.HEART_5,o_.get(0).getProgressingTrick().getCards().get(1).getCards().get(0));
        assertEq(CardPresident.HEART_6,o_.get(0).getProgressingTrick().getCards().get(1).getCards().get(1));
    }
    @Test
    public void t2() {
        TricksHandsPresident t_ = new TricksHandsPresident();
        t_.setReversed(false);
        t_.setRanks(Bytes.newList());
        t_.setTricks(new CustList<TrickPresident>());
        t_.setSwitchedCards(new CustList<HandPresident>());
        t_.setDistribution(new DealPresident());
        t_.setProgressingTrick(new TrickPresident());
        t_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        TricksHandsPresident o_ = saveTricksHandsPresident(t_);
        assertFalse(o_.isReversed());
    }
    @Test
    public void t3() {
        TricksHandsPresident t_ = new TricksHandsPresident();
        t_.setReversed(true);
        t_.setRanks(Bytes.newList());
        t_.setTricks(new CustList<TrickPresident>());
        t_.setSwitchedCards(new CustList<HandPresident>());
        t_.setDistribution(new DealPresident());
        t_.setProgressingTrick(new TrickPresident());
        t_.setCardsHandsAtInitialState(new CustList<HandPresident>());
        TricksHandsPresident o_ = saveTricksHandsPresident(t_);
        assertTrue(o_.isReversed());
    }
    @Test
    public void t4() {
        DisplayingPresident d_ = new DisplayingPresident();
        d_.setNbDeals(1);
        d_.getDisplaying().setDecreasing(true);
        d_.getDisplaying().setClockwise(true);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.HEART);
        d_.getDisplaying().setSuits(s_);
        DisplayingPresident o_ = saveDisplayingPresident(d_);
        assertEq(1,o_.getNbDeals());
        assertTrue(o_.getDisplaying().isDecreasing());
        assertTrue(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.HEART,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t5() {
        DisplayingPresident d_ = new DisplayingPresident();
        d_.setNbDeals(2);
        d_.getDisplaying().setDecreasing(false);
        d_.getDisplaying().setClockwise(false);
        IdList<Suit> s_ = new IdList<Suit>();
        s_.add(Suit.SPADE);
        d_.getDisplaying().setSuits(s_);
        DisplayingPresident o_ = saveDisplayingPresident(d_);
        assertEq(2,o_.getNbDeals());
        assertFalse(o_.getDisplaying().isDecreasing());
        assertFalse(o_.getDisplaying().isClockwise());
        assertEq(1,o_.getDisplaying().getSuits().size());
        assertEq(Suit.SPADE,o_.getDisplaying().getSuits().get(0));
    }
    @Test
    public void t6() {
        ResultsPresident ls_ = new ResultsPresident();
        GamePresident g_ = new GamePresident();
        g_.setRanks(Bytes.newList((byte) 2));
        DealPresident d_ = new DealPresident();
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.HEART_2);
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        TrickPresident pt_ = new TrickPresident();
        HandPresident pl_ = new HandPresident();
        pl_.ajouter(CardPresident.HEART_3);
        pl_.ajouter(CardPresident.HEART_4);
        pt_.getCards().add(pl_);
        HandPresident pl2_ = new HandPresident();
        pl2_.ajouter(CardPresident.HEART_5);
        pl2_.ajouter(CardPresident.HEART_6);
        pt_.getCards().add(pl2_);
        g_.setProgressingTrick(pt_);
        CustList<TrickPresident> done_ = new CustList<TrickPresident>();
        done_.add(new TrickPresident());
        g_.setTricks(done_);
        CustList<HandPresident> sw_ = new CustList<HandPresident>();
        sw_.add(new HandPresident());
        g_.setSwitchedCards(sw_);
        g_.setType(GameType.RANDOM);
        ls_.setGame(g_);
        CustList<Longs> lgs_ = new CustList<Longs>();
        lgs_.add(Longs.newList(7));
        ls_.getRes().setScores(lgs_);
        ls_.getRes().setNicknames(new StringList());
        ls_.getRes().setSigmas(new CustList<Rate>());
        ResultsPresident o_ = saveResultPresident(ls_);
        assertEq(1,o_.getGame().getRanks().size());
        assertEq(2,o_.getGame().getRanks().get(0));
        assertEq(1,o_.getGame().getDeal().getDeal().size());
        assertEq(2,o_.getGame().getDeal().getDeal().get(0).getCards().size());
        assertEq(CardPresident.HEART_1,o_.getGame().getDeal().getDeal().get(0).getCards().get(0));
        assertEq(CardPresident.HEART_2,o_.getGame().getDeal().getDeal().get(0).getCards().get(1));
        assertEq(1,o_.getRes().getScores().size());
        assertEq(1,o_.getRes().getScores().get(0).size());
        assertEq(7,o_.getRes().getScores().get(0).get(0));
    }
    @Test
    public void t7() {
        GamePresident g_ = new GamePresident();
        g_.setType(GameType.RANDOM);
        g_.getRules().setNbPlayers(7);
        g_.setDeal(new DealPresident());
        GamePresident o_ = saveGamePresident(g_);
        assertEq(7,o_.getRules().getNbPlayers());
    }
    @Test
    public void t8() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.HEART_2);
        HandPresident o_ = saveHandPresident(h_);
        assertEq(2,o_.getCards().size());
        assertEq(CardPresident.HEART_1,o_.getCards().get(0));
        assertEq(CardPresident.HEART_2,o_.getCards().get(1));
    }
    @Test
    public void t9() {
        RulesPresident d_ = new RulesPresident();
        d_.setSwitchCards(true);
        d_.setLooserStartsFirst(true);
        d_.setLoosingIfFinishByBestCards(true);
        d_.setHasToPlay(true);
        d_.setPossibleReversing(true);
        RulesPresident o_ = saveRulesPresident(d_);
        assertTrue(o_.isSwitchCards());
        assertTrue(o_.isLooserStartsFirst());
        assertTrue(o_.isLoosingIfFinishByBestCards());
        assertTrue(o_.isHasToPlay());
        assertTrue(o_.isPossibleReversing());
    }
    @Test
    public void t10() {
        RulesPresident d_ = new RulesPresident();
        d_.setSwitchCards(false);
        d_.setLooserStartsFirst(false);
        d_.setLoosingIfFinishByBestCards(false);
        d_.setHasToPlay(false);
        d_.setPossibleReversing(false);
        RulesPresident o_ = saveRulesPresident(d_);
        assertFalse(o_.isSwitchCards());
        assertFalse(o_.isLooserStartsFirst());
        assertFalse(o_.isLoosingIfFinishByBestCards());
        assertFalse(o_.isHasToPlay());
        assertFalse(o_.isPossibleReversing());
    }
}
