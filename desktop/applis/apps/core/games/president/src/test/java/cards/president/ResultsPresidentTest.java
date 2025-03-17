package cards.president;

import cards.consts.GameType;
import cards.president.enumerations.CardPresident;
import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public final class ResultsPresidentTest extends EquallablePresidentUtil {
    @Test
    public void calculateScoresTest() {
        ResultsPresident res_ = new ResultsPresident();
        res_.getRes().setUser(0);
//        res_.getRes().setScores(new CustList<Longs>());
        res_.getRes().setGlobalResultsPageTitle("");
        res_.getRes().setDetailResultsTitle("");
        res_.getRes().setSigmas(new CustList<Rate>());
        res_.getRes().setSums(new Longs());
        res_.getRes().setNicknames(new StringList());
        res_.getRes().setRenderedPages(new StringMap<String>());
//        res_.getRes().setLoc("");
        res_.calculateScores(new CustList<Longs>(),new Longs(),GameType.RANDOM,1,0);
        assertEq(0, res_.getRes().getScores().size());
        assertEq(0, res_.getRes().getSums().size());
        assertEq(0, res_.getRes().getSigmas().size());
        assertEq(0, res_.getRes().getNicknames().size());
        assertEq(0, res_.getRes().getRenderedPages().size());
        assertEq(0, res_.getRes().getUser());
//        assertEq("", res_.getRes().getLoc());
        assertEq("", res_.getRes().getGlobalResultsPageTitle());
        assertEq("", res_.getRes().getDetailResultsTitle());
    }

    @Test
    public void initialize1Test() {
        GamePresident g_ = getSimpleDeal();
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(g_);
        res_.getRes().setUser(0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>());
        assertEq(4, res_.getRes().getScores().get(0).size());
        assertSame(g_, res_.getGame());
    }

    @Test
    public void initialize2Test() {
        GamePresident g_ = getSimpleDeal();
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(g_);
        res_.getRes().setUser(0);
        res_.initialize(new StringList("1","2","3","4"),new CustList<Longs>(), g_.getNewRanks());
        assertEq(4, res_.getRes().getScores().get(0).size());
        assertEq(4, res_.getRes().getHistory().get(0).getScores().size());
    }

    @Test
    public void initPartie1Test() {
        GamePresident g_ = getSimpleDeal();
        HandPresident cards_ = g_.getCards();
        g_.restituerMainsDepartRejouerDonne();
        assertTrue(cards_.validStack(1));
        assertEq(4,g_.getDeal().nombreDeMains());
        assertEq(13,g_.getDeal().hand(0).total());
        assertEq(13,g_.getDeal().hand(1).total());
        assertEq(13,g_.getDeal().hand(2).total());
        assertEq(13,g_.getDeal().hand(3).total());
    }

    @Test
    public void initPartie2Test() {
        GamePresident g_ = getSimpleDeal2();
        HandPresident cards_ = g_.getCards();
        g_.restituerMainsDepartRejouerDonne();
        assertTrue(cards_.validStack(1));
        assertEq(4,g_.getDeal().nombreDeMains());
        assertEq(13,g_.getDeal().hand(0).total());
        assertEq(13,g_.getDeal().hand(1).total());
        assertEq(13,g_.getDeal().hand(2).total());
        assertEq(13,g_.getDeal().hand(3).total());
    }

    GamePresident getSimpleDeal2() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = new Ints();
        rk_.add(1);
        rk_.add(4);
        rk_.add(3);
        rk_.add(2);
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        g_.donnerMeilleuresCartes();
        g_.giveWorstCards();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_JACK);
        played_.ajouter(CardPresident.DIAMOND_JACK);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_5);
        played_.ajouter(CardPresident.SPADE_5);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_6);
        played_.ajouter(CardPresident.SPADE_6);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_QUEEN);
        played_.ajouter(CardPresident.SPADE_QUEEN);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_1);
        played_.ajouter(CardPresident.CLUB_1);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        g_.noPlay();
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        return g_;
    }

    GamePresident getSimpleDeal() {
        RulesPresident r_ = new RulesPresident(4);
        Ints rk_ = new Ints();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, 0);
        GamePresident g_ = new GamePresident(GameType.EDIT, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        HandPresident played_;
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_3);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_4);
        played_.ajouter(CardPresident.DIAMOND_4);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_4);
        played_.ajouter(CardPresident.HEART_4);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_7);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_8);
        played_.ajouter(CardPresident.HEART_8);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_8);
        played_.ajouter(CardPresident.CLUB_8);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_9);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_10);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_QUEEN);
        played_.ajouter(CardPresident.DIAMOND_QUEEN);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_QUEEN);
        played_.ajouter(CardPresident.CLUB_QUEEN);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_KING);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_1);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_1);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_2);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_JACK);
        played_.ajouter(CardPresident.SPADE_JACK);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_JACK);
        played_.ajouter(CardPresident.HEART_JACK);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.DIAMOND_2);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_5);
        played_.ajouter(CardPresident.HEART_5);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.CLUB_5);
        played_.ajouter(CardPresident.DIAMOND_5);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.HEART_6);
        played_.ajouter(CardPresident.DIAMOND_6);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_6);
        played_.ajouter(CardPresident.CLUB_6);
        g_.addCardsToCurrentTrickAndLoop(played_);
        played_ = new HandPresident();
        played_.ajouter(CardPresident.SPADE_2);
        played_.ajouter(CardPresident.CLUB_2);
        g_.addCardsToCurrentTrickAndLoop(played_);
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
