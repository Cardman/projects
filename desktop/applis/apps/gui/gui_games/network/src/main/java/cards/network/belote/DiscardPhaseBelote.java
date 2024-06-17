package cards.network.belote;
import cards.belote.HandBelote;
import cards.network.common.DiscardPhase;


public final class DiscardPhaseBelote {

    public static final int TAKER_NO = 0;
    public static final int TAKER_BOT = 1;
    public static final int TAKER_HUM_WRITE = 2;
    public static final int TAKER_HUM_READ = 3;
    private HandBelote discard;
    private final DiscardPhase discardPhase = new DiscardPhase();

    public HandBelote getDiscard() {
        return discard;
    }

    public void setDiscard(HandBelote _dog) {
        discard = _dog;
    }

    public DiscardPhase getDiscardPhase() {
        return discardPhase;
    }


}
