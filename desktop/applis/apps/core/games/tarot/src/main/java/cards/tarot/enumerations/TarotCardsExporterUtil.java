package cards.tarot.enumerations;

import cards.consts.CouleurValeur;

public final class TarotCardsExporterUtil {
    public static final String BID = "0";
    public static final String DEAL = "1";
    public static final String MODE = "2";
    public static final String END_DEAL = "3";
    public static final String HANDFUL = "4";
    public static final String MISERES = "5";
    public static final String SMALL_BOUND = "60";
    public static final String SLAM = "61";
    public static final String SAVE_SMALL = "70";
    public static final String HUNT_SMALL = "71";
    public static final String LEAD_SMALL_BOUND = "72";
    private TarotCardsExporterUtil(){
    }

    public static String fromBidTarot(BidTarot _role) {
        return _role.getSt();
    }
    public static String fromModeTarot(ModeTarot _role) {
        return _role.getSt();
    }
    public static String fromDealingTarot(DealingTarot _role) {
        return _role.getSt();
    }
    public static String fromHandfuls(Handfuls _role) {
        return _role.getSt();
    }
    public static String fromMiseres(Miseres _role) {
        return _role.getSt();
    }
    public static String fromEndDealTarot(EndDealTarot _role) {
        return _role.getSt();
    }
    public static String fromCardTarot(CardTarot _ct) {
        return CouleurValeur.fromCard(_ct.getId());
    }
}
