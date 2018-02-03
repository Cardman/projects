package cards.tarot.beans;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BidTarot;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

abstract class TarotBean extends Bean {

    private BidTarot bid;

    private GameTarot game;

    private StringList nicknames;

    private CustList<Numbers<Long>> scores;

    private byte user;

    private String loc;

    protected final boolean playClassicGame() {
        return getBid().isJouerDonne();
    }

    protected final boolean playVariantModeGame() {
        return !getBid().isJouerDonne() && !game.unionPlis(true).isEmpty();
    }

    protected final GameTarot getGame() {
        return game;
    }

    protected final void setGame(GameTarot _game) {
        game = _game;
    }

    protected final StringList getNicknames() {
        return nicknames;
    }

    protected final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    protected final CustList<Numbers<Long>> getScores() {
        return scores;
    }

    protected final void setScores(CustList<Numbers<Long>> _scores) {
        scores = _scores;
    }

    protected final byte getUser() {
        return user;
    }

    protected final void setUser(byte _user) {
        user = _user;
    }

    protected final String getLoc() {
        return loc;
    }

    protected final void setLoc(String _loc) {
        loc = _loc;
    }

    protected final ResultsTarot getResults() {
        return (ResultsTarot)getDataBase();
    }

    protected final BidTarot getBid() {
        return bid;
    }

    protected final void setBid(BidTarot _bid) {
        bid = _bid;
    }
}
