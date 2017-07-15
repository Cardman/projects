package cards.network.belote.displaying;
import code.util.annot.RwXml;
import cards.belote.HandBelote;
import cards.network.common.PlayerActionGame;

@RwXml
public class RefreshHandBelote extends PlayerActionGame {

    private HandBelote refreshedHand;

    private byte takerIndex;

    public HandBelote getRefreshedHand() {
        return refreshedHand;
    }

    public void setRefreshedHand(HandBelote _refreshedHand) {
        refreshedHand = _refreshedHand;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
