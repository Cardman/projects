package aiki.network.stream;
import code.network.AttemptConnecting;


public class IndexOfArriving extends PlayerActionBeforeGame implements AttemptConnecting {

    private static final String POKEMON = "POKEMON";

    @Override
    public String getServerName() {
        return POKEMON;
    }

}
