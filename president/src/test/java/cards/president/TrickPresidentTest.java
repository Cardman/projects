package cards.president;
import static cards.president.EquallablePresidentUtil.assertEq;

import org.junit.Test;

import code.util.EqList;
import code.util.Numbers;
import cards.president.enumerations.CardPresident;

@SuppressWarnings("static-method")
public class TrickPresidentTest {

    @Test
    public void getPlayer1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 0);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        assertEq(0, tr_.getPlayer(0, (byte) 3));
    }

    @Test
    public void getPlayer2Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 2);
        assertEq(1, tr_.getPlayer(0, (byte) 3));
    }

    @Test
    public void getPlayer3Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 2);
        assertEq(2, tr_.getPlayer(1, (byte) 3));
    }

    @Test
    public void getPlayer4Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 1);
        assertEq(1, tr_.getPlayer(3, (byte) 3));
    }

    @Test
    public void getPlayedCardsIndexes1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 1);
        Numbers<Integer> indexes_ = tr_.getPlayedCardsIndexes((byte) 1, (byte) 3);
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0).intValue());
        assertEq(3, indexes_.get(1).intValue());
    }


    @Test
    public void getPlayedCardsIndexes2Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 1);
        Numbers<Integer> indexes_ = tr_.getPlayedCardsIndexes((byte) 2, (byte) 3);
        assertEq(1, indexes_.size());
        assertEq(1, indexes_.get(0).intValue());
    }

    @Test
    public void getFilledHandsBefore1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 1);
        EqList<HandPresident> hs_ = tr_.getFilledHandsBefore(1);
        assertEq(1, hs_.size());
        assertEq(1, hs_.get(0).total());
        assertEq(CardPresident.CLUB_3, hs_.get(0).carte(0));
    }

    @Test
    public void getFilledHandsBefore2Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_, (byte) 2);
        EqList<HandPresident> hs_ = tr_.getFilledHandsBefore(4);
        assertEq(3, hs_.size());
        assertEq(1, hs_.get(0).total());
        assertEq(CardPresident.CLUB_3, hs_.get(0).carte(0));
        assertEq(1, hs_.get(1).total());
        assertEq(CardPresident.CLUB_4, hs_.get(1).carte(0));
        assertEq(1, hs_.get(2).total());
        assertEq(CardPresident.CLUB_5, hs_.get(2).carte(0));
    }

    @Test
    public void getPlayedCards1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_, (byte) 2);
        EqList<HandPresident> hs_ = tr_.getPlayedCards((byte) 1, (byte) 3);
        assertEq(2, hs_.size());
        assertEq(1, hs_.get(0).total());
        assertEq(CardPresident.CLUB_3, hs_.get(0).carte(0));
        assertEq(0, hs_.get(1).total());
    }

    @Test
    public void getBestCards1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_, (byte) 2);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(1, bestCards_.total());
        assertEq(CardPresident.CLUB_6, bestCards_.carte(0));
    }

    @Test
    public void getBestCards2Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(1, bestCards_.total());
        assertEq(CardPresident.CLUB_5, bestCards_.carte(0));
    }

    @Test
    public void getBestCards3Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(0, bestCards_.total());
    }

    @Test
    public void getRamasseur1Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_, (byte) 2);
        assertEq(2, tr_.getRamasseur((byte) 3));
    }

    @Test
    public void getRamasseur2Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        assertEq(0, tr_.getRamasseur((byte) 3));
    }

    @Test
    public void getRamasseur3Test() {
        TrickPresident tr_ = new TrickPresident((byte) 1);
        HandPresident h_;
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 0);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 1);
        h_ = new HandPresident();
        tr_.ajouter(h_, (byte) 2);
        assertEq(0, tr_.getRamasseur((byte) 3));
    }
}
