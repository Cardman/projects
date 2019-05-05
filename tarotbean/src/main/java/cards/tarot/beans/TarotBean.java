package cards.tarot.beans;
import cards.consts.CoreResourcesAccess;
import cards.consts.MixCardsChoice;
import cards.consts.Status;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.enumerations.*;
import code.bean.Bean;
import code.format.Format;
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
    protected static String toString(ModeTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_MODE, _b.name());
    }
    protected static String toString(DealingTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_DEAL, _b.name());
    }

    protected static String toString(BidTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_BID,_b.name());
    }
    protected static String toString(CardTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_CARD,_b.name());
    }
    protected static String toString(Handfuls _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_HANDFULS,_b.name());
    }
    protected static String toString(EndDealTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_END,_b.name());
    }
    protected static String toString(Miseres _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_MISERES, _b.name());
    }
    protected static String toString(Status _b, String _locale) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, CoreResourcesAccess.STATUS,_b.name());
    }
    protected static String toString(MixCardsChoice _b, String _locale) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, CoreResourcesAccess.MIX,_b.name());
    }
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
