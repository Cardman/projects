package aiki.network.stream;
import code.network.AttemptConnecting;


public final class IndexOfArrivingAiki extends PlayerActionBeforeGameAiki implements AttemptConnecting {

    private static final String POKEMON = "POKEMON";

    @Override
    public String getServerName() {
        return POKEMON;
    }

}
