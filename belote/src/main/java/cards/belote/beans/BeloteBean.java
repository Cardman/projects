package cards.belote.beans;
import code.bean.Accessible;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;

public abstract class BeloteBean extends Bean {

    @Accessible
    private BidBeloteSuit bid;

    @Accessible
    private GameBelote game;

    @Accessible
    private StringList nicknames;

    @Accessible
    private CustList<Numbers<Long>> scores;

    @Accessible
    private byte user;

    @Accessible
    private String loc;

    @Accessible
    protected final boolean playGame() {
        return getBid().jouerDonne();
    }

    public final GameBelote getGame() {
        return game;
    }

    public final void setGame(GameBelote _game) {
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
    protected final ResultsBelote getResults() {
        return (ResultsBelote) getDataBase();
    }

    protected final BidBeloteSuit getBid() {
        return bid;
    }

    protected final void setBid(BidBeloteSuit _bid) {
        bid = _bid;
    }
}
