package cards.president;

import org.junit.Test;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.util.*;


public class GamePresidentTest extends EquallablePresidentUtil {

    @Test
    public void getWinners1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertEq(0, g_.getWinners().size());
        assertTrue(g_.readyToPlay());
    }

    @Test
    public void getWinners2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        rk_.add(4);
        rk_.add(1);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        Ints ws_ = g_.getWinners();
        assertEq(2, ws_.size());
        assertEq(1, ws_.get(0));
        assertEq(3, ws_.get(1));
        ws_ = g_.getWinners(Ints.newList(3));
        assertEq(1, ws_.size());
        assertEq(3, ws_.get(0));
        assertEq(1,g_.numberGivenCards(3));
        assertEq(0,g_.numberGivenCards(0));
    }


    @Test
    public void getLoosers1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertEq(0, g_.getLoosers().size());
    }

    @Test
    public void getLoosers2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        rk_.add(4);
        rk_.add(1);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        Ints ws_ = g_.getLoosers();
        assertEq(2, ws_.size());
        assertEq(0, ws_.get(0));
        assertEq(2, ws_.get(1));
        ws_ = g_.getLoosers(Ints.newList(2));
        assertEq(1, ws_.size());
        assertEq(2, ws_.get(0));
    }

    @Test
    public void giveWorstCards1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        rk_.add(4);
        rk_.add(1);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        HandPresident hand_ = new HandPresident();
        hand_.ajouter(CardPresident.DIAMOND_3);
        assertTrue(g_.giveWorstCards(Ints.newList(0,3), 3, hand_));
    }

    @Test
    public void giveWorstCards2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        rk_.add(4);
        rk_.add(1);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        HandPresident hand_ = new HandPresident();
        assertTrue(!g_.giveWorstCards(Ints.newList(0,3), 3, hand_));
    }

    @Test
    public void giveWorstCards3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        rk_.add(4);
        rk_.add(1);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        HandPresident hand_ = new HandPresident();
        assertTrue(g_.giveWorstCards(Ints.newList(0,2), 3 , hand_));
    }
    @Test
    public void getStatus1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.SKIPPED, g_.getStatus());
    }

    @Test
    public void getStatus2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.HAS_TO_EQUAL, g_.getStatus());
    }

    @Test
    public void getStatus3Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.PASS, g_.getStatus());
    }

    @Test
    public void getStatus4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus6Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.PASS, g_.getStatus());
    }

    @Test
    public void getStatus7Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.PASS, g_.getStatus());
    }

    @Test
    public void getStatus8Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus9Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus10Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus11Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus12Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus13Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.CAN_PLAY, g_.getStatus());
    }

    @Test
    public void getStatus14Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getProgressingTrick().ajouter(played_);
        assertEq(Playing.HAS_TO_EQUAL, g_.getStatus());
    }
    @Test
    public void cartesJouables1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident playable_ = playable(g_, 1);
        assertEq(13, playable_.total());
    }

    @Test
    public void cartesJouables2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.FORBIDDEN);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 2);
        playable_.sortCards(false, g_.isReversed());
        assertEq(7, playable_.total());
        assertEq(CardPresident.HEART_9, playable_.carte(0));
        assertEq(CardPresident.HEART_10, playable_.carte(1));
        assertEq(CardPresident.SPADE_QUEEN, playable_.carte(2));
        assertEq(CardPresident.CLUB_QUEEN, playable_.carte(3));
        assertEq(CardPresident.HEART_KING, playable_.carte(4));
        assertEq(CardPresident.SPADE_2, playable_.carte(5));
        assertEq(CardPresident.CLUB_2, playable_.carte(6));
    }

    @Test
    public void cartesJouables3Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 2);
        playable_.sortCards(false, g_.isReversed());
        assertEq(8, playable_.total());
        assertEq(CardPresident.HEART_7, playable_.carte(0));
        assertEq(CardPresident.HEART_9, playable_.carte(1));
        assertEq(CardPresident.HEART_10, playable_.carte(2));
        assertEq(CardPresident.SPADE_QUEEN, playable_.carte(3));
        assertEq(CardPresident.CLUB_QUEEN, playable_.carte(4));
        assertEq(CardPresident.HEART_KING, playable_.carte(5));
        assertEq(CardPresident.SPADE_2, playable_.carte(6));
        assertEq(CardPresident.CLUB_2, playable_.carte(7));
    }

    @Test
    public void cartesJouables4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 2);
        playable_.sortCards(false, g_.isReversed());
        assertEq(8, playable_.total());
        assertEq(CardPresident.HEART_7, playable_.carte(0));
        assertEq(CardPresident.HEART_9, playable_.carte(1));
        assertEq(CardPresident.HEART_10, playable_.carte(2));
        assertEq(CardPresident.SPADE_QUEEN, playable_.carte(3));
        assertEq(CardPresident.CLUB_QUEEN, playable_.carte(4));
        assertEq(CardPresident.HEART_KING, playable_.carte(5));
        assertEq(CardPresident.SPADE_2, playable_.carte(6));
        assertEq(CardPresident.CLUB_2, playable_.carte(7));
    }

    @Test
    public void cartesJouables5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 3);
        playable_.sortCards(false, g_.isReversed());
        assertEq(0, playable_.total());
    }

    @Test
    public void cartesJouables6Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 3);
        playable_.sortCards(false, g_.isReversed());
        assertEq(1, playable_.total());
        assertEq(CardPresident.DIAMOND_7, playable_.carte(0));
    }

    @Test
    public void cartesJouables7Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 3);
        playable_.sortCards(false, g_.isReversed());
        assertEq(0, playable_.total());
    }

    @Test
    public void cartesJouables8Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 3);
        playable_.sortCards(false, g_.isReversed());
        assertEq(0, playable_.total());
    }

    @Test
    public void cartesJouables9Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.getProgressingTrick().ajouter(played_);
        HandPresident playable_ = playable(g_, 3);
        playable_.sortCards(false, g_.isReversed());
        assertEq(0, playable_.total());
    }
    @Test
    public void cartesJouables10Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.getDeal().hand(1).supprimerCartes();
        g_.getLastStatus().put(1, Playing.FINISH);
        g_.getProgressingTrick().ajouter(cards(CardPresident.WHITE));
        HandPresident playable_ = playable(g_, 1);
        assertEq(0, playable_.total());
    }
    @Test
    public void allowPlaying1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertTrue(g_.allowPlaying(CardPresident.SPADE_3));
    }

    @Test
    public void allowPlaying2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.FORBIDDEN);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(!g_.allowPlaying(CardPresident.SPADE_5));
    }
    @Test
    public void canPass1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(false);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        play(g_,cards(CardPresident.CLUB_7), 1);
        assertTrue(g_.canPass());
    }

    @Test
    public void canPass2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        play(g_,cards(CardPresident.CLUB_7), 1);
        assertTrue(!g_.canPass());
    }

    @Test
    public void canPass3Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 2);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        play(g_,cards(CardPresident.SPADE_1,CardPresident.CLUB_1), 3);
        assertTrue(g_.canPass());
    }

    @Test
    public void canPass4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        play(g_,cards(CardPresident.SPADE_3), 1);
        play(g_,cards(CardPresident.HEART_3), 2);
        play(g_,cards(CardPresident.DIAMOND_3), 3);
        play(g_,cards(CardPresident.CLUB_3), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4), 0);
        play(g_,cards(CardPresident.SPADE_4,CardPresident.HEART_4), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.CLUB_7), 1);
        play(g_,cards(CardPresident.HEART_7), 2);
        play(g_,cards(CardPresident.DIAMOND_7), 3);
        play(g_,cards(CardPresident.SPADE_7), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.DIAMOND_8,CardPresident.HEART_8), 0);
        play(g_,cards(CardPresident.SPADE_8,CardPresident.CLUB_8), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.SPADE_9), 1);
        play(g_,cards(CardPresident.HEART_9), 2);
        play(g_,cards(CardPresident.DIAMOND_9), 3);
        play(g_,cards(CardPresident.CLUB_9), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.SPADE_10), 0);
        play(g_,cards(CardPresident.CLUB_10), 1);
        play(g_,cards(CardPresident.HEART_10), 2);
        play(g_,cards(CardPresident.DIAMOND_10), 3);
        g_.initializeTrick(3);
        play(g_,cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN), 3);
        play(g_,cards(), 0);
        play(g_,cards(), 1);
        play(g_,cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN), 2);
        g_.initializeTrick(2);
        play(g_,cards(CardPresident.HEART_KING), 2);
        play(g_,cards(CardPresident.DIAMOND_KING), 3);
        play(g_,cards(CardPresident.SPADE_KING), 0);
        play(g_,cards(CardPresident.CLUB_KING), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.HEART_1), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.DIAMOND_1), 0);
        play(g_,cards(), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.HEART_2), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK), 0);
        assertTrue(g_.canPass());
    }

    @Test
    public void canPass5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        r_.setLoosingIfFinishByBestCards(false);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        play(g_,cards(CardPresident.SPADE_3), 1);
        play(g_,cards(CardPresident.HEART_3), 2);
        play(g_,cards(CardPresident.DIAMOND_3), 3);
        play(g_,cards(CardPresident.CLUB_3), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4), 0);
        play(g_,cards(CardPresident.SPADE_4,CardPresident.HEART_4), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.CLUB_7), 1);
        play(g_,cards(CardPresident.HEART_7), 2);
        play(g_,cards(CardPresident.DIAMOND_7), 3);
        play(g_,cards(CardPresident.SPADE_7), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.DIAMOND_8,CardPresident.HEART_8), 0);
        play(g_,cards(CardPresident.SPADE_8,CardPresident.CLUB_8), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.SPADE_9), 1);
        play(g_,cards(CardPresident.HEART_9), 2);
        play(g_,cards(CardPresident.DIAMOND_9), 3);
        play(g_,cards(CardPresident.CLUB_9), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.SPADE_10), 0);
        play(g_,cards(CardPresident.CLUB_10), 1);
        play(g_,cards(CardPresident.HEART_10), 2);
        play(g_,cards(CardPresident.DIAMOND_10), 3);
        g_.initializeTrick(3);
        play(g_,cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN), 3);
        play(g_,cards(), 0);
        play(g_,cards(), 1);
        play(g_,cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN), 2);
        g_.initializeTrick(2);
        play(g_,cards(CardPresident.HEART_KING), 2);
        play(g_,cards(CardPresident.DIAMOND_KING), 3);
        play(g_,cards(CardPresident.SPADE_KING), 0);
        play(g_,cards(CardPresident.CLUB_KING), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.HEART_1), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.DIAMOND_1), 0);
        play(g_,cards(), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.HEART_2), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK), 0);
        assertTrue(!g_.canPass());
    }

    @Test
    public void canPass6Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setHasToPlay(true);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        play(g_,cards(CardPresident.SPADE_3), 1);
        play(g_,cards(CardPresident.HEART_3), 2);
        play(g_,cards(CardPresident.DIAMOND_3), 3);
        play(g_,cards(CardPresident.CLUB_3), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4), 0);
        play(g_,cards(CardPresident.SPADE_4,CardPresident.HEART_4), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.CLUB_7), 1);
        play(g_,cards(CardPresident.HEART_7), 2);
        play(g_,cards(CardPresident.DIAMOND_7), 3);
        play(g_,cards(CardPresident.SPADE_7), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.DIAMOND_8,CardPresident.HEART_8), 0);
        play(g_,cards(CardPresident.SPADE_8,CardPresident.CLUB_8), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.SPADE_9), 1);
        play(g_,cards(CardPresident.HEART_9), 2);
        play(g_,cards(CardPresident.DIAMOND_9), 3);
        play(g_,cards(CardPresident.CLUB_9), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.SPADE_10), 0);
        play(g_,cards(CardPresident.CLUB_10), 1);
        play(g_,cards(CardPresident.HEART_10), 2);
        play(g_,cards(CardPresident.DIAMOND_10), 3);
        g_.initializeTrick(3);
        play(g_,cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN), 3);
        play(g_,cards(), 0);
        play(g_,cards(), 1);
        play(g_,cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN), 2);
        g_.initializeTrick(2);
        play(g_,cards(CardPresident.HEART_KING), 2);
        play(g_,cards(CardPresident.DIAMOND_KING), 3);
        play(g_,cards(CardPresident.SPADE_KING), 0);
        play(g_,cards(CardPresident.CLUB_KING), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.DIAMOND_2), 1);
        g_.initializeTrick(1);
        play(g_,cards(CardPresident.HEART_1), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.DIAMOND_1), 0);
        play(g_,cards(), 1);
        play(g_,cards(), 2);
        play(g_,cards(), 3);
        play(g_,cards(CardPresident.HEART_2), 0);
        g_.initializeTrick(0);
        play(g_,cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK), 0);
        assertTrue(!g_.canPass());
    }

    @Test
    public void keepPlayingCurrentTrick1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick4Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick5Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(0,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(!g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick6Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick7Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(1,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(!g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick8Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(1,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
//        played_ = new HandPresident();
//        g_.getProgressingTrick().ajouter(played_);
        assertTrue(!g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick9Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        HandPresident played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(1,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(2,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        played_ = new HandPresident();
        g_.getLastStatus().put(3,Playing.PASS);
        g_.getProgressingTrick().ajouter(played_);
        assertTrue(!g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick10Test() {
        RulesPresident r_ = new RulesPresident(2);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = dealTwoPlayers();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick11Test() {
        RulesPresident r_ = new RulesPresident(2);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = dealTwoPlayers();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(HandPresident.create(new CardPresident[]{CardPresident.HEART_7}));
        assertTrue(g_.keepPlayingCurrentTrick());
    }

    @Test
    public void keepPlayingCurrentTrick12Test() {
        RulesPresident r_ = new RulesPresident(2);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = dealTwoPlayers();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(HandPresident.create(new CardPresident[]{CardPresident.HEART_7}));
        g_.noPlay();
        assertEq(1,g_.getTricks().size());
        assertEq(0,g_.getProgressingTrick().total());
        assertEq(1,g_.getProgressingTrick().getEntameur());
    }

    @Test
    public void keepPlayingCurrentTrick13Test() {
        RulesPresident r_ = new RulesPresident(2);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = dealTwoPlayers();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(HandPresident.create(new CardPresident[]{CardPresident.HEART_7}));
        g_.addCardsToCurrentTrickAndLoop(HandPresident.create(new CardPresident[]{CardPresident.CLUB_7}));
        assertEq(0, g_.nextPlayer());
    }

    @Test
    public void addCardsToCurrentTrick1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_7));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(1, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(12, g_.getDeal().hand(1).total());
    }

    @Test
    public void addCardsToCurrentTrick2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_2));
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(1, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(1, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_2, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(2, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(12, g_.getDeal().hand(2).total());
    }

    @Test
    public void addCardsToCurrentTrick3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrick(cards(CardPresident.HEART_9));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(1, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.HEART_9, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(12, g_.getDeal().hand(2).total());
    }

    @Test
    public void addCardsToCurrentTrick4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(2, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(2, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick6Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(false);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal2();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(9, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick7Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal2();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(9, g_.getDeal().hand(0).total());
        assertTrue(g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick8Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        r_.setPossibleReversing(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick9Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(2, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(2, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick10Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_8,CardPresident.DIAMOND_8,CardPresident.SPADE_8,CardPresident.HEART_8));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(2, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(1, g_.unionPlis().get(1).total());
        assertEq(4, g_.unionPlis().get(1).carte(0).total());
        assertEq(CardPresident.CLUB_8, g_.unionPlis().get(1).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_8, g_.unionPlis().get(1).carte(0).carte(1));
        assertEq(CardPresident.SPADE_8, g_.unionPlis().get(1).carte(0).carte(2));
        assertEq(CardPresident.HEART_8, g_.unionPlis().get(1).carte(0).carte(3));
        assertEq(5, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick11Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_3));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(1).total());
        assertEq(13, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick12Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_3));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(1).total());
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(13, g_.getDeal().hand(2).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick13Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_3));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(3,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(4, g_.unionPlis().get(0).total());
        assertEq(1, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(0, g_.unionPlis().get(0).carte(1).total());
        assertEq(0, g_.unionPlis().get(0).carte(2).total());
        assertEq(0, g_.unionPlis().get(0).carte(3).total());
        assertEq(13, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrick14Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_8,CardPresident.DIAMOND_8,CardPresident.SPADE_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrick(cards(CardPresident.CLUB_3));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(3,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(0,Playing.CAN_PLAY);
        g_.getLastStatus().put(1,Playing.CAN_PLAY);
        g_.getLastStatus().put(2,Playing.CAN_PLAY);
        g_.getLastStatus().put(3,Playing.CAN_PLAY);
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_7));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(3,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(0,Playing.CAN_PLAY);
        g_.getLastStatus().put(1,Playing.CAN_PLAY);
        g_.getLastStatus().put(2,Playing.CAN_PLAY);
        g_.getLastStatus().put(3,Playing.CAN_PLAY);
        g_.addCardsToCurrentTrick(cards(CardPresident.SPADE_KING));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(3,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(0,Playing.CAN_PLAY);
        g_.getLastStatus().put(1,Playing.CAN_PLAY);
        g_.getLastStatus().put(2,Playing.CAN_PLAY);
        g_.getLastStatus().put(3,Playing.CAN_PLAY);
        g_.addCardsToCurrentTrick(cards(CardPresident.DIAMOND_1));
        g_.getLastStatus().put(1,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(2,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(3,Playing.PASS);
        g_.addCardsToCurrentTrick(cards());
        g_.getLastStatus().put(0,Playing.CAN_PLAY);
        g_.getLastStatus().put(1,Playing.CAN_PLAY);
        g_.getLastStatus().put(2,Playing.CAN_PLAY);
        g_.getLastStatus().put(3,Playing.CAN_PLAY);
        g_.addCardsToCurrentTrick(cards(CardPresident.HEART_2));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(8, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(6).total());
        assertEq(1, g_.unionPlis().get(6).carte(0).total());
        assertEq(CardPresident.HEART_2, g_.unionPlis().get(6).carte(0).carte(0));
//        assertEq(2, g_.unionPlis().get(7).total());
        assertEq(1, g_.unionPlis().get(7).total());
        assertEq(0, g_.unionPlis().get(7).carte(0).total());
//        assertEq(0, g_.unionPlis().get(7).carte(1).total());
//        assertEq(Playing.FINISH, g_.getStatus((byte) 0));
        assertEq(0, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
    }

    @Test
    public void addCardsToCurrentTrickAndLoop1Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(1, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(12, g_.getDeal().hand(1).total());
        assertEq(2, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop2Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2));
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(1, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(1, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_2, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(2, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(12, g_.getDeal().hand(2).total());
        assertEq(2, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop3Test() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_7, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(1, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.HEART_9, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(12, g_.getDeal().hand(2).total());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.SKIPPED, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(2, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(2, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(1, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop6Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(false);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal2();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(9, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop7Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal2();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(9, g_.getDeal().hand(0).total());
        assertTrue(g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop8Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        r_.setPossibleReversing(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.SKIPPED, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop9Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(2, g_.unionPlis().get(0).total());
        assertEq(2, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(2, g_.unionPlis().get(0).carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(1, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop10Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_8,CardPresident.DIAMOND_8,CardPresident.SPADE_8,CardPresident.HEART_8));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(2, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(0).total());
        assertEq(4, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.unionPlis().get(0).carte(0).carte(1));
        assertEq(CardPresident.SPADE_4, g_.unionPlis().get(0).carte(0).carte(2));
        assertEq(CardPresident.HEART_4, g_.unionPlis().get(0).carte(0).carte(3));
        assertEq(1, g_.unionPlis().get(1).total());
        assertEq(4, g_.unionPlis().get(1).carte(0).total());
        assertEq(CardPresident.CLUB_8, g_.unionPlis().get(1).carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_8, g_.unionPlis().get(1).carte(0).carte(1));
        assertEq(CardPresident.SPADE_8, g_.unionPlis().get(1).carte(0).carte(2));
        assertEq(CardPresident.HEART_8, g_.unionPlis().get(1).carte(0).carte(3));
        assertEq(5, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop11Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(1).total());
        assertEq(13, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(2, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop12Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(1).total());
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(13, g_.getDeal().hand(2).total());
        assertTrue(!g_.isReversed());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(1));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop13Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(1, g_.unionPlis().size());
        assertEq(4, g_.unionPlis().get(0).total());
        assertEq(1, g_.unionPlis().get(0).carte(0).total());
        assertEq(CardPresident.CLUB_3, g_.unionPlis().get(0).carte(0).carte(0));
        assertEq(0, g_.unionPlis().get(0).carte(1).total());
        assertEq(0, g_.unionPlis().get(0).carte(2).total());
        assertEq(0, g_.unionPlis().get(0).carte(3).total());
        assertEq(13, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop14Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_8,CardPresident.DIAMOND_8,CardPresident.SPADE_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(8, g_.unionPlis().size());
        assertEq(1, g_.unionPlis().get(6).total());
        assertEq(1, g_.unionPlis().get(6).carte(0).total());
        assertEq(CardPresident.HEART_2, g_.unionPlis().get(6).carte(0).carte(0));
//        assertEq(2, g_.unionPlis().get(7).total());
        assertEq(1, g_.unionPlis().get(7).total());
        assertEq(0, g_.unionPlis().get(7).carte(0).total());
//        assertEq(0, g_.unionPlis().get(7).carte(1).total());
//        assertEq(Playing.FINISH, g_.getStatus((byte) 0));
        assertEq(0, g_.getDeal().hand(0).total());
        assertTrue(!g_.isReversed());
        assertEq(1, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop15Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        r_.setPossibleReversing(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(4, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(0, g_.getProgressingTrick().carte(3).total());
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.SKIPPED, g_.getLastStatus().getVal(2));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(3));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop16Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.SPADE_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(1, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.HEART_3, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(13, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal(3));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop17Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.SPADE_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(1, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.HEART_3, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(12, g_.getDeal().hand(2).total());
        assertTrue(!g_.isReversed());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop18Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        //
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(6, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.SPADE_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(1).total());
        assertEq(1, g_.getProgressingTrick().carte(2).total());
        assertEq(CardPresident.DIAMOND_3, g_.getProgressingTrick().carte(2).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(3).total());
        assertEq(1, g_.getProgressingTrick().carte(4).total());
        assertEq(CardPresident.CLUB_7, g_.getProgressingTrick().carte(4).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(5).total());
        assertEq(11, g_.getDeal().hand(1).total());
        assertEq(13, g_.getDeal().hand(2).total());
        assertTrue(!g_.isReversed());
        assertEq(3, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal(0));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop19Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal4();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        //
        assertEq(2, g_.getProgressingTrick().getEntameur());
        assertEq(0, g_.getProgressingTrick().total());
        assertEq(0, g_.getDeal().hand(0).total());
        assertEq(0, g_.getDeal().hand(1).total());
        assertEq(13, g_.getDeal().hand(2).total());
        assertEq(13, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
        assertEq(2, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.FINISH, g_.getLastStatus().getVal(0));
        assertEq(Playing.FINISH, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop20Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        //
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(8, g_.getProgressingTrick().total());
        assertEq(3, g_.getDeal().hand(0).total());
        assertEq(3, g_.getDeal().hand(1).total());
        assertEq(6, g_.getDeal().hand(2).total());
        assertEq(6, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
        assertEq(1, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.PASS, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(2));
        assertEq(Playing.PASS, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop21Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.NO_SKIP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        assertEq(0, g_.getProgressingTrick().getEntameur());
        assertEq(2, g_.getProgressingTrick().total());
        assertEq(2, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.CLUB_4, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(CardPresident.DIAMOND_4, g_.getProgressingTrick().carte(0).carte(1));
        assertEq(2, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.SPADE_4, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(CardPresident.HEART_4, g_.getProgressingTrick().carte(1).carte(1));
        assertEq(11, g_.getDeal().hand(1).total());
        assertTrue(!g_.isReversed());
        assertEq(2, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(3));
    }

    @Test
    public void addCardsToCurrentTrickAndLoop22Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards());
        assertEq(1, g_.getProgressingTrick().getEntameur());
        assertEq(3, g_.getProgressingTrick().total());
        assertEq(1, g_.getProgressingTrick().carte(0).total());
        assertEq(CardPresident.SPADE_3, g_.getProgressingTrick().carte(0).carte(0));
        assertEq(1, g_.getProgressingTrick().carte(1).total());
        assertEq(CardPresident.HEART_3, g_.getProgressingTrick().carte(1).carte(0));
        assertEq(0, g_.getProgressingTrick().carte(2).total());
        assertEq(13, g_.getDeal().hand(3).total());
        assertTrue(!g_.isReversed());
        assertEq(0, g_.nextPlayer());
        assertEq(4, g_.getLastStatus().size());
        assertEq(Playing.DO_NOT_EQUAL, g_.getLastStatus().getVal(3));
        assertEq(Playing.HAS_TO_EQUAL, g_.getLastStatus().getVal(0));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(1));
        assertEq(Playing.CAN_PLAY, g_.getLastStatus().getVal(2));
    }
    @Test
    public void getNewRanks1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(3, ranks_.get(1));
        assertEq(4, ranks_.get(2));
        assertEq(2, ranks_.get(3));
    }

    @Test
    public void getNewRanks2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(false);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(3, ranks_.get(2));
        assertEq(4, ranks_.get(3));
    }

    @Test
    public void getNewRanks3Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4,CardPresident.HEART_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 2, played_);
//        played_ = new HandPresident();
//        g_.addCardsToCurrentTrick((byte) 3, played_);
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5,CardPresident.HEART_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2,CardPresident.CLUB_2));
        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(4, ranks_.get(2));
        assertEq(3, ranks_.get(3));
    }

    @Test
    public void getNewRanks4Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setLoosingIfFinishByBestCards(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9,CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10,CardPresident.SPADE_10));
//        CheckerGamePresidentWithRules.check(g_);
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_1,CardPresident.CLUB_1));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));

        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(4, ranks_.get(2));
        assertEq(3, ranks_.get(3));
    }

    @Test
    public void getNewRanks5Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setPossibleReversing(true);
        r_.setLoosingIfFinishByBestCards(true);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal3();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_3));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9,CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_4,CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8,CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_10,CardPresident.SPADE_10));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.addCardsToCurrentTrickAndLoop(cards());
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_1,CardPresident.CLUB_1));
        g_.addCardsToCurrentTrickAndLoop(cards());

        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));

        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(4, ranks_.get(2));
        assertEq(3, ranks_.get(3));
    }

    @Test
    public void getNewRanks6Test() {
        RulesPresident r_ = new RulesPresident(13);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal5();
        DealPresident d_ = new DealPresident(hs_, 12);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2,CardPresident.DIAMOND_2,CardPresident.SPADE_2,CardPresident.CLUB_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1,CardPresident.HEART_1,CardPresident.SPADE_1,CardPresident.CLUB_1));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING,CardPresident.CLUB_KING,CardPresident.HEART_KING,CardPresident.DIAMOND_KING));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN,CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10,CardPresident.SPADE_10,CardPresident.CLUB_10,CardPresident.DIAMOND_10));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9,CardPresident.HEART_9,CardPresident.DIAMOND_9,CardPresident.CLUB_9));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8,CardPresident.HEART_8,CardPresident.SPADE_8,CardPresident.CLUB_8));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7,CardPresident.CLUB_7,CardPresident.HEART_7,CardPresident.DIAMOND_7));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6,CardPresident.CLUB_6,CardPresident.HEART_6,CardPresident.DIAMOND_6));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_5,CardPresident.CLUB_5,CardPresident.DIAMOND_5,CardPresident.SPADE_5));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_4,CardPresident.SPADE_4,CardPresident.HEART_4,CardPresident.CLUB_4));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3,CardPresident.CLUB_3,CardPresident.HEART_3,CardPresident.SPADE_3));

        //
        Ints ranks_ = g_.getNewRanks();
        //
        assertEq(13, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(3, ranks_.get(2));
        assertEq(4, ranks_.get(3));
        assertEq(5, ranks_.get(4));
        assertEq(6, ranks_.get(5));
        assertEq(7, ranks_.get(6));
        assertEq(8, ranks_.get(7));
        assertEq(9, ranks_.get(8));
        assertEq(10, ranks_.get(9));
        assertEq(11, ranks_.get(10));
        assertEq(12, ranks_.get(11));
        assertEq(13, ranks_.get(12));
    }

    @Test
    public void getNewRanks7Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.HEART_3);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.SPADE_3);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.DIAMOND_3);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_2);
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.CLUB_3);
        hs_.add(h_);
        DealPresident d_ = new DealPresident(hs_, 3);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_1));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_KING));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_QUEEN));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_JACK));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_10));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_9));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_8));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_7));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_6));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_5));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_4));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.HEART_3));
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_1));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_KING));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_QUEEN));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_JACK));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_10));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_9));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_8));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_7));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_6));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_5));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_4));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        g_.noPlay();
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_2));
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_1));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_KING));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_QUEEN));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_JACK));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_10));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_9));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_8));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_7));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_6));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_5));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_4));
        g_.noPlay();
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.DIAMOND_3));
        Ints ranks_ = g_.getNewRanks();
        assertEq(4, ranks_.size());
        assertEq(1, ranks_.get(0));
        assertEq(2, ranks_.get(1));
        assertEq(3, ranks_.get(2));
        assertEq(4, ranks_.get(3));
    }
    @Test
    public void playedCards1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        HandPresident played_ = g_.addCardsToCurrentTrick();
        assertEq(1, played_.total());
        assertSame(CardPresident.SPADE_3,played_.carte(0));
    }
    @Test
    public void playedCards2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        int  nextPlayer_ = g_.nextPlayer();
        HandPresident played_ = g_.addCardsToCurrentTrick();
        assertEq(1, played_.total());
        assertSame(CardPresident.HEART_3,played_.carte(0));
    }
    @Test
    public void addCardsToCurrentTrickPlayedCards1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        HandPresident played_ = g_.addCardsToCurrentTrick(CardPresident.SPADE_3, 1);
        assertEq(1, played_.total());
        assertSame(CardPresident.SPADE_3,played_.carte(0));
    }
    @Test
    public void currentPlayerHasPlayed1Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        assertTrue(!g_.currentPlayerHasPlayed(1));
        assertTrue(g_.currentPlayerHasPlayed(1));
    }
    @Test
    public void currentPlayerHasPlayed2Test() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        g_.addCardsToCurrentTrickAndLoop(cards(CardPresident.SPADE_3));
        int  nextPlayer_ = g_.nextPlayer();
        assertTrue(!g_.currentPlayerHasPlayed(nextPlayer_));
        assertTrue(g_.currentPlayerHasPlayed(nextPlayer_));
    }
    @Test
    public void getPlayedCardsByStrengthTest() {
        RulesPresident r_ = new RulesPresident(4);
        r_.setLoosingIfFinishByBestCards(true);
        Ints rk_ = Ints.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        assertEq(13, g_.getPlayedCardsByStrength().size());
    }
    @Test
    public void test() {
        GamePresident g_ = new GamePresident();
        g_.setNumber(5);
        g_.setNombre();
        DealPresident d_ = new DealPresident();
        HandPresident h_ = new HandPresident();
        h_.setCards(new IdList<CardPresident>());
        h_.ajouter(CardPresident.WHITE);
        h_.supprimerCarte(0);
        assertEq(0,h_.getCards().size());
        d_.setDeal(new CustList<HandPresident>());
        d_.getDeal().add(h_);
        d_.getDeal().add(new HandPresident());
        d_.getDeal().add(new HandPresident());
        d_.getDeal().add(new HandPresident());
        RulesPresident rules_ = new RulesPresident();
        d_.donneurSuivant(3, rules_);
        d_.setNbDeals(1);
        g_.setDeal(d_);
        g_.setRules(rules_);
        g_.setScores(new Longs());
        g_.setRanks(Ints.newList());
        g_.setSwitchedCards(new CustList<HandPresident>());
        g_.getSwitchedCards().add(new HandPresident());
        g_.getSwitchedCards().add(new HandPresident());
        g_.getSwitchedCards().add(new HandPresident());
        g_.getSwitchedCards().add(new HandPresident());
        TrickPresident trick_ = new TrickPresident();
        trick_.setCards(new CustList<HandPresident>());
        g_.getTricks().add(trick_);
        g_.setType(GameType.RANDOM);
        assertTrue(!h_.validStack(1));
        assertEq(1,d_.getNbDeals());
        assertEq(0,d_.getDealer());
        assertEq(1, d_.getNbDeals());
        assertEq(6,g_.getNumber());
        h_ = HandPresident.pileBase();
        assertTrue(h_.validStack(1));
        g_.giveWorstCards(new HandPresident());
        g_.setRanks(Ints.newList(4,3,2,1));
        assertEq(3,g_.getMatchingWinner(0));
        assertEq(0,g_.getMatchingLoser(3));
        assertEq(0,g_.mainUtilisateurTriee(new DisplayingPresident()).total());
        assertEq(Playing.CAN_PLAY,Playing.retrieve(""));
        assertEq(Playing.CAN_PLAY,Playing.retrieve("0"));
        assertEq(0,new DefGamePresident().strategieEchangeUser(new HandPresident()).total());
        assertEq(0,new DefGamePresident().playedCardsUser(new HandPresident()).total());
        assertEq(0,new DefGamePresident().currentSwitch().total());
        assertEq(0,new DefGamePresident().currentCards().total());
        g_.getDeal().hand(0).ajouter(CardPresident.WHITE);
        g_.getProgressingTrick().setEntameur(0);
        g_.keepPlayingCurrentGame();
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

    static CustList<HandPresident> deal2() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.SPADE_7);
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
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
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
        h_.ajouter(CardPresident.SPADE_5);
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

    static CustList<HandPresident> deal3() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
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

    static CustList<HandPresident> deal4() {
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
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
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
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.HEART_4);
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

    static CustList<HandPresident> deal5() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.DIAMOND_10);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.CLUB_9);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.DIAMOND_7);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.SPADE_5);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_4);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_3);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> dealTwoPlayers() {
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

    private HandPresident playable(GamePresident _g, int _pl) {
        HandPresident hPl_ = _g.getDeal().hand(_pl);
        return _g.cartesJouables(hPl_,_pl);
    }

    private void play(GamePresident _g, HandPresident _played, int _pl) {
        _g.getProgressingTrick().ajouter(_played);
        _g.getDeal().hand(_pl).supprimerCartes(_played);
    }

    public static HandPresident cards(CardPresident... _cards) {
        return HandPresident.create(_cards);
    }
}
