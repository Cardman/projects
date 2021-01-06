package cards.tarot.beans;
import cards.consts.CoreResourcesAccess;
import cards.consts.MixCardsChoice;
import cards.consts.Status;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.*;
import code.bean.Bean;
import code.format.Format;
import code.util.*;
import code.util.StringList;

abstract class TarotBean extends Bean {

    private BidTarot bid;

    private GameTarot game;

    private StringList nicknames;

    private CustList<Longs> scores;

    private int user;

    private String loc;
    protected static String toString(ModeTarot _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_MODE, _b.name());
    }
    protected static String toString(DealingTarot _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_DEAL, _b.name());
    }

    protected static String toString(BidTarot _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_BID,_b.name());
    }
    protected static String toString(CardTarot _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_CARD,_b.name());
    }
    protected static String toString(Handfuls _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_HANDFULS,_b.name());
    }
    protected static String toString(EndDealTarot _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_END,_b.name());
    }
    protected static String toString(Miseres _b, String _file){
        return Format.getConstanteLangue(_file, TarotResoucesAccess.TAROT_MISERES, _b.name());
    }
    protected static String toString(Status _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.STATUS,_b.name());
    }
    protected static String toString(MixCardsChoice _b, String _file) {
        return Format.getConstanteLangue(_file, CoreResourcesAccess.MIX,_b.name());
    }
    protected final boolean playClassicGame() {
        return getBid().isJouerDonne();
    }

    protected final boolean playVariantModeGame() {
        return !getBid().isJouerDonne() && !game.unionPlis().isEmpty();
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

    protected final CustList<Longs> getScores() {
        return scores;
    }

    protected final void setScores(CustList<Longs> _scores) {
        scores = _scores;
    }

    protected final int getUser() {
        return user;
    }

    protected final void setUser(int _user) {
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
