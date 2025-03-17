package cards.network.belote.displaying;
import cards.belote.HandBelote;
import cards.network.common.PlayerActionGame;


public final class RefreshHandBelote extends PlayerActionGame {

    private HandBelote refreshedHand;

    private int takerIndex;

    public HandBelote getRefreshedHand() {
        return refreshedHand;
    }

    public void setRefreshedHand(HandBelote _refreshedHand) {
        refreshedHand = _refreshedHand;
    }

    public int getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(int _takerIndex) {
        takerIndex = _takerIndex;
    }
}
