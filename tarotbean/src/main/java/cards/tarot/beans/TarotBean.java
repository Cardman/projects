package cards.tarot.beans;
import code.bean.Accessible;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.BidTarot;

public abstract class TarotBean extends Bean {

    @Accessible
    private BidTarot bid;

    @Accessible
    private GameTarot game;

    @Accessible
    private StringList nicknames;

    @Accessible
    private CustList<Numbers<Long>> scores;

    @Accessible
    private byte user;

    @Accessible
    private String loc;

    @Accessible
    protected final boolean playClassicGame() {
        return getBid().isJouerDonne();
    }

    @Accessible
    protected final boolean playVariantModeGame() {
        return !getBid().isJouerDonne() && !game.unionPlis(true).isEmpty();
    }

    public final GameTarot getGame() {
        return game;
    }

    public final void setGame(GameTarot _game) {
        game = _game;
    }

    public final StringList getNicknames() {
        return nicknames;
    }

    public final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public final CustList<Numbers<Long>> getScores() {
        return scores;
    }

    public final void setScores(CustList<Numbers<Long>> _scores) {
        scores = _scores;
    }

    public final byte getUser() {
        return user;
    }

    public final void setUser(byte _user) {
        user = _user;
    }

    public final String getLoc() {
        return loc;
    }

    public final void setLoc(String _loc) {
        loc = _loc;
    }

    @Accessible
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
