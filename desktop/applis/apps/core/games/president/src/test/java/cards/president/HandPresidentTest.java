package cards.president;

import cards.consts.CardChar;
import cards.consts.Suit;
import code.util.IdList;
import code.util.core.StringUtil;
import org.junit.Test;

import cards.president.enumerations.CardPresident;


public class HandPresidentTest extends EquallablePresidentUtil {

    @Test
    public void sortCards1Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_1);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_3);
        h_.sortCards(true, false);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards2Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_1);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.HEART_7);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_3);
        h_.sortCards(true, false);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards3Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_3);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_1);
        h_.sortCards(true, true);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards4Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_3);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.HEART_7);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_1);
        h_.sortCards(true, true);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards5Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_1);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_3);
        h_.sortCards(false, true);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards6Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_1);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.HEART_7);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_3);
        h_.sortCards(false, true);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards7Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_3);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_1);
        h_.sortCards(false, false);
        assertEq(e_, h_);
    }

    @Test
    public void sortCards8Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident e_ = new HandPresident();
        e_.ajouter(CardPresident.CLUB_3);
        e_.ajouter(CardPresident.CLUB_6);
        e_.ajouter(CardPresident.HEART_7);
        e_.ajouter(CardPresident.CLUB_7);
        e_.ajouter(CardPresident.CLUB_10);
        e_.ajouter(CardPresident.CLUB_KING);
        e_.ajouter(CardPresident.CLUB_1);
        h_.sortCards(false, false);
        assertEq(e_, h_);
    }

    @Test
    public void supprimerCartes1Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_2);
        h_.supprimerCartes(d_);
        h_.sortCards(false, false);
        assertEq(7, h_.total());
        assertEq(CardPresident.CLUB_3, h_.carte(0));
        assertEq(CardPresident.CLUB_6, h_.carte(1));
        assertEq(CardPresident.HEART_7, h_.carte(2));
        assertEq(CardPresident.CLUB_7, h_.carte(3));
        assertEq(CardPresident.CLUB_10, h_.carte(4));
        assertEq(CardPresident.CLUB_KING, h_.carte(5));
        assertEq(CardPresident.CLUB_1, h_.carte(6));
    }

    @Test
    public void supprimerCartes2Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_1);
        h_.supprimerCartes(d_);
        h_.sortCards(false, false);
        assertEq(6, h_.total());
        assertEq(CardPresident.CLUB_3, h_.carte(0));
        assertEq(CardPresident.CLUB_6, h_.carte(1));
        assertEq(CardPresident.HEART_7, h_.carte(2));
        assertEq(CardPresident.CLUB_7, h_.carte(3));
        assertEq(CardPresident.CLUB_10, h_.carte(4));
        assertEq(CardPresident.CLUB_KING, h_.carte(5));
    }

    @Test
    public void supprimerCartes3Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        h_.supprimerCartes(d_);
        h_.sortCards(false, false);
        assertEq(6, h_.total());
        assertEq(CardPresident.CLUB_3, h_.carte(0));
        assertEq(CardPresident.CLUB_6, h_.carte(1));
        assertEq(CardPresident.HEART_7, h_.carte(2));
        assertEq(CardPresident.CLUB_10, h_.carte(3));
        assertEq(CardPresident.CLUB_KING, h_.carte(4));
        assertEq(CardPresident.CLUB_1, h_.carte(5));
    }

    @Test
    public void supprimerCartes4Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        h_.supprimerCartes(d_);
        h_.sortCards(false, false);
        assertEq(7, h_.total());
        assertEq(CardPresident.CLUB_3, h_.carte(0));
        assertEq(CardPresident.CLUB_6, h_.carte(1));
        assertEq(CardPresident.HEART_7, h_.carte(2));
        assertEq(CardPresident.CLUB_7, h_.carte(3));
        assertEq(CardPresident.CLUB_10, h_.carte(4));
        assertEq(CardPresident.CLUB_KING, h_.carte(5));
        assertEq(CardPresident.CLUB_1, h_.carte(6));
    }

    @Test
    public void supprimerCartes5Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_7);
        h_.supprimerCartes(d_);
        h_.sortCards(false, false);
        assertEq(6, h_.total());
        assertEq(CardPresident.CLUB_3, h_.carte(0));
        assertEq(CardPresident.CLUB_6, h_.carte(1));
        assertEq(CardPresident.HEART_7, h_.carte(2));
        assertEq(CardPresident.CLUB_10, h_.carte(3));
        assertEq(CardPresident.CLUB_KING, h_.carte(4));
        assertEq(CardPresident.CLUB_1, h_.carte(5));
    }

    @Test
    public void containsCards1Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_2);
        assertTrue(!h_.containsCards(d_));
    }

    @Test
    public void containsCards2Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_1);
        assertTrue(h_.containsCards(d_));
    }

    @Test
    public void containsCards3Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        assertTrue(h_.containsCards(d_));
    }

    @Test
    public void containsCards4Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        assertTrue(h_.containsCards(d_));
    }

    @Test
    public void containsCards5Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_7);
        assertTrue(h_.containsCards(d_));
    }

    @Test
    public void containsCards6Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_7);
        assertTrue(!h_.containsCards(d_));
    }

    @Test
    public void containsCards7Test() {
        HandPresident h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.CLUB_6);
        HandPresident d_ = new HandPresident();
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_7);
        d_.ajouter(CardPresident.CLUB_2);
        assertTrue(!h_.containsCards(d_));
    }
    @Test
    public void simpleTest() {
        assertSame(CardChar.KING, CardPresident.SPADE_KING.getId().getNomFigure());
        assertEq(0, CardPresident.SPADE_KING.getId().getValeur());
        assertTrue(!StringUtil.concatNb(CardPresident.SPADE_KING.getId().getNo(), "").isEmpty());
        assertTrue(HandPresident.pileBase().validStack(1));
        assertTrue(!HandPresident.pileBase().validStack(2));
    }
    @Test
    public void vientAvant1Test() {
        IdList<Suit> suits_ = new IdList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardPresident.SPADE_KING.vientAvant(CardPresident.HEART_KING,false,suits_));
    }
    @Test
    public void vientAvant2Test() {
        IdList<Suit> suits_ = new IdList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardPresident.SPADE_QUEEN.vientAvant(CardPresident.SPADE_KING,false,suits_));
    }
    @Test
    public void vientAvant3Test() {
        IdList<Suit> suits_ = new IdList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(CardPresident.SPADE_KING.vientAvant(CardPresident.HEART_KING,true,suits_));
    }
    @Test
    public void vientAvant4Test() {
        IdList<Suit> suits_ = new IdList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardPresident.SPADE_QUEEN.vientAvant(CardPresident.SPADE_KING,true,suits_));
    }
    @Test
    public void vientAvant5Test() {
        IdList<Suit> suits_ = new IdList<Suit>();
        suits_.add(Suit.SPADE);
        suits_.add(Suit.HEART);
        suits_.add(Suit.CLUB);
        suits_.add(Suit.DIAMOND);
        assertTrue(!CardPresident.HEART_KING.vientAvant(CardPresident.SPADE_KING,false,suits_));
    }
}
