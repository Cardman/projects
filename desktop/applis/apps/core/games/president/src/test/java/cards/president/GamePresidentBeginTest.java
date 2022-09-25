package cards.president;

import cards.president.comparators.HandPresidentRepartition;
import cards.president.enumerations.CardPresident;
import code.util.ByteTreeMap;
import code.util.CustList;
import org.junit.Test;

public final class GamePresidentBeginTest extends CommonGamePresident {
    @Test
    public void getLeadingCardsPlayer1Test() {
        byte nb_ = 4;
        RulesPresident r_ = new RulesPresident(nb_);
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident((byte)0);
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_3);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_6);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        HandPresidentRepartition pl_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(false, trs_, pr_,4);
        ByteTreeMap<HandPresident> m_ = cur_.getCardsByStrength(false);
        CustList<HandPresident> seqs_ = GamePresidentBegin.getLeadingCardsPlayer(false, r_, m_, pl_);
        assertEq(1,seqs_.size());
        assertEq(1,seqs_.get(0).total());
        assertSame(CardPresident.DIAMOND_2,seqs_.get(0).premiereCarte());
    }
    @Test
    public void getLeadingCardsPlayer2Test() {
        byte nb_ = 4;
        RulesPresident r_ = new RulesPresident(nb_);
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident((byte)0);
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_3);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_2);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.DIAMOND_2);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_6);
        t_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        HandPresidentRepartition pl_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(false, trs_, pr_,4);
        ByteTreeMap<HandPresident> m_ = cur_.getCardsByStrength(false);
        CustList<HandPresident> seqs_ = GamePresidentBegin.getLeadingCardsPlayer(false, r_, m_, pl_);
        assertEq(1,seqs_.size());
        assertEq(4,seqs_.get(0).total());
        assertSame(CardPresident.DIAMOND_1,seqs_.get(0).carte(0));
        assertSame(CardPresident.CLUB_1,seqs_.get(0).carte(1));
        assertSame(CardPresident.SPADE_1,seqs_.get(0).carte(2));
        assertSame(CardPresident.HEART_1,seqs_.get(0).carte(3));
    }
    @Test
    public void beginTrick1Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.CLUB_2);
        cur_.ajouter(CardPresident.SPADE_2);
        cur_.ajouter(CardPresident.HEART_2);
        cur_.ajouter(CardPresident.SPADE_KING);
        cur_.ajouter(CardPresident.DIAMOND_KING);
        cur_.ajouter(CardPresident.SPADE_10);
        cur_.ajouter(CardPresident.HEART_10);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.HEART_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident pr_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(4,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.DIAMOND_6, g_).total());
    }
    @Test
    public void beginTrick2Test() {
        byte nb_ = 4;
        byte dealer_ = 3;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.CLUB_2);
        cur_.ajouter(CardPresident.SPADE_2);
        cur_.ajouter(CardPresident.HEART_2);
        cur_.ajouter(CardPresident.SPADE_KING);
        cur_.ajouter(CardPresident.DIAMOND_KING);
        cur_.ajouter(CardPresident.SPADE_10);
        cur_.ajouter(CardPresident.HEART_10);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.HEART_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident pr_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(4,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.DIAMOND_5, g_).total());
    }
    @Test
    public void beginTrick3Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.CLUB_2);
        cur_.ajouter(CardPresident.SPADE_2);
        cur_.ajouter(CardPresident.HEART_2);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.SPADE_KING);
        cur_.ajouter(CardPresident.DIAMOND_KING);
        cur_.ajouter(CardPresident.SPADE_10);
        cur_.ajouter(CardPresident.HEART_10);
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident pr_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(2,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.SPADE_10, g_).total());
    }
    @Test
    public void beginTrick4Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.CLUB_2);
        cur_.ajouter(CardPresident.SPADE_2);
        cur_.ajouter(CardPresident.HEART_2);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(1,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.SPADE_JACK, g_).total());
    }
    @Test
    public void beginTrick5Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_2);
        cur_.ajouter(CardPresident.CLUB_2);
        cur_.ajouter(CardPresident.SPADE_2);
        cur_.ajouter(CardPresident.HEART_2);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(4,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.SPADE_2, g_).total());
    }
    @Test
    public void beginTrick6Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.SPADE_2);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.CLUB_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(4,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.SPADE_1, g_).total());
    }
    @Test
    public void beginTrick7Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        r_.setPossibleReversing(true);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_1);
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.SPADE_2);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.CLUB_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(1,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.DIAMOND_JACK, g_).total());
    }
    @Test
    public void beginTrick8Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        r_.setPossibleReversing(true);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.DIAMOND_JACK);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_1);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.SPADE_2);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.CLUB_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(3,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.DIAMOND_1, g_).total());
    }
    @Test
    public void beginTrick9Test() {
        byte nb_ = 4;
        byte dealer_ = 0;
        RulesPresident r_ = new RulesPresident(nb_);
        r_.setPossibleReversing(true);
        CustList<HandPresident> deal_ = new CustList<HandPresident>();
        HandPresident cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_QUEEN);
        cur_.ajouter(CardPresident.CLUB_QUEEN);
        cur_.ajouter(CardPresident.SPADE_QUEEN);
        cur_.ajouter(CardPresident.HEART_QUEEN);
        cur_.ajouter(CardPresident.HEART_KING);
        cur_.ajouter(CardPresident.CLUB_KING);
        cur_.ajouter(CardPresident.CLUB_10);
        cur_.ajouter(CardPresident.DIAMOND_10);
        cur_.ajouter(CardPresident.DIAMOND_5);
        cur_.ajouter(CardPresident.CLUB_5);
        cur_.ajouter(CardPresident.HEART_5);
        cur_.ajouter(CardPresident.SPADE_5);
        cur_.ajouter(CardPresident.DIAMOND_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.CLUB_1);
        cur_.ajouter(CardPresident.SPADE_1);
        cur_.ajouter(CardPresident.HEART_1);
        cur_.ajouter(CardPresident.DIAMOND_1);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_8);
        cur_.ajouter(CardPresident.CLUB_8);
        cur_.ajouter(CardPresident.SPADE_8);
        cur_.ajouter(CardPresident.HEART_8);
        cur_.ajouter(CardPresident.SPADE_JACK);
        cur_.ajouter(CardPresident.HEART_4);
        cur_.ajouter(CardPresident.SPADE_9);
        cur_.ajouter(CardPresident.HEART_9);
        cur_.ajouter(CardPresident.DIAMOND_6);
        cur_.ajouter(CardPresident.CLUB_6);
        cur_.ajouter(CardPresident.HEART_6);
        cur_.ajouter(CardPresident.SPADE_6);
        cur_.ajouter(CardPresident.SPADE_4);
        deal_.add(cur_);
        cur_ = new HandPresident();
        cur_.ajouter(CardPresident.DIAMOND_7);
        cur_.ajouter(CardPresident.CLUB_7);
        cur_.ajouter(CardPresident.SPADE_7);
        cur_.ajouter(CardPresident.HEART_7);
        cur_.ajouter(CardPresident.CLUB_JACK);
        cur_.ajouter(CardPresident.HEART_JACK);
        cur_.ajouter(CardPresident.CLUB_9);
        cur_.ajouter(CardPresident.DIAMOND_9);
        cur_.ajouter(CardPresident.DIAMOND_3);
        cur_.ajouter(CardPresident.CLUB_3);
        cur_.ajouter(CardPresident.HEART_3);
        cur_.ajouter(CardPresident.SPADE_3);
        cur_.ajouter(CardPresident.CLUB_4);
        deal_.add(cur_);
        CustList<TrickPresident> trs_ = new CustList<TrickPresident>();
        TrickPresident t_ = new TrickPresident(r_.getSortedPlayersAfter(dealer_).first());
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.HEART_10);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_JACK);
        t_.ajouter(h_);
        t_.ajouter();
        t_.ajouter();
        t_.ajouter();
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_2);
        h_.ajouter(CardPresident.SPADE_2);
        t_.ajouter(h_);
        trs_.add(t_);
        t_ = new TrickPresident(t_.getRamasseur(nb_));
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_2);
        h_.ajouter(CardPresident.CLUB_2);
        t_.ajouter(h_);
        trs_.add(t_);
        TrickPresident pr_ = new TrickPresident(t_.getRamasseur(nb_));
        GamePresident g_ = newGamePresident(r_, deal_, trs_, pr_, dealer_);
        GamePresidentBegin gt_ = newGamePresidentBegin(g_);
        HandPresident out_ = gt_.beginTrick();
        assertEq(4,out_.total());
        assertEq(out_.total(), filter(out_, CardPresident.DIAMOND_1, g_).total());
    }

    private HandPresident filter(HandPresident _out, CardPresident _card, GamePresident _g) {
        return _out.getCardsByStrength(_card.strength(_g.isReversed()), _g.isReversed());
    }
}
