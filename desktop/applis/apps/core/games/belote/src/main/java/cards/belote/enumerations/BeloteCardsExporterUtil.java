package cards.belote.enumerations;

import cards.consts.CouleurValeur;

public final class BeloteCardsExporterUtil {
    public static final String BID = "0";
    public static final String TRUMPING = "1";
    public static final String DECLARE = "2";
    public static final String DEAL = "3";
    public static final String LAST_TRICK = "4";
    public static final String DECLARE_PAIR = "5";
    private BeloteCardsExporterUtil() {
    }

    public static String fromDeclaresBelote(DeclaresBelote _role) {
        return _role.getSt();
    }
    public static String fromBeloteTrumpPartner(BeloteTrumpPartner _role) {
        return _role.getBeloteTrSt();
    }

    public static String fromBidBelote(BidBelote _role) {
        return _role.getSt();
    }

    public static String fromDealingBelote(DealingBelote _role) {
        return _role.getSt();
    }

    public static String fromCardBelote(CardBelote _ct) {
        return CouleurValeur.fromCard(_ct.getId());
    }
}
