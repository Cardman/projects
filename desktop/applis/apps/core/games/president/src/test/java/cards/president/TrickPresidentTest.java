package cards.president;

import code.util.CustList;
import org.junit.Test;

import cards.president.enumerations.CardPresident;
import code.util.Ints;


public class TrickPresidentTest extends EquallablePresidentUtil {

    @Test
    public void getPlayer1Test() {
        TrickPresident tr_ = new TrickPresident(0);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        assertEq(0, tr_.getPlayer(0, 3));
    }

    @Test
    public void getPlayer2Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        assertEq(1, tr_.getPlayer(0, 3));
    }

    @Test
    public void getPlayer3Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        assertEq(2, tr_.getPlayer(1, 3));
    }

    @Test
    public void getPlayer4Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        assertEq(1, tr_.getPlayer(3, 3));
    }

    @Test
    public void getPlayedCardsIndexes1Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        Ints indexes_ = tr_.getPlayedCardsIndexes(1, 3);
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(3, indexes_.get(1));
    }


    @Test
    public void getPlayedCardsIndexes2Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        Ints indexes_ = tr_.getPlayedCardsIndexes(2, 3);
        assertEq(1, indexes_.size());
        assertEq(1, indexes_.get(0));
    }

    @Test
    public void getFilledHandsBefore1Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        CustList<HandPresident> hs_ = tr_.getFilledHandsBefore(1);
        assertEq(1, hs_.size());
        assertEq(1, hs_.get(0).total());
        assertEq(CardPresident.CLUB_3, hs_.get(0).carte(0));
    }

    @Test
    public void getFilledHandsBefore2Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_);
        CustList<HandPresident> hs_ = tr_.getFilledHandsBefore(4);
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
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_);
        CustList<HandPresident> hs_ = tr_.getPlayedCards(1, 3);
        assertEq(2, hs_.size());
        assertEq(1, hs_.get(0).total());
        assertEq(CardPresident.CLUB_3, hs_.get(0).carte(0));
        assertEq(0, hs_.get(1).total());
    }

    @Test
    public void getBestCards1Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(1, bestCards_.total());
        assertEq(CardPresident.CLUB_6, bestCards_.carte(0));
    }

    @Test
    public void getBestCards2Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(1, bestCards_.total());
        assertEq(CardPresident.CLUB_5, bestCards_.carte(0));
    }

    @Test
    public void getBestCards3Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        HandPresident bestCards_ = tr_.getBestCards();
        assertEq(0, bestCards_.total());
    }

    @Test
    public void getRamasseur1Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_6);
        tr_.ajouter(h_);
        assertEq(2, tr_.getRamasseur(3));
    }

    @Test
    public void getRamasseur2Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_4);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_5);
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        assertEq(0, tr_.getRamasseur(3));
    }

    @Test
    public void getRamasseur3Test() {
        TrickPresident tr_ = new TrickPresident(1);
        HandPresident h_;
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        h_ = new HandPresident();
        tr_.ajouter(h_);
        assertEq(0, tr_.getRamasseur(3));
    }
}
