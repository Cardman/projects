package cards.belote.beans;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;
import cards.belote.enumerations.*;
import cards.consts.CoreResourcesAccess;
import cards.consts.MixCardsChoice;
import cards.consts.Status;
import cards.consts.Suit;
import code.bean.Bean;
import code.format.Format;
import code.util.*;
import code.util.StringList;

abstract class BeloteBean extends Bean {
    private static final String SPACE = " ";
    private BidBeloteSuit bid;

    private GameBelote game;

    private StringList nicknames;

    private CustList<Longs> scores;

    private byte user;

    private String loc;
    protected static String toString(DeclaresBelote _b, String _file){
        return Format.getConstanteLangue(_file,BeloteResoucesAccess.BELOTE_DECLARES, _b.name());
    }
    protected static String toString(DeclaresBeloteRebelote _b, String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_DECLARES_BEL_REB, _b.name());
    }
    protected static String toString(BonusBelote _b, String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_BONUS,_b.name());
    }
    protected static String toString(BidBeloteSuit _b, String _coreFile, String _file) {
        StringBuilder pts_ = new StringBuilder();
        if (_b.getPoints() > 0) {
            pts_.append(SPACE);
            pts_.append(_b.getPoints());
        }
        if (_b.getCouleurDominante()) {
            pts_.insert(0, toString(_b.getSuit(), _coreFile));
            return pts_.toString();
        }
        pts_.insert(0,toString(_b.getBid(), _file));
        return pts_.toString();
    }
    protected static String toString(DealingBelote _b, String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_DEAL, _b.name());
    }
    protected static String toString(BidBelote _b, String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_BID,_b.name());
    }
    protected static String toString(BeloteTrumpPartner _b, String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_TRUMP_PART,_b.name());
    }
    protected static String toString(Suit _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.SUIT, _b.name());
    }
    protected static String toString(Status _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.STATUS,_b.name());
    }
    protected static String toString(MixCardsChoice _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.MIX,_b.name());
    }
    protected final boolean playGame() {
        return getBid().jouerDonne();
    }

    protected final GameBelote getGame() {
        return game;
    }

    protected final void setGame(GameBelote _game) {
        game = _game;
    }

    protected final StringList getNicknames() {
        return nicknames;
    }

    protected final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    protected final CustList<Longs> getScores() {
        return scores;
    }

    protected final void setScores(CustList<Longs> _scores) {
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
