package cards.belote.enumerations;

public final class BeloteCardsExporterUtil {
    private BeloteCardsExporterUtil() {
    }

    public static String fromDeclaresBelote(DeclaresBelote _role) {
        if (_role == DeclaresBelote.FOUR_1) {
            return "FOUR_1";
        }
        if (_role == DeclaresBelote.FOUR_KING) {
            return "FOUR_KING";
        }
        if (_role == DeclaresBelote.FOUR_QUEEN) {
            return "FOUR_QUEEN";
        }
        if (_role == DeclaresBelote.FOUR_JACK) {
            return "FOUR_JACK";
        }
        if (_role == DeclaresBelote.FOUR_10) {
            return "FOUR_10";
        }
        if (_role == DeclaresBelote.FOUR_9) {
            return "FOUR_9";
        }
        if (_role == DeclaresBelote.FOUR_8) {
            return "FOUR_8";
        }
        if (_role == DeclaresBelote.FOUR_7) {
            return "FOUR_7";
        }
        if (_role == DeclaresBelote.HUNDRED) {
            return "HUNDRED";
        }
        if (_role == DeclaresBelote.FIFTY) {
            return "FIFTY";
        }
        if (_role == DeclaresBelote.THIRTY) {
            return "THIRTY";
        }
        return "UNDEFINED";
    }
    public static String fromBeloteTrumpPartner(BeloteTrumpPartner _role) {
        if (_role == BeloteTrumpPartner.OVERTRUMP_ONLY) {
            return "OVERTRUMP_ONLY";
        }
        if (_role == BeloteTrumpPartner.UNDERTRUMP_ONLY) {
            return "UNDERTRUMP_ONLY";
        }
        if (_role == BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP) {
            return "UNDERTRUMP_OVERTRUMP";
        }
        return "NO_UNDERTRUMP_NO_OVERTRUMP";
    }

    public static String fromBidBelote(BidBelote _role) {
        if (_role == BidBelote.SUIT) {
            return "SUIT";
        }
        if (_role == BidBelote.OTHER_SUIT) {
            return "OTHER_SUIT";
        }
        if (_role == BidBelote.NO_TRUMP) {
            return "NO_TRUMP";
        }
        if (_role == BidBelote.ALL_TRUMP) {
            return "ALL_TRUMP";
        }
        return "FOLD";
    }

    public static String fromDealingBelote(DealingBelote _role) {
        if (_role == DealingBelote.COINCHE_2_VS_2) {
            return "COINCHE_2_VS_2";
        }
        return "CLASSIC_2_VS_2";
    }

    public static String fromCardBelote(CardBelote _ct) {
        return _ct.getId().getSt();
    }
}
