package cards.belote.beans;

import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.consts.beans.LineDealStruct;
import code.bean.Bean;
import code.bean.IntBeanBuilderHelperCommon;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;

public abstract class BeloteBean extends Bean {
    private BidBeloteSuit bid;

    private GameBelote game;

    private StringList nicknames;

    private CustList<LineDeal> history;

    private ResultsBelote dataBase;
    private RulesBelote dataBaseRules;
    private IntBeanBuilderHelperCommon builder;
    public RulesBelote db() {
        return dataBaseRules;
    }

    public void setDataBase(ResultsBelote _dataBase, RulesBelote _dataBaseRules) {
        dataBase = _dataBase;
        dataBaseRules = _dataBaseRules;
    }

    protected static String toString(DeclaresBelote _b, StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(_b));
    }
    protected static String toStringBeloteReb(StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.DECLARE_PAIR);
    }
    protected static String toStringBonusBelote(StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.LAST_TRICK);
    }
    protected static String toString(BidBeloteSuit _b, StringMap<String> _coreFile, StringMap<String> _file) {
        return BidBeloteSuit.toString(_b,EnumCardsExporterUtil.SUITS,_coreFile,BeloteCardsExporterUtil.BID,_file);
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
    protected static String toString(DealingBelote _b, StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(_b));
    }

    protected static String toString(BidBelote _b, StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(_b));
    }

    protected static String toString(BeloteTrumpPartner _b, StringMap<String> _file){
        return _file.getVal(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(_b));
    }
    protected static String toString(Role _b, StringMap<String> _file) {
        return _file.getVal(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(_b));
    }
    protected static String toString(MixCardsChoice _b, StringMap<String> _file) {
        return _file.getVal(EnumCardsExporterUtil.fromMixCardsChoice(_b));
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

    public IntBeanBuilderHelperCommon getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelperCommon _b) {
        this.builder = _b;
    }
}
