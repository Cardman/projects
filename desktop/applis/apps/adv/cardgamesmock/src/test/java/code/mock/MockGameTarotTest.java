package code.mock;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class MockGameTarotTest extends EquallableCardsMockUtil {
    @Test
    public void bids1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getBids().add(BidTarot.GUARD);
        assertEq(BidTarot.GUARD,m_.currentBid());
        assertEq(BidTarot.GUARD,m_.strategieContrat(null));
    }
    @Test
    public void bids2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getBids().add(BidTarot.GUARD);
        assertEq(BidTarot.GUARD,m_.currentBid());
        assertEq(BidTarot.GUARD,m_.strategieContratUser(null));
    }
    @Test
    public void calls1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getCalls().add(new HandTarot());
        assertEq(0,m_.currentCall().total());
        assertEq(0,m_.strategieAppel(null).total());
    }
    @Test
    public void calls2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getCalls().add(new HandTarot());
        assertEq(0,m_.currentCall().total());
        assertEq(0,m_.strategieAppelUser(null).total());
    }
    @Test
    public void discard1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getDiscardIa().add(new HandTarot());
        assertEq(0,m_.strategieEcart(null).total());
    }
    @Test
    public void discard2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getDiscardVarIa().add(new CallDiscard());
        assertEq(0,m_.strategieAppelApresEcart(null,false).getEcartAFaire().total());
    }
    @Test
    public void discard3() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getDiscard().add(CardTarot.SPADE_1);
        assertEq(CardTarot.SPADE_1,m_.currentDiscard());
        assertEq(CardTarot.SPADE_1,m_.discard(CardTarot.SPADE_1));
        assertEq(CardTarot.SPADE_1,m_.restore(CardTarot.SPADE_1));
    }
    @Test
    public void slam1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getSlams().add(BoolVal.FALSE);
        assertFalse(m_.annoncerUnChelem(null));
    }
    @Test
    public void slam2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getSlams().add(BoolVal.TRUE);
        assertTrue(m_.annoncerUnChelem(null));
    }
    @Test
    public void cards1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getCards().add(CardTarot.SPADE_1);
        assertEq(CardTarot.SPADE_1,m_.currentCard());
        assertEq(CardTarot.SPADE_1,m_.changerConfianceJeuCarteUnique(null));
    }
    @Test
    public void cards2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getCards().add(CardTarot.SPADE_1);
        assertEq(CardTarot.SPADE_1,m_.currentCard());
        assertEq(CardTarot.SPADE_1,m_.changerConfianceJeuCarteUniqueUser(null));
    }
    @Test
    public void cards3() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getCards().add(CardTarot.SPADE_1);
        assertEq(CardTarot.SPADE_1,m_.currentCard());
        assertEq(CardTarot.SPADE_1,m_.changerConfianceJeuCarteUniqueQuick(null));
    }
    @Test
    public void handful1() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getHandfuls().add(new IdList<Handfuls>());
        assertEq(0,m_.currentHandful().size());
        assertEq(0,m_.handful(new IdList<Handfuls>()).size());
    }
    @Test
    public void handful2() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getHandfulsCard().add(new HandTarot());
        assertEq(0,m_.currentHandfulCard().total());
        assertEq(0,m_.handfulCard(new HandTarot()).total());
    }
    @Test
    public void misere() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getMiseres().add(new IdList<Miseres>());
        assertEq(0,m_.currentMiseres().size());
        assertEq(0,m_.misere(new IdList<Miseres>()).size());
    }
    @Test
    public void possible() {
        assertTrue(new MockGameTarot().cartesPossibles(new MockGameTarot().gameTarotTrickInfo(null),null).isEmpty());
    }
    @Test
    public void sure() {
        assertTrue(new MockGameTarot().cartesCertaines(new MockGameTarot().gameTarotTrickInfo(null),null).isEmpty());
    }
    @Test
    public void stacks() {
        MockGameTarot m_ = new MockGameTarot();
        m_.getStacks().add(new DealTarot());
        assertEq(0,m_.empiler(0, null,null).nombreDeMains());
    }
}
