package cards.tarot.beans;

import cards.consts.CoreResourcesAccess;
import cards.consts.LineDeal;
import cards.consts.MixCardsChoice;
import cards.consts.Role;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import code.bean.Bean;
import code.format.Format;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;

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
    protected static String toString(ModeTarot _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }
    protected static String toString(DealingTarot _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }

    protected static String toString(BidTarot _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }
    protected static String toString(CardTarot _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }

    protected static String toString(Handfuls _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }
    protected static String toString(EndDealTarot _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }

    protected static String toString(Miseres _b, String _file){
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), _file);
    }

    protected static String toString(Role _b, String _file) {
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), _file);
    }
    protected static String toString(MixCardsChoice _b, String _file) {
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), _file);
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

    public CustList<Longs> scores() {
        CustList<Longs> l_ = new CustList<Longs>();
        for (LineDeal e: history) {
            l_.add(e.getScores());
        }
        return l_;
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
