package cards.belote.beans;

import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.consts.beans.LineDealStruct;
import code.bean.Bean;
import code.format.Format;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;

public abstract class BeloteBean extends Bean {
    private BidBeloteSuit bid;

    private GameBelote game;

    private StringList nicknames;

    private CustList<LineDeal> history;

    private ResultsBelote dataBase;
    private RulesBelote dataBaseRules;
    public RulesBelote db() {
        return dataBaseRules;
    }

    public void setDataBase(ResultsBelote _dataBase, RulesBelote _dataBaseRules) {
        dataBase = _dataBase;
        dataBaseRules = _dataBaseRules;
    }

    protected static String toString(DeclaresBelote _b, String _file){
        return Format.getConstanteLangue(key(_b), _file);
    }
    protected static String toStringBeloteReb(String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_DECLARES_BEL_REB, BeloteResoucesAccess.BELOTE_REBELOTE);
    }
    protected static String toStringBonusBelote(String _file){
        return Format.getConstanteLangue(_file, BeloteResoucesAccess.BELOTE_BONUS, BeloteResoucesAccess.LAST_TRICK);
    }
    protected static String toString(BidBeloteSuit _b, String _coreFile, String _file) {
        return BidBeloteSuit.toString(_b,_coreFile,_file);
//        StringBuilder pts_ = new StringBuilder();
//        if (_b.getPoints() > 0) {
//            pts_.append(SPACE);
//            pts_.append(_b.getPoints());
//        }
//        if (_b.getCouleurDominante()) {
//            pts_.insert(0, Format.getConstanteLangue(key(_b.getSuit()), _coreFile));
//            return pts_.toString();
//        }
//        pts_.insert(0, Format.getConstanteLangue(key(_b.getBid()), _file));
//        return pts_.toString();
    }
    protected static String toString(DealingBelote _b, String _file){
        return Format.getConstanteLangue(key(_b), _file);
    }

    protected static String toString(BidBelote _b, String _file){
        return Format.getConstanteLangue(key(_b), _file);
    }

    protected static String toString(BeloteTrumpPartner _b, String _file){
        return Format.getConstanteLangue(key(_b), _file);
    }
    protected static String toString(Role _b, String _file) {
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), _file);
    }
    protected static String toString(MixCardsChoice _b, String _file) {
        return Format.getConstanteLangue(key(_b), _file);
    }

    protected static String key(DealingBelote _b) {
        return Format.concatParts(BeloteResoucesAccess.BELOTE_DEAL, BeloteCardsExporterUtil.fromDealingBelote(_b));
    }

    protected static String key(BidBelote _b) {
        return BeloteResoucesAccess.key(_b);
    }

    protected static String key(BeloteTrumpPartner _b) {
        return BeloteResoucesAccess.key(_b);
    }

    protected static String key(MixCardsChoice _b) {
        return CoreResourcesAccess.key(_b);
    }

    protected static String key(DeclaresBelote _b) {
        return BeloteResoucesAccess.key(_b);
    }

    public boolean playGame() {
        return getBid().jouerDonne();
    }

    protected final GameBelote getGame() {
        return game;
    }

    protected final void setGame(GameBelote _game) {
        game = _game;
    }

    public StringList getNicknames() {
        return nicknames;
    }

    protected final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public CustList<Longs> scoresBelote() {
        return LineDealStruct.scores(history);
    }
    public CustList<LineDeal> getHistory() {
        return history;
    }

    public void setHistory(CustList<LineDeal> _h) {
        this.history = _h;
    }

    protected final ResultsBelote getResults() {
        return dataBase;
    }

    protected final BidBeloteSuit getBid() {
        return bid;
    }

    protected final void setBid(BidBeloteSuit _bid) {
        bid = _bid;
    }
}
