package cards.network.common.before;
import code.network.AttemptConnecting;


public class IndexOfArriving extends PlayerActionBeforeGame implements AttemptConnecting {

    private static final String CARDS = "CARDS";

    @Override
    public String getServerName() {
        return CARDS;
    }
}
