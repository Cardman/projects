package cards.network.belote.actions;
import cards.belote.BidBeloteSuit;
import cards.network.common.PlayerActionGame;


public final class BiddingBelote extends PlayerActionGame {

    private BidBeloteSuit bidBelote;

    public BidBeloteSuit getBidBelote() {
        return bidBelote;
    }

    public void setBidBelote(BidBeloteSuit _bidBelote) {
        bidBelote = _bidBelote;
    }
}
