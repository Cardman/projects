package cards.network.common;
import cards.facade.Games;
import code.util.*;


public final class DelegateServer {

    private Games games;

    private IntMap<String> nicknames;

    public Games getGames() {
        return games;
    }

    public void setGames(Games _games) {
        games = _games;
    }

    public IntMap<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(IntMap<String> _nicknames) {
        nicknames = _nicknames;
    }
}
