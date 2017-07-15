package cards.network.common;
import code.util.NumberMap;
import code.util.annot.RwXml;
import cards.facade.Games;

@RwXml
public class DelegateServer {

    private Games games;

    private NumberMap<Integer,String> nicknames;

    public Games getGames() {
        return games;
    }

    public void setGames(Games _games) {
        games = _games;
    }

    public NumberMap<Integer,String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(NumberMap<Integer,String> _nicknames) {
        nicknames = _nicknames;
    }
}
