package cards.solitaire;

import code.util.IdList;
import org.junit.Test;

public final class HandSolitaireTest extends EquallableSolitaireUtil {
    @Test
    public void validStack1() {
        assertTrue(HandSolitaire.stack(1).validStack(1));
    }
    @Test
    public void validStack2() {
        assertFalse(HandSolitaire.stack(1).validStack(2));
    }
    @Test
    public void patch1() {
        assertEq(1,HandSolitaire.nullToEmpty(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.WHITE})).total());
    }
    @Test
    public void patch2() {
        assertEq(0,HandSolitaire.nullToEmpty(null).total());
    }
    @Test
    public void patch3() {
        HandSolitaire h_ = new HandSolitaire();
        IdList<CardSolitaire> cards_ = new IdList<CardSolitaire>();
        cards_.add(CardSolitaire.WHITE);
        h_.setCards(cards_);
        assertEq(1,h_.total());
        assertEq(CardSolitaire.WHITE,h_.premiereCarte());
        h_.supprimerCarte(0);
        assertEq(0,h_.total());
        h_.ajouter(CardSolitaire.WHITE);
        h_.supprimerCartes();
        assertEq(0,h_.total());
    }
}
