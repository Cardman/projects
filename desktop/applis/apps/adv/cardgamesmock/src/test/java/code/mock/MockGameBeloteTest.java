package code.mock;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.Suit;
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
}
