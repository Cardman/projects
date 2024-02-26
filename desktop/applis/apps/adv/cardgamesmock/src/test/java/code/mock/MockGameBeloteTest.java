package code.mock;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.Suit;
import code.util.core.BoolVal;
import org.junit.Test;

public final class MockGameBeloteTest extends EquallableCardsMockUtil {
    @Test
    public void bids1() {
        MockGameBelote m_ = new MockGameBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        m_.getBids().add(b_);
        assertEq(BidBelote.SUIT,m_.currentBid().getBid());
        assertEq(BidBelote.SUIT,m_.strategieContrat(null).getBid());
    }
    @Test
    public void bids2() {
        MockGameBelote m_ = new MockGameBelote();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        m_.getBids().add(b_);
        assertEq(BidBelote.SUIT,m_.currentBid().getBid());
        assertEq(BidBelote.SUIT,m_.strategieContratUser(null).getBid());
    }
   @Test
    public void discard1() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getDiscardIa().add(new HandBelote());
        assertEq(0,m_.strategieEcart(null).total());
    }
    @Test
    public void discard2() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getDiscard().add(CardBelote.SPADE_1);
        assertEq(CardBelote.SPADE_1,m_.currentDiscard());
        assertEq(CardBelote.SPADE_1,m_.discard(CardBelote.SPADE_1));
        assertEq(CardBelote.SPADE_1,m_.restore(CardBelote.SPADE_1));
    }
    @Test
    public void slam1() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getSlams().add(BoolVal.FALSE);
        assertFalse(m_.annoncerUnChelem(null));
    }
    @Test
    public void slam2() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getSlams().add(BoolVal.TRUE);
        assertTrue(m_.annoncerUnChelem(null));
    }
    @Test
    public void cards1() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getCards().add(CardBelote.SPADE_1);
        assertEq(CardBelote.SPADE_1,m_.currentCard());
        assertEq(CardBelote.SPADE_1,m_.strategieJeuCarteUnique(null));
    }
    @Test
    public void cards2() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getCards().add(CardBelote.SPADE_1);
        assertEq(CardBelote.SPADE_1,m_.currentCard());
        assertEq(CardBelote.SPADE_1,m_.strategieJeuCarteUniqueUser(null));
    }
    @Test
    public void possible() {
        MockGameBelote m_ = new MockGameBelote();
        assertTrue(m_.cartesPossibles(null,null).isEmpty());
    }
    @Test
    public void sure() {
        MockGameBelote m_ = new MockGameBelote();
        assertTrue(m_.cartesCertaines(null,null).isEmpty());
    }
    @Test
    public void stacks() {
        MockGameBelote m_ = new MockGameBelote();
        m_.getStacks().add(new DealBelote());
        assertEq(0,m_.empiler(0, null,null).nombreDeMains());
    }
}
