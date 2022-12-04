package cards.network.common.before;
import code.network.AttemptConnecting;


public final class IndexOfArrivingCards extends PlayerActionBeforeGameCards implements AttemptConnecting {

    private static final String CARDS = "CARDS";

    @Override
    public String getServerName() {
        return CARDS;
    }
}
