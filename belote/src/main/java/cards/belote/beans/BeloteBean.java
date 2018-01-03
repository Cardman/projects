package cards.belote.beans;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public abstract class BeloteBean extends Bean {

    private BidBeloteSuit bid;

    private GameBelote game;

    private StringList nicknames;

    private CustList<Numbers<Long>> scores;

    private byte user;

    private String loc;

    public final boolean playGame() {
        return getBid().jouerDonne();
    }

    protected final GameBelote getGame() {
        return game;
    }

    protected final void setGame(GameBelote _game) {
        game = _game;
    }

    public final StringList getNicknames() {
        return nicknames;
    }

    protected final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public final CustList<Numbers<Long>> getScores() {
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
