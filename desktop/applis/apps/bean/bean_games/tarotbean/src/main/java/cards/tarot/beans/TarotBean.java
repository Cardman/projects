package cards.tarot.beans;

import cards.consts.*;
import cards.consts.beans.LineDealStruct;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import code.bean.Bean;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;

public abstract class TarotBean extends Bean {
    private BidTarot bid;

    private GameTarot game;

    private StringList nicknames;

    private CustList<LineDeal> history;

    private ResultsTarot dataBase;
    private RulesTarot dataBaseRules;
    public RulesTarot db() {
        return dataBaseRules;
    }

    public void setDataBase(ResultsTarot _dataBase, RulesTarot _dataBaseRules) {
        dataBase = _dataBase;
        dataBaseRules = _dataBaseRules;
    }
    protected static String toString(ModeTarot _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(_b));
    }
    protected static String toString(DealingTarot _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(_b));
    }

    protected static String toString(BidTarot _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(_b));
    }
    protected static String toString(CardTarot _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.fromCardTarot(_b));
    }

    protected static String toString(Handfuls _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(_b));
    }
    protected static String toString(EndDealTarot _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(_b));
    }

    protected static String toString(Miseres _b, StringMap<String> _file){
        return _file.getVal(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(_b));
    }

    protected static String toString(Role _b, StringMap<String> _file) {
        return _file.getVal(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(_b));
    }
    protected static String toString(MixCardsChoice _b, StringMap<String> _file) {
        return _file.getVal(EnumCardsExporterUtil.fromMixCardsChoice(_b));
    }
    public final boolean playClassicGame() {
        return getBid().isJouerDonne();
    }

    public final boolean playVariantModeGame() {
        return !getBid().isJouerDonne() && !game.unionPlis().isEmpty();
    }

    protected final GameTarot getGame() {
        return game;
    }

    protected final void setGame(GameTarot _game) {
        game = _game;
    }

    public final StringList getNicknames() {
        return nicknames;
    }

    protected final void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public CustList<Longs> scoresTarot() {
        return LineDealStruct.scores(history);
    }
    public CustList<LineDeal> getHistory() {
        return history;
    }

    protected void setHistory(CustList<LineDeal> _h) {
        this.history = _h;
    }

    protected final ResultsTarot getResults() {
        return dataBase;
    }

    protected final BidTarot getBid() {
        return bid;
    }

    protected final void setBid(BidTarot _bid) {
        bid = _bid;
    }
}
